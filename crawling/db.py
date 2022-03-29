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
    "mysql+pymysql://ssafy:" + "a301showing" + "@j6a301.p.ssafy.io:3306/test?charset=utf8"
)
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()

# 중복 ID 업데이트 함수
def insert_on_duplicate(table, conn, keys, data_iter):
    insert_stmt = insert(table.table).values(list(data_iter))
    on_duplicate_key_stmt = insert_stmt.on_duplicate_key_update(insert_stmt.inserted)
    conn.execute(on_duplicate_key_stmt)


# query = "SELECT s.interpark_id, s.playdb_id, p.performance_name FROM season s join performance p on s.performance_id=p.id where p.performance_type = 2 and s.interpark_id is not null"

# query = "SELECT * from season"
# season_db = pd.read_sql_query(query, conn)
# season_db.to_csv(r"./시즌들어간거.csv", index=False, encoding="utf-8-sig")

df1 = pd.read_csv("./시즌들어간거.csv")
df_all = pd.read_csv("./시즌합친거.csv") 

start_time = time.time()

indexList = []

for i in range(0,len(df1)+1):
  id = df1.loc[i]["playdb_id"]
  print(i,"번째")
  print(id)
  if (df_all["playdb_id"]==id).any():
    # print(id)
    idx = df_all.loc[df_all["playdb_id"]==id].index
    print(idx)
  # indexList.append(idx)
    df_all = df_all.drop(idx,inplace=True)

# id = df1.loc[1]["playdb_id"]
# print(id)
# idx = df_all.loc[df_all["playdb_id"]==id]


# for item in indexList:
  # df2.drop(item,inplace=True)




df_all.to_csv("./시즌안들어간거.csv",index=False, encoding="utf-8-sig",  header=True)
# df_all['id']=None

# df_all["description"]= df_all["description"].replace(r'\\n','', regex=True)
# df_all.to_csv("./seasons_noid_noenter.csv",index=False, encoding="utf-8-sig",  header=True)
# 로우 별 db 저장
# for i in range(0, len(df_all.index) + 1):

  # df_all["id"][i]=i+1802
# df = df_all.iloc[1:2]
# try:
#         df.to_sql(
#             name="season",
#             con=db_connection,
#             if_exists="append",
#             index=False,
#             # method="multi",
#         )
# except Exception as e:
#     print("에러 발생 : ")
#     print(df)
# df_all.to_csv("./seasons_play_newid.csv",index=False, encoding="utf-8-sig",  header=True)


# 전체 db 저장
# try:
#     df_all.to_sql(
#         name="season",
#         con=db_connection,
#         if_exists="append",
#         chunksize=10000,
#         index=False,
#         method="multi",
#     )
# except Exception as e:
#     print("error!!!!!!!!!!!!!!!!!!!!")
#     print(e)

print("수행시간 : ", time.time() - start_time)
