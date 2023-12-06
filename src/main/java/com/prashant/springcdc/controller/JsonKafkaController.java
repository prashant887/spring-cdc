package com.prashant.springcdc.controller;

import com.prashant.springcdc.payload.User;
import com.prashant.springcdc.services.JsonKafkaProducer;
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
public class JsonKafkaController {

    private static final Logger LOGGER= LoggerFactory.getLogger(JsonKafkaController.class);


    JsonKafkaProducer jsonKafkaProducer;


    public JsonKafkaController(JsonKafkaProducer jsonKafkaProducer){
        this.jsonKafkaProducer=jsonKafkaProducer;
    }

    @PostMapping(path = "/producer/json",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> publishMessage( @RequestBody User message){
        this.jsonKafkaProducer.sendMessage( message);
        return ResponseEntity.ok("Message Published");

    }

}
