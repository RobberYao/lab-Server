package com.labServer.manager;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.labServer.Dao.LabModifyMapper;
import com.labServer.Util.MyBatisUtil;
import com.labServer.entity.LabModify;

public class LabModifyManager {

	/**
	 * 通过原探头名找到探头校正实例
	 * 
	 * @param inputProbreNumber
	 * @return
	 */
	public List<LabModify> getLabModifyByInputProbNum(String inputProbreNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		LabModifyMapper mapper = sqlSession.getMapper(LabModifyMapper.class);
		List<LabModify> labModify = mapper.getLabModifyByInputProbNum(inputProbreNumber);
		sqlSession.close();
		return labModify;

	}

}
