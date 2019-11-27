package kafka_debug.tools;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ProducerMain {
    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "vm01.com:9093");
        properties.put("retries", 2); // 发送失败的最大尝试次数
        properties.put("batch.size", "1048576"); // 1048576
        properties.put("compression.type", "gzip");
        properties.put("linger.ms", "5000");
        properties.put("buffer.memory", "67108864");// 67108864
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        Long i = 0L;
        while(true){
            String data = i+"\\t2\\tAndroid\\t869830039316690\\thuawei\\t0\\tarmeabi-v7a\\t9\\t10.1.5\\tHWI-AL00\\tHUAWEI";

            producer.send(new ProducerRecord<String, String>("yzhoutest01",data), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(null == recordMetadata){
                        e.printStackTrace();
                    }
                }
            });
            Thread.currentThread().sleep(1000L);
            System.out.println(data);
            i++;
        }

    }
}
