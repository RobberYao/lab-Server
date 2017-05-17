package com.labServer.manager;

import org.apache.ibatis.session.SqlSession;
import com.labServer.Dao.LabInputParamterMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.entity.LabInputParamter;

public class LabInputParamterManagerImpl {

	/**
	 * 新增原始数据
	 * 
	 * @param labInputParamter
	 */
	public void addLabInputParamter(LabInputParamter labInputParamter) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabInputParamterMapper labInputParamterMapper = sqlSession.getMapper(LabInputParamterMapper.class);
		int i = labInputParamterMapper.insertLabInputParamter(labInputParamter);
		sqlSession.commit();//手动提交事务
		sqlSession.close();
		System.out.println("i   :" + i);
	}
	
	/**
	 * 新增原始数据,动态表名
	 * 
	 * @param labInputParamter
	 */
	public void addLabInputParamterByDynamicTableName(LabInputParamter labInputParamter,String inputTable) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabInputParamterMapper labInputParamterMapper = sqlSession.getMapper(LabInputParamterMapper.class);
		int i = labInputParamterMapper.insertLabInputParamterByInputTable(labInputParamter,inputTable);
		sqlSession.commit();//手动提交事务
		sqlSession.close();
		System.out.println("i   :" + i);
	}
	
	
	
	

}
