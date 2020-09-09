import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.changhong.repository.KafkaconsumerRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.lang.Boolean;






@RestController
public class testKafkaData {
    public static void saveModbusAnomalous(String jsonStr){
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


    }


    public static anomalous anomalous;
    public static void main(String[] args) {
        String jsonStr="{\"raw\":{\"timestamp\":\"1599447605341\",\"layers\":{\"frame_time_epoch\":[\"1599447605.341735107\"],\"eth_dst\":[\"cc:64:a6:b5:b3:52\"],\"eth_src\":[\"00:90:e8:78:21:a5\"],\"ip_dst\":[\"10.52.105.171\"],\"ip_src\":[\"10.52.78.191\"],\"ip_ttl\":[\"62\"],\"tcp_dstport\":[\"502\"],\"tcp_srcport\":[\"54444\"],\"tcp_payload\":[\"00:00:00:00:00:06:01:03:03:e8:00:0a\"]}},\"modelCode\":200,\"modelResult\":{\"outputs\":false}}";

//        saveModbusAnomalous(jsonStr);

        // 初始化 Date 对象
//        anomalous anomalous = new anomalous();

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

//        anomalous.setTime(time);
//        anomalous.setType(type);
//        anomalous.setSource(source);
//        anomalous.setTarget(target);
//        anomalous.setPort_s(port_s);
//        anomalous.setPort_t(port_t);
//        anomalous.setDetails(details);
//        anomalous.setIs_abnormal(is_abnomal);


        System.out.println(anomalous);
        System.out.println(timestamp);
        System.out.println(stampToDate(timestamp));

    }



    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
