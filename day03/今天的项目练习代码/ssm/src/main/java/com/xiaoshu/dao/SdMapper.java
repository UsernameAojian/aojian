package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.QueryVosd;
import com.xiaoshu.entity.Sd;
import com.xiaoshu.entity.SdExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SdMapper extends BaseMapper<Sd> {

	List<QueryVosd> findAll(Sd sd);

}