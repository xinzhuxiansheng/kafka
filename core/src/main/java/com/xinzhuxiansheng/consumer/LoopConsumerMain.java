package com.xinzhuxiansheng.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 循环
 */
public class LoopConsumerMain {

    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "10.168.100.16:9092");
        properties.put("client.id","dc");
        properties.setProperty("group.id", "yzhougid20200407021");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "earliest");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("yzhoutp01"));

        System.out.println("Thread id: "+Thread.currentThread().getId());

        while(true) {
            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ofSeconds(10));
            System.out.println("reocrds.count() :"+ records.count());
//            for(ConsumerRecord<String,String> record : records){
//                System.out.println(record.value());
//            }
            //Thread.sleep(32000);
            System.out.println("逻辑结束！");
            Thread.sleep(1000);
        }


    }

}

