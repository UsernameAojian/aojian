package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;

@Service
public class PersonService {

	@Autowired
	private PersonMapper personMapper;
	
	@Autowired
	private CompanyMapper companyMapper;

	public PageInfo<Person> findAll(Person person, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Person> plist = personMapper.findAll(person);
		PageInfo<Person> pageInfo = new PageInfo<Person>(plist);
		return pageInfo;
	};

	// 新增
	public void addPerson(Person person) throws Exception {
		personMapper.insert(person);
	};

	// 修改
	public void updatePerson(Person person) throws Exception {
		personMapper.updateByPrimaryKeySelective(person);
	};

	// 删除
	public void deletePerson(Integer id) throws Exception {
		personMapper.deleteByPrimaryKey(id);
	}

	public List<Company> findCompany() {
		// TODO Auto-generated method stub
		return companyMapper.selectAll();
	}

	



}
