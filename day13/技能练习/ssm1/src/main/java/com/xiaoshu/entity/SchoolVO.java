package com.xiaoshu.entity;

public class SchoolVO extends School{
	 private String areaname;

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	@Override
	public String toString() {
		return "SchoolVO [areaname=" + areaname + "]";
	}
	 
	 
}
