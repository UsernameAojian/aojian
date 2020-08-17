package com.xiaoshu.entity;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonVo extends Person {
	
	 @Column(name = "b_name")
	    private String bName;
	 	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private Date cratetime;
	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	private Date start;
	 	@DateTimeFormat(pattern="yyyy-MM-dd")
	 	private Date end;
	 	
	 	
	 	private Integer num;
	 	
	 	
	 	
	 	
		public Integer getNum() {
			return num;
		}
		public void setNum(Integer num) {
			this.num = num;
		}
		public Date getStart() {
			return start;
		}
		public void setStart(Date start) {
			this.start = start;
		}
		public Date getEnd() {
			return end;
		}
		public void setEnd(Date end) {
			this.end = end;
		}
		public String getbName() {
			return bName;
		}
		public void setbName(String bName) {
			this.bName = bName;
		}
		public Date getCratetime() {
			return cratetime;
		}
		public void setCratetime(Date cratetime) {
			this.cratetime = cratetime;
		}
		@Override
		public String toString() {
			return "PersonVo [bName=" + bName + ", cratetime=" + cratetime + ", start=" + start + ", end=" + end + "]";
		}
	 	
	 	
	 	

}
