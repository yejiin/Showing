import os
import numpy as np
import pandas as pd
import csv
import selenium
import pymysql
import time
import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.dialects.mysql import insert
from selenium.common.exceptions import NoSuchElementException
from selenium import webdriver
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from datetime import datetime

db_connection_str = (
    "mysql+pymysql://root:" + "ssafy" + "@127.0.0.1:3306/showing?charset=utf8"
)
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()


def merge_file(data):
    files = os.listdir(f"./data/{data}")

    df_all = pd.DataFrame()

    # 폴더 안에 파일 모으기
    for i in range(0, len(files)):
        file = f"./data/{data}/" + files[i]
        df = pd.read_csv(file)
        df_all = pd.concat([df_all, df])

    # playdb_id로 중복 값 제거
    df_all = df_all.drop_duplicates(["playdb_id"], keep="last")

    # df_all.loc[(df_all["end_date"] == "오픈런"), "end_date"] = None

    # csv 파일로 만들기
    df_all.to_csv(
        f"./data/{data}s.csv",
        mode="w",
        encoding="utf-8-sig",
        header=True,
        index=False,
    )


# db 저장
# df_all.to_sql(
#     name="performance",
#     con=db_connection,
#     if_exists="append",
#     chunksize=10000,
#     index=False,
#     method="multi",
# )
