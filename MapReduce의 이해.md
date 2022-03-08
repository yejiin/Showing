## MapReduce의 이해

​    

맵리듀스는 여러 노드에 태스크를 분배하는 방법으로 각 노드 프로세스 데이터는 가능한 경우, 해당 노드에 저장됩니다. 맵리듀스 태스크는 맵(Map)과 리듀스(Reduce) 총 두단계로 구성됩니다. 간단한 예를 들면 fork-join의 개념을 들 수 있습니다. 큰 작업에 대해 fork로 분할하고 또다시 fork로 분할합니다. 작은 작업을 다시 join하게 됩니다. 하둡에서는 큰 데이터가 들어왔을 때 64MB단위 블럭으로 분할합니다. 각각 블럭에 대한 연산을 합니다. 

​    

구글에는 많은 텍스트 파일이 존재합니다. Word1, Word2, Word3... 텍스트 안에 단어가 몇 번 나왔는가하는 것을 계산하는 작업을 수행하게 됩니다. 즉, 텍스트파일을 64MB 단위로 잘라내어 각 블럭에 대해서 특정 단어가 몇번 출현했는지를 계산하는 것입니다.

​    

이 후, Map 작업을 수행한 각각의 블럭의 결과 정보를 합치는 작업(Reduce)를 수행하게 되는 방식입니다. **하둡에서는 계산시, 큰 파일을 블럭단위로 나누고 모든 블럭은 같은 Map 작업을 수행하고 이후 Reduce 작업을 수행하게 됩니다.**

​    

​    

**Hadoop Components : MapReduce**

------

MapReduce는 Hadoop 클러스터의 데이터를 처리하기 위한 시스템으로 총 2개(Map, Reduce)의 phase로 구성됩니다. Map과 Reduce 사이에는 shuffle과 sort라는 스테이지가 존재합니다. 각 Map task는 전체 데이터 셋에 대해서 별개의 부분에 대한 작업을 수행하게 되는데, 기본적으로 하나의 HDFS block을 대상으로 수행하게 됩니다. 모든 Map 태스크가 종료되면, MapReduce 시스템은 intermediate 데이터를 Reduce phase를 수행할 노드로 분산하여 전송합니다. 

​    

**Distributed File System에서 수행되는 MapReduce 작업이 끝나면 HDFS에 파일이 써지고, MapReduce 작업이 시작할때는 HDFS로 부터 파일을 가져오는 작업이 수행됩니다.** 



![img](https://t1.daumcdn.net/cfile/tistory/2136A84B59381A8428)

​    

​    

​    

**JobTracker**

------

맵리듀스 Job들은 JobTracker라는 소프트웨어 데몬에 의해 제어됩니다. JobTracker들은 마스터 노드에 존재하면 다음과 같은 역할을 수행합니다.

​    

> 클라이언트는 맵리듀스 Job을 JobTracker에게 보냅니다.
>
> JobTracker는 클러스터의 다른 노드들에게 맵과 리듀스 Task를 할당합니다.
>
> 이 노드들은 TaskTracker라는 소프트웨어 데몬에 의해 각각 실행됩니다.
>
> TaskTracker는 실제로 맵 또는 리듀스 Task를 인스턴스화하고, 진행 상황을 JobTracker에게 보고할 책임이 있습니다.

​    

Job은 'Full Program' 즉, 전체 프로그램을 의미합니다. 데이터 집합을 통해 Mapper와 Reducer를 전체 실행합니다. Task는 데이터 조각을 통해 하나의 맵퍼 또는 리듀서를 실행하게 됩니다.

​    

Task attempt는 Task를 실행하기 위한 특정 인스턴스를 의미하는데, 적어도 Task가 존재하기때문에 많은 Task attempt가 있을 것입니다. 만약 Task attempt가 실패하면, JobTracker에 의해서 다른 Task Attempt가 시작될 것입니다. Speculative execution(나중에 참조)는 또한 완료된 Task들 보다 더 많은 Task를 시도할 수 있습니다.

​    

​    

**Mapper**

------

하둡은 네트워크 트래픽을 방지하기 위해, 메타 데이터의 일부분을 가지고 노드에서 처리합니다. 동시에 실행되는 여러 맵퍼는 각각 입력 데이터의 일부를 처리하는 단계를 포함합니다. 맵퍼는 Key/Value 상의 형태로 데이터를 읽습니다.

​    

Mapper의 0개 또는 그 이상의 Key/Value 쌍을 출력합니다.

```
map(in_key, in_value) -> (inter_key, inter_value) list
```

​    

![img](https://t1.daumcdn.net/cfile/tistory/253BEA3F59381DC632)



​    

각각의 map, reduce를 어떤 PC에서 수행할지는 Job Tracker에서 결정합니다. Mapper에서 중간 결과가 나오면 다시 그 중간 결과를 Shuffling 하게 됩니다. Shuffling한 결과를 Reducer로 전달하게 됩니다. Reducer는 실제 어떤 상황에서 사용될까요? **예를 들자면, 회사에서 어떤 데이터에 대한 월별 통계를 내고 싶습니다. 그렇다면 reducer 결과로 12개의 결과가 나오면 각각의 reducer 결과는 각 월별 통계 자료를 갖고 있게 되는 것입니다. 만약 일별 통계를 내고 싶다면, 총 7개(월, 화, 수, 목, 금, 토, 일)의 결과만 나오면 됩니다. Mapper가 수집한 결과를 Shuffling하여 우리가 원하는 결과를 얻어내는 과정입니다.** 

​    

Map 함수의 input은 key, value가 필요합니다. ouput은 key, value의 리스트가 나옵니다. Reduce 역시 입력, 결과값으로 key, value 쌍을 갖습니다. key, value 쌍을 선호하는 이유는 key값을 이용한 sorting, grouping 작업이 용이하기 때문입니다. 이러한 이유로 빅데이터를 구성하는 기본데이터 구조가 key, value 쌍이 된 것입니다. Spark에서도 기본적인 자료구조형은 key, value 형태입니다. 

​    

Mapper는 입력값의 Key를 사용하기도 하지만, 완전히 무시하기도 합니다.

​    

> 예를 들어, 표준 패턴은 한번에 파일의 라인을 읽습니다. 
>
>  \- Key는 라인이 시작되는 파일에 Byte Offset이다.
>
>  \- Value는 라인 자체의 내용이다.
>
>  \- 일반적으로 Key는 관련이 없는 것으로 간주한다.

​    

만약 맵퍼가 무언가를 출력하는 경우, 출력형태는 Key/Value 쌍이어야 합니다.

​    

​    

**Mapper Example**

​    

다음은 대문자로 바꾸기(pseudo-code) Mapper 함수의 예제입니다.

```
Let map(k, v) =
		emit(k.toUpper(), v.toUpper())

('foo', 'bar') -> [('FOO', 'BAR')]
('foo', 'other') -> [('FOO', 'OTHER')]
('baz', 'more data') -> [('BAZ', 'MORE DATA')]
```

입력된 단어를 철자로 분리하여 출력하는 Mapper 함수 예제입니다.

​    

```
Let map(k, v) =
		foreach char c in v:
				emit(k, c)

('foo', 'bar') -> [('foo', 'b'), ('foo', 'a'), ('foo', 'r')]
('baz', 'other') -> [('baz', 'o'), ('baz', 't'), ('baz', 'h'), ('baz', 'e'), ('baz', 'r')]
```

입력값의 Value가 소수인 경우에만 Key/Value 상을 출력하는 예제입니다.

​    

```
Let map(k, v) =
		if (isPrime(v)) then emit(k, v)

('foo', 7) -> [('foo', 7)]
('baz', 10) -> nothing
```

맵퍼의 출력 key값이 입력데이터의 key값과 동일할 필요는 없다. 단어의 길이를 키로 출력하는 예제

​    

```
Let map(k, v) =
		emit(v.length(), v)

('foo', 'bar') -> [(3, 'bar')]
('baz', 'other') -> [(5, 'other')]
('foo', 'abracadabra') -> [(11, 'abracadabra')]
```

​    

**Reducer**

------

맵 단계가 끝나면, **중간 단계의 키 값을 기반으로 중간 값(intermediate values)를 리스트 형태로 조합하게 됩니다. 그 뒤 리스트는 리듀서로 전달이 되는데** 수행 절차는 아래와 같습니다.

​    

> 하나의 리듀서나 여러개의 리듀서가 존재할 것이다. (job 설정에서 이 부분은 정의되어 있다)
>
> 중간 키와 연관되어 있는 모든 값은 같은 리듀서로 보내진다.
>
> 중간 키와 그 값들의 리스트들은 키 순서대로 정렬(해쉬값이 같은건 같은 리듀서)되어 리듀서로 보내진다.
>
> 이 단계는 '셔플(Shuffle)과 정렬(Sort)'라고 알려져 있다. (Expensive한 과정)

​    

리듀서의 output은 O이거나 key/value의 쌍이며, 이 결과들은 HDFS에 저장됩니다. 실제로 리듀서는 보통 input키에 대해서 하나의 key/value 쌍으로 배출되어 쓰여집니다.

​    

​    

**Reducer Example**

​    

각 중간 키 값과 관련있는 모든 값들의 합을 구하는 예입니다

```
let reduce(k, vals) =
	sum = 0
	foreach int i in vals:
		sum += i
	emit(k, sum)

('bar', [9, 3, -17, 44]) -> ('bar', 39)
('foo', [123, 100, 77]) -> ('foo', 300)
```

Identity 리듀서는 매우 흔합 Reducer의 예입니다.

​    

```
let reduce(k, vals) =
	foreach v in vals:
		emit(k, v)

('bar', [123, 100, 77]) -> ('bar', 123), ('bar', 100), ('bar', 77)
('foo', [9, 3, -17, 44]) -> ('foo', 9), ('foo', 3), ('foo', -17), ('foo', 44)
```

​    

출처: https://12bme.tistory.com/154 [길은 가면, 뒤에 있다.]