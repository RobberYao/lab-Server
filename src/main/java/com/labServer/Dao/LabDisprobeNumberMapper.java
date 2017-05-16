package com.labServer.Dao;

import org.apache.ibatis.annotations.Select;

import com.labServer.entity.LabDisprobeNumber;

public interface LabDisprobeNumberMapper {
  
  //http://blog.csdn.net/a352193394/article/details/39940259
  
  @Select("select * from lab_disprobenumber where INPUTPROBENUMBER = #{inputProbeNumber}")
  public LabDisprobeNumber getDisprobeNumberByInputProbNum(String inputProbeNumber);
  
  
  
  
  
  
  
  
}
