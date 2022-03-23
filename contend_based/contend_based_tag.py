import pandas as pd
import numpy as np
import warnings;
from sqlalchemy.sql.functions import percent_rank
from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity
import json

warnings.filterwarnings('ignore')


def content_based_tag_to_csv():
    with open("C:/Users/SSAFY/Downloads/UI/test.json", "r", encoding="utf8") as f:
        json_data = json.load(f)

    print(json_data)

    keys = list(json_data.keys()) # ['193', '179', '145', '197', '148', '163', '190', '171', '154', '149', '155', '192', '174', '146', '144', '182', '156', '150', '172', '142', '151', '187', '186', '175', '167', '176', '177', '166', '181', '180', '165', '153', '188', '160', '195', '173']
    print(list(json_data.keys()))

    # 0을 뺀 새로운 dictionary 만들기
    e = {}
    for i in range(len(keys)):
        e[keys[i]] = json_data[keys[i]]['0'] # {'i': {'데스': 41.7049521733, '데스노트': 40.6126831601, '노트': 34.9861600762, '데': 23.2812831277, '스': 22.6366570209, '라이토': 20.3063415801, '미사': 16.9219513167, '컬': 15.9068400688, '뮤지컬': 15.2950385276, '뮤': 15.2950385276}}
    print(e)

    # DataFrame 만들기
    e_df = pd.DataFrame.from_dict(e)
    # NaN을 0으로 바꾸기
    e_df = e_df.replace(np.nan, 0)
    # print(e_df)

    # df 전치시키기
    e_df = pd.DataFrame.transpose(e_df)
    # e_df.index(keys)
    print(e_df)


    # 코사인 유사도 구하기
    tags_sim = cosine_similarity(e_df, e_df)
    # print(tags_sim.shape)
    # print(tags_sim)

    # 구한 거 정렬
    tags_sim_sort_ind = tags_sim.argsort()[:, ::-1]
    # tags_sim_sort_ind.index(keys)
    print(tags_sim_sort_ind)

    # 코사인 유사도 저장
    path = "C:/Users/SSAFY/Downloads/UI/output.csv"  # 저장할 경로
    tag_csv = pd.DataFrame(tags_sim_sort_ind, index=keys)
    tag_csv.to_csv(path, header=True, index=True)

    pass


def find_recommend(pid) :
    # tags_sim_sort_ind 갖고옴 : index 는 공연 id
    tags_sim_sort_ind = pd.read_csv('C:/Users/SSAFY/Downloads/UI/output.csv', index_col=0)
    # print(tags_sim_sort_ind)

    # 함수 호출
    find_sim_performance(tags_sim_sort_ind, pid)


def find_sim_performance(sorted_ind, pid, top_n=16):
    # performance_id 는 공연 id를 str 으로 변환한 값
    performance_id = str(pid)

    # 원하는 개수만큼만 뽑기
    similar_indexes = sorted_ind.loc[pid, : str(top_n)]
    print(similar_indexes.index)

    # 0번째는 자기 자신이므로 없앰
    similar_indexes = similar_indexes[1:]
    print(similar_indexes)

    # 공연 번호로 변환
    keys = pd.DataFrame(sorted_ind.index)
    similar_indexes_id = keys.iloc[similar_indexes]
    print(similar_indexes_id)

    # DataFrame 으로 바꿔서 리턴
    return pd.DataFrame(similar_indexes_id)


def content_based_recommend(pid):
    find_recommend(pid)


content_based_tag_to_csv()
# find_recommend()
content_based_recommend(193)
