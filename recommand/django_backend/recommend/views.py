from django.shortcuts import render

import pandas as pd
import numpy as np

from konlpy.tag import Kkma
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity

from rest_framework.views import APIView
from rest_framework.exceptions import NotFound
from rest_framework.response import Response

from season.models import Season
from performance.models import Performance
from tag.models import Tag
from recommend.models import Recommend
from casting.models import Casting


class PerformanceSimilarity(APIView):
    def get_object(self):
        try:
            return Season.objects.all()
        except Season.DoesNotExist:
            raise NotFound({'message': '공연이 존재하지 않습니다.'})

    def get(self, request):

        seasons = self.get_object()

        tags_sim_sort_ind = content_based_tag(seasons)
        actors_sim_sort_ind = content_based_actor(seasons)

        performances = Performance.objects.values('id').order_by('id')

        for id in performances:
            content_based_recommend(id['id'], tags_sim_sort_ind, actors_sim_sort_ind)

        return Response()


# 공연 태그 생성
def create_tag(seasons):
    performance_desc = {}

    # 시즌 별 공연 합치기
    for season in seasons:
        pid = season.performance_id
        pdes = season.description
        if pdes != None:
            if pid in performance_desc:
                desc = performance_desc[pid] + " " + pdes
            else:
                desc = pdes
        performance_desc[pid] = desc

    # 공연 정보 자연어 처리
    kkma = Kkma()
    performances_id = []
    descriptions = []  # 공연 설명 리스트
    for id in performance_desc:
        description_tag = []
        # print('설명 : ', performance_desc[id])

        try:
            morpheme = kkma.pos(performance_desc[id])
            description_tag.append(morpheme)
        except:
            print('error', performance_desc[id])

        tag_list = []
        for sentence in description_tag:
            for word, tag in sentence:
                if tag in ['NNG'] and len(word) > 1:
                    tag_list.append(word)

        tag_sentence = ' '.join(t for t in tag_list)
        performances_id.append(id)
        descriptions.append(tag_sentence)

    # TF-IDF 계산
    vect = CountVectorizer()
    tag_matrix = vect.fit_transform(descriptions)  # 공연 - 태그 행렬

    tf = pd.DataFrame(tag_matrix.toarray(), index=performances_id, columns=vect.get_feature_names())

    D = len(tf)
    df = tf.astype(bool).sum(axis=0)
    idf = np.log((D+1)/(df+1))+1

    tfidf = tf * idf
    tfidf = tfidf / np.linalg.norm(tfidf, axis=1, keepdims=True)

    # 태그 - 가중치 생성,
    tag_weight = {}
    for id in performances_id:
        val_list = tfidf.loc[id].sort_values(ascending=False).head(15).to_dict()
        tag_weight[id] = val_list

        # # DB 저장
        # for t in val_list:
        #     performance = Performance.objects.get(pk=id)

            # tag = Tag()
            # tag.performance = performance
            # tag.tag_name = t
            # tag.weight = val_list[t]
            # tag.save()

    return performances_id, tag_weight


# 태그 기반 유사도
def content_based_tag(seasons):
    performances_id, tag_weight = create_tag(seasons)
    # print(tag_weight)

    e_df = pd.DataFrame.from_dict(tag_weight)

    # NaN을 0으로 바꾸기
    e_df = e_df.replace(np.nan, 0)

    # df 전치시키기
    e_df = pd.DataFrame.transpose(e_df)

    # 코사인 유사도 구하기
    tags_sim = cosine_similarity(e_df, e_df)
    # print(tags_sim)

    # 코사인 유사도가 높은 순으로 정렬
    tags_sim_sort_ind = tags_sim.argsort()[:, ::-1]
    tags_sim_sort_ind = pd.DataFrame(tags_sim_sort_ind, index=performances_id)
    return tags_sim_sort_ind


# 배우 기반 유사도
def content_based_actor(seasons):
    performances = Performance.objects.values('id', 'last_season_id')
    performances_df = pd.DataFrame(performances)
    # print(performances_df)

    seasons = Season.objects.values('id', 'playdb_id').order_by('id')
    seasons_df = pd.DataFrame(seasons)
    # print(seasons_df)

    # season과 performance 행렬 합치기
    df_merge_col = pd.merge(performances_df, seasons_df, left_on='last_season_id', right_on='id')
    df_merge_col.rename(columns={'id_x': 'performance_id'}, inplace=True)
    df_merge_col = df_merge_col[['performance_id', 'last_season_id', 'playdb_id']]
    # print(df_merge_col)

    actors = Casting.objects.values('season_playdb_id', 'actor_playdb_id')
    df = pd.DataFrame(actors)
    df = df.set_index('actor_playdb_id')
    # print(df)

    actors_df = df[['season_playdb_id']]
    actors_df['cast'] = 1
    # print(actors_df)

    # last_season_id에 casting이 존재하는 performance만 가져오기 위해 merge
    df_merge_col = pd.merge(df_merge_col, actors_df, left_on='playdb_id', right_on='season_playdb_id')
    df_merge_col = df_merge_col.drop_duplicates(ignore_index=True)
    # print(df_merge_col)

    # dictionary화: keys = playdb_id, index = actor_playdb_id, 내용 = cast
    e = {}
    for i in df_merge_col['playdb_id']:
        # print(i)
        e[df_merge_col[df_merge_col['playdb_id'] == i]['performance_id'].tolist()[0]] = \
            actors_df[actors_df['season_playdb_id'] == i]['cast'].to_dict()

    # print(e)
    # print(len(e))

    # dictionary를 df로 변경
    actors_df = pd.DataFrame.from_dict(e)
    actors_df = actors_df.replace(np.nan, 0)
    actors_df = actors_df.transpose()

    # 코사인 유사도 구하기
    sim_actor = cosine_similarity(actors_df, actors_df)
    # print(sim_actor)

    # 코사인 유사도가 높은 순으로 정렬
    actors_sim_sort_ind = sim_actor.argsort()[:, ::-1]

    # 정렬된 행렬 저장 index 는 공연 id
    keys = df_merge_col['performance_id'].tolist()
    actors_sim_sort_ind = pd.DataFrame(actors_sim_sort_ind, index=keys)

    return actors_sim_sort_ind


def content_based_recommend(pid, tags_sim_sort_ind, actors_sim_sort_ind):
    print('pid:', pid)
    # 함수 호출
    p = find_sim_performance(tags_sim_sort_ind, pid)
    pa = find_sim_performance(actors_sim_sort_ind, pid)
    # print("p:", p)
    # print("pa: ", pa)
    
    if p == None:
        p = []
    if pa == None:
        pa = []

    pid_list = list(set(p + pa))
    performance = Performance.objects.get(pk=pid)
    for sim_id in pid_list:
        recommend_performance = Performance.objects.get(pk=sim_id)

        recommend = Recommend()
        recommend.performance = performance
        recommend.recommend_performance = recommend_performance
        recommend.save()
    # 특정 공연과 유사도가 높은 공연 top_n개 리턴


def find_sim_performance(sorted_ind, pid, top_n=30):

    if pid not in sorted_ind[0]:
        return

    # 원하는 개수만큼만 뽑기
    similar_indexes = sorted_ind.loc[pid, : top_n]
    # print(similar_indexes)
    similar_indexes = similar_indexes.values.flatten()

    # 중복으로 뽑힌 거 삭제
    similar_indexes_set = set(similar_indexes)
    similar_indexes = list(similar_indexes_set)
    # print(similar_indexes)

    # 공연 번호로 변환
    keys = pd.DataFrame(sorted_ind.index)
    similar_indexes_id = keys.iloc[similar_indexes]
    similar_indexes_id = np.setdiff1d(similar_indexes_id, pid)

    return similar_indexes_id.tolist()