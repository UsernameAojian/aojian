package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoshu.dao.DeviceMapper;
import com.xiaoshu.dao.DevicetypeMapper;
import com.xiaoshu.entity.Device;
import com.xiaoshu.entity.Devicetype;
import com.xiaoshu.entity.QueryVo;
import com.xiaoshu.entity.QueryVoDevicetype;

@Service
public class DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	
	@Autowired
	private DevicetypeMapper devicetypeMapper;
	
	// 查询所有
	public PageInfo<Device> findDevice(Device device, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Device> dlist = deviceMapper.findDevice(device);
		PageInfo<Device> pageInfo = new PageInfo<>(dlist);
		return pageInfo;
	};



	// 新增
	public void addDevice(Device device) throws Exception {
		device.setCreatetime(new Date());
		deviceMapper.insert(device);
	};

	// 修改
	public void updateDevice(Device device) throws Exception {
		deviceMapper.updateByPrimaryKeySelective(device);
	};

	// 删除
	public void deleteDevice(Integer id) throws Exception {
		deviceMapper.deleteByPrimaryKey(id);
	}



	public List<Devicetype> findDevicetype() {
		// TODO Auto-generated method stub
		return devicetypeMapper.selectAll();
	}



	public List<QueryVoDevicetype> exportDevice() {
		// TODO Auto-generated method stub
		return null;
	}



	

	


}
