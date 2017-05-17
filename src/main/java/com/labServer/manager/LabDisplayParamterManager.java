package com.labServer.manager;

import java.util.List;
import java.util.Map;

import com.labServer.entity.LabDisplayParamter;

public interface LabDisplayParamterManager {

  void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modify);

  void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modifys, String displayTable);
}
