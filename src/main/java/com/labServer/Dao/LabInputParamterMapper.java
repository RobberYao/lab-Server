package com.labServer.Dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.labServer.entity.LabInputParamter;

public interface LabInputParamterMapper {

	@Insert("insert into lab_inputparamter(INPUTPROBENUMBER,CREATEDON,INPUTTEMPERATURE,INPUTHUMIDITY) values(#{inputProbeNumber}, #{createdOn}, #{inputTemperature}, #{inputHumidity})")
	public int insertLabInputParamter(LabInputParamter labInputParamter);

	
	
	@Insert("insert into ${inputTable} (INPUTPROBENUMBER,CREATEDON,INPUTTEMPERATURE,INPUTHUMIDITY) values(#{labInputParamter.inputProbeNumber}, #{labInputParamter.createdOn}, #{labInputParamter.inputTemperature}, #{labInputParamter.inputHumidity})")
	public int insertLabInputParamter1(@Param("labInputParamter")LabInputParamter labInputParamter,@Param("inputTable")String inputTable);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
