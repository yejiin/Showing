# 목차

- [병렬 분산 알고리즘](#병렬-분산-알고리즘)
  - [병렬처리 컴퓨팅 이란?](#병렬처리-컴퓨팅-이란)
  - [분산처리 컴퓨팅 이란?](#분산처리-컴퓨팅-이란)
- [서버의 성능을 높이는 방법](#서버의-성능을-높이는-방법)
  - [Scale-out](#scale-out)
  - [Scale-up](#scale-up)
- [MapReduce Framework](#mapreduce-framework)
  - [사용 이유?](#사용-이유)
  - [MapReduce Framework 란?](#mapreduce-framework-란)
  - [구현](#구현)
  - [Programming Model](#programming-model)
  - [특징](#특징)
  - [MapReduce Phase](#mapreduce-phase)
    - [Map phase](#map-phase)
    - [Shuffling phase](#shuffling-phase)
    - [Reduce phase](#reduce-phase)
- [Hadoop](#hadoop)
  - [주요 구성 요소](#주요-구성-요소)
    - [MapReduce](#mapreduce)
    - [HDFS (Hadoop Distributed File System)](#hdfs-hadoop-distributed-file-system)
  - [Master - Slaves](#master---slaves)
  - [MapReduce의 함수](#mapreduce의-함수)
    - [Map 함수](#map-함수)
    - [Reduce 함수](#reduce-함수)
    - [Combine 함수](#combine-함수)
- [참고](#참고)
  - [분산 및 병렬처리](#분산-및-병렬처리)

# 병렬 분산 알고리즘

## 병렬처리 컴퓨팅 이란?

여러가지 일을 동시에 처리

## 분산처리 컴퓨팅 이란?

한가지 일을 여러개로 나눠서 다른 컴퓨터에 보내고 처리를 하게 하고 결과를 모으는 형태

# 서버의 성능을 높이는 방법

## Scale-out

아주 많은 값싼 서버들을 이용

데이터 중심(data-intensive) 어플리케이션 분야에서는 아주 많은 값싼 서버들을 많이 이용하는 것을 선호

## Scale-up

적은 수의 값비싼 서버들을 이용

고가의 서버들은 가격의 관점에서는 선형으로 성능이 증가하지 않는다 → 두 배의 성능의 프로세서 한 개를 가진 컴퓨터의 가격이 일반적인 프로세서 한 개를 가진 컴퓨터 가격의 두 배보다 훨씬 더 비싸다

# MapReduce Framework

## 사용 이유?

**데이터 중심 프로세싱 (Data-intensive processing)** 은 한 대의 컴퓨터 능력으로는 처리가 어렵다. 근본적으로 **수십대, 수백대 혹은 수천대의 컴퓨터를 묶어서 처리**해야 하는데, 이것을 `MapReduce Framework`가 해준다.

## MapReduce Framework 란?

기존에 존재하는 여러 가지 다른 병렬 컴퓨팅 방법에서는 프로그래머가 낮은 레벨의 시스템 세부 내용까지 아주 잘 알고 많은 시간을 쏟아야 했다.

빅데이터를 이용한 효율적인 계산이 가능한 첫 번째 프로그래밍 모델로,

값싼 컴퓨터들을 모아서 **클러스터**를 만들고, 여기에서 빅데이터를 처리하기 위한 **`scalable` 병렬 소프트웨어의 구현을 쉽게 할 수 있도록** 도와주는 간단한 프로그래밍 모델이다.

> 💡 **scalable 하다?**  
> 사용자 수나 데이터가 급증해도 프로그램이 멈추거나 성능이 크게 떨어지는 일이 없다

## 구현

**구글의 MapReduce** 또는 **오픈소스인 하둡(Hadoop)** 은 MapReduce Framework의 우수한 구현의 형태이다.

드라이버에 해당하는 **main 함수가 map 함수와 reduce 함수를 차례로 호출해서 처리**한다.

## Programming Model

함수형 프로그래밍(Functional programming) 언어의 형태이다.

유저는 아래 3가지 함수를 구현해서 제공해야 한다. 대괄호는 리스트 형태를 나타낸다.

- Main 함수
- Map 함수
  - 입력 : (key1, val1)
  - 출력 : [(key2, val2)]
- Reduce 함수
  - 입력 : (key2, [val2])
  - 출력 : [(key3, val3)]

## 특징

MapReduce Framework에서는 각각의 레코드(record) 또는 튜플(tuple)은 **키-밸류(KEY, VALUE) 쌍으로 표현**된다.

Main 함수를 한 개의 **master machine**에서 수행하는데, 이 machine은 **Map 함수를 수행하기 전에 전처리**를 하는 데 사용되거나, **Reduce 함수의 결과를 후처리**하는 데 사용될 수 있다.

**컴퓨팅**은 유저가 정의한 함수 한 쌍(Map과 Reduce)으로 이루어진 **MapReduce 페이즈(phase)** 를 한번 또는 그 이상 반복해서 수행할 수 있다. 이 MapReduce phase를 수행시키는 것은 드라이버에 해당하는 Main 프로그램이다.

한번의 MapReduce phase는 Map 함수를 먼저 호출하고 그 다음에 Reduce 함수를 호출하는데, 때에 따라서는 Map 함수가 끝난 후에 Combine 함수를 중간에 수행할 수 있다. (Map → Combine → Reduce)

## MapReduce Phase

### Map phase

Map 함수가 실행되는 페이즈로, 제일 먼저 수행되며 **데이터의 여러 파티션에 병렬 분산으로 호출**되어 수행된다.

> 💡 **데이터의 파티션에 병렬 분산 호출?**  
> 우리 눈에는 데이터가 하나의 큰 파일로 보이지만, 실제로는 여러 파티션으로 여러 machine에 분산되어 저장되어있다. 따라서 데이터가 저장되어 있는 여러 파티션에서 map 함수들이 병렬로 수행된다.

각 machine마다 Mapper가 수행되고, **Mapper는 입력 데이터의 한 줄마다 Map 함수를 호출**한다. 다시 말하면, 한 데이터에 Map 함수를 호출하고 해당 함수가 끝나면 다음 데이터에 대해 Map 함수를 또 호출하는 식이다.

따라서 **한 machine(cpu)에 대해**서는 parallel(병렬적) 으로 수행되는 것이 아닌 **sequential (순차적) 으로 수행**되지만, 여러 대(수백 또는 수천 대)의 컴퓨터에서 각 Mapper가 machine마다 Map 함수를 sequential (순차적) 으로 수행하기 때문에 **전체적으로 본다면 여러 Map 함수가 parallel(병렬적) 으로** 돌아가게 된다.

Map 함수는 (KEY, VALUE)쌍 형태로 결과를 출력해 여러 machine에 나누어 보낸다. 이 때 같은 KEY를 가진 (KEY, VALUE)쌍은 같은 machine 으로 보내진다.

### Shuffling phase

모든 machine에서 Map phase가 다 끝나면 시작된다.

Map phase에서 각각의 machine으로 보내진 (KEY, VALUE) 쌍을 **KEY를 이용해서 정렬**한다. 그 후, 각각의 KEY마다 같은 KEY를 가진 (KEY, VALUE) 쌍을 모아서 **VALUE-LIST**를 만든 다음, (KEY, VALUE-LIST) 형태로 KEY에 따라 여러 machine 에 분산해서 보낸다.

### Reduce phase

모든 machine에서 Shuffling phase가 다 끝나면 각 machine마다 Reduce 페이즈가 시작된다.

각각의 machine에서는 Shuffling phase에서 해당 machine으로 보내진 각각의 (KEY, VALUE-LIST) 쌍마다 Reduce 함수가 호출된다. 하나의 Reduce 함수가 끝나면 다음 (KEY, VALUE-LIST) 쌍에 Reduce 함수가 호출된다.

출력이 있다면 (KEY, VALUE) 쌍 형태로 출력한다.

# Hadoop

Apache 프로젝트의 MapReduce Framework의 오픈 소스이고, 빅데이터를 수천 대의 값싼 컴퓨터에 병렬 처리하기 위해 분산해둔다.

자바 프로그래밍 언어로 MapReduce 알고리즘을 구현한다.

## 주요 구성 요소

### MapReduce

: 소프트웨어의 수행을 분산한다. (컴퓨팅을 분산한다.)

### HDFS (Hadoop Distributed File System)

: 데이터를 분산한다.

우리 눈에는 큰 빅데이터 파일이 하나가 있더라도, 사실은 그 파일을 **조각내서 여러 컴퓨터에 저장**을 해둔다. 각 파일은 여러 개의 **순차적인 블록으로 저장**한다.

어떤 파일 조각 하나가 못쓰게 되면 복사해둔 다른 machine에 있는 것을 가져다 사용할 수 있도록 하기 위해(**fault tolerance**를 위해) 그 각각의 조각들도 복사를 해서 여러 machine 여기저기에 저장을 해둔다.

> 💡 **폴트 톨러런스 (fault tolerance) ?**  
> 시스템을 구성하는 부품의 일부에서 결함(fault) 또는 고장(failure)이 발생해도 정상적 혹은 부분적으로 기능을 수행할 수 있는 것

## Master - Slaves

한 개의 Namenode (master) 와 여러 개의 Datanode (slaves) 를 사용한다.

- Namenode : 파일 시스템을 관리하고 클라이언트가 파일에 접근할 수 있게 한다.
- Datanode : 컴퓨터에 들어있는 데이터를 접근할 수 있게 한다. 컴퓨팅을 나눠서 한다.

## MapReduce의 함수

### Map 함수

org.apache.hadoop.mapreduce 패키지의 Mapper 클래스를 상속받아서 map method를 수정(정의)한다.

입력 텍스트 파일에서 라인 단위로 Map 함수가 호출되고, 입력(한 라인)은 (KEY, VALUE-LIST)의 형태이다. 이때 **KEY**는 입력 텍스트 파일에서 맨 앞 문자(character)를 기준으로 Map 함수가 호출된 해당 라인의 첫 번째 문자까지의 **오프셋(offset)**이나 입력 데이터 파일의 이름 등 여러 가지 정보로 이루어져있다. **VALUE**는 **데이터의 한 라인 전체**이다.

### Reduce 함수

Shuffling phase를 거치게 되면 Map 함수가 출력한 KEY-VALUE 페어들을 모아서 처리한 뒤, 각 KEY마다 Reduce 함수가 호출된다.

org.apache.hadoop.mapreduce라는 패키지에 있는 Reducer 클래스를 상속받아서 reduce method를 수정한다.

(KEY, VALUE-LIST)의 형태인 Shuffling phase의 출력을 입력으로 받는데, VALUE-LIST는 Map 함수의 출력에서 같은 KEY를 갖는 (KEY, VALUE) 쌍들의 VALUE 리스트이다.

### Combine 함수

필요에 따라 사용할수도, 사용하지 않을 수도 있다.

Reduce 함수와 유사한 함수인데, 각 머신에서 Map phase에서 Map 함수의 출력 크기를 줄여서 Shuffling phase와 Reduce phase의 비용을 줄여주는 데 사용된다.

# 참고

## 분산 및 병렬처리

[[분산 및 병렬처리 시스템] 분산 처리란?](https://jihyeong-ji99hy99.tistory.com/102)
