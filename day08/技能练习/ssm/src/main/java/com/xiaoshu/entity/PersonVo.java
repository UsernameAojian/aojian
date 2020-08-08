package com.xiaoshu.entity;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonVo extends Person{
	 @Column(name = "express_name")
	    private String expressName1;

	    private String status;
	    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    @Column(name = "create_time")
	    private Date createTime;
	    
	    private Integer nums;
	    
		public Integer getNums() {
			return nums;
		}
		public void setNums(Integer nums) {
			this.nums = nums;
		}
		public String getExpressName1() {
			return expressName1;
		}
		public void setExpressName1(String expressName1) {
			this.expressName1 = expressName1;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		@Override
		public String toString() {
			return "PersonVo [expressName1=" + expressName1 + ", status=" + status + ", createTime=" + createTime + "]";
		}
	    
	    
}
