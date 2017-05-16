package com.labServer.Dao;

import org.apache.ibatis.annotations.Select;

import com.labServer.entity.LabDisprobeNumber;

public interface LabDisprobeNumberMapper {
  
  @Select("select * from lab_disprobenumber where INPUTPROBENUMBER = #(inputProbeNumber)")
  public LabDisprobeNumber getDisprobeNumberByInputProbNum(String inputProbeNumber);
  
  
}
