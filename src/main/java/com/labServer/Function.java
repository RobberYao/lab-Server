package com.labServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.labServer.Util.RegexUtil;
import com.labServer.Util.SCMUtil;
import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisplayParamterManagerImpl;
import com.labServer.manager.LabDisprobeNumberManager;
import com.labServer.manager.LabDisprobeNumberManagerImpl;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabInputParamterManagerImpl;
import com.labServer.manager.LabModifyManager;
import com.labServer.manager.LabModifyManagerImpl;
import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabInputParamter;
import com.labServer.model.LabModify;

public class Function {

  private final double tempCheck = -10.00;
  private final double humCheck = 10.00;
  private LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
  private LabDisprobeNumberManager labDisprobeNumberManager = new LabDisprobeNumberManagerImpl();
  private LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
  private LabModifyManager labModifyManager = new LabModifyManagerImpl();
  List<LabInputParamter> listInputItems = new ArrayList<LabInputParamter>();// 批量原数据集合
  List<LabDisplayParamter> listDisplayItems = new ArrayList<LabDisplayParamter>();// 批量显示数据集合
  private int itemsSize = 16;// 批处理量
  int w = 1;
  
  // 查找配置信息(预加载)
  Map<String, LabDisprobeNumber> labDisprobeNumber = labDisprobeNumberManager.getSumDisprobeNumber();// 显示数据实例
  // 查找该探头的校准值(预加载)
  Map<String, LabModify> modifys = labModifyManager.getSumLabModify();
  
  
  
  
  public static void main(String[] args) {

    Function function = new Function();
    for (int i = 0; i < 1000; i++) {
      String str1 = "+YAV:0005AABB" + ",820 000 000 007 001 " + ",820 000 000 007 001 "
          + ",820 001 007 000 000 " + ",820 001 008 000 000 " + ",820 000 004 000 000 "
          + ",820 000 008 001 003 " + ",820 005 004 000 002 " + ",820 00C 00B 008 008 "
          + ",0 0,0 0,0 0 0 0,00" + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      function.loadParamBySCM(str1);
      
      
      String str2 = "+YAV:0005AABB" + ",820 000 000 007 001 " + ",820 000 000 007 001 "
          + ",820 001 007 000 000 " + ",820 001 008 000 000 " + ",820 000 004 000 000 "
          + ",820 000 008 001 003 " + ",820 005 004 000 002 " + ",820 00C 00B 008 008 "
          + ",0 0,0 0,0 0 0 0,00" + ",FF0203FF,V V V V V V V V" + ",8AD00002,X,EEFF";
      function.loadParamBySCM(str2);
      
      String str3 = "+YAV:0005AABB" + ",820 000 000 007 001 " + ",820 000 000 007 001 "
          + ",820 001 007 000 000 " + ",820 001 008 000 000 " + ",820 000 004 000 000 "
          + ",820 000 008 001 003 " + ",820 005 004 000 002 " + ",820 00C 00B 008 008 "
          + ",0 0,0 0,0 0 0 0,00" + ",FF0203FF,V V V V V V V V" + ",8AD00003,X,EEFF";
      function.loadParamBySCM(str3);
      
      String str4 = "+YAV:0005AABB" + ",820 000 000 007 001 " + ",820 000 000 007 001 "
          + ",820 001 007 000 000 " + ",820 001 008 000 000 " + ",820 000 004 000 000 "
          + ",820 000 008 001 003 " + ",820 005 004 000 002 " + ",820 00C 00B 008 008 "
          + ",0 0,0 0,0 0 0 0,00" + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      function.loadParamBySCM(str4);
      
      
      
      
      
      
      
      
      try {
        Thread.sleep(1000);
     } catch (InterruptedException e) {
        e.printStackTrace();
     }
      // String str1 = "+YAV:0005AABB" + ",822 000 000 007 001 " + ",920
      // 000 000 007 001 " + ",000
      // 001 007 000 000 "
      // + ",000 001 008 000 000 " + ",000 000 004 000 000 " + ",000 000
      // 008 001 003 "
      // + ",000 005 004 000 002 " + ",000 00C 00B 008 008 " + ",0 0,0 0,0
      // 0 0 0,00"
      // + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      // function.loadParamBySCM(str1);
      // String str2 = "+YAV:0005AABB" + ",821 000 000 007 001 " + ",990
      // 000 000 007 001 " + ",000
      // 001 007 000 000 "
      // + ",000 001 008 000 000 " + ",000 000 004 000 000 " + ",000 000
      // 008 001 003 "
      // + ",000 005 004 000 002 " + ",000 00C 00B 008 008 " + ",0 0,0 0,0
      // 0 0 0,00"
      // + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      // function.loadParamBySCM(str2);
    }

  }

  /**
   * 传入未解析数据，解析后存数据库（单片机用）
   * 
   * @param str
   */
  public void loadParamBySCM(String str) {

    String inputProbNum = "";// 板号+端口号
    String createdOn = "";// 采集时间（单片机端）
    Double temperature;// 采集温度
    Double humidity;// 采集湿度
    String displayTemprature = "";// 显示数据温度
    String displayProbNum = "";// 探头编号（客户定制）
    String displayTabName = "";// 显示表名
    String inputTabName = "";// 原数据表名
    

    LabInputParamter labInputParamter;
    LabDisplayParamter labDisplayParamter;
    


    
    
    try {
      String[] paramterStr = SCMUtil.getArrayFromSCM(RegexUtil.getParams(str));// 将长数据按分号分割成数组
      for (int i = 0; i < paramterStr.length; i++) {
        long checkstartTime = System.currentTimeMillis();// 解析开始计时
        String[] paramters = SCMUtil.getParamterFromArray(paramterStr[i]);
        inputProbNum = paramters[0];// 板号+端口号
        createdOn = SCMUtil.getSimpledDateTime();
        temperature = Double.valueOf(paramters[1]);
        humidity = Double.valueOf(paramters[2]);

        // 查找原数据表名
        inputTabName = labDisprobeNumber.get(inputProbNum).getTab_InputName();
        // 查找显示表名
        displayTabName = labDisprobeNumber.get(inputProbNum).getTab_DisplayName();
        // 查找该探头对应的商户自定名
        displayProbNum = labDisprobeNumber.get(inputProbNum).getDisplayProbeNumber();

        if (Double.valueOf(temperature) > tempCheck && Double.valueOf(humidity) > humCheck) {
          // System.out.println(displayTabName + " 优化==== 原始 ：" +
          // temperature + " ==> 显示：" +
          // displayTemprature);
          // System.out.println("\n" + inputProbNum + " " + createdOn
          // + " " + temperature + " " +
          // humidity);

          // 组装原数据对象
          labInputParamter = new LabInputParamter(inputProbNum, createdOn, temperature, humidity, inputTabName);
          // 写入原数据分表（为了数据优化只能舍弃原数据的分表批量业务）
          labInputParamterManager.addLabInputParamter(labInputParamter);//
          // 组装显示显示数据对象
          labDisplayParamter = new LabDisplayParamter(inputProbNum, displayProbNum, createdOn, temperature, humidity, displayTabName);
          // AVG for Temperture 10sec
          labDisplayParamter.setDisTemperature(labInputParamterManager.getAVGInputTemperatureByCreatedOn(labInputParamter, inputTabName));
          // 校准值计算
          labDisplayParamterManager.calParamterByModify(labDisplayParamter, modifys);
          // 加入原始批量数据
          listInputItems.add(labInputParamter);
          // 加入显示批量数据
          listDisplayItems.add(labDisplayParamter);

          long checkendTime = System.currentTimeMillis();// 计时结束
          float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
          System.out.println("第" + w + "批数据解析耗时： " + Float.toString(seconds) + " 秒");
          if (listDisplayItems.size() >= itemsSize) {
           // System.out.println("inputSize  :" + listInputItems.size());
           // System.out.println("DisSize ：" + listDisplayItems.size());
            long checkstartTime2 = System.currentTimeMillis();// 批处理开始计时
            // 写入显示数据表
            labDisplayParamterManager.addListItemsToDiffDisplay(listDisplayItems);
            // 写入原始数据汇总表
            labInputParamterManager.addListItemsToSumInput(listInputItems);
            // 写入显示数据汇总表
            labDisplayParamterManager.addListItemsToSumDisplay(listDisplayItems);
            listDisplayItems.clear();
            listInputItems.clear();
            long checkendTime2 = System.currentTimeMillis();// 计时结束
            float seconds2 = (checkendTime2 - checkstartTime2) / 1000F;// 计算耗时
            System.out.println("=======第" + w + "次批处理耗时： " + Float.toString(seconds2) + " 秒=======");
            w++;
          }
        }
      }

    } catch (Exception e) {
      System.out.println("异常原因： " + e);
    }
  }
}
