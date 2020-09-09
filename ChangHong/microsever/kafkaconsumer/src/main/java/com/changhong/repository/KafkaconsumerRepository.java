package com.changhong.repository;

import com.changhong.entity.anomalous;
import com.changhong.entity.statistics;
import org.apache.ibatis.annotations.Insert;

import java.sql.SQLException;
import java.util.List;

public interface KafkaconsumerRepository {
    //定义一些接口方法，主要是向两张表插入数据和查询数据

    /**
     *
     * @param statistics  插入实体类statistics到t_statistics表当中
     * @return
     * @throws SQLException
     */
    public int insertIntoStatistics(statistics statistics) ;

    /**
     *
     * @param anomalous 插入实体类anomalous到t_anomalous表当中
     * @return
     * @throws SQLException
     */
    public int insertIntoAnomalous(anomalous anomalous) ;

    /**
     *
     * @param type  协议类型
     * @param time  时间
     * @return List<statistics>
     */
    public List<statistics> findByTimeAndType(String time, String type);

    /**
     *
     * @param time              时间（按照小时）
     * @param type              协议类型
     * @param total             每小时总数
     * @param abnomal_total     异常总数
     * @param attack_total      攻击总数（即确认异常总数）
     * @return
     */

    int insertIntoStatistics(String time,String type,int total,int abnomal_total,int attack_total);

    /**
     *更新总数total 和abnomal_total,即加1
     * @param time 时间（按照小时）
     * @param type 协议类型
     * @return
     */
    int updateAbnomalTotalByTimeAndType(String time,String type);


    /**
     *  更新总数total，即总数加1
     * @param time 时间（按照小时）
     * @param type 协议类型
     * @return
     */
    int updateTotalByTimeAndType(String time,String type);

}
