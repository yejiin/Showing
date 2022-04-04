package ssafy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.db.*;
import org.apache.hadoop.mapreduce.lib.input.*;
import org.apache.hadoop.mapreduce.lib.output.*;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;


public class Wordcount {
    public static class DBOutputWritable implements Writable, DBWritable {
        private int performanceId;
        private String word;
        private int weight;

        public DBOutputWritable() {
        }

        public DBOutputWritable(int performanceId, String word, int weight) {
            this.performanceId = performanceId;
            this.word = word;
            this.weight = weight;
        }

        public void write(PreparedStatement statement) throws SQLException {
            statement.setInt(1, performanceId);
            statement.setString(2, word);
            statement.setInt(3, weight);
        }

        public void readFields(ResultSet resultSet) throws SQLException {
        }

        public void write(DataOutput out) throws IOException {
        }

        public void readFields(DataInput in) throws IOException {
        }
    }

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final IntWritable one = new IntWritable(1);
        private int performanceId;

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringBuilder sb = new StringBuilder();

            // 한 줄로 된 파일을 읽어온다.
            StringTokenizer itr = new StringTokenizer(value.toString());
            Text word = new Text();

            while (itr.hasMoreTokens()) {
                String word_tmp = itr.nextToken();

                // 한글자는 카운트하지 않는다.
                if (word_tmp.length() == 1) {
                    continue;
                }

                // performanceId와 단어로 키를 만든다.
                sb.append(performanceId).append("\t").append(word_tmp);
                word.set(sb.toString());

                // reducer로 키와 벨류을 보낸다.
                context.write(word, one);
                sb.setLength(0);
            }
        }

        protected void setup(Context context) throws IOException, InterruptedException {
            String fileName = context.getConfiguration().get("fileName");
            performanceId = Integer.parseInt(fileName);
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, DBOutputWritable, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            // 현재 단어(key)가 나온 횟수를 구한다.
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }

            // 가중치가 1 이하인 단어들은 저장하지 않는다.
            if (sum <= 1) {
                return;
            }

            // key에서 performanceId와 단어를 추출한다.
            String[] keyValues = key.toString().split("\t");
            int performance_id = Integer.parseInt(keyValues[0]);
            String word = keyValues[1];

            // DB write를 위한 객체를 만든다.
            DBOutputWritable wordRecord = new DBOutputWritable(performance_id, word, sum);

            // DB에 저장한다.
            context.write(wordRecord, NullWritable.get());
        }
    }


    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        // DB 연결 설정
        DBConfiguration.configureDB(conf, // Configuration
                "com.mysql.jdbc.Driver", // driver class
                "jdbc:mysql://localhost:3306/test?serverTimezone=Asia/Seoul", // db url
                "ssafy", // user name
                "a301showing" // password
        );

        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();

        Job job = new Job(conf, "word count");

        // jar 파일을 만들 클래스를 지정한다.
        job.setJarByClass(Wordcount.class);

        // hadoop에 map과 reduce 클래스를 알린다.
        job.setMapperClass(TokenizerMapper.class);
        job.setReducerClass(IntSumReducer.class);

        // 기본 출력 key,value 클래스를 정한다.
        job.setOutputKeyClass(DBOutputWritable.class);
        job.setOutputValueClass(NullWritable.class);

        // map의 출력 key,value 클래스를 정한다.
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // reduce의 개수를 정한다.
        job.setNumReduceTasks(10);

        // 입력 폴더를 정한다.
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        DBOutputFormat.setOutput(job, // Job
                "wordcloud", // output table name
                new String[]{"performance_id", "word", "weight"} // table columns
        );

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}