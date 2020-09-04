package com.changhong.entity;

import lombok.Data;

import java.util.Date;

@Data
public class anomalous {
    private int     key_a;
    private Date    time;
    private String  type;
    private String  source;
    private String  target;
    private String  port_s;
    private String  port_t;

    public anomalous(int key_a, Date time, String type, String source, String target, String port_s, String port_t, String details, boolean is_abnormal) {
        this.key_a = key_a;
        this.time = time;
        this.type = type;
        this.source = source;
        this.target = target;
        this.port_s = port_s;
        this.port_t = port_t;
        this.details = details;
        this.is_abnormal = is_abnormal;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setPort_s(String port_s) {
        this.port_s = port_s;
    }

    public void setPort_t(String port_t) {
        this.port_t = port_t;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setIs_abnormal(boolean is_abnormal) {
        this.is_abnormal = is_abnormal;
    }

    public int getKey_a() {
        return key_a;
    }

    public Date getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getPort_s() {
        return port_s;
    }

    public String getPort_t() {
        return port_t;
    }

    public String getDetails() {
        return details;
    }

    public boolean isIs_abnormal() {
        return is_abnormal;
    }

    private String  details;
    private boolean is_abnormal;

    public void setKey_a(int key_a) {
        this.key_a = key_a;
    }
}
