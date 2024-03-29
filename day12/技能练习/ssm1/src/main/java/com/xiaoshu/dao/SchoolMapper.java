package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.School;
import com.xiaoshu.entity.SchoolExample;
import com.xiaoshu.entity.SchoolVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolMapper extends BaseMapper<School> {
    long countByExample(SchoolExample example);

    int deleteByExample(SchoolExample example);

    List<School> selectByExample(SchoolExample example);

    int updateByExampleSelective(@Param("record") School record, @Param("example") SchoolExample example);

    int updateByExample(@Param("record") School record, @Param("example") SchoolExample example);

	List<SchoolVO> findSchool(SchoolVO school);


}