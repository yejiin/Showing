import pandas as pd
import numpy as np
import warnings;
from sqlalchemy.sql.functions import percent_rank
from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity
import json

warnings.filterwarnings('ignore')

# 유사도 높은 공연 저장한 파일 읽고 특정 공연으로 검색
def find_recommend(pid) :
    # tags_sim_sort_ind 갖고옴 : index 는 공연 id
    # tags_sim_sort_ind = pd.read_csv('C:/Users/SSAFY/Downloads/UI/output.csv', index_col=0) # 행렬 저장된 경로
    actors_sim_sort_ind = pd.read_csv('C:/Users/SSAFY/Downloads/UI/output_actor.csv', index_col=0)
    # print(tags_sim_sort_ind)

    # 함수 호출
    # p =find_sim_performance_by_tag(tags_sim_sort_ind, pid)
    pa = find_sim_performance_by_tag(actors_sim_sort_ind, pid)
    print(pa)


# 특정 공연과 유사도가 높은 공연 top_n개 리턴
def find_sim_performance_by_tag(sorted_ind, pid, top_n=3):

    # 원하는 개수만큼만 뽑기
    similar_indexes = sorted_ind.loc[pid, : str(top_n)]
    print(similar_indexes)
    similar_indexes = similar_indexes.values.flatten()

    # 0번째는 자기 자신이므로 없앰
    similar_indexes_set = set(similar_indexes)
    similar_indexes = list(similar_indexes_set)
    print(similar_indexes)

    # 공연 번호로 변환
    keys = pd.DataFrame(sorted_ind.index)
    similar_indexes_id = keys.iloc[similar_indexes]
    similar_indexes_id = np.setdiff1d(similar_indexes_id, pid)
    # print(similar_indexes_id)

    # DataFrame 으로 바꿔서 리턴
    return pd.DataFrame(similar_indexes_id)


# 전체 호출 함수 : pid 는 list
def content_based_recommend(pid_list):
    find_recommend(pid_list)


content_based_recommend([2,6])