package com.labServer.Dao;

import org.apache.ibatis.annotations.Select;

import com.labServer.entity.LabDisprobeNumber;

public interface LabDisprobeNumberMapper {

  // http://blog.csdn.net/a352193394/article/details/39940259
  /**
   * 通过原探头号查找对应探头映射表实力
   * 
   * @param inputProbeNumber
   * @return
   */
  @Select("select * from lab_disprobenumber where INPUTPROBENUMBER = #{inputProbeNumber}")
  LabDisprobeNumber getDisprobeNumberByInputProbNum(String inputProbeNumber);

}
