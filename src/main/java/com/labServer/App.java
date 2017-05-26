package com.labServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisplayParamterManagerImpl;
import com.labServer.manager.LabDisprobeNumberManager;
import com.labServer.manager.LabDisprobeNumberManagerImpl;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabInputParamterManagerImpl;
import com.labServer.manager.LabModifyManager;
import com.labServer.manager.LabModifyManagerImpl;
import com.labServer.mapper.LabModifyMapper;
import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabInputParamter;
import com.labServer.model.LabModify;

/**
 * Hello world!
 *
 */
public class App {

  public LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
  public LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
  public LabModifyManager labModifyManager = new LabModifyManagerImpl();
  public LabDisprobeNumberManager disprobeNumberManager = new LabDisprobeNumberManagerImpl();


  public static void main(String[] args) {
    App app = new App();
    app.test();

  }

  public void test() {


    // LabModifyManagerImpl labModifyManager = new LabModifyManagerImpl();
    // List<Map<String, Double>> labModify =
    // labModifyManager.getLabModifyByInputProbNum("8AD0000101");
    // List<LabInputParamter> list = new ArrayList<LabInputParamter>();
    // LabInputParamter labInputParamter = new LabInputParamter();
    // labInputParamter.setCreatedOn("2017-05-17 11:11:00");
    // labInputParamter.setInputProbeNumber("8AD0000101");
    // labInputParamter.setInputTemperature(Double.valueOf("1.33"));
    // labInputParamter.setInputHumidity(Double.valueOf("22.22"));
    // labInputParamter.setInputTableName("lab_inputparamter0101");
    // list.add(labInputParamter);

    // List<LabDisplayParamter> list = new ArrayList<LabDisplayParamter>();
    // for (int i = 0; i < 10; i++) {
    // LabDisplayParamter lab = new LabDisplayParamter();
    // lab.setCreatedOn("2017-05-17 11:11:00");
    // lab.setInputProbeNumber("8AD0000101");
    // lab.setDisProbeNumber("pro0101");
    // lab.setDisTemperature(Double.valueOf("1.33"));
    // lab.setDisHumidity(Double.valueOf("22.22"));
    // lab.setDisplayTableName("lab_displayparamter0101");
    //
    // list.add(lab);
    // LabDisplayParamter lab1 = new LabDisplayParamter();
    // lab1.setCreatedOn("2017-05-17 11:11:00");
    // lab1.setInputProbeNumber("8AD0000102");
    // lab1.setDisProbeNumber("pro0102");
    // lab1.setDisTemperature(Double.valueOf("1.33"));
    // lab1.setDisHumidity(Double.valueOf("22.22"));
    // lab1.setDisplayTableName("lab_displayparamter0102");
    //
    // list.add(lab1);
    // LabInputParamter labInputParamter1 = new LabInputParamter();
    // labInputParamter1.setCreatedOn("2017-05-17 11:11:00");
    // labInputParamter1.setInputProbeNumber("8AD0000102");
    // labInputParamter1.setInputTemperature(Double.valueOf("1.33"));
    // labInputParamter1.setInputHumidity(Double.valueOf("22.22"));
    // labInputParamter1.setInputTableName("lab_inputparamter0102");
    // list.add(labInputParamter1);
    // }
    // String inputTable = "lab_inputparamter0102";
    // labInputParamterManager.addLabInputParamter(labInputParamter);
    // labInputParamterManager.addListItems(list);
    // labInputParamterManager.addListItemsToDiff(list);
    // double avg=
    // labInputParamterManager.getAVGInputTemperatureByCreatedOn(labInputParamter,
    // inputTable);
    // labInputParamterManager.OptimizedTemp(33.3, avg);
    long checkstartTime = System.currentTimeMillis();// 开始计时
    // Map<String, LabModify> modifyMap = new HashMap<String, LabModify>();
    // modifyMap = labModifyManager.getSumLabModify();
    // modifyMap.get("8AD0000103");
    Map<String, LabDisprobeNumber> labDisprobeNumber =disprobeNumberManager.getSumDisprobeNumber();
    labDisprobeNumber.get("8AD0000101").getDisplayProbeNumber();
    // labDisplayParamterManager.addListItemsToSumDisplay(list);
    // labDisplayParamterManager.addListItemsToDiffDisplay(list);
    long checkendTime = System.currentTimeMillis();// 计时结束
    float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
    System.out.println("完毕耗时： " + Float.toString(seconds) + " seconds.");

  }

  public static Date getSimpledDateTime() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date time = null;
    try {
      time = sdf.parse(sdf.format(new Date()));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return time;
  }

}
