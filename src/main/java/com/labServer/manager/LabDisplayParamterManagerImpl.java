package com.labServer.manager;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import com.labServer.Util.MyBatisUtil;
import com.labServer.mapping.LabDisplayParamterMapper;
import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabModify;

public class LabDisplayParamterManagerImpl implements LabDisplayParamterManager {

	/**
	 * 新增显示数据(暂不用)
	 * 
	 * @param labInputParamter
	 */
/*	public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter, List<Map<String, Double>> modifys) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabDisplayParamterMapper labDisplayParamterMapper = sqlSession.getMapper(LabDisplayParamterMapper.class);
		calParamterByModify(labDisplayParamter, modifys);// 计算校准值
		labDisplayParamterMapper.insertLabDisplayParamter(labDisplayParamter);
		sqlSession.commit();
		sqlSession.close();

	}*/

	/**
	 * 新增显示数据,动态表名(暂不用)
	 * 
	 * @param labInputParamter
	 */
/*	public void addLabDiaplayParamter(LabDisplayParamter labDisplayParamter, List<Map<String, Double>> modifys,
			String displayTable) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabDisplayParamterMapper labDisplayParamterMapper = sqlSession.getMapper(LabDisplayParamterMapper.class);
		calParamterByModify(labDisplayParamter, modifys);// 计算校准值
		labDisplayParamterMapper.insertLabDisplayParamterByDisplayTable(labDisplayParamter, displayTable);
		sqlSession.commit();
		sqlSession.close();

	}*/

	/**
	 * 传入显示探头参数对象、校准值，并计算。
	 * 
	 * @param labDisplayParamter
	 * @param modify
	 * @return
	 */
	public LabDisplayParamter calParamterByModify(LabDisplayParamter labDisplayParamter,
			Map<String, LabModify> modifys) {
		// 遍历校准 并对温湿进行赋值（暂无光照）
		String inputNum=labDisplayParamter.getInputProbeNumber();
			labDisplayParamter.setDisTemperature(modifys.get(inputNum).getModifyTemp() + labDisplayParamter.getDisTemperature());
			labDisplayParamter.setDisHumidity(modifys.get(inputNum).getModifyHum() + labDisplayParamter.getDisHumidity());
		
		return labDisplayParamter;

	}

	
	
	/**
	 * 插入显示数据汇总表
	 * 
	 */
	public void addListItemsToSumDisplay(@Param("list") List <LabDisplayParamter> list) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.labServer.mapping.LabDisplayParamterMapper.addListItemsToSumDisplay";
		sqlSession.insert(statement, list);
		sqlSession.commit();// 手动提交事务
		sqlSession.close();
	}
	
	/**
	 * 插入显示数据分表
	 * 
	 * 
	 */
	public void addListItemsToDiffDisplay(@Param("list") List<LabDisplayParamter> list) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		String statement = "com.labServer.mapping.LabDisplayParamterMapper.addListItemsToDiffDisplay";
		sqlSession.insert(statement, list);
		sqlSession.commit();// 手动提交事务
		sqlSession.close();
	}

}
