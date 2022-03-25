import pandas as pd
import csv

# Okt 패키지 안 Kkma 모듈 호출
from konlpy.tag import Kkma
kkma = Kkma()


# 파일 읽기
filename = "seasons_play.csv";
f = open(filename, 'r', encoding='utf-8')
lines = csv.reader(f)


# 시즌별 공연 정보 합치기
performance_id_set = set()
performance_des_dict = {}
for line in lines:
    # 공연정보가 없으면 제외
    if line[7] != "":
        performance_id = line[1]

        performance_id_set.add(performance_id)
        performance_desc = ""

        if performance_id in performance_des_dict:
            performance_desc = performance_des_dict[performance_id] + " " + line[7]
        else:
            performance_desc = line[7]
    performance_des_dict[performance_id] = performance_desc

performance_id_set.discard('performance_id')

performance_id_set_list = list(performance_id_set)
performances = []  # {공연 id, 공연 설명} 저장
for i in performance_id_set_list:
    # 각 공연 설명별 형태소 구분
    description_tags = [] 

    morpheme = kkma.pos(performance_des_dict[i])
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
    performance = {'performance_id': i, 'description': tags_sentence}
    performances.append(performance)
    print(i)


dataframe = pd.DataFrame(performances)
dataframe.to_csv('seasons_play_tag.csv', header=True, index=False, encoding='utf-8-sig')
f.close()