import pandas as pd
import numpy as np
import warnings;
from sqlalchemy.sql.functions import percent_rank
from ast import literal_eval
from sklearn.feature_extraction.text import  CountVectorizer
from sklearn.metrics.pairwise import  cosine_similarity

warnings.filterwarnings('ignore')


def content_based_tag_to_csv():
    performances = pd.read_csv()  # csv 데이터 읽어올 곳 경로 작성
    performances_df = performances[['id', 'tags']]  # 태그 기반 추천(tags는 나중에 맞는 이름으로 변경)
    print(performances)  # 입력받은 데이터 출력

    # 태그는 List임 -> ' '로 나눠지게 처리
    performances_df['tags'] = performances_df['tags'].apply(literal_eval())  # {}를 각각 나눠주기
    performances_df['tags'] = performances_df['tags'].apply(lambda x: [y['tag_name'] for y in x])
    # 태그 가중치 높은 3개만 분석할 것 -> 변경 가능
    performances_df['tags'] = performances_df['tags'].sort_values('weight')[:3]

    performances_df['tags_literal'] = performances_df['tags'].apply(lambda x: (' ').join(x))
    count_vec = CountVectorizer(min_df=0, ngram_range=(1, 2))
    tag_mat = count_vec.fit_transform(performances_df['tags_literal'])

    # 평점을 오름차순으로 짤라보자

    # 그 담은 리뷰 개수 몇 개 이상

    # 코사인 유사도 구함
    tags_sim = cosine_similarity(tag_mat, tag_mat)
    print(tags_sim)

    # 구한 거 정렬
    tags_sim_sort_ind = tags_sim.argsort()[:, ::-1]
    print(tags_sim_sort_ind)

    # 코사인 유사도 저장
    path = ""  # 저장할 경로
    dataframe = pd.DataFrame(tags_sim_sort_ind)
    dataframe.to_csv(path, header=False, index=False)
    pass


# 하나의 공연에 대한 관련 공연
def find_sim_performance(df, sorted_ind, pid, top_n=15):
    performance_id = df[df['id'] == pid]

    performance_index = performance_id.index.values
    similar_indexes = sorted_ind[performance_index, :(top_n*2)]

    similar_indexes = similar_indexes[similar_indexes != pid]
    similar_indexes = similar_indexes.reshape(-1)
    return df.iloc[similar_indexes]


# 공연 여러 개 입력 받음
def find_sim_performances(perform, performances_df, tags_sim_sort_ind):
    p = []
    for i in perform :
        p.append(find_sim_performance(performances_df, tags_sim_sort_ind, i))

    return p

# 공연 간 유사도 구하기
# -> df, sorted_ind 등을 태그, 장르, 배우로 바꿀 수 있는 것!
# 현재까지는 평균 별점, 리뷰 수 고려하지 x


# 결론
# 영화 리스트를 넣어주면 추천 리스트를 반환!
def content_based_tag(perform):
    performances = pd.read_csv()  # csv 데이터 읽어올 곳 경로 작성
    performances_df = performances[['id', 'tags']]  # 태그 기반 추천(tags는 나중에 맞는 이름으로 변경)
    tags_sim_sorted_ind = pd.read_csv() # sim 계산한 거 불러 옴
    recommend_list = find_sim_performances(perform, performances_df, tags_sim_sorted_ind)
    return recommend_list
