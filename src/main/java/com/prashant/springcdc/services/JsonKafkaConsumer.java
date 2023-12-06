package com.prashant.springcdc.services;


import com.prashant.springcdc.payload.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    @Value("${spring.kafka.topic}")
    private  String topicName;

    @Value("spring.kafka.consumer.group-id")
    private String groupName;



    @KafkaListener(topics = "users",groupId = "orders_group")
    public void consumeUser(User message){
        System.out.println("Message Recived :"+message);

    }
}
