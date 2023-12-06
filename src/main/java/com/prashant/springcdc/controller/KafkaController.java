package com.prashant.springcdc.controller;

import com.prashant.springcdc.services.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    private static final Logger LOGGER= LoggerFactory.getLogger(KafkaController.class);


    KafkaProducer kafkaProducer;


    public KafkaController(KafkaProducer kafkaProducer){
        this.kafkaProducer=kafkaProducer;
    }

    @PostMapping(path = "/producer/{topic}")
    ResponseEntity<String> publishMessage(@PathVariable("topic") String topic, @RequestBody String message){
        this.kafkaProducer.publishMessage(topic, message);
        return ResponseEntity.ok("Message Published");

    }

}
