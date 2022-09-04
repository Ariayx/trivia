import pandas as pd
from sqlalchemy import create_engine, MetaData, Table, Column
from sqlalchemy.dialects.postgresql import UUID, JSON, INTEGER, TEXT
from sqlalchemy_utils import database_exists, create_database

import os

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
DB_PASSWORD = 'aria'


def main():
    question_path = os.path.join(CURRENT_DIR, 'question_table.csv')

    # import question table
    print('Readnig from csv')
    df = pd.read_csv(question_path)
    engine = create_engine('postgresql://postgres:%s@localhost:5432/trivia' % DB_PASSWORD)
    if not database_exists(engine.url):
        create_database(engine.url)
    df.to_sql('question', engine, index=False, if_exists='replace',
              dtype={
                  'id': INTEGER,
                  'type': INTEGER,
                  'title': TEXT,
                  'description': JSON,
                  'answer': TEXT
              })

    # create users table
    meta = MetaData()
    users = Table(
        'users', meta,
        Column('uuid', UUID, primary_key=True)
    )

    # create answer table
    answers = Table(
        'answers', meta,
        Column('user_id', UUID, primary_key=True),
        Column('question_id', INTEGER, primary_key=True),
        Column('answers', JSON),
    )
    print('Creating tables')
    meta.create_all(engine)


if __name__ == '__main__':
    main()
