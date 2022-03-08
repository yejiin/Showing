
# 1강

## MapReduce Phase (3단계)

- 맵 페이즈

    - 제일 먼저 수행, 여러 파티션에 병렬 분산되어 수행
    - 각 머신마다 수행된 mapper가 입력 데이터 한 줄 마다 맵 함수 호출
    - 맵 함수는 (키,밸류) 쌍 형태로 결과를 출력, 여러 머신에 나누어 보내며 같은 키를 가진 키밸류 쌍은 같은 머신으로 보내짐

- 셔플링 페이즈

    - 모든 머신에서 맵 페이즈가 끝나면 시작
    - 머신으로 보내진 데이터를 키를 이용하여 정렬, 같은 키값을 가진 쌍을 모아 밸류 리스트를 생성, 키에 따라서 여러 머신에 분산하여 보냄

- 리듀스 페이즈

    - 모든 머신에서 셔플링 페이즈가 끝나면 시작
    - 각각의 머신에서는 셔플링 페이즈에서 해당 머신으로 보내진 각가의 키밸류 쌍 마다 리듀스 함수가 호출되며 하나의 리듀스 함수가 끝나면 다음 키밸류 쌍에 리듀스 함수가 호출된다.
    - 출력이 있다면 키밸류 쌍 형태로 출력한다.

## Hadoop 실행 단계

- 모든 명령은 hadoop 계정에서 실행
    
    ```bash
    /home/hadoop$ source.bashrc
    ```
    
- Name node 포맷하기
    
    ```bash
    $ hadoop namenode -format
    ```
    
- Dfs deamon 실행
    
    ```bash
    $ start-dfs.sh
    ```
    
- MapReduce deamon 실행 (standalone 에선 불필요)
    
    ```bash
    $ start-mapred.sh
    ```
    
- 확인
    
    ```bash
    $ jps
    ```
    

### 컴파일 후 실행

- ant 실행
    
    ```bash
    ant
    ```
    
- 리듀스 함수 결과 디렉토리 삭제
    
    ```bash
    hdfs dfs -rm -r wordcount_test_out
    ```
    
    - safemode로 삭제 안될 시
        
        ```bash
        hdfs dfsadmin -safemode leave
        ```
        
- 이후 작업
    
    ```bash
    hadoop jar ssafy.jar wordcount1char wordcount_test wordcount_test_out
    hdfs dfs -cat wordcount_test_out/part-r-00000 | more
    ```
