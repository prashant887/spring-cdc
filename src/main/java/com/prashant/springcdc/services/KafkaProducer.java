package com.prashant.springcdc.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer {

    //@Autowired

    private static Logger LOGGER= LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String,Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }



    public void publishMessage(String topic,String message){
        System.out.println("Message Published :"+message);

        CompletableFuture
                <SendResult<String, Object>> future= this.kafkaTemplate.send(topic,message);
        future.whenComplete((result,ex)->{
            if (ex==null){
                LOGGER.info("Sent Message {} With: \n Offset {} \nTopic {} \nPartition {} \nTimestamp {}"
                        ,message,result.getRecordMetadata().offset(),
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().timestamp()
                );
            }
            else {
                LOGGER.error("Unable to Send Message Due to {} ",ex.getMessage());
            }
        });



    }
}
