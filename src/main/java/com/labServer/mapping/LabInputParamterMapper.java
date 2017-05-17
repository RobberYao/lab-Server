package com.labServer.mapping;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.labServer.model.LabInputParamter;

public interface LabInputParamterMapper {

	@Insert("insert into lab_inputparamter(INPUTPROBENUMBER,CREATEDON,INPUTTEMPERATURE,INPUTHUMIDITY) values(#{inputProbeNumber}, #{createdOn}, #{inputTemperature}, #{inputHumidity})")
	public int insertLabInputParamter(LabInputParamter labInputParamter);

	
	
	@Insert("insert into ${inputTable} (INPUTPROBENUMBER,CREATEDON,INPUTTEMPERATURE,INPUTHUMIDITY) values(#{labInputParamter.inputProbeNumber}, #{labInputParamter.createdOn}, #{labInputParamter.inputTemperature}, #{labInputParamter.inputHumidity})")
	public int insertLabInputParamterByInputTable(@Param("labInputParamter")LabInputParamter labInputParamter, @Param("inputTable")String inputTable);
	
	
	//String sql = "select AVG(INPUTTEMPERATURE) from " + inputProbNum + " where DATE_SUB('" + createdOn+ "',INTERVAL 10 SECOND ) < CREATEDON";
	@Select("select AVG(INPUTTEMPERATURE) from ${inputTable} where DATE_SUB(#{labInputParamter.createdOn}, INTERVAL 10 SECOND )< #{labInputParamter.createdOn}")
	public Double findAVGInputTemperature(@Param("labInputParamter")LabInputParamter labInputParamter, @Param("inputTable")String inputTable);
	
	
	
	
	
	
	
	
	
	
	
}
