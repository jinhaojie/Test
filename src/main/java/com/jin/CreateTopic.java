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
        props.put("bootstrap.servers", Constants.address);
        AdminClient adminClient = AdminClient.create(props);
        ArrayList<NewTopic> topics = new ArrayList<NewTopic>();
        NewTopic newTopic = new NewTopic(Constants.topic, 1, (short) 1);
        topics.add(newTopic);
        CreateTopicsResult result = adminClient.createTopics(topics);
        try {
            result.all().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
