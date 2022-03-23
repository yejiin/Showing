import pandas as pd
import numpy as np
import warnings;
from sqlalchemy.sql.functions import percent_rank
from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity
import json

warnings.filterwarnings('ignore')


# 공연 id - 태그 json 파일 읽어서 가공 - 저장하는 함수
def content_based_tag_to_csv():
    with open("C:/Users/SSAFY/Downloads/UI/test.json", "r", encoding="utf8") as f: # json 파일 경로
        json_data = json.load(f)

    print(json_data)

    keys = list(json_data.keys()) # ['193', '179', '145', '197', '148', '163', '190', '171', '154', '149', '155', '192', '174', '146', '144', '182', '156', '150', '172', '142', '151', '187', '186', '175', '167', '176', '177', '166', '181', '180', '165', '153', '188', '160', '195', '173']
    print(list(json_data.keys()))

    # 0을 뺀 새로운 dictionary 만들기
    e = {}
    for i in range(len(keys)):
        e[keys[i]] = json_data[keys[i]]['0']
    print(e)

    # DataFrame 만들기
    e_df = pd.DataFrame.from_dict(e)
    # NaN을 0으로 바꾸기
    e_df = e_df.replace(np.nan, 0)

    # df 전치시키기
    e_df = pd.DataFrame.transpose(e_df)

    # 코사인 유사도 구하기
    tags_sim = cosine_similarity(e_df, e_df)
    # print(tags_sim)

    # 코사인 유사도가 높은 순으로 정렬
    tags_sim_sort_ind = tags_sim.argsort()[:, ::-1]
    print(tags_sim_sort_ind)

    # 정렬된 행렬 저장 index 는 공연 id
    path = "C:/Users/SSAFY/Downloads/UI/output.csv"  # 저장할 경로
    tag_csv = pd.DataFrame(tags_sim_sort_ind, index=keys)
    tag_csv.to_csv(path, header=True, index=True)

    pass


# 유사도 높은 공연 저장한 파일 읽고 특정 공연으로 검색
def find_recommend(pid) :
    # tags_sim_sort_ind 갖고옴 : index 는 공연 id
    tags_sim_sort_ind = pd.read_csv('C:/Users/SSAFY/Downloads/UI/output.csv', index_col=0) # 행렬 저장된 경로
    # print(tags_sim_sort_ind)

    # 함수 호출
    p =find_sim_performance_by_tag(tags_sim_sort_ind, pid)
    print(p)


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

# print("printing 193 : ")
# content_based_recommend([193])
# print("printing 193, 179 : ")
# content_based_recommend([193, 179])
# print("printing 193, 179, 145, 182 : ")
# content_based_recommend([193, 179, 145, 182])
# 다 똑같음;;; 소름