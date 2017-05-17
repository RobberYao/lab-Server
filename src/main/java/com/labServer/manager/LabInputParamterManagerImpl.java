package com.labServer.manager;

import org.apache.ibatis.session.SqlSession;
import com.labServer.Dao.LabInputParamterMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.entity.LabInputParamter;

public class LabInputParamterManagerImpl implements LabInputParamterManager {

  /**
   * 新增原始数据
   * 
   * @param labInputParamter
   */
  public void addLabInputParamter(LabInputParamter labInputParamter) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabInputParamterMapper labInputParamterMapper =
        sqlSession.getMapper(LabInputParamterMapper.class);
    int i = labInputParamterMapper.insertLabInputParamter(labInputParamter);
    sqlSession.commit();// 手动提交事务
    sqlSession.close();
   // System.out.println("i   :" + i);
  }

  /**
   * 新增原始数据,动态表名
   * 
   * @param labInputParamter
   */
  public void addLabInputParamterByDynamicTableName(LabInputParamter labInputParamter,
      String inputTable) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabInputParamterMapper labInputParamterMapper =
        sqlSession.getMapper(LabInputParamterMapper.class);
    int i = labInputParamterMapper.insertLabInputParamterByInputTable(labInputParamter, inputTable);
    sqlSession.commit();// 手动提交事务
    sqlSession.close();
  }



  /**
   * 查找出10秒内的平均原数据
   * 
   * @param labInputParamter
   * @param inputTable
   * @return
   */
  public Double getAVGInputTemperatureByCreatedOn(LabInputParamter labInputParamter,
      String inputTable) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabInputParamterMapper labInputParamterMapper = sqlSession.getMapper(LabInputParamterMapper.class);
    Double avgInputTemperature = labInputParamterMapper.findAVGInputTemperature(labInputParamter, inputTable);  
    avgInputTemperature = OptimizedTemp(labInputParamter.getInputTemperature(), avgInputTemperature);
    sqlSession.commit();
    sqlSession.close();
    //System.out.println("avgInputTemperature  :" + avgInputTemperature);
    return avgInputTemperature;

  }

  /**
   * 数据优化：如果有平均值 则返回平均值 平均值为null 返回实际温度
   */

  public Double OptimizedTemp(Double temperature, Double avgTemperature) {
      // 如果平均值不为Null 则返回平均值 反之返回实际温度
      if (avgTemperature != null) {
        //  System.out.println("==============================平均avg :"+avgTemperature);
          return avgTemperature;
      }
    //  System.out.println("==============================平均avg : null");
      return temperature;
  }
}
