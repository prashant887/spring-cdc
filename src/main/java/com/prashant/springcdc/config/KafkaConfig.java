package com.prashant.springcdc.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic}")
    private  String topicName;

    public KafkaConfig(){
        System.out.println("Construtor Called For "+KafkaConfig.class.getCanonicalName());
    }

    @Bean
    public MyBean configBean(){
        System.out.println("Bean Object Is Created");
        return new MyBean();
    }

    @Bean
    public NewTopic topicCreation(){
        System.out.println("Creating Topic :"+this.topicName);
        return TopicBuilder
                .name(this.topicName)
                .build();

    }

    @Bean
    public NewTopic userTopicCreation(){
        System.out.println("Creating Topic :"+this.topicName);
        return TopicBuilder
                .name("users")
                .build();

    }
}
