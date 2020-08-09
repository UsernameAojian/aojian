package com.xiaoshu.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.StudentService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("student")
public class StudentController extends LogController{
	static Logger logger = Logger.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("studentIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", roleList);
		List<School> schoollist = studentService.findSchool();
		request.setAttribute("schoollist", schoollist);
		return "student";
	}
	
	
	@RequestMapping(value="studentList",method=RequestMethod.POST)
	public void userList(Student student,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<StudentVo> studentList= studentService.findStudent(student,pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",studentList.getTotal() );
			jsonObj.put("rows", studentList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveStudnet")
	public void reserveUser(Student student,HttpServletRequest request,User user,HttpServletResponse response){
		Integer sid = student.getSid();
		JSONObject result=new JSONObject();
		try {
			if (sid != null) {   // userId不为空 说明是修改
					studentService.updateStudent(student);
					result.put("success", true);
				
			}else {   // 添加
					studentService.addStudent(student);
					result.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存用户信息错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	@RequestMapping("deleteStudent")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				studentService.deleteStudent(Integer.parseInt(id));
			}
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除用户信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	//导出
			@RequestMapping("export")
			public void excelPoi(Student student,HttpServletRequest request, HttpServletResponse response) throws IOException{
				//创建工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//创建sheet
				HSSFSheet sheet = workbook.createSheet();
				//定义头 标题
				String string[] = {"学生编号","学生名称","学生生日","学生性别","学生年龄","学生学校"};
				//先创建标题
				HSSFRow hssfRow = sheet.createRow(0);
				for (int i = 0; i < string.length; i++) {
					hssfRow.createCell(i).setCellValue(string[i]);
				}
				//查询数据库
				List<StudentVo> studentlist = studentService.exportStudent();
				for (int i = 0; i < studentlist.size(); i++) {
					HSSFRow row = sheet.createRow(i+1);
					row.createCell(0).setCellValue(studentlist.get(i).getSid());
					row.createCell(1).setCellValue(studentlist.get(i).getSname());
					row.createCell(2).setCellValue(TimeUtil.formatTime(studentlist.get(i).getBirthday(), "yyyy-MM-dd"));
					row.createCell(3).setCellValue(studentlist.get(i).getSex());
					row.createCell(4).setCellValue(studentlist.get(i).getAge());
					row.createCell(5).setCellValue(studentlist.get(i).getCname());
				}
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("员工列表.xls", "UTF-8"));
				response.setHeader("Connection", "close");
				response.setHeader("Content-Type", "application/octet-stream");
				//通过io写出
				workbook.write(response.getOutputStream());
				workbook.close();
				
			}
	
}
