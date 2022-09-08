-- import database
-- psql -h hostname -d databasename -U username -f ImportData.sql

DROP TABLE IF EXISTS questions;
CREATE TABLE IF NOT EXISTS questions
(
    id SERIAL PRIMARY KEY,
    type INTEGER NOT NULL,
    title TEXT NOT NULL,
    answer TEXT
);

INSERT INTO questions (id, type, title, answer)
VALUES
    (1,1,'Which team won the 2017 super bowl?','Patriots'),
    (2,1,'trivia question 2','q2_ans1'),
    (3,2,'What''s your favorite car brand?',''),
    (4,3,'What are the colors do you like?',''),
    (5,4,'Please tell us a bit about yourself?','');


DROP TABLE IF EXISTS question_options;
CREATE TABLE IF NOT EXISTS question_options
(
    id SERIAL PRIMARY KEY,
    question_id SERIAL NOT NULL,
    header BOOLEAN NOT NULL,
    option TEXT NOT NULL
);

INSERT INTO question_options(question_id, header, option)
VALUES
    (1,FALSE,'Falcons'),
    (1,FALSE,'Patriots'),
    (2,FALSE,'q2_ans1'),
    (2,FALSE,'q2_ans2'),
    (3,FALSE,'Nissan'),
    (3,FALSE,'Honda'),
    (3,FALSE,'Audi'),
    (3,FALSE,'BMW'),
    (4,FALSE,'Red'),
    (4,FALSE,'Blue'),
    (4,FALSE,'Yellow'),
    (4,FALSE,'Green'),
    (4,FALSE,'Black'),
    (4,FALSE,'Purple'),
    (5,FALSE,'<18'),
    (5,FALSE,'18 to 35'),
    (5,FALSE,'35 to 55'),
    (5,FALSE,'>55'),
    (5,TRUE,'Age/Gender'),
    (5,TRUE,'Male'),
    (5,TRUE,'Female');