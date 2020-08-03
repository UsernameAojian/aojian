package com.xiaoshu.dao;

import com.xiaoshu.base.dao.BaseMapper;
import com.xiaoshu.entity.Md;
import com.xiaoshu.entity.MdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MdMapper extends BaseMapper<Md> {
    long countByExample(MdExample example);

    int deleteByExample(MdExample example);

    List<Md> selectByExample(MdExample example);

    int updateByExampleSelective(@Param("record") Md record, @Param("example") MdExample example);

    int updateByExample(@Param("record") Md record, @Param("example") MdExample example);
}