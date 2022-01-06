package personnel.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PunchClock implements Serializable {

    private Integer id;
    private Integer userId; // 用户ID；
    private Date sgin;  // 上班打卡时间
    private Date offwork;  // 下班打卡时间
    private Integer sgin_status; // 上班打卡状态
    private Integer offwork_status; // 下班打卡状态
    private Integer sgin_status_id; // 上班打卡状态
    private Integer offwork_status_id; // 下班打卡状态
    private String date;
    private String content;

    //关于时间的格式转换
    private String sgin_time;
    private String offwork_time;
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSgin() {
        return sgin;
    }

    public void setSgin(Date sgin) {
        this.sgin = sgin;
        String time = sdf.format(sgin);
        setSgin_time(time);
    }

    public Date getOffwork() {
        return offwork;
    }

    public void setOffwork(Date offwork) {
        this.offwork = offwork;
        String time = sdf.format(offwork);
        setOffwork_time(time);
    }

    public Integer getSgin_status() {
        return sgin_status;
    }

    public void setSgin_status(Integer sgin_status) {
        this.sgin_status = sgin_status;
    }

    public Integer getOffwork_status() {
        return offwork_status;
    }

    public void setOffwork_status(Integer offwork_status) {
        this.offwork_status = offwork_status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getSgin_time() {
        return sgin_time;
    }

    public void setSgin_time(String sgin_time) {
        this.sgin_time = sgin_time;
    }

    public String getOffwork_time() {
        return offwork_time;
    }

    public void setOffwork_time(String offwork_time) {
        this.offwork_time = offwork_time;
    }

    public Integer getSgin_status_id() {
        return sgin_status_id;
    }

    public void setSgin_status_id(Integer sgin_status_id) {
        this.sgin_status_id = sgin_status_id;
    }

    public Integer getOffwork_status_id() {
        return offwork_status_id;
    }

    public void setOffwork_status_id(Integer offwork_status_id) {
        this.offwork_status_id = offwork_status_id;
    }

    @Override
    public String toString() {
        return "PunchClock{" +
                "id=" + id +
                ", userId=" + userId +
                ", sgin=" + sgin +
                ", offwork=" + offwork +
                ", sgin_status=" + sgin_status +
                ", offwork_status=" + offwork_status +
                ", sgin_status_id=" + sgin_status_id +
                ", offwork_status_id=" + offwork_status_id +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                ", sgin_time='" + sgin_time + '\'' +
                ", offwork_time='" + offwork_time + '\'' +
                ", sdf=" + sdf +
                '}';
    }
}
