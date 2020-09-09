package com.changhong.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaConsumer {
    @KafkaListener(topics = "mytopic",groupId ="mygroup")
    public  void obtainMessange(ConsumerRecord<String,String> consumerRecord){
            System.out.println("获取记录：  ");
            System.out.println(consumerRecord.topic());
            System.out.println(consumerRecord.key());
            System.out.println(consumerRecord.value());
            System.out.println(consumerRecord.partition());
            System.out.println(consumerRecord.timestamp());
            System.out.println("-----------------------------------------------------------------------------");
    }

}
