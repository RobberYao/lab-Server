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



public class Function {

  private final double tempCheck = -10.00;
  private final double humCheck = 10.00;
  private LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
  private LabDisprobeNumberManager labDisprobeNumberManager = new LabDisprobeNumberManagerImpl();
  private LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
  private LabModifyManager labModifyManager = new LabModifyManagerImpl();


  public static void main(String[] args) {

    Function function = new Function();
    for (int i = 0; i < 100; i++) {
      String str = "+YAV:0005AABB" + ",820 000 000 007 001 " + ",820 000 000 007 001 "
          + ",000 001 007 000 000 " + ",000 001 008 000 000 " + ",000 000 004 000 000 "
          + ",000 000 008 001 003 " + ",000 005 004 000 002 " + ",000 00C 00B 008 008 "
          + ",0 0,0 0,0 0 0 0,00" + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      function.loadParamBySCM(str);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      // String str1 = "+YAV:0005AABB" + ",822 000 000 007 001 " + ",920 000 000 007 001 " + ",000
      // 001 007 000 000 "
      // + ",000 001 008 000 000 " + ",000 000 004 000 000 " + ",000 000 008 001 003 "
      // + ",000 005 004 000 002 " + ",000 00C 00B 008 008 " + ",0 0,0 0,0 0 0 0,00"
      // + ",FF0203FF,V V V V V V V V" + ",8AD00001,X,EEFF";
      // function.loadParamBySCM(str1);
      // String str2 = "+YAV:0005AABB" + ",821 000 000 007 001 " + ",990 000 000 007 001 " + ",000
      // 001 007 000 000 "
      // + ",000 001 008 000 000 " + ",000 000 004 000 000 " + ",000 000 008 001 003 "
      // + ",000 005 004 000 002 " + ",000 00C 00B 008 008 " + ",0 0,0 0,0 0 0 0,00"
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
    long checkstartTime = System.currentTimeMillis();// 开始计时
    String inputProbNum = "";// 板号+端口号
    String createdOn = "";// 采集时间（单片机端）
    Double temperature;// 采集温度
    Double humidity;// 采集湿度
    String displayTemprature = "";// 显示数据温度
    String displayProbNum = "";// 探头编号（客户定制）
    String displayTabName = "";// 显示表名
    String inputTabName = "";// 原数据表名
    LabDisprobeNumber labDisprobeNumber;// 显示数据实例
    LabInputParamter labInputParamter;
    LabDisplayParamter labDisplayParamter;
    List<Map<String, Double>> modifys;

    List<LabInputParamter> listInputItems = new ArrayList<LabInputParamter>();// 批量原数据
    List<LabDisplayParamter> listDisplayItems = new ArrayList<LabDisplayParamter>();// 批量显示数据



    try {
      String[] paramterStr = SCMUtil.getArrayFromSCM(RegexUtil.getParams(str));// 将长数据按分号分割成数组
      for (int i = 0; i < paramterStr.length; i++) {
        String[] paramters = SCMUtil.getParamterFromArray(paramterStr[i]);
        inputProbNum = paramters[0];// 板号+端口号
        createdOn = SCMUtil.getSimpledDateTime();
        temperature = Double.valueOf(paramters[1]);
        humidity = Double.valueOf(paramters[2]);
        
        //查找配置信息
        labDisprobeNumber = labDisprobeNumberManager.getDisprobeNumberByInputProbNum(inputProbNum);
        // 查找原数据表名
        inputTabName = labDisprobeNumber.getTab_InputName();
        // 查找显示表名
        displayTabName = labDisprobeNumber.getTab_DisplayName();
        // 查找该探头对应的商户自定名
        displayProbNum = labDisprobeNumber.getDisplayProbeNumber();
        // 查找该探头的校准值
        modifys = labModifyManager.getLabModifyByInputProbNum(inputProbNum);

        if (Double.valueOf(temperature) > tempCheck && Double.valueOf(humidity) > humCheck) {
          // System.out.println(displayTabName + " 优化==== 原始 ：" + temperature + " ==> 显示：" +
          // displayTemprature);
          // System.out.println("\n" + inputProbNum + " " + createdOn + " " + temperature + " " +
          // humidity);
          
          //组装原数据对象
          labInputParamter = new LabInputParamter(inputProbNum, createdOn, temperature, humidity);
          // 写入原数据表（为了数据优化只能舍弃原数据的分表批量业务）
         // labInputParamterManager.addLabInputParamterByDynamicTableName(labInputParamter, inputTabName);//
          //加入原始批量数据
          listInputItems.add(labInputParamter);
          //组装显示显示数据对象
          labDisplayParamter = new LabDisplayParamter(inputProbNum, displayProbNum, createdOn, temperature, humidity);
          // AVG for Temperture 10sec
          labDisplayParamter.setDisTemperature(labInputParamterManager.getAVGInputTemperatureByCreatedOn(labInputParamter, inputTabName));
          //加入显示批量数据
          listDisplayItems.add(labDisplayParamter);
          
          //校准值计算
          labDisplayParamterManager.calParamterByModify(labDisplayParamter, modifys);
          // 写入显示数据表
          labDisplayParamterManager.addLabDiaplayParamter(labDisplayParamter, modifys, displayTabName);
          // 写入原始数据汇总表
          labInputParamterManager.addLabInputParamter(labInputParamter);
          // 写入显示数据汇总表
          labDisplayParamterManager.addLabDiaplayParamter(labDisplayParamter, modifys);
          
          

        }
      }

      long checkendTime = System.currentTimeMillis();// 计时结束
      float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
      System.out.println("插入数据完毕耗时： " + Float.toString(seconds) + " seconds.");

    } catch (Exception e) {
      System.out.println("异常原因： " + e);
    }
  }
}
