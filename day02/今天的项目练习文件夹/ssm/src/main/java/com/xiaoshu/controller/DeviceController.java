package com.xiaoshu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
import com.xiaoshu.entity.Device;
import com.xiaoshu.entity.Devicetype;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.QueryVoDevicetype;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.DeviceService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("device")
public class DeviceController extends LogController{
	static Logger logger = Logger.getLogger(DeviceController.class);

	@Autowired
	private DeviceService deviceService;
	
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("deviceIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		List<Devicetype> tlist = deviceService.findDevicetype();
		request.setAttribute("tlist", tlist);
		return "device";
	}
	
	
	@RequestMapping(value="deviceList",method=RequestMethod.POST)
	public void userList(Device device,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Device> deviceList= deviceService.findDevice(device,pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",deviceList.getTotal() );
			jsonObj.put("rows", deviceList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	// 新增或修改
	@RequestMapping("reserveDevice")
	public void reserveUser(Device device,HttpServletRequest request,HttpServletResponse response){
		Integer deviceid = device.getDeviceid();
		JSONObject result=new JSONObject();
		try {
			if (deviceid != null) {   // deviceid不为空 说明是修改
					deviceService.updateDevice(device);
					result.put("success", true);
				
			}else {   // 添加
					deviceService.addDevice(device);
					result.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	@RequestMapping("deleteDevice")
	public void delUser(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				deviceService.deleteDevice(Integer.parseInt(id));
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
					String[] title = {"编号","设备名称","设备类型名称","内存","特点","状态","价格","创建时间"};
					
					for (int i = 0; i < title.length; i++) {
					    rowFirst.createCell(i).setCellValue(title[i]);
					}
					List<QueryVoDevicetype> li = deviceService.exportDevice();
					for (int i = 0;i < li.size(); i++) {
					    //获取list里面存在是数据集对象
						QueryVoDevicetype qv = li.get(i);
					    //创建数据行
					    HSSFRow row = sheet.createRow(i+1);
					    //设置对应单元格的值
					    row.createCell(0).setCellValue(qv.getDevicetypeid());
					    row.createCell(1).setCellValue(qv.getDevicename());
					    row.createCell(2).setCellValue(qv.getDeviceram());
					    row.createCell(3).setCellValue(qv.getStatus());
					    row.createCell(4).setCellValue(qv.getPrice());
					    row.createCell(5).setCellValue(TimeUtil.formatTime(qv.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
					}
						File file = new File("d:/h1909e.xls");
						OutputStream os = new FileOutputStream(file);
						wb.write(os);
						wb.close();
			}
	
	
}
