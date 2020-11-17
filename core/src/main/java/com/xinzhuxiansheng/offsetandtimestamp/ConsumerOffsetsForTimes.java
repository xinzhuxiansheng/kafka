package com.xinzhuxiansheng.offsetandtimestamp;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class ConsumerOffsetsForTimes {

    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("client.id", "dc");
        properties.setProperty("group.id", "yzhougid05140672");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
//        properties.put("max.partition.fetch.bytes",100000);L
//        properties.put("auto.offset.reset","earliest");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        String topic = "resource_pool_article_item_lf";

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<TopicPartition> partitions = new ArrayList<>();
        Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
        for (PartitionInfo partitionInfo : partitionInfos) {
            partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            timestampsToSearch.put(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()), 1590643800000L);
        }
        consumer.assign(partitions);

        OffsetAndTimestamp offsetTimestamp = null;
        Map<TopicPartition, OffsetAndTimestamp> result = consumer.offsetsForTimes(timestampsToSearch);
        for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : result.entrySet()) {
            offsetTimestamp = entry.getValue();
            if (offsetTimestamp != null) {
                int partition = entry.getKey().partition();
                long timestamp = offsetTimestamp.timestamp();
                long offset = offsetTimestamp.offset();
                System.out.println("partition = " + partition +
                        ", time = " + df.format(new Date(timestamp)) +
                        ", offset = " + offset);
                // 设置读取消息的偏移量
                consumer.seek(entry.getKey(), offset);//每个topic的partition都seek到执行的offset
            }

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                for (ConsumerRecord<String, String> record : records) {
                    if(record.value().contains("\"businessLine\":\"0002\"")){
                    System.out.println(record.timestamp() + " , " + record.offset() + " , " + record.value());}
                }
            }
        }
    }

}

