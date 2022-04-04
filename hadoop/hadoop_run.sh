# 분석할 파일 수만큼 반복
for FULL_NAME in /home/hadoop/Project/data/review/*
do
    # 경로, 확장자 없는 순수 파일명 추출
    BASE_NAME=$(basename "$FULL_NAME" .txt)

    # hdfs에 output folder 생성
    hdfs dfs -mkdir wordcount_${BASE_NAME}

    # 분석할 리뷰 파일 ec2 -> hdfs에 put
    hdfs dfs -put ${FULL_NAME} wordcount_${BASE_NAME}

    # 분석 (output 추출)
    hadoop jar ssafy.jar wordcount -D fileName=${BASE_NAME} wordcount_${BASE_NAME}
done