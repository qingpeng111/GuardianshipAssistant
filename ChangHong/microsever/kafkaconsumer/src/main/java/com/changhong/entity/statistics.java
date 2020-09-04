package com.changhong.entity;

import lombok.Data;

import java.util.Date;

@Data
public class statistics {
    private  int    key_s;
    private  Date   time;
    private  String type;
    private  int    count;

    public statistics(int key_s, Date time, String type, int count) {
        this.key_s = key_s;
        this.time = time;
        this.type = type;
        this.count = count;
    }

    public int getKey_s() {
        return key_s;
    }

    public void setKey_s(int key_s) {
        this.key_s = key_s;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
