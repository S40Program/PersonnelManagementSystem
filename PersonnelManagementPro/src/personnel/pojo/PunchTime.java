package personnel.pojo;


public class PunchTime {

    private Integer id;
    private String am;
    private String pm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return "PunchTime{" +
                "id=" + id +
                ", am='" + am + '\'' +
                ", pm='" + pm + '\'' +
                '}';
    }
}
