/*
package com.xinzhuxiansheng.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerMain {

    public static void main(String[] args) throws InterruptedException {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("client.id","dc");
        properties.put("retries", 2); // 发送失败的最大尝试次数
        properties.put("batch.size", "1048576"); // 1MB
        properties.put("compression.type", "snappy");
        properties.put("linger.ms", "5"); // 最长延迟5秒必须发送
        properties.put("buffer.memory", "67108864");// 64MB
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        Long i = 0L;
        while(true){
            String data = "evt\\t1588935849979\\tauto_android\\toperation_req_show\\toperation_req\\t1588935848446\\tnull\\t3e434f47_d9d2_4cb3_8084_10e96b2d6f2a\\te729a25e38063f5ad859bccda2577c08\\thuawei\\t10.8.0\\tnull\\tnull\\tnull\\tnull\\tnull\\tnull\\tnull\\tnull\\tnull\\tnull\\t117.136.7.104\\t46.819024\\t130.346348\\t1588935848696\\t05491860_5037_4b42_aea6_846de81b6d9e_184809527141070\\tnull\\t7.4.10\\t1cf6b70e5a7216ce\\t74:60:FA:49:FE:39\\t0\\t0\\tandroid\\t10\\tzh\\t1080x2265\\tHUAWEI ELE-AL00\\t13\\t0\\t46002\\t\\t\\tAsia/Shanghai\\tnull\\t3\\t0.44\\t1\\t0\\t\\t\\t\\tclicklog\\tnull\\t0\\tapp_open_ad_pv\\t\\t\\t\\t\\t\\taritlce_newest_article_list_pv\\t\\tc6af97b1_1bd1_4943_aeda_f2b2750a7b49_184806656303050\\tapp\\t2667\\t\\t{\"autoid\":\"7b40370fa2c4780efe34ab9259cf63a2\",\"uuid\":\"d_3e434f47_d9d2_4cb3_8084_10e96b2d6f2a\",\"bdlat\":\"46.81902436210853\",\"bdlot\":\"130.3463478986643\",\"province_id\":\"230000\",\"city_id\":\"230800\",\"district_id\":\"0\",\"address\":\"中国黑龙江省佳木斯市佳木斯市市辖区建设街道志同胡同\",\"session_id_number\":\"2667\",\"faketime\":\"1588935848446\",\"antifake\":\"a0f283657e21cd2713bbd088fbdc44bf\"}\\t{\"ev_content\":\"{\\\"iscache\\\":0,\\\"showcount\\\":0}\",\"operation_type\":\"4\",\"plan_content\":\"4||ZXB-014||4|3|S|1|40|1096|00100||6||699999-992733||unknown||unknown||81-40-4867-37812||0||0||b96680478c6f71202043f64535d36917a558f43735721414\",\"user_id\":\"0\",\"campaignId\":\"35680\",\"operation_id\":\"ZXB-014\",\"plan_id\":\"10088914\"}\\t0";
            producer.send(new ProducerRecord<String, String>("yzhoutp01",data), new Callback() {
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
*/
