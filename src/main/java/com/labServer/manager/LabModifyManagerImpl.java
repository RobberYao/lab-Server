package com.labServer.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.labServer.Util.MyBatisUtil;
import com.labServer.mapping.LabModifyMapper;
import com.labServer.model.LabModify;

public class LabModifyManagerImpl implements LabModifyManager {

	/**
	 * 通过原探头名找到探头校正实例
	 * 
	 * @param inputProbreNumber
	 * @return
	 */
	public List<Map<String, Double>> getLabModifyByInputProbNum(String inputProbreNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabModifyMapper mapper = sqlSession.getMapper(LabModifyMapper.class);
		List<LabModify> labModifys = mapper.getLabModifyByInputProbNum(inputProbreNumber);
		List<Map<String, Double>> modify = new ArrayList<Map<String, Double>>();
		sqlSession.commit();
		sqlSession.close();

		HashMap<String, Double> modifyMap = new HashMap<String, Double>();
		for (LabModify labModify : labModifys) {
			modifyMap.put(labModify.getModifyParamter(), labModify.getModifyNumber());
			modify.add(modifyMap);
		}
		return modify;
	}
}
