package com.prashant.springcdc.services;

import com.prashant.springcdc.payload.User;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class JsonKafkaProducer {
    private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, User> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(User data){

        Message<User> message= MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC,"users")
                .build();

        CompletableFuture<SendResult<String,User>> future=kafkaTemplate.send(message);

        future.whenComplete((result,ex)->{
           if(ex != null) {
              RecordMetadata metadata= result.getRecordMetadata();
               LOGGER.info("Message {} Sent to Topic {} Partition {} Offset {}",
                       data,metadata.topic(),metadata.partition(),metadata.offset());
           }
           else {
               LOGGER.error("Error Sending Message {}",ex.getMessage());
           }
        });
    }
}
