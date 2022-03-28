import pandas as pd
import numpy as np
import warnings;

from sklearn.metrics.pairwise import  cosine_similarity


warnings.filterwarnings('ignore')


def content_based_actor_to_csv():
    # csv to DataFrame
    df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/performance.csv', encoding='cp949')
    performance = df[['id', 'last_season_id']]
    # print(performance)

    df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/season.csv', encoding='cp949')
    seasons = df[['id', 'playdb_id']]
    # print(seasons)

    # season과 performance 행렬 합치기
    df_merge_col = pd.merge(performance, seasons, left_on='last_season_id', right_on='id')
    df_merge_col.rename(columns={'id_x':'performance_id'}, inplace=True)
    df_merge_col = df_merge_col[['performance_id', 'last_season_id', 'playdb_id']]
    # print("df_merge_col : ")

    # casting csv 읽어오기 : 나중에 dictionary화를 위해 cast column 추가
    df = pd.read_csv('C:/Users/SSAFY/Downloads/UI/casting.csv', encoding='cp949', index_col='actor_playdb_id')
    actors = df[['season_playdb_id']]
    actors['cast'] = 1
    # print(actors)

    # last_season_id에 casting이 존재하는 performance만 가져오기 위해 merge
    df_merge_col = pd.merge(df_merge_col, actors, left_on='playdb_id', right_on='season_playdb_id')
    df_merge_col = df_merge_col.drop_duplicates(ignore_index = True)
    # print(df_merge_col)

    # dictionary화 : keys=playdb_id, index=actor_playdb_id, 내용=cast
    e = {}
    for i in df_merge_col['playdb_id']:
        # print(i)
        e[df_merge_col[df_merge_col['playdb_id']==i]['performance_id'].tolist()[0]] = \
            actors[actors['season_playdb_id']==i]['cast'].to_dict()

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
    tags_sim_sort_ind = sim_actor.argsort()[:, ::-1]
    # print(tags_sim_sort_ind)

    # 정렬된 행렬 저장 index 는 공연 id
    keys = df_merge_col['performance_id'].tolist()
    path = "C:/Users/SSAFY/Downloads/UI/output_actor.csv"  # 저장할 경로
    tag_csv = pd.DataFrame(tags_sim_sort_ind, index=keys)
    tag_csv.to_csv(path, header=True, index=True)

