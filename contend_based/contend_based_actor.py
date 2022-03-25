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


# csv to DataFrame
df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/performance.csv', encoding='cp949')
# print(df.columns)
performance = df[['id', 'last_season_id']]
# print(performance)

df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/season.csv', encoding='cp949')
seasons = df[['id', 'playdb_id']]
# print(seasons)

df_merge_col = pd.merge(performance, seasons, left_on='last_season_id', right_on='id')
df_merge_col.rename(columns={'id_x':'performance_id'}, inplace=True)
df_merge_col = df_merge_col[['performance_id', 'last_season_id', 'playdb_id']]
# print("df_merge_col : ")
# print(df_merge_col.shape)

df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/casting.csv', encoding='cp949', index_col='actor_playdb_id')
actors = df[['season_playdb_id']]
actors['cast'] = 1
# print(actors)

df_merge_col = pd.merge(df_merge_col, actors, left_on='playdb_id', right_on='season_playdb_id')
df_merge_col = df_merge_col.drop_duplicates(ignore_index = True)
print(df_merge_col)

j = 86403
print(df_merge_col[df_merge_col['playdb_id']==j])
print(df_merge_col[df_merge_col['playdb_id']==j]['performance_id'])

e = {}
for i in df_merge_col['playdb_id']:
    # print(i)
    e[df_merge_col[df_merge_col['playdb_id']==i]['performance_id'].tolist()[0]] = \
        actors[actors['season_playdb_id']==i]['cast'].to_dict()

print(e)
print(len(e))

actors_df = pd.DataFrame.from_dict(e)
actors_df = actors_df.replace(np.nan, 0)
print("actors_df : ")
print(actors_df)
print(actors_df.keys())
# print(actors_df[86403].tolist())
# print(actors[actors['season_playdb_id']==86403])


