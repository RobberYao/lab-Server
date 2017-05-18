package com.labServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisprobeNumberManagerImpl;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabInputParamterManagerImpl;
import com.labServer.manager.LabModifyManagerImpl;
import com.labServer.mapping.LabModifyMapper;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabInputParamter;
import com.labServer.model.LabModify;

/**
 * Hello world!
 *
 */
public class App {



  public LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();

  public static void main(String[] args) {
    App app= new App();
    app.test();

  }



  public  void test() {


    long checkstartTime = System.currentTimeMillis();// 开始计时
    // LabModifyManagerImpl labModifyManager = new LabModifyManagerImpl();
    // List<Map<String, Double>> labModify =
    // labModifyManager.getLabModifyByInputProbNum("8AD0000101");
    List<LabInputParamter> list= new ArrayList<LabInputParamter>();
    for (int i = 0; i < 2; i++) {
      
      LabInputParamter labInputParamter = new LabInputParamter();
      labInputParamter.setCreatedOn("2017-05-17 11:11:00");
      labInputParamter.setInputProbeNumber("8AD0000101");
      labInputParamter.setInputTemperature(Double.valueOf("1.33"));
      labInputParamter.setInputHumidity(Double.valueOf("22.22"));
      labInputParamter.setInputTableName("lab_inputparamter0101");
      list.add(labInputParamter);
      
      LabInputParamter labInputParamter1 = new LabInputParamter();
      labInputParamter1.setCreatedOn("2017-05-17 11:11:00");
      labInputParamter1.setInputProbeNumber("8AD0000102");
      labInputParamter1.setInputTemperature(Double.valueOf("1.33"));
      labInputParamter1.setInputHumidity(Double.valueOf("22.22"));
      labInputParamter1.setInputTableName("lab_inputparamter0102");
      list.add(labInputParamter1);
      
      
      
    }
    
    //String inputTable = "lab_inputparamter0102";
    
    // labInputParamterManager.addLabInputParamter(labInputParamter);
    //labInputParamterManager.addListItems(list);
    labInputParamterManager.addListItemsToDiff(list);
    //double avg= labInputParamterManager.getAVGInputTemperatureByCreatedOn(labInputParamter, inputTable);
    //labInputParamterManager.OptimizedTemp(33.3, avg);
    
    long checkendTime = System.currentTimeMillis();// 计时结束
    float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
    System.out.println("插入数据完毕耗时： " + Float.toString(seconds) + " seconds.");


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
