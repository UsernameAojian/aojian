package com.xiaoshu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Md;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.QueryVosd;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Sd;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.SdService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("sd")
public class SdController extends LogController{
	static Logger logger = Logger.getLogger(SdController.class);

	@Autowired
	private SdService ss;
	
	@Autowired
	private OperationService operationService;
	
	
	@RequestMapping("sdIndex")
	public String index(HttpServletRequest request,Integer menuid) throws Exception{
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		List<Md> mdList = ss.findMd();
		request.setAttribute("mdList", mdList);
		
		return "sd";
	}
	
	
	@RequestMapping(value="sdList",method=RequestMethod.POST)
	public void userList(Sd sd,HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<QueryVosd> sdList= ss.findSd(sd,pageNum,pageSize);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",sdList.getTotal() );
			jsonObj.put("rows", sdList.getList());
	        WriterUtil.write(response,jsonObj.toString());
	}
	
	
	// 新增或修改
	@RequestMapping("caozuoSd")
	public void reserveUser(HttpServletRequest request,Sd sd,HttpServletResponse response){
		Integer sdId = sd.getSdId();
		JSONObject result=new JSONObject();
		try {
			if (sdId != null) {   // userId不为空 说明是修改
					ss.updateSd(sd);
					result.put("success", true);
				
			}else {   // 添加
					ss.addSd(sd);
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
	
	
	@RequestMapping("deleteSd")
	public void deleteSd(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			for (String id : ids) {
				ss.deletesd(Integer.parseInt(id));
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
	
}
