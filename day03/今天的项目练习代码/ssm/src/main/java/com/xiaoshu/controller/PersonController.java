package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Company;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Person;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.PersonService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

@Controller
@RequestMapping("person")
public class PersonController extends LogController{
	static Logger logger = Logger.getLogger(PersonController.class);

	@Autowired
	private PersonService pc;
	
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("personIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		List<Company> cList = pc.findCompany();
		request.setAttribute("cList", cList);
		return "person";
	}
	
	
	@RequestMapping(value="personList",method=RequestMethod.POST)
	public void userList(Person person,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<QueryVo> personList= pc.findPerson(person,pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",personList.getTotal() );
			jsonObj.put("rows", personList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	// 新增或修改
		@RequestMapping("reservePerson")
		public void reserveUser(Person person,HttpServletRequest request,HttpServletResponse response){
			Integer personId = person.getId();
			JSONObject result=new JSONObject();
			try {
				if (personId != null) {   // userId不为空 说明是修改
						pc.updatePerson(person);
						result.put("success", true);
					
				}else {   // 添加
						pc.addPerson(person);
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
		
		
		@RequestMapping("deletePerson")
		public void delUser(HttpServletRequest request,HttpServletResponse response){
			JSONObject result=new JSONObject();
			try {
				String[] ids=request.getParameter("ids").split(",");
				for (String id : ids) {
					pc.deletePerson(Integer.parseInt(id));
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
		
		
	
		
		// 导出到硬盘
		@RequestMapping("exp")
		public void exp(HttpServletRequest request) throws Exception {
			
				HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
				HSSFSheet sheet = wb.createSheet();//第一个sheet
				HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
				String[] title = {"用户编号","人员名字","人员性别","人员特点","入职时间","创建时间","所属公司"};
				
				for (int i = 0; i < title.length; i++) {
				    rowFirst.createCell(i).setCellValue(title[i]);
				}
				List<QueryVo> li = pc.exportPerson();
				for (int i = 0;i < li.size(); i++) {
				    //获取list里面存在是数据集对象
				    QueryVo qv = li.get(i);
				    //创建数据行
				    HSSFRow row = sheet.createRow(i+1);
				    //设置对应单元格的值
				    row.createCell(0).setCellValue(qv.getId());
				    row.createCell(1).setCellValue(qv.getExpressName());
				    row.createCell(2).setCellValue(qv.getSex());
				    row.createCell(3).setCellValue(qv.getExpressTrait());
				    row.createCell(4).setCellValue(TimeUtil.formatTime(qv.getEntryTime(), "yyyy-MM-dd"));
				    row.createCell(5).setCellValue(TimeUtil.formatTime(qv.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				    row.createCell(6).setCellValue(qv.getExpressName1());
				}
					File file = new File("d:/h1909e.xls");
					OutputStream os = new FileOutputStream(file);
					wb.write(os);
					wb.close();
		}
		
		
		
		
		

		
	
}
