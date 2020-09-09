package com.changhong.controller;

import com.changhong.entity.anomalous;
import com.changhong.entity.statistics;
import com.changhong.repository.FindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@ComponentScan(basePackages = {"com.changhong.entity","com.changhong.repository"})
@RestController
@RequestMapping("/find")
public class FindHandler {
//    @Value("${server.port}")
//    private String port;
//
//    @GetMapping("/index")
//    public String index(){
//        return "find的端口: "+this.port;
//    }

    @Autowired
    private FindRepository findRepository;



    ////    http://localhost:8010/find/findByTimeTempAndType/2011-01-05 01:53:56/2019-01-05 01:53:56/modbus/192.168.71.1/8280/1/0/100
//    @GetMapping("/findByTimeTempAndType/{start}/{end}/{type}/{source}/{port_s}/{is_abnormal}/{index}/{limit}")
    //    http://localhost:8010/find/findByTimeTempAndType?start=2011-01-05 01:53:56&end=2019-01-05 01:53:56&type=modbus&source=192.168.71.1&port_s=8280&is_abnormal=1&index=0&limit=100
    @RequestMapping(value = "/findByTimeTempAndType",method=RequestMethod.GET)
    public List<anomalous> findAnomalousTraffic(@RequestParam(name = "start") String start,
                                                @RequestParam(name = "end") String end,
                                                @RequestParam(name = "type") String type,
                                                @RequestParam(name = "source") String source,
                                                @RequestParam(name = "port_s") String port_s,
                                                @RequestParam(name = "is_abnormal") int is_abnormal,
                                                @RequestParam(name = "index") int index,
                                                @RequestParam(name = "limit") int limit){
        return findRepository.findAnomalousTraffic(  start,
                                                     end,
                                                     type,
                                                     source,
                                                     port_s,
                                                     is_abnormal,
                                                     index, limit);
    }



    //http://localhost:8010/find/findStatisticsByTimetypeAndTraffictype/全部/S7
    //http://localhost:8010/find/findStatisticsByTimetypeAndTraffictype？timetype=all&traffictype=S7
    /**
     *根据时间类型、流量类型查询统计数据
     * @param timetype  时间类型（全部/24小时/7天/1个月）
     * @param traffictype 流量类型（总流量/异常流量/攻击流量）
     * @return  List<anomalous>
     */
//    @GetMapping("/findStatisticsByTimetypeAndTraffictype/{timetype}/{traffictype}")
    @RequestMapping (value = "/findStatisticsByTimetypeAndTraffictype",method=RequestMethod.GET)
    public List<statistics> findStatisticsByTimetypeAndTraffictype(@RequestParam(name = "timetype") String timetype,
                                                                   @RequestParam(name = "traffictype")  String traffictype){

        List<statistics> list = findRepository.findStatistics(traffictype);
        System.out.println("timetype:"+timetype+"   "+timetype.getClass().getTypeName());

        List<statistics> res=new LinkedList<statistics>();

        System.out.println("timetype:"+timetype+"   "+timetype.getClass().getTypeName());
        //分类统计sout
        System.out.println("timetype==\"all\""+timetype=="all");

        if(timetype.equals("all")){
            res=list;
        }else if (timetype.equals("day")){

            Map<String ,int[]> map=new HashMap();//用于存放
            statistics s1=null;
            for(int i=0;i<list.size();i++){
                s1=list.get(i);

                String date=getDate(s1.getTime());
                System.out.println("date: "+date);

                if(map.containsKey(date)){//已经存在
                    int arr[]=(int[]) map.get(date);
                    arr[0]+=s1.getTotal();
                    arr[1]+=s1.getAbnomaltotal();
                    arr[2]+=s1.getAttacktotal();


                    map.put(date,arr);
                }else{//不存在
                    int arr[]={s1.getTotal(),s1.getAbnomaltotal(),s1.getAttacktotal()};
                    map.put(date,arr);
                }
            }


            //将map里面的封装成statistics对象放到 res里面
            System.out.println("map.size() :  "+map.size());
            for(String date: map.keySet()){
                statistics statistics=new statistics();
                SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String str1=date+" 00:00:00";
                Date d=new Date();
                try{
                    d=sim.parse(str1);
                }catch(ParseException e){
                    System.out.println("时间格式解析出错："+e);
                }

                statistics.setTime(d);
                statistics.setTotal(map.get(date)[0]);
                statistics.setAbnomaltotal(map.get(date)[1]);
                statistics.setAttacktotal(map.get(date)[2]);
                statistics.setType(traffictype);


                res.add(statistics);
            }


        }else if (timetype.equals("week")){

        }else if (timetype.equals("month")){

            Map<String ,int[]> map=new HashMap();//用于存放
            statistics s1=null;
            for(int i=0;i<list.size();i++){
                s1=list.get(i);

                String date=getMonth(s1.getTime());
                System.out.println("date: "+date);

                if(map.containsKey(date)){//已经存在
                    int arr[]=(int[]) map.get(date);
                    arr[0]+=s1.getTotal();
                    arr[1]+=s1.getAbnomaltotal();
                    arr[2]+=s1.getAttacktotal();


                    map.put(date,arr);
                }else{//不存在
                    int arr[]={s1.getTotal(),s1.getAbnomaltotal(),s1.getAttacktotal()};
                    map.put(date,arr);
                }
            }


            //将map里面的封装成statistics对象放到 res里面
            System.out.println("map.size() :  "+map.size());
            for(String date: map.keySet()){
                statistics statistics=new statistics();
                SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String str1=date+"-01 00:00:00";
                Date d=new Date();
                try{
                    d=sim.parse(str1);
                }catch(ParseException e){
                    System.out.println("时间格式解析出错："+e);
                }

                statistics.setTime(d);
                statistics.setTotal(map.get(date)[0]);
                statistics.setAbnomaltotal(map.get(date)[1]);
                statistics.setAttacktotal(map.get(date)[2]);
                statistics.setType(traffictype);


                res.add(statistics);
            }


        }


        return res;
    }




    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
    public static String getMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(date);
        return dateString;
    }

//
//
////    http://localhost:8010/find/findAll/0/100
//    @GetMapping("/findAll/{index}/{limit}")
//    public List<anomalous> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findAll(index,limit);
//    }
//
////    http://localhost:8010/find/findByTimeTempAndType/2017-01-05 01:53:56/2019-01-05 01:53:56/http/0/100
//    @GetMapping("/findByTimeTempAndType/{start}/{end}/{type}/{index}/{limit}")
//    public List<anomalous> findByTimeTempAndType(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findByTimeTempAndType(start, end, type,index,limit);
//    }
//
//    //    http://localhost:8010/find/findByType/http/0/100
//    @GetMapping("/findByType/{type}/{index}/{limit}")
//    public List<anomalous> findByType(@PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findByType(type,index,limit);
//    }
//
//    //    http://localhost:8010/find/findByTimeTemp/2017-01-05 01:53:56/2019-01-05 01:53:56/0/100
//    @GetMapping("/findByTimeTemp/{start}/{end}/{index}/{limit}")
//    public List<anomalous> findByTimeTemp(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findByTimeTemp(start, end,index,limit);
//    }
//
//    //    http://localhost:8010/find/findByTime/2019-01-05 01:53:56/0/100
//    @GetMapping("/findByTime/{time}/{index}/{limit}")
//    public List<anomalous> findByTime(@PathVariable("time") String time, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findByTime(time,index,limit);
//    }
//
////    更新确定状态
//    @PutMapping("/update")
//    public void update(@RequestBody anomalous anomalous) {
//        findRepository.update(anomalous);
//    }
//
//
//
///////////////////////////////////////////////////
////    http://localhost:8010/find/findStatisticsAll/0/100
//    @GetMapping("/findStatisticsAll/{index}/{limit}")
//    public List<anomalous> findStatisticsAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findStatisticsAll(index,limit);
//    }
//
//    //    http://localhost:8010/find/findStatisticsByTimeTempAndType/2017-01-05 01:53:56/2019-01-05 01:53:56/http/0/100
//    @GetMapping("/findStatisticsByTimeTempAndType/{start}/{end}/{type}/{index}/{limit}")
//    public List<anomalous> findStatisticsByTimeTempAndType(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findStatisticsByTimeTempAndType(start, end, type,index,limit);
//    }
//
//    //    http://localhost:8010/find/findStatisticsByType/http/0/100
//    @GetMapping("/findStatisticsByType/{type}/{index}/{limit}")
//    public List<anomalous> findStatisticsByType(@PathVariable("type") String type, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findStatisticsByType(type,index,limit);
//    }
//
//    //    http://localhost:8010/find/findStatisticsByTimeTemp/2017-01-05 01:53:56/2019-01-05 01:53:56/0/100
//    @GetMapping("/findStatisticsByTimeTemp/{start}/{end}/{index}/{limit}")
//    public List<anomalous> findStatisticsByTimeTemp(@PathVariable("start") String start, @PathVariable("end") String end, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findStatisticsByTimeTemp(start, end,index,limit);
//    }
//
//    //    http://localhost:8010/find/findStatisticsByTime/2019-01-05 01:53:56/0/100
//    @GetMapping("/findStatisticsByTime/{time}/{index}/{limit}")
//    public List<anomalous> findStatisticsByTime(@PathVariable("time") String time, @PathVariable("index") int index, @PathVariable("limit") int limit){
//        return findRepository.findStatisticsByTime(time,index,limit);
//    }

}
