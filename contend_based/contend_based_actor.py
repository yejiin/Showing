import pandas as pd
import numpy as np
import warnings;

from sqlalchemy.sql.functions import percent_rank

from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity
import pyodbc
from sqlite3 import connect
import pymysql


warnings.filterwarnings('ignore')


df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/casting.csv', encoding='cp949', index_col=0)
actors = df[['actor_id']]
# print(actors)

# csv to dataframe
df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/performance.csv', encoding='cp949')
# print(df.columns)
performance = df[['id', 'last_season_id']]
# print(performance)

df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/season.csv', encoding='cp949')
seasons = df[['id', 'playdb_id']]
# print(seasons)

# performance['playdb_id'] = seasons[seasons['id']==performance['last_season_id']]['playdb_id']
# print(performance)

df_merge_col = pd.merge(performance, seasons, left_on='last_season_id', right_on='id')
df_merge_col.rename(columns={'id_x':'performance_id'}, inplace=True)
df_merge_col = df_merge_col[['performance_id', 'last_season_id', 'playdb_id']]
print(df_merge_col)