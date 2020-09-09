package com.changhong.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class statistics {
///    private  int    key_s;
    private  Date   time;
    private  String type;
    private  int    total;
    private  int    abnomaltotal;
    private  int    attacktotal;

    public statistics() {
    }

    public statistics(Date time, String type, int total, int abnomaltotal, int attacktotal) {
        this.time = time;
        this.type = type;
        this.total = total;
        this.abnomaltotal = abnomaltotal;
        this.attacktotal = attacktotal;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAbnomaltotal() {
        return abnomaltotal;
    }

    public void setAbnomaltotal(int abnomaltotal) {
        this.abnomaltotal = abnomaltotal;
    }

    public int getAttacktotal() {
        return attacktotal;
    }

    public void setAttacktotal(int attacktotal) {
        this.attacktotal = attacktotal;
    }

    @Override
    public String toString() {
        return "statistics{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", total=" + total +
                ", abnomaltotal=" + abnomaltotal +
                ", attacktotal=" + attacktotal +
                '}';
    }
}
