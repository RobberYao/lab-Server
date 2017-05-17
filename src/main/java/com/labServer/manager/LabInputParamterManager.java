package com.labServer.manager;

import com.labServer.entity.LabInputParamter;

public interface LabInputParamterManager {

  void addLabInputParamter(LabInputParamter labInputParamter);

  void addLabInputParamterByDynamicTableName(LabInputParamter labInputParamter, String inputTable);

  Double getAVGInputTemperatureByCreatedOn(LabInputParamter labInputParamter, String inputTable);

  Double OptimizedTemp(Double temperature, Double avgTemperature);
}
