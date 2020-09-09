package com.changhong.service;

import com.changhong.entity.statistics;
import com.changhong.repository.KafkaconsumerRepository;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.changhong.entity.anomalous;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class KafkaService {
    //处理相关业务，主要是统计总数和插入错误数据
    @Autowired
    private KafkaconsumerRepository kafkaConsumerRepository;

    @Autowired
    private anomalous anomalous;
    //传入json数据，并将错误的数据统计出来插入到t_anomalous表当中


    /**
     *  保存Modbus的异常数据数据
     * @param jsonStr
     */
    public  void saveModbusAnomalous(String jsonStr){
        jsonStr="["+jsonStr+"]";
        //解析json字符创数组
        Object obj=JSONValue.parse(jsonStr);
        JSONArray array=(JSONArray)obj;
        JSONObject obj1=(JSONObject)array.get(0);

        JSONObject raw=(JSONObject)obj1.get("raw");
        JSONObject layers=(JSONObject)raw.get("layers");
        JSONObject modelResult=(JSONObject)obj1.get("modelResult");



        // time
        String timestamp=raw.get("timestamp").toString();
//        String time= stampToDate(timestamp);
        Date time = new Date(Long.valueOf(timestamp));

        //type
        String type="modbus";

        //source
        JSONArray ip_src = (JSONArray)layers.get("ip_src");
        String source=ip_src.get(0).toString();

        //target
        JSONArray  ip_dst= (JSONArray)layers.get("ip_dst");
        String target=ip_dst.get(0).toString();

        //port_t
        JSONArray  tcp_dstport= (JSONArray)layers.get("tcp_dstport");
        String port_t=tcp_dstport.get(0).toString();

        //port_s
        JSONArray  tcp_srcport= (JSONArray)layers.get("tcp_srcport");
        String port_s=tcp_srcport.get(0).toString();

        // details
        String details=jsonStr;


        // is_abnomal
        Boolean is_abnomal=(Boolean)modelResult.get("outputs");

        System.out.println("----------------------------");
        System.out.println(time.toString()+"\n"+type+"\n"+source+"\n"+target+"\n"+port_s+"\n"+port_t+"\n"+details+"\n"+is_abnomal.toString());

        String timestampForHour=String.valueOf((Long.valueOf(timestamp)/3600000)*3600000);
        Date timeForHour=new Date(timestampForHour);


        //判断异常数据
        if(is_abnomal==true){

            anomalous.setTime(time);
            anomalous.setType(type);
            anomalous.setSource(source);
            anomalous.setTarget(target);
            anomalous.setPort_s(port_s);
            anomalous.setPort_t(port_t);
            anomalous.setDetails(details);
            anomalous.setIs_abnormal(is_abnomal);

            //将异常数据插入t_anomalous表中
            kafkaConsumerRepository.insertIntoAnomalous(anomalous);


            //针对于t_statistics表进行操作的时候需要对timestamp进行处理，按照小时的颗粒度来进行操作
            //更新t_statistics每一小时异常值，即在原来的基础上面异常值对应+1

            List<statistics> List=kafkaConsumerRepository.findByTimeAndType(stampToDate(timestamp),type);

            //这里需要考虑第一次的问题，插入的数据否则有问题
            //首先查询出来某一个值，存在则在原来的基础上面+1，并更新原来的值。不存在则插入一条新数据
            if(List.size()>0){
            //即已经存在对应的值，只需要更新t_statistics的abnomal_total字段和total加1即可
                kafkaConsumerRepository.updateAbnomalTotalByTimeAndType(timestampForHour,type);

            }else{
                //不存在，因此需要向t_statistics插入数据。此时总数据和异常数据均为1条，攻击数据为0条（即确认数据）
//                statistics.setTime(timeForHour);
//                statistics.setType(type);
//                statistics.setTotal(1);
//                statistics.setAbnomaltotal(1);
//                statistics.setAttacktotal(0);
                kafkaConsumerRepository.insertIntoStatistics(stampToDate(timestampForHour),type,1,1,0);


            }


        }else{
            kafkaConsumerRepository.updateTotalByTimeAndType(timestampForHour,type);
        }
        //更新t_statistics每一小时总条数，即在原来的基础上面总值对应+1

    }



    /**
     *
     * @param s 时间戳（按小时和按照毫秒）
     * @return "yyyy-MM-dd HH:mm:ss"的格式
     */
    public static String stampToDate(String s){
        s=s+"000";//秒转成毫秒
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        System.out.println("date:"+date);
        res = simpleDateFormat.format(date);
        return res;

    }
}
