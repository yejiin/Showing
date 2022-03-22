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

files = os.listdir("./data/casting")

# df_all = pd.read_csv("./data/performances.csv")
df_all = pd.DataFrame()

for i in range(0, len(files)):
    file = "./data/casting/" + files[i]
    df = pd.read_csv(file)
    df_all = pd.concat([df_all, df])

# df_all = df_all.drop_duplicates(["playdb_id"], keep="last")

df_all.to_csv(
    f"./data/castings_musical.csv",
    mode="w",
    encoding="utf-8-sig",
    header=True,
    index=False,
)

# df_all.to_sql(
#     name="performance",
#     con=db_connection,
#     if_exists="append",
#     chunksize=10000,
#     index=False,
#     method="multi",
# )

# query = "SELECT * FROM performance"

# df = pd.read_sql_query(query, conn)
# df.to_csv(r"./data/mysql_output_performance.csv", index=False)
