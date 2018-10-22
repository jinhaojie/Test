package com.jin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @description
 * @auth jhj
 * @date 18-1-11 下午1:59
 */
public class CreateTopic {

    public static void main(String[] args) {
        //创建topic
        Properties props = new Properties();
        // kafka broker地址
        props.put("bootstrap.servers", "118.25.42.12:9092;118.25.42.12:9093");
        AdminClient adminClient = AdminClient.create(props);
        ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
        // 第三个参数副本个数必须小于等于broker实例个数
        NewTopic newTopic = new NewTopic("topic2", 2, (short) 2);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            System.out.println(result.all().get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
