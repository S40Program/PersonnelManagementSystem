package personnel.pojo;

import java.io.Serializable;

public class Recruitment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private JobType jobtype;
	
	private String content;
	
	private Integer peoplenum;
	
	private RecruitmentStatus status;
	
	private String enddate;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobType getJobtype() {
		return jobtype;
	}

	public void setJobtype(JobType jobtype) {
		this.jobtype = jobtype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPeoplenum() {
		return peoplenum;
	}

	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}


	public RecruitmentStatus getStatus() {
		return status;
	}

	public void setStatus(RecruitmentStatus status) {
		this.status = status;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "Recruitment [id=" + id + ", jobtype=" + jobtype + ", content=" + content + ", peoplenum=" + peoplenum
				+ ", status=" + status + ", enddate=" + enddate + "]";
	}

	
	
	
}
