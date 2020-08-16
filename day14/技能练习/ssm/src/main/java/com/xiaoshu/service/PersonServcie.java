package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PersonServcie {

	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	public PageInfo<PersonVo> findPerson(Person person, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> plist = personMapper.findPerson(person);
		PageInfo<PersonVo> pageInfo = new PageInfo<>(plist);
		return pageInfo;
	};
	
	// 新增
	public void addPerson(Person t) throws Exception {
		t.setCrateTime(new Date());
		personMapper.insert(t);
	};



	// 修改
	public void updatePerson(Person t) throws Exception {
		personMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	}


	public List<Company> findCompany() {
		// TODO Auto-generated method stub
		return companyMapper.selectAll();
	}

	public List<PersonVo> getEcharts(){
		return personMapper.getEcharts();
	}


	



}
