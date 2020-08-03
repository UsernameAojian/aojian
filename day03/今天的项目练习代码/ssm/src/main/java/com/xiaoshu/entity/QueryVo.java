package com.xiaoshu.entity;

import java.util.Date;

import javax.persistence.Column;

public class QueryVo extends Person {
	
	 @Column(name = "express_name")
	    private String expressName1;

	    private Integer status;

	    @Column(name = "create_time")
	    private Date createTime;

		public String getExpressName1() {
			return expressName1;
		}

		public void setExpressName1(String expressName1) {
			this.expressName1 = expressName1;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

}
