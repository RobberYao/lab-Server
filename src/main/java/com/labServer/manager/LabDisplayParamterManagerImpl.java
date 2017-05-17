package com.labServer.manager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.labServer.Dao.LabDisplayParamterMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.model.LabDisplayParamter;


public class LabDisplayParamterManagerImpl implements LabDisplayParamterManager {

  /**
   * 新增显示数据
   * 
   * @param labInputParamter
   */
  public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modifys) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabDisplayParamterMapper labDisplayParamterMapper = sqlSession.getMapper(LabDisplayParamterMapper.class);
    calParamterByModify(labDisplayParamter, modifys);// 计算校准值
    labDisplayParamterMapper.insertLabDisplayParamter(labDisplayParamter);
    sqlSession.commit();
    sqlSession.close();

  }

  /**
   * 新增显示数据,动态表名
   * 
   * @param labInputParamter
   */
  public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modifys, String displayTable) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabDisplayParamterMapper labDisplayParamterMapper =
        sqlSession.getMapper(LabDisplayParamterMapper.class);
    calParamterByModify(labDisplayParamter, modifys);// 计算校准值
    labDisplayParamterMapper.insertLabDisplayParamterByDisplayTable(labDisplayParamter,
        displayTable);
    sqlSession.commit();
    sqlSession.close();

  }

  /**
   * 传入显示探头参数对象、校准值，并计算。
   * 
   * @param labDisplayParamter
   * @param modify
   * @return
   */
  public LabDisplayParamter calParamterByModify(LabDisplayParamter labDisplayParamter,
      List<Map<String, Double>> modifys) {
    // 遍历校准 并对温湿进行赋值（暂无光照）
    for (Map<String, Double> modify : modifys) {
      labDisplayParamter
          .setDisTemperature(modify.get("DISTEMPERATURE") + labDisplayParamter.getDisTemperature());
      labDisplayParamter
          .setDisHumidity(modify.get("DISHUMIDITY") + labDisplayParamter.getDisHumidity());
    }
    return labDisplayParamter;

  }



}
