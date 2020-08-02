package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.CompanyMapper;
import com.xiaoshu.dao.PersonMapper;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.QueryVo;

@Service
public class PersonService {

	@Autowired
	private PersonMapper pm;
	
	@Autowired
	private CompanyMapper cm;

	public PageInfo<QueryVo> findPerson(Person person, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<QueryVo> plist = pm.findPerson(person);
		PageInfo<QueryVo> pageInfo = new PageInfo<QueryVo>(plist);
		return pageInfo;
	}
	
		// 新增
		public void addPerson(Person person) throws Exception {
			person.setCreateTime(new Date());
			pm.insert(person);
		};

		// 修改
		public void updatePerson(Person person) throws Exception {
			pm.updateByPrimaryKeySelective(person);
		};

		// 删除
		public void deletePerson(Integer id) throws Exception {
			pm.deleteByPrimaryKey(id);
		}

		public List<Company> findCompany(){
			// TODO Auto-generated method stub
			return cm.selectAll();
		}


//		public List<QueryVo> queryexport() {
//			// TODO Auto-generated method stub
//			return pm.findPerson(null);
//		}

		public List<QueryVo> exportPerson() {
			// TODO Auto-generated method stub
			return pm.findPerson(null);
		}

	
	
	
	


}
