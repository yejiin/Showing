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


df_all = pd.read_csv("./data/castings_musical.csv")

start_time = time.time()

df_all.to_sql(
    name="casting",
    con=db_connection,
    if_exists="append",
    chunksize=10000,
    index=False,
    method="multi",
)

print("수행시간 : ", time.time() - start_time)
# query = "SELECT * FROM performance"

# df = pd.read_sql_query(query, conn)
# df.to_csv(r"./data/mysql_output_performance.csv", index=False)
