import pandas as pd
from sqlalchemy import create_engine, MetaData, Table, Column
from sqlalchemy.dialects.postgresql import UUID, ARRAY, INTEGER, TEXT
from sqlalchemy_utils import database_exists, create_database

import os
import json

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
DB_PASSWORD = 'aria'

def convert_to_obj(raw):
    raw = raw.strip()
    if raw == "":
        return ""
    json_str = raw.replace('""', '"')
    return json.loads(json_str)

def main():
    question_path = os.path.join(CURRENT_DIR, 'question_table.csv')

    # import question table
    print('Readnig from csv')
    df = pd.read_csv(question_path, converters={'description':convert_to_obj, 'headers':convert_to_obj})
    engine = create_engine('postgresql://postgres:%s@localhost:5432/trivia' % DB_PASSWORD)
    if not database_exists(engine.url):
        create_database(engine.url)
    df.to_sql('questions', engine, index=False, if_exists='replace',
            dtype={
                      'id': INTEGER,
                      'type': INTEGER,
                      'title': TEXT,
                      'description': ARRAY(TEXT),
                      'headers': ARRAY(TEXT),
                      'answer': TEXT
                  })


if __name__ == '__main__':
    main()
