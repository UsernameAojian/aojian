package com.xiaoshu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.SchoolMapper;
import com.xiaoshu.dao.StudentMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private SchoolMapper schoolMapper;

	// 查询所有
	public PageInfo<StudentVo> findStudent(StudentVo studentVo, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> slist = studentMapper.findStudent(studentVo);
		PageInfo<StudentVo> pageInfo = new PageInfo<StudentVo>(slist);
		return pageInfo;
	}
	// 新增
	public void addStudent(Student t) throws Exception {
		studentMapper.insert(t);
	};

	// 修改
	public void updateStudent(Student t) throws Exception {
		studentMapper.updateByPrimaryKeySelective(t);
	};

	// 删除
	public void deleteStudent(Integer id) throws Exception {
		studentMapper.deleteByPrimaryKey(id);
	};
	
	//查询所有部门
	public List<School> findSchool() {
		// TODO Auto-generated method stub
		return schoolMapper.selectAll();
	}

	//导出
	public List<StudentVo> exportStudent() {
		// TODO Auto-generated method stub
		return studentMapper.findStudent(null);
	}
	
	
	

	


}
