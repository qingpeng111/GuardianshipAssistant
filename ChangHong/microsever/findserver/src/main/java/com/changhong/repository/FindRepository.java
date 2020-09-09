package com.changhong.repository;

import com.changhong.entity.anomalous;
import com.changhong.entity.statistics;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface FindRepository {
    public List<anomalous> findAnomalousTraffic(String start,
                                                String end,
                                                String type,
                                                String source,
                                                String port_s,
                                                int is_abnormal,
                                                int index,int limit);


    public List<statistics>  findStatistics(String type);

//    更新is_abnomal需要对两张表操作
    int updateIsabnomalByTypeAndTime(String type,String time);
    int updateAttackTotalByTimeAndType(String type,String time);

//    /**
//     *  分页查询所有的数据
//     * @param index 开始下标
//     * @param limit 长度
//     * @return List<anomalous>
//     */
//    public List<anomalous> findAll(int index,int limit);
//
//    /**
//     * 根据时间段、类型分页查询所有的数据
//     * @param start 起始时间
//     * @param end   结束时间
//     * @param type  类型
//     * @param index 起始位置
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous>  findByTimeTempAndType(String start, String end, String type, int index,int limit);
//
//    /**
//     * 根据类型分页查询所有的数据
//     * @param type  类型
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return List<anomalous>
//     */
//    public List<anomalous> findByType(String type, int index,int limit);
//
//
//    /**
//     *根据时间段分页查询所有的数据
//     * @param start 起始时间
//     * @param end   结束时间
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous> findByTimeTemp(String start,String end,int index,int limit);
//
//    /**
//     *根据时间分页查询所有的数据
//     * @param time  时间
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous> findByTime(String time,int index,int limit);
//
//    //更新该状态时候确定
//    void update(anomalous anomalous);
//
//
//
//    /**
//     *  分页查询所有的统计数据
//     * @param index 开始下标
//     * @param limit 长度
//     * @return List<anomalous>
//     */
//    public List<anomalous> findStatisticsAll(int index,int limit);
//
//    /**
//     * 根据时间段、类型分页查询所有的统计数据
//     * @param start 起始时间
//     * @param end   结束时间
//     * @param type  类型
//     * @param index 起始位置
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous>  findStatisticsByTimeTempAndType(String start, String end, String type, int index,int limit);
//
//    /**
//     * 根据类型分页查询所有的统计数据
//     * @param type  类型
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return List<anomalous>
//     */
//    public List<anomalous> findStatisticsByType(String type, int index,int limit);
//
//
//    /**
//     *根据时间段分页查询所有的统计数据
//     * @param start 起始时间
//     * @param end   结束时间
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous> findStatisticsByTimeTemp(String start,String end,int index,int limit);
//
//    /**
//     *根据时间分页查询所有的统计数据
//     * @param time  时间
//     * @param index 起始页
//     * @param limit 每页数量
//     * @return  List<anomalous>
//     */
//    public List<anomalous> findStatisticsByTime(String time,int index,int limit);


}

