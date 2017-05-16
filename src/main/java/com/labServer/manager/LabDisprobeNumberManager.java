package com.labServer.manager;

import org.apache.ibatis.session.SqlSession;

import com.labServer.Dao.LabDisprobeNumberMapper;
import com.labServer.Util.MyBatisUtil;

public class LabDisprobeNumberManager {

  public void getDisprobeNumberByInputProbNum(String inputProbeNumber) {
    SqlSession sqlSession = MyBatisUtil.getSqlSession();
    LabDisprobeNumberMapper mapper = sqlSession.getMapper(LabDisprobeNumberMapper.class);
    String name = mapper.getDisprobeNumberByInputProbNum(inputProbeNumber).getDisplayProbeNumber();
    sqlSession.close();
    System.out.println("2223:  " + name);


  }



}
