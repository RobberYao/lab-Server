package com.labServer.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.labServer.mapper.LabDisprobeNumberMapper;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabModify;
import com.labServer.util.MyBatisUtil;

public class LabDisprobeNumberManagerImpl implements LabDisprobeNumberManager {

	/**
	 * 通过原探头号查找对应探头映射表实力
	 * 
	 * @param inputProbeNumber
	 * @return
	 */
	public LabDisprobeNumber getDisprobeNumberByInputProbNum(String inputProbeNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabDisprobeNumberMapper mapper = sqlSession.getMapper(LabDisprobeNumberMapper.class);
		LabDisprobeNumber labDisprobeNumber = mapper.getDisprobeNumberByInputProbNum(inputProbeNumber);
		sqlSession.commit();
		sqlSession.close();
		return labDisprobeNumber;
	}

	public Map<String, LabDisprobeNumber> getSumDisprobeNumber() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabDisprobeNumberMapper mapper = sqlSession.getMapper(LabDisprobeNumberMapper.class);
		List<LabDisprobeNumber> labDisprobeNumbers = mapper.getSumDisprobeNumber();
		Map<String, LabDisprobeNumber> LabDisprobeNumberMaps = new HashMap<String, LabDisprobeNumber>();
		sqlSession.commit();
		sqlSession.close();
		for (LabDisprobeNumber labDisprobeNumber : labDisprobeNumbers) {
			LabDisprobeNumberMaps.put(labDisprobeNumber.getInputProbeNumber(), labDisprobeNumber);
		}
		return LabDisprobeNumberMaps;
	}

}
