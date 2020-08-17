package com.xiaoshu.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Area;
import com.xiaoshu.entity.Attachment;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.SchoolVO;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.SchoolService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("school")
public class SchoolController extends LogController{
	static Logger logger = Logger.getLogger(SchoolController.class);

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private RoleService roleService ;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("schoolIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Role> roleList = roleService.findRole(new Role());
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		request.setAttribute("roleList", roleList);
		List<Area> alist = schoolService.findArea();
		request.setAttribute("alist", alist);
		return "school";
	}
	
	
	@RequestMapping(value="schoolList",method=RequestMethod.POST)
	public void userList(SchoolVO school,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<SchoolVO> sList= schoolService.findSchool(school,pageNum,pageSize);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",sList.getTotal() );
			jsonObj.put("rows", sList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户展示错误",e);
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveSchool")
	public void reserveUser(School school,HttpServletRequest request,User user,HttpServletResponse response){
		Integer id = school.getId();
		JSONObject result=new JSONObject();
		try {
			if (id != null) {   // userId不为空 说明是修改
//					user.setUserid(userId);
					schoolService.updateSchool(school);
					result.put("success", true);
				
			}else {   // 添加
					schoolService.addSchool(school);
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
	
	
	@RequestMapping("deleteSchool")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				schoolService.deleteSchool(Integer.parseInt(id));
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
		@RequestMapping("exportExcel")
		public void ExportExcelToDisk(HttpServletResponse response,HttpServletRequest request) throws IOException {
			
				HSSFWorkbook wb = new HSSFWorkbook();//创建工作簿
				HSSFSheet sheet = wb.createSheet();//第一个sheet
				HSSFRow rowFirst = sheet.createRow(0);//第一个sheet第一行为标题
				String string[] = {"编号","所在城市","联系方式","详细地址","分校状态","创建时间","分校名称"}; 
						//写标题了
				for (int i = 0; i < string.length; i++) {
				    //获取第一行的每一个单元格
				    rowFirst.createCell(i).setCellValue(string[i]);
				}
				List<SchoolVO> sslist = schoolService.findSchool();
				for (int i = 0;i < sslist.size(); i++) {
				    //创建数据行
				    HSSFRow row = sheet.createRow(i+1);
				    row.createCell(0).setCellValue(sslist.get(i).getId());
				    row.createCell(1).setCellValue(sslist.get(i).getSchoolname());
				    row.createCell(2).setCellValue(sslist.get(i).getPhone());
				    row.createCell(3).setCellValue(sslist.get(i).getAddress());
				    row.createCell(4).setCellValue(sslist.get(i).getStatus());
				    row.createCell(5).setCellValue(TimeUtil.formatTime(sslist.get(i).getCreatetime(), "yyyy-MM-dd"));
				    row.createCell(6).setCellValue(sslist.get(i).getAreaname());
				}
				//写出文件（path为文件路径含文件名）
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("学生列表.xls", "UTF-8"));
				response.setHeader("Connection", "close");
				response.setHeader("Content-Type", "application/octet-stream");
				//通过io写出
				wb.write(response.getOutputStream());
				wb.close();
		}
	
}
