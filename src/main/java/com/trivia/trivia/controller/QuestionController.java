package com.trivia.trivia.controller;

import com.trivia.trivia.entity.*;
import com.trivia.trivia.service.AnswerService;
import com.trivia.trivia.service.QuestionService;
import com.trivia.trivia.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/v1/question")
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, UserService userService, AnswerService answerService) {
        this.questionService = questionService;
        this.userService = userService;
        this.answerService = answerService;
    }

    @GetMapping("/site/{siteName}/user/{userId}")
    public ResponseEntity<?> getQuestionByUserId(@PathVariable("siteName") String site, @PathVariable("userId") UUID userId){
        if (! site.equals("football")){
            // currently not support
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        UserEntity user = userService.getUserById(userId);

        if(user == null || user.answerQuestionId == null){
            QuestionEntity questionEntity = questionService.getNewQuestion(userId);
            user = new UserEntity(userId, questionEntity.id);
            userService.saveUser(user);
            return new ResponseEntity<>(new QuestionForUser(questionEntity), HttpStatus.OK);
        } else {
            QuestionEntity questionEntity = questionService.getQuestionById(user.answerQuestionId.longValue());
            return new ResponseEntity<>(new QuestionForUser(questionEntity), HttpStatus.OK);
        }
    }

    private boolean saveUserAnswers(UUID userId, AnswerFromUser answer, Set<String> validAnswers){
        List<AnswerEntity> userAnswers = new ArrayList<>();
        answerService.clearUserQuestionAnswers(userId, answer.questionId);
        for(Map.Entry<String, String[]> entry: answer.answers.entrySet()){
            String header = entry.getKey();
            String[] allAnswers = entry.getValue();
            for (String ans : allAnswers){
                if(validAnswers.contains(ans)){
                    userAnswers.add(new AnswerEntity(userId, answer.questionId, header, ans, false));
                } else {
                    return false;
                }
            }
        }
        answerService.saveAQuestionAnswers(userAnswers);
        return true;
    }

    @PostMapping("/site/{siteName}/user/{userId}")
    public ResponseEntity<?> receiveAnswer(@PathVariable("siteName") String site, @PathVariable("userId") UUID userId, @RequestBody AnswerFromUser answer){
        if (! site.equals("football")){
            // currently not support
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        QuestionEntity question = questionService.getQuestionById(answer.questionId);
        UserEntity user = userService.getUserById(userId);
        if (question == null || user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (question.id != user.answerQuestionId){
            // not the answering questions.
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        // update answer info
        if (! saveUserAnswers(userId, answer, new HashSet<String>(question.options))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // update the user info
        user.answerQuestionId = null;
        userService.saveUser(user);

        // TODO: return the statistics of answer options
        // if the question contains answer
        if (question.answer != ""){
            if (answer.answers.size() > 0 && question.answer.equals(answer.answers.get("#")[0])){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        // question doesn't contain answer
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/site/{siteName}/user/{userId}")
    public ResponseEntity<?> skipQuestion(@PathVariable("siteName") String site, @PathVariable("userId") UUID userId){
        if (! site.equals("football")){
            // currently not support
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        UserEntity user = userService.getUserById(userId);
        if (user == null || user.answerQuestionId == null){
            // not the answering questions.
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // update answer info, mark the question as answered
        answerService.saveAQuestionAnswer(new AnswerEntity(user.userId, user.answerQuestionId, "#", "Skip", false));

        // find new valid question
        QuestionEntity questionEntity = questionService.getNewQuestion(userId);
        user.answerQuestionId = questionEntity.id;
        userService.saveUser(user);
        return new ResponseEntity<>(new QuestionForUser(questionEntity), HttpStatus.OK);
    }
}
