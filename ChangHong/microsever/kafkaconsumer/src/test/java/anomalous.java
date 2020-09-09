import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class anomalous {
    private Date    time;
    private String  type;
    private String  source;
    private String  target;
    private String  port_s;
    private String  port_t;
    private  String details;
    private Boolean is_abnormal;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPort_s() {
        return port_s;
    }

    public void setPort_s(String port_s) {
        this.port_s = port_s;
    }

    public String getPort_t() {
        return port_t;
    }

    public void setPort_t(String port_t) {
        this.port_t = port_t;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getIs_abnormal() {
        return is_abnormal;
    }

    public void setIs_abnormal(Boolean is_abnormal) {
        this.is_abnormal = is_abnormal;
    }

    public anomalous(Date time, String type, String source, String target, String port_s, String port_t, String details, Boolean is_abnormal) {
        this.time = time;
        this.type = type;
        this.source = source;
        this.target = target;
        this.port_s = port_s;
        this.port_t = port_t;
        this.details = details;
        this.is_abnormal = is_abnormal;
    }
}
