package com.labServer.manager;

import java.util.List;
import java.util.Map;

public interface LabModifyManager {

  List<Map<String, Double>> getLabModifyByInputProbNum(String inputProbreNumber);

}
