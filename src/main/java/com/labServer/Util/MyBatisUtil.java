package com.labServer.Util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
//http://www.cnblogs.com/xdp-gacl/p/4262895.html
  /**
   * 获取SqlSessionFactory
   * 
   * @return
   */
  public static SqlSessionFactory getSqlSessionFactory() {
    String resource = "conf.xml";
    InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
    return factory;

  }


  public static SqlSession getSqlSession() {
    return getSqlSessionFactory().openSession();

  }


  public static SqlSession getSqlSession(boolean isAutoCommit) {
    return getSqlSessionFactory().openSession(isAutoCommit);

  }



}
