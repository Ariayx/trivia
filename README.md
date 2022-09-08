# System Design

## Requirements

### Functional

Provide a unique question once called by Frontend

- Frontend call by the user's UUID
- Return right/wrong of the answer to the frontend
- Alternatives
  - If questions are used up, reset all the answers

Store questions in database

Store question result from users in database

- Alternatives:
  
  - When questions are used up, results of that user
    will be reset in database as well

User cannot create or delete questions in database

### Non-functional

- Use REST API

- Use relational database

- Implement JPA/Hibernate/MyBatis, etc.

- Try not to use REST interface to manage the questions in the DB

## Assumptions

- Although there is no timeout for users to answer the question, backend will block the question by 10 minutes (configurable) cool down. The same question will not be provided for the same user within the cool down even if the user refreshes the page without answering it.

- When the questions are used up and a resetting is running, no questions will be provided to the specific user.

- Users with the same UUID shall not appear from different frontend at the same time.

## APIs

### Request a new question

GET `/v1/question/site/{siteName}/user/{userId}`

If everything goes right, server should return a question description, which includes

```text
{
    questionId: {questionId},
    questionType: {questionType},
    questionTitle: {questionTitle},
    questionDescription: [{item1}, {item2},...],
    questionHeaders: [{item1}, {item2},...]
}
```

If the `questionType` is not `matrix`,  `questionHeaders` will be empty. 

HTTP return codes are:

| Return Code | Name            | Reason                                                                 |
|-------------|-----------------|------------------------------------------------------------------------|
| 200         | OK              | When everything goes ok. A question description will also be attached. |
| 501         | NOT_IMPLEMENTED | When client requests a site other than football                        |
| 400         | BAD_REQUEST     | When request contains wrong parameter                                  |
| ???         | ???             | Other default exception handler provided by Spring                     |



### Send an answer
POST `/v1/question/site/{siteName}/users/{userId}`

put question and answers in request data as a JSON format. If `questionType` is not matrix, the `{header}` should be `#`.
```text
{
    'questionId':{questionID},
    'answer':{
        {header}: [{text1}, {text2}]
    }
}
```
If the question has a correct answer, and user gives the wrong one, a `false` will be returned. Otherwise, a`true`will be returned.

| Return Code | Name            | Reason                                                                       |
|-------------|-----------------|------------------------------------------------------------------------------|
| 200         | OK              | When everything goes ok. A boolean will be returned indicate right or wrong. |
| 501         | NOT_IMPLEMENTED | When client requests a site other than football                              |
| 400         | BAD_REQUEST     | When request contains wrong parameter                                        |
| 409         | CONFLICT        | When question id is not the one user should answer                           |
| ???         | ???             | Other default exception handler provided by Spring                           |

### Skip a question
PUT `/v1/question/site/{siteName}/users/{userId}`
The backend will mark the current question as skipped, and return a new question description to client.
Return format would be the same as that in `Request a new question`

| Return Code | Name            | Reason                                                                                          |
|-------------|-----------------|-------------------------------------------------------------------------------------------------|
| 200         | OK              | When everything goes ok. A boolean will be returned indicate right or wrong.                    |
| 501         | NOT_IMPLEMENTED | When client requests a site other than football                                                 |
| 400         | BAD_REQUEST     | When request contains wrong parameter, or try to skip a question when no question is answering. |
| ???         | ???             | Other default exception handler provided by Spring                                              |

## Data Schema

### Table questions

| Column name | type  | description                                |
|-------------|-------|--------------------------------------------|
| id          | SERIAL| unique, Primary key                        |
| type        | int   | 1: trivia; 2: poll; 3: checkbox; 4: matrix |
| title       | TEXT  |                                            |
| answer      | TEXT  | empty string if no right answer            |
Since headers and options are arrays, I store them in another table `question_options`


### Table question_options

| Column name | type    | description                       |
|-------------|---------|-----------------------------------|
| id          | SERIAL  | unique, Primary key               |
| question_id | SERIAL  | Foreign key                       |
| header      | Boolean | true: is header. false: is option |
| option      | TEXT    | The string of header or option    |


### Table users

| Column name         | type   | description                                    |
|---------------------|--------|------------------------------------------------|
| uuid                | UUID   | the UUID of users                              |
| answer_question_id  | SERIAL | The current question_id the user is answering. |

### Table answers

| Column name | type    | description                                                           |
|-------------|---------|-----------------------------------------------------------------------|
| answer_id   | long    | The auto generated id, primary key                                    |
| user_id     | UUID    | user's UUID, Composite Primary Key                                    |
| question_id | SERIAL  | the question id, Composite Primary Key                                |
| header      | TEXT    | the column name in `matrix` type. For other types, the filed is `'#'` |
| answer      | TEXT    | chosen texts. multiple rows if multiple answers are chosen            |
| deleted     | BOOLEAN | If true, this answer is marked as deleted.                            |


# Build and Run
## Create and import data to database
1. Install and run a PostgreSQL database
2. Create a database named `trivia`
3. Import SQL script at `database_script/ImportData.sql`

## Change database configuration in Spring Boot project
Change password or url in `src/main/resources/application.properties`

## Run project
I'm using IntelliJ IDEA Community Edition. Find file `src/main/java/com/trivia/trivia/TriviaApplication.java` from Project sidebar, right mouse click it and choose `Run` 
Or, you could try the following command line
- On Windows: `gradlew.bat bootRun`
- On Linux/Mac: `./gradlew bootRun`

# Security Concerns
- There is no authentication module and no limitation on the frequency of requests a user can make, it will be easy to perform DDoS attack.
- If using HTTP protocol, it will be easy to decode the protocol.

# Scalability
The scalability could be considered from 2 aspects.
 - Increase the volume of questions.
   - If there are multiple sites, questions of different site can be stored in different table
   - If one site has a large amount of questions, a further partition could be applied, e.g. split the questions into hard, medium and easy, or simply into section #.
 - Increase the volume of users.
   - UUID can be used to partition the database because of its natural randomness.
   - Distributed system could be applied to enable expand elastically.
     - A Message Queue could serve as a waiting line.
     - Multiple workers could be defined to digest the requested data
     - A cluster management and election strategy could be used to keep workers alive.
     - A distributed database could be used if one database faces performance bottlenecks.