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

### Request a new trivia

GET `/v1/question?site={siteId}&users={uuid}`

Server should return a question description, which includes

```text
{
    site: {siteId},
    users: {uuid},
    questionId: {questionId},
    questionType: {questionType},
    questionTitle: {questionTitle},
    questionHeaders: {questionHeaders},
    questionsItems: [{item1}, {item2},...]
}
```

If the `questionType` is not `matrix`,  `questionHeaders` will be empty. 

### Send an answer
PUT `/v1/answer/?site={siteId}&users={uuid}`

put question and answers in request data as a JSON format. If `questionType` is not matrix, the `{header}` should be `#`.
```text
{
    'questionId':{questionID},
    'answer':{
        {header}: [{text1}, {text2}]
    }
}
```

### Data Schema

#### Table Question

| Column name | type  | description                 |
|-------------|-------|-----------------------------|
| id | int   | unique, Primary key         |
| type        | int   | 1: trivia; 2: poll; 3: checkbox; 4: matrix |
| title       | TEXT  |                             |
| description | array | array for type 1-3, dictionary for type 4 |
| headers     | array | Only works in matrix.       |
| answer      | TEXT  | empty string if no right answer |

### Table users

| Column name | type | description       |
| ----------- | ---- |-------------------|
| uuid        | UUID | the UUID of users |

### Table answers

| Column name | type  | description                                                           |
|-------------|-------|-----------------------------------------------------------------------|
| answer_id   | long  | The auto generated id, primary key                                    |
| user_id     | UUID  | user's UUID, Composite Primary Key                                    |
| question_id | int   | the question id, Composite Primary Key                                |
| header      | text  | the column name in `matrix` type. For other types, the filed is `'#'` |
| answer      | array | array of chosen texts                                                 |



# Build and Run
## Create and import data to database
1. Change database password or url in `database_script/importDb.py`, line 9 and line 18.
2. Using python 3, and install package by command `pip install -r database_script/requirements.txt`
3. Run python script to import data from csv to database `python database_script/importDb.py`

## Change database configuration in Spring Boot project
Change password or url in `src/main/resources/application.properties`

## Run project
I'm using IntelliJ IDEA Community Edition. Find file `src/main/java/com/trivia/trivia/TriviaApplication.java` from Project sidebar, right mouse click it and choose `Run` 
Or, you could try the following command line
- On Windows: `gradlew.bat bootRun`
- On Linux/Mac: `./gradlew bootRun`

# Security Concerns
- There is no authentication module, it will be easy to perform DDoS attack.
- If using HTTP protocol, it will be easy to decode the protocol

# Scalability
