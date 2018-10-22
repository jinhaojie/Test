package com.jin;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

/**
 * @description
 * @auth jhj
 * @date 18-1-11 下午2:20
 */
public class MessageConsumer {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", Constants.address);
        props.put("group.id", Constants.groupId);
        // 是否把offsets存储到kafka外面， true标识是
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        consumer.subscribe(Arrays.asList(Constants.topic),new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                //此方法会在消费者停止消费消费后，在重平衡开始前调用。
            }
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
//                此方法在分区分配给消费者后，在消费者开始读取消息前调用。
                //将偏移设置到最开始
                consumer.seekToBeginning(collection);
            }
        });
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                System.out.printf(record.toString());

            }
        }
    }


}
