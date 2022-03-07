## 하둡의 구성

Map Phase -> Shuffle -> Reduce

- Map : key, value 생성
- Shuffle : 분산에서 보낼 때 같은 key는 한데 모음
- Reduce : 한데 모은 같은 key의 value로 연산

### Combine
 Map 함수 결과 크기를 줄여 수행 시간 또한 절약
각 머신에서 Reduce 함수 이용하는 것처럼 수행

### 전, 후 함수
Mapper, Reduce는 필요하면 setup(), cleanup() 함수를 사용 가능
- setup() : 시작 전에 호출. 정보 받아와 초기화하는 데에 사용
- cleanup() : 함수 끝에 호출. 결과 출력하는 데에 사용


## WordCount
Map : 각 단어를 key, 1을 value로 하는 쌍을 생성한다.
Shuffle : 같은 key를 가진 값을 한 줄로 모은다. ex) Harry | 1,1,1
Reduce : 각 key 당 개수를 연산한다. ex) Harry | 3

### Partitioner
Map 함수 출력 Entry를 어느 Reducer에 보낼지 정한다.
기본 타입은 key를 해시한 값으로 -> 같은 key는 같은 Reducer로 가야하는 게 중요하다!
또한 분산 처리를 위해 가능한 랜덤하게 Reducer가 선택되어야 한다.

IntWritable의 경우 ) 
원래 partitioner는 홀/짝으로 보냄 -> 전체 sort가 어렵다는 단점(merge 필요)
=> 수정해서 n 이상/이하로 변경 가능 -> 전체 sort 원하는 경우 갖다 붙이기만 하면 됨

```
private static void MyPartitioner extends Partitioner<Key타입, Value타입>{
	@Override
	private int getPartition(Key, Value, int numPartition){
		// 여기서 리턴하는 숫자에 해당하는 Reducer로 이동
	}
}
```

이러한 partitioner를 사용하기 위해서는 Main에
```
job.setPartitionerClass(MyPartitioner.class)
```
또한 추가해야한다.


## Inverted Index
각 단어가 어느 문서 어느 위치에 있는지 정리

- Map : 문서를 읽으면서 단어를 Key로 위치를 Value로 하는 쌍 생성
| 단어 | 위치 |
 ---- | ----
| Harry | Doc1:6 |
| Potter | Doc2:1|

- Shuffle : 같은 Key를 가진 쌍들 한데 모음
| Harry | Doc1:6, Doc4:15 |

- Reducer : 모은 쌍을 output format으로 변환해줌
```
단어 : <tab>파일이름:오프셋,<space>파일이름:오프셋
```

### Long
```
Long : ((LongWritable)Key).get();
```
현재 라인 시작 위치의 byte offset

### 입력파일 알아내기
setup() 에서
```
filename = (FileSplit) context.getInputSplit().getPath().getName()
```

## Matrix Addition 연산
- input : A 0 0 3 (행렬이름, i, j, 행렬[i][j])
- output : 0 0 5 (i,j, 출력[i][j])

이 역시,
Map으로 (좌표, 값)이라는 Key-Value 쌍을 만든 후
Shuffle에서 같은 좌표에 해당하는 값을 모두 모은 후
Reduce에서 같은 좌표의 값들을 모두 더한다.

각 단계에서 좌표별로 순차적으로 일어날 것이라는 보장이 없다. 하지만 i, j로 좌표 표시를 하고 있으므로 이는 상관 없다.
