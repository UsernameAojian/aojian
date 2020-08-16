package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeptMapper;
import com.xiaoshu.dao.EmpMapper;
import com.xiaoshu.entity.Dept;
import com.xiaoshu.entity.Emp;
import com.xiaoshu.entity.EmpVo;

@Service
public class EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Autowired
	private DeptMapper deptMapper;

	//查询所有
	public PageInfo<EmpVo> findEmp(EmpVo emp, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<EmpVo> elist = empMapper.findEmp(emp);
		PageInfo<EmpVo> pageInfo = new PageInfo<>(elist);
		return pageInfo;
	};

//	 新增

	public void addEmp(Emp emp) throws Exception {
		emp.setCreatetime(new Date());
		empMapper.insert(emp);
	};

	// 修改
	public void updateEmp(Emp emp) throws Exception {
		empMapper.updateByPrimaryKeySelective(emp);
	}

	public List<Dept> findDept() {
		// TODO Auto-generated method stub
		return deptMapper.selectAll();
	};

	// 删除
	public void deleteEmp(Integer id) throws Exception {
		empMapper.deleteByPrimaryKey(id);
	}

	



}
