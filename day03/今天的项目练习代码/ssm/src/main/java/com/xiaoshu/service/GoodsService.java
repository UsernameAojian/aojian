package com.xiaoshu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.GoodsMapper;
import com.xiaoshu.dao.TypeMapper;
import com.xiaoshu.dao.UserMapper;
import com.xiaoshu.entity.Goods;
import com.xiaoshu.entity.Type;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private TypeMapper typeMapper;

	public PageInfo<Goods> findGoods(Integer pageNum, Integer pageSize, Goods goods) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<Goods> glist = goodsMapper.findGoods(goods);
		PageInfo<Goods> pageInfo = new PageInfo<Goods>(glist);
		return pageInfo;
	};

	// 新增
	public void addGoods(Goods goods) throws Exception {
		goods.setCreatetime(new Date());
		goodsMapper.insert(goods);
	};

	// 修改
	public void updateGoods(Goods goods) throws Exception {
		goodsMapper.updateByPrimaryKeySelective(goods);
	};

	// 删除
	public void deleteGoods(Integer id) throws Exception {
		goodsMapper.deleteByPrimaryKey(id);
	}

	public List<Type> findType() {
		// TODO Auto-generated method stub
		
		return typeMapper.selectAll();
	}



	



}
