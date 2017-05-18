package com.labServer.manager;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.labServer.model.LabInputParamter;

public interface LabInputParamterManager {

  void addLabInputParamter(LabInputParamter labInputParamter);

  void addLabInputParamterByDynamicTableName(LabInputParamter labInputParamter);

  void addListItemsToSum(@Param("list")List<LabInputParamter> list);
  
  void addListItemsToDiff(@Param("list")List<LabInputParamter> list);

  Double getAVGInputTemperatureByCreatedOn(LabInputParamter labInputParamter, String inputTable);

  Double OptimizedTemp(Double temperature, Double avgTemperature);
}
