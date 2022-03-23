import pandas as pd  # 데이터프레임 사용
from math import log   # IDF 계산
import csv

# 파일 읽기
filename = "tag_list.csv";
f = open(filename, 'r', encoding='utf-8')
lines = csv.reader(f)

performance_ids=[]  # 공연 id 리스트
descriptions = []  # 공연 설명 기스트
for line in lines:
    performance_ids.append(line[0])
    descriptions.append(line[1])

# 태그 리스트
tag_list = list(set(w for des in descriptions for w in des.split()))
tag_list.sort()

# 공연 개수
N = len(performance_ids)

# 문서 d에서 단어 t가 언급된 횟수
def tf(t, d):
    return d.count(t)

# 다른 문서에 비해 단어 t가 포함된 비율
def idf(t):
    df = 0
    for des in descriptions:
        df += t in des
    return log(N/(df+1))

# 문서군에서 단어 t가 얼마나 유니크한가를 표현한 가중치
def tfidf(t, d):
    return tf(t,d) * idf(t)

# 태그별 가중치 결과
tag_weight = []  
for i in range(N):
  tag_weight.append([])
  d = descriptions[i]
  for j in range(len(tag_list)):
    t = tag_list[j]
    tag_weight[-1].append(tfidf(t,d))

# 데이터프레임 생성
tfidf_ = pd.DataFrame(tag_weight, index=performance_ids, columns = tag_list)

# 행 열 전환
t = tfidf_.transpose()

result = {};
for id in range(1, len(performance_ids)):
    # 설명이 없는 경우 제외시킴
    if (descriptions[id] == ''):
        continue

    val_list = tfidf_.loc[performance_ids[id]].sort_values(ascending=False).head(15).to_dict();
    result[performance_ids[id]] = val_list

dataframe = pd.DataFrame([result])
dataframe.to_json('tag_weight.json', force_ascii=False)