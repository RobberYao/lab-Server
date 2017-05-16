package com.labServer.manager;

import org.apache.ibatis.session.SqlSession;
import com.labServer.Dao.LabDisprobeNumberMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.entity.LabDisprobeNumber;

public class LabDisprobeNumberManager {

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
		sqlSession.close();
		return labDisprobeNumber;
	}

}
