package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MdMapper;
import com.xiaoshu.dao.SdMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Md;
import com.xiaoshu.entity.QueryVosd;
import com.xiaoshu.entity.Sd;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class SdService {

	@Autowired
	private SdMapper sm;
	
	@Autowired
	private MdMapper mm;
	// 查询所有
	public PageInfo<QueryVosd> findSd(Sd sd, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<QueryVosd> slist = sm.findAll(sd);
		PageInfo<QueryVosd> pageInfo = new PageInfo<QueryVosd>(slist);
		return pageInfo;
	};

	// 新增
	public void addSd(Sd sd) throws Exception {
		sm.insert(sd);
	};

	// 修改
	public void updateSd(Sd sd) throws Exception {
		sm.updateByPrimaryKeySelective(sd);
	};

	// 删除
	public void deletesd(Integer id) throws Exception {
		sm.deleteByPrimaryKey(id);
	}

	public List<Md> findMd() {
		// TODO Auto-generated method stub
		return mm.selectAll();
	}


	



}
