package com.labServer.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.labServer.entity.LabDisplayParamter;

public interface LabDisplayParamterMapper {

  @Insert("insert into lab_displayparamter(INPUTPROBENUMBER,DISPROBENUMBER,CREATEDON,DISTEMPERATURE,DISHUMIDITY) values(#{inputProbeNumber}, #{disProbeNumber}, #{createdOn}, #{disTemperature}, #{disHumidity})")
  public int insertLabDisplayParamter(LabDisplayParamter labDisplayParamter);


  @Insert("insert into ${displayTable} (INPUTPROBENUMBER,DISPROBENUMBER,CREATEDON,DISTEMPERATURE,DISHUMIDITY) values(#{labDisplayParamter.inputProbeNumber}, #{labDisplayParamter.disProbeNumber}, #{labDisplayParamter.createdOn}, #{labDisplayParamter.disTemperature}, #{labDisplayParamter.disHumidity})")
  public int insertLabDisplayParamterByDisplayTable(@Param("labDisplayParamter") LabDisplayParamter labDisplayParamter, @Param("displayTable") String displayTable);


}
