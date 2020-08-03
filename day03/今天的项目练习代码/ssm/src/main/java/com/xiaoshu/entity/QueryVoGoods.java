package com.xiaoshu.entity;

import javax.persistence.Column;

public class QueryVoGoods extends Goods {
	@Column(name = "t_typename")
    private String tTypename;

	public String gettTypename() {
		return tTypename;
	}

	public void settTypename(String tTypename) {
		this.tTypename = tTypename;
	}

	@Override
	public String toString() {
		return "QueryVoGoods [tTypename=" + tTypename + "]";
	}
	
	
}
