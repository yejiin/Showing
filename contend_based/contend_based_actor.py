import pandas as pd
import numpy as np
import warnings;

from sqlalchemy.sql.functions import percent_rank

warnings.filterwarnings('ignore')
from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity
import pyodbc
from sqlite3 import connect
import pymysql
from sqlalchemy import create_engine


db_connection_str = (
    "mysql+pymysql://root:" + "ssafy" + "@127.0.0.1:3306/showing?charset=utf8"
)
db_connection = create_engine(db_connection_str)
conn = db_connection.connect()

# cnxn  = "DRIVER={SQL Server};SERVER=" + server +";uid=" + user +";pwd=" +password + "DATABASE=" + db
# cursor = cnxn.cursor()

# sql = 'select p.id id, p.performance_name name, t.id tag_id, t.tag_name tag_name'
# sql += ' avg(rating) avg '
# sql += ' from performance p join tag t on p.id=t.performance_id'

sql2 = 'select s.id id, p.performance_name name'
# sql2 += ' avg(rating) avg '
sql2 += ' from performance p join season s on p.id=s.performance_id'


performances = pd.read_sql(sql2, con= conn) # 데이터 읽어올 곳 경로 작성
performances_df = performances[['id', 'name']] # 태그 기반 추천
# print(performances)

for i in performances_df['id']:
    # sql = 'a.id actors, a.actor_name actor_name'
    # sql += ' from performance p join season s on p.id=s.performance_id join casting c on c.season_id=s.id'
    # sql += ' join actor a on a.id = c.actor_id'
    # sql += ' where p.id=' + str(i)
    sql2 = 'select s.id id, p.performance_name name'
    sql2 += ' from performance p join season s on p.id=s.performance_id'
    performances = pd.read_sql(sql2, con=conn)  # 데이터 읽어올 곳 경로 작성
    print(performances)
    str = ''
    print(performances[1])
    for i in performances:
        str.join(i[['name']])
    performances_df['actors'] = performances[['id', 'name']]   # 배우 기반 추천
    print(performances_df)

# sql2 = 'select * from performance'


performances_df['actors'] = performances_df['actors'].apply(literal_eval)
performances_df['actors'] = performances_df['actors'].apply(lambda  x : [y['actor_name'] for y in x])

# performances_df['tags_literal'] = performances_df['tags'].apply(lambda x : (' ').join(x))
# count_vect = CountVectorizer(min_df=0, ngram_range=(1,2))
# tag_mat = count_vect.fit_transform(performances_df['tags_literal'])

performances_df['actor_literal'] = performances_df['actors'].apply(lambda  x : (' ').join(x))
count_vecta = CountVectorizer(min_df=0, ngram_range=(1,2))
actor_mat = count_vecta.fit_transform(performances_df['actor_literal'])
print(actor_mat.shape)

# 평점을 오름차순으로 짤라보자

# 그 담은 리뷰 개수 몇 개 이상

# 코사인 유사도 구함
# tags_sim = cosine_similarity(tag_mat, tag_mat)

actor_sim = cosine_similarity(actor_mat, actor_mat)

# 구한 거 정렬
# tags_sim_sort_ind = tags_sim.argsort()[:,::-1]

actor_sim_sort_ind = actor_sim.argsort()[:,::-1]


def find_sim_performance(df, sorted_ind, name, top_n=15):
    performance_name = df[df['performance_name']==name]

    performance_index = performance_name.index.values
    similar_indexes = sorted_ind[performance_index, :(top_n)]

    similar_indexes = similar_indexes.reshape(-1)
    return df.iloc[similar_indexes]


find_sim_performance(performances_df, actor_sim_sort_ind, "레베카");
# 공연 간 유사도 구하기
# -> df, sorted_ind 등을 태그, 장르, 배우로 바꿀 수 있는 것!
# 현재까지는 평균 별점, 리뷰 수 고려하지 x


conn.close()