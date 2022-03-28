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

    keys = list(json_data.keys())
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


# test
# print("printing 193 : ")
# content_based_recommend([193])
# print("printing 193, 179 : ")
# content_based_recommend([193, 179])
# print("printing 193, 179, 145, 182 : ")
# content_based_recommend([193, 179, 145, 182])
# content_based_tag_to_csv()