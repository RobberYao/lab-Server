package com.labServer.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.labServer.entity.LabModify;

public interface LabModifyMapper {

	/**
	 * 通过原探头名找到探头校正实例
	 * 
	 * @param inputProbreNumber
	 * @return
	 */
	@Select("select * from lab_modify where INPUTPROBENUMBER = #{inputProbeNumber}")
	List<LabModify> getLabModifyByInputProbNum(String inputProbeNumber);

}
