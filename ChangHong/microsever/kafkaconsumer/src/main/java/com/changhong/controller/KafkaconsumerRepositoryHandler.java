package com.changhong.controller;


import com.changhong.entity.anomalous;
import com.changhong.kafka.kafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.ServerNotActiveException;
import java.util.Date;

@RestController
@RequestMapping(value = "/kafka",produces = "application/json;charset=UTF-8")
public class KafkaconsumerRepositoryHandler {
//    @Autowired
////    private kafkaProducer kafkaProducer;
//
//    @RequestMapping(value = "/message",method =RequestMethod.GET)
//    public anomalous sendKafkaMessage(@RequestParam(name = "key_a")int key_a,
//                                      @RequestParam(name = "time") Date time,
//                                      @RequestParam(name = "type")String type,
//                                      @RequestParam(name = "source") String  source,
//                                      @RequestParam(name = "source") String  target,
//                                      @RequestParam(name = "source")String  port_s,
//                                      @RequestParam(name = "source")String  port_t){
////sout
//        System.out.println("sendKafkaMessage");
//        anomalous anomalous=new anomalous();
//        anomalous.setKey_a(key_a);
//        anomalous.setTime(time);
//        anomalous.setType(type);
//        anomalous.setSource(source);
//        anomalous.setTarget(target);
//        anomalous.setPort_s(port_s);
//        anomalous.setPort_t(port_t);
//        this.kafkaProducer.sendKafka(anomalous);
//        return anomalous;
//    }

}
