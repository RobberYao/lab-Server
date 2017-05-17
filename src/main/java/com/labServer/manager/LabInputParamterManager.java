package com.labServer.manager;

import com.labServer.entity.LabInputParamter;

public interface LabInputParamterManager {

  public void addLabInputParamter(LabInputParamter labInputParamter);

  public void addLabInputParamterByDynamicTableName(LabInputParamter labInputParamter, String inputTable);

}
