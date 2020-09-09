package com.changhong.kafka;

import com.changhong.entity.anomalous;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPPart;

@Component
public class kafkaProducer {
    @Autowired
    private KafkaTemplate<String ,String> Kafkatemplate;
    public  void sendKafka(anomalous anomalous){
        this.Kafkatemplate.send("mytopic",new Gson().toJson(anomalous));
        System.out.println("-------------p----------------");
    }
}
