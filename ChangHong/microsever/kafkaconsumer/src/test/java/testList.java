import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class testList {
    public static void main(String[] args) {
//        String s1="sssss";
//        String s2= "ssscdfvsdvdfvdvdgvss";
//        List<String> list = new ArrayList<String>() ;
//        list.add(s1);
//        list.add(s2);
//
//        System.out.println(list.size());
//        System.out.println(list.get(1));
//        String s="1599447605341";
//        System.out.println("-----------------------");
//        System.out.println(s);
//        System.out.println((Long.valueOf(s)/3600000)*3600000);
//        String ss=String.valueOf((Long.valueOf(s)/3600000)*3600000);
//        System.out.println(stampToDate(ss));
//        System.out.println(Long.MAX_VALUE);







                // 每个地区的标准时间是不一样的，因为存在时区的差异新，中国属于东八区，因此标准时间是1970年1月1日8时0分0秒
                // 1.使用各种不同的版本构造对象
                Date d1 = new Date();
                System.out.println("d1=" + d1);// 打印系统时间

                Date d2 = new Date(1000);
                System.out.println("d2=" + d2);// 1000毫秒



                // Date d3 = new Date(2008 - 1900, 8 - 1, 8, 20, 8, 8);//
                // 此方法已过时，年份减去1900年，月份减去1，表示当前计算时间
                // System.out.println("d3=" + d3);
                //2.使用取代的方法来构造年月日时分秒
                //2.1获取calendar类型的对象
                Calendar c1 = Calendar.getInstance();
                //2.2调用set（）方法来设置年月日时分秒
                c1.set(2008, 8-1,8,20,8,8);
                //2.3转换成Date类型
                Date d6 = c1.getTime();
                System.out.println("d6 = "+d6);
                //3.调整输出格式




                long msec = d1.getTime();
                System.out.println("距离当前系统时间标准时间的好秒速msec=" + msec);
                d1.setTime(2000);
                System.out.println("d1 = " + d1);// 距离1970 1 1 8 0 2

                // 1.创建SimpleDateFormat类型的对象
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                // 将上述日期信息按照构造方法指定的格式来转换成字符串类型
                String str = sdf.format(d1);
                System.out.println(str);
                //根据字符串转换成日期类型
//                Date d5 = sdf.parse(str);
//                Date d6 = sdf.p
//                System.out.println(d5);

                Map map=new HashMap();
                map.put("1",2);
                String key="1";
//                map.put(key,map.get("1")+1);
                map.get("1");
                System.out.println(map.get("1").getClass().getTypeName());
//                System.out.println(map.get("1")+1);

             System.out.println(map.containsKey("1"));
             Date date = new Date();
             System.out.println(date.toString());
             String temp=date.toString().substring(0,11)+"00:00:00 CST"+date.toString().substring(23);
             Date date2=new Date(temp);
//
//            System.out.println(temp);
//            System.out.println(date2);

        //Date或者String转化为时间戳
//        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time="1970-01-06 11:45:55";
//        Date date = format.parse(time);
//        System.out.print("Format To times:"+date.getTime());

        String date1=getDate(new Date());




//        int arr[]={1,2,3};
//        Map m=new HashMap();
////        m.put(date1,[1,2,3]);
//        arr[0]+=33;
//        System.out.println(arr[0]);
//        m.put(date1,arr);
//
//        System.out.println();
//        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        String str1="2011-5-31 00:00:00";
//        Date d=new Date();
//        try{
//             d=sim.parse(str1);
//            System.out.println(d);
//            System.out.println(d.getClass().getTypeName());
//        }catch(ParseException e){
//            System.out.println(e);
//        }











    }


    public static String getDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
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
