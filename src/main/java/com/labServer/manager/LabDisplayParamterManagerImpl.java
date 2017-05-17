package com.labServer.manager;

import org.apache.ibatis.session.SqlSession;

import com.labServer.Dao.LabDisplayParamterMapper;
import com.labServer.Dao.LabInputParamterMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.entity.LabDisplayParamter;

public class LabDisplayParamterManagerImpl implements LabDisplayParamterManager {

  /**
   * 新增显示数据
   * 
   * @param labInputParamter
   */
  public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabDisplayParamterMapper labDisplayParamterMapper = sqlSession.getMapper(LabDisplayParamterMapper.class);
    labDisplayParamter.getDisTemperature();
    
    labDisplayParamterMapper.insertLabDisplayParamter(labDisplayParamter);
    sqlSession.commit();
    sqlSession.close();

  }

  /**
   * 新增显示数据,动态表名
   * 
   * @param labInputParamter
   */
  public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter, String displayTable) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabDisplayParamterMapper labDisplayParamterMapper =
        sqlSession.getMapper(LabDisplayParamterMapper.class);
    labDisplayParamterMapper.insertLabDisplayParamterByDisplayTable(labDisplayParamter,
        displayTable);
    sqlSession.commit();
    sqlSession.close();

  }



}
