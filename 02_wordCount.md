
💡 **데이터 생성이나 코딩**은 **Linux**에서
맵리듀스 코드와 입력 데이터로 **맵리듀스 알고리즘**은 **HDFS**에서


## Hadoop 디렉토리

- wordcount_test/ :  `맵리듀스 코드 실행을 위한 데이터 디렉토리`
- wordcount_test_out/ :  `맵리듀스 코드 실행 결과를 저장하는 디렉토리`

## 맵리듀스 코드 컴파일

- **ant**
    - Java 개발 환경에서의 표준 빌드 도구
    - src 디렉토리에 있는 것을 다 모아서 컴파일 한 후에 jar을 생성한다.
    - Project 디렉토리에 있는 build.xml 파일에 정의한 대로 수행

## 새로운 맵리듀스 코드 컴파일

- 새로운 소스 코드 파일을 Project/src/ 에 넣는다.
- Driver.java 파일에 아래 코드를 넣는다.
    
    `pgd.addClass(”program name”, class name, “description”);`
    
- Project 디렉토리에서 ant 명령어를 실행한다.

## Linux 하둡 프로젝트 구조

```bash
Project
├── **build.xml** # 맵리듀스 코드 컴파일을 위한 파일
├── **data** # 과제를 위한 데이터
│   ├── data-knn-R.txt
│   ├── data-knn-S.txt
│   ├── equijoin-R-data.txt
│   ├── equijoin-S-data.txt
│   ├── kmeans-data.txt
│   ├── knnjoin-data.txt
│   ├── labelratio-data.txt
│   ├── matadd-data-2x2.txt
│   ├── matmulti-data-2x2.txt
│   ├── setjoin-data.txt
│   ├── topksearch-data.txt
│   ├── vecsimjoin-data.txt
│   └── wordcount-data.txt
├── **datagen** # 과제 데이터를 생성하기 위한 코드
│   ├── KMeansGenerator.java
│   ├── MatrixGenerator.java
│   ├── SetGenerator.java
│   ├── TwoWayJoinGenerator.java
│   └── VecSimJoinGenerator.java
├── set_hadoop_env.sh
├── **src** # 맵리듀스 코드
│   ├── Driver.java # 맵리듀스 코드 컴파일을 위한 파일
│   ├── Wordcount1char.java
│   └── Wordcount.java
├── **ssafy.jar** # 빌드된 jar 파일
└── **template** # 과제를 위한 template
    ├── AllPairJoin.template.java
    ├── AllPairPartitionSelf.template.java
    ├── AllPairPartition.template.java
    ├── CommonItemCount.template.java
    ├── KMeans.template.java
    ├── KNNJoin.template.java
    ├── LabelRatio.template.java
    ├── MatrixAdd.template.java
    ├── MatrixMulti1.template.java
    ├── MatrixMulti2.template.java
    ├── MatrixMulti3.template.java
    ├── MatrixMulti.template.java
    ├── RPJoinSort.template.java
    ├── RPJoin.template.java
    ├── SetSimJoin.template.java
    ├── TopKSearch.template.java
    ├── VecSimJoin.template.java
    └── VecSimTopKJoin.template.java
```

## Wordcount

```java
package ssafy;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class Wordcount {

	/* 
	**Map 함수**
	Object, Text : input key-value pair type (always same (to get a line of input file))
	Text, IntWritable : output key-value pair type
	**<Map 함수 입력 KEY 타입, Map 함수 입력 VALUE 타입, Map 함수 출력 KEY 타입, Map 함수 출력 VALUE 타입>**
	*/
	public static class TokenizerMapper
			extends Mapper<Object,Text,Text,IntWritable> {
		
		// **변수 선언**
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		// map function (Context -> fixed parameter)
		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			// value.toString() : get a line
			StringTokenizer itr = new StringTokenizer(value.toString());
			while ( itr.hasMoreTokens() ) {
				word.set(itr.nextToken());

				// **KEY-VALUE 쌍을 emit**
				context.write(word,one);
			}
		}
	}

	/*
	**Reduce 함수**
	Text, IntWritable : input key type and the value type of input value list
	Text, IntWritable : output key-value pair type
	*/
	public static class IntSumReducer
			extends Reducer<Text,IntWritable,Text,IntWritable> {

		// variables
		private IntWritable result = new IntWritable();

		// **key : a disticnt word**
		// **values :  IntWritable 타입의 리스트**
		public void reduce(Text key, Iterable<IntWritable> values, Context context) 
				throws IOException, InterruptedException {

			int sum = 0;
			for ( IntWritable val : values ) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key,result);
		}
	}

	/* 
	**Main 함수** 
	*/
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration(); // job 수행하기 위한 설정 초기화
		String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();

		if ( otherArgs.length != 2 ) {
			System.err.println("Usage: <in> <out>");
			System.exit(2);
		}

		Job job = new Job(conf,"word count"); // job 작성, 따옴표 안은 설명 쓰면 됨
		job.setJarByClass(Wordcount.class); // job을 수행할 class 선언, 파일명.class, 대소문자 주의

		job.setMapperClass(TokenizerMapper.class); // Map class 선언, 위에서 작성한 class 명
		job.setReducerClass(IntSumReducer.class); // Reduce class 선언
		// job.setCombinerClass(IntSumReducer.class); Combiner class 선언

		// Reduce의 output의 key와 value 타입 지정
		job.setOutputKeyClass(Text.class); // Output key type 선언
		job.setOutputValueClass(IntWritable.class); // Output value type 선언
		// Map과 Reduce의 output 같을 때는 Map output에 대한 지정 생략 가능
		// job.setMapOutputKeyClass(Test.class);
		// job.setMapOutputValueClass(IntWritable.class);

		job.setNumReduceTasks(2); // 동시에 수행되는 reducer 개수

		FileInputFormat.addInputPath(job,new Path(otherArgs[0])); // 입력 데이터가 있는 path
		FileOutputFormat.setOutputPath(job,new Path(otherArgs[1])); // 결과를 출력할 path
		System.exit(job.waitForCompletion(true) ? 0 : 1 ); // 실행
	}
}
```

## 코드 실행하기

- 테스트 데이터를 HDFS에 넣기

```bash
# 하둡의 hdfs에 wordcount_test 디렉토리 생성
hdfs dfs -mkdir wordcount_test

# Linux의 data 디렉토리에 있는 wordcount_data.txt 파일을 hdfs의 wordcount_test 디렉토리에 보냄
hdfs dfs -put data/wordcount_data.txt wordcount_test

# 반드시 맵리듀스 프로그램이 결과를 저장할 디렉토리를 삭제한 후 프로그램 실행하기
hdfs dfs -rm -r wordcount_test_out

# Wordcount MapReduce 알고리즘 코드 실행
# Driver.java에 표시한 대로 wordcount를 써서 Wordcount 맵 리듀스 코드 수행
# Wordcount_test 디렉토리에 들어있는 모든 파일을 맵 함수의 입력으로 사용
hadoop jar ssafy.jar wordcount wordcount_test wordcount_test_out

# 결과 확인 (reducer 개수를 2개 사용하면 아래와 같은 출력 파일 2개가 생성된다)
hdfs dfs -cat wordcount_test_out/part-r-00000 | more
hdfs dfs -cat wordcount_test_out/part-r-00001 | more
```

## Main 함수에서 Mapper나 Reducer에 값을 Broadcast하는 방법

```java
public static void main(String[] args) throws Exception {
	
	...
	job.setOutputValueClass(IntWritable.class); // Output value type 선언

	// 넘겨줄 인자 setting
	Configuration config = job.getConfiguration();
	config.set("name","Shim"); // Name이라는 심볼의 값은 "Shim" 이라는 스트링
	config.setInt("one",1); // one이라는 심볼의 값은 int 타입이고 값은 1
	config.setFloat("point_five", (float) 0.5); // point_five라는 심볼은 float 타입이고 값은 0.5

	FileInputFormat.addInputPath(job, new Path(otherArgs[0])); // 입력 데이터가 있는 path
	FileOutputFormat.setOutputPath(job, new Path(otherArgs[1])); // 결과를 출력할 path
	System.exit(job.waitForCompletion(true) ? 0 : 1); // 실행

}
```
