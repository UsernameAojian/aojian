package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.AreaMapper;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Area;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.SchoolVO;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class SchoolService {

	@Autowired
	private SchoolMapper schoolMapper;
	
	@Autowired
	private AreaMapper areaMapper;

	// 查询所有
	public PageInfo<SchoolVO> findSchool(SchoolVO school, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<SchoolVO> schoollist = schoolMapper.findSchool(school);
		PageInfo<SchoolVO> pageInfo = new PageInfo<SchoolVO>(schoollist);
		return pageInfo;
	};

	// 新增
	public void addSchool(School t) throws Exception {
		t.setCreatetime(new Date());
		schoolMapper.insert(t);
	};

	// 修改
	public void updateSchool(School t) throws Exception {
		schoolMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteSchool(Integer id) throws Exception {
		schoolMapper.deleteByPrimaryKey(id);
	}

	public List<Area> findArea() {
		// TODO Auto-generated method stub
		return areaMapper.selectAll();
	}

	


	public List<SchoolVO> findSchool() {
		// TODO Auto-generated method stub
		return schoolMapper.findSchool(null);
	}

	



}
