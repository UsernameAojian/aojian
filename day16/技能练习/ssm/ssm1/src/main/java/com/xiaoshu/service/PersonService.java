package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.BankMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Bank;
import com.xiaoshu.entity.PersonVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class PersonService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private BankMapper bankMapper;

	// 查询所有
	public PageInfo<PersonVo> findPersonPage(PersonVo person, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<PersonVo> plist = personMapper.findPersonPage(person);
		PageInfo<PersonVo> pageInfo = new PageInfo<>(plist);
		return pageInfo;
	};


	// 新增
	public void addPerson(PersonVo t) throws Exception {
		personMapper.insert(t);
	};

	// 修改
	public void updatePerson(PersonVo t) throws Exception {
		personMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	}

	//查询主表
	public List<Bank> findBankAll() {
		// TODO Auto-generated method stub
		return bankMapper.selectAll();
	}


	public List<PersonVo> getEcharts() {
		// TODO Auto-generated method stub
		return personMapper.getEcharts();
	}

	



}
