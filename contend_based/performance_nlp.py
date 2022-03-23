import pandas as pd
import csv

# Okt 패키지 안 Kkma 모듈 호출
from konlpy.tag import Kkma
kkma = Kkma()

# 파일 읽기
filename = "seasons_musical.csv";
f = open(filename, 'r', encoding='utf-8')
lines = csv.reader(f)

# 공연 설명 저장
performances = []  # {공연 id, 공연 설명} 저장
for line in lines:
    performance = {'id': line[1], 'desc': line[7]}
    performances.append(performance)
    # print(performance)

# print(len(performances))

for i in range(1, len(performances)):
    # 각 공연 설명별 형태소 구분
    description_tags = [] 

    morpheme = kkma.pos(performances[i]['desc'])
    description_tags.append(morpheme)
    # print(morpheme)

    # 명사, 형용사, 동사 선별하여 리스트에 담기
    tags_list=[]
    for sentence in description_tags:
        for word, tag in sentence:

            # NNG 태그 단어 사용
            if tag in ['NNG'] and len(word) > 1:
                tags_list.append(word)
    
    # 리스트를 문장으로 변환하여 저장
    tags_sentence = ' '.join(t for t in tags_list)
    performances[i]['desc'] = tags_sentence

dataframe = pd.DataFrame(performances)
dataframe.to_csv('tag_list.csv', header=False, index=False, encoding='utf-8-sig')

f.close()