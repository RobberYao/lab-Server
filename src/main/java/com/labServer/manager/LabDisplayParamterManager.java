package com.labServer.manager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabInputParamter;

public interface LabDisplayParamterManager {

//  void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
//      List<Map<String, Double>> modify);
//
//  void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
//      List<Map<String, Double>> modifys, String displayTable);
	
	void addListItemsToSumDisplay(@Param("list")List<LabDisplayParamter> list);
	
	void addListItemsToDiffDisplay(@Param("list")List<LabDisplayParamter> list);
  
  LabDisplayParamter calParamterByModify(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modifys);

  

}
