package com.changhong.controller;

import com.changhong.entity.anomalous;
import com.changhong.repository.FindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/find")
public class FindHandler {
    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public String index(){
        return "find的端口: "+this.port;
    }

    @Autowired
    private FindRepository findRepository;

//    http://localhost:8010/find/findAll/0/100
    @GetMapping("/findAll/{index}/{limit}")
    public List<anomalous> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findAll(index,limit);
    }

//    http://localhost:8010/find/findByTimeTempAndType/2017-01-05 01:53:56/2019-01-05 01:53:56/http/0/100
    @GetMapping("/findByTimeTempAndType/{start}/{end}/{type}/{index}/{limit}")
    public List<anomalous> findByTimeTempAndType(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findByTimeTempAndType(start, end, type,index,limit);
    }

    //    http://localhost:8010/find/findByType/http/0/100
    @GetMapping("/findByType/{type}/{index}/{limit}")
    public List<anomalous> findByType(@PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findByType(type,index,limit);
    }

    //    http://localhost:8010/find/findByTimeTemp/2017-01-05 01:53:56/2019-01-05 01:53:56/0/100
    @GetMapping("/findByTimeTemp/{start}/{end}/{index}/{limit}")
    public List<anomalous> findByTimeTemp(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findByTimeTemp(start, end,index,limit);
    }

    //    http://localhost:8010/find/findByTime/2019-01-05 01:53:56/0/100
    @GetMapping("/findByTime/{time}/{index}/{limit}")
    public List<anomalous> findByTime(@PathVariable("time") String time, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findByTime(time,index,limit);
    }

//    更新确定状态
    @PutMapping("/update")
    public void update(@RequestBody anomalous anomalous) {
        findRepository.update(anomalous);
    }



/////////////////////////////////////////////////
//    http://localhost:8010/find/findStatisticsAll/0/100
    @GetMapping("/findStatisticsAll/{index}/{limit}")
    public List<anomalous> findStatisticsAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findStatisticsAll(index,limit);
    }

    //    http://localhost:8010/find/findStatisticsByTimeTempAndType/2017-01-05 01:53:56/2019-01-05 01:53:56/http/0/100
    @GetMapping("/findStatisticsByTimeTempAndType/{start}/{end}/{type}/{index}/{limit}")
    public List<anomalous> findStatisticsByTimeTempAndType(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findStatisticsByTimeTempAndType(start, end, type,index,limit);
    }

    //    http://localhost:8010/find/findStatisticsByType/http/0/100
    @GetMapping("/findStatisticsByType/{type}/{index}/{limit}")
    public List<anomalous> findStatisticsByType(@PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findStatisticsByType(type,index,limit);
    }

    //    http://localhost:8010/find/findStatisticsByTimeTemp/2017-01-05 01:53:56/2019-01-05 01:53:56/0/100
    @GetMapping("/findStatisticsByTimeTemp/{start}/{end}/{index}/{limit}")
    public List<anomalous> findStatisticsByTimeTemp(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findStatisticsByTimeTemp(start, end,index,limit);
    }

    //    http://localhost:8010/find/findStatisticsByTime/2019-01-05 01:53:56/0/100
    @GetMapping("/findStatisticsByTime/{time}/{index}/{limit}")
    public List<anomalous> findStatisticsByTime(@PathVariable("time") String time, @PathVariable("index") int index, @PathVariable("limit") int limit){
        return findRepository.findStatisticsByTime(time,index,limit);
    }

}
