package com.labServer.labServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.labServer.Dao.LabModifyMapper;
import com.labServer.entity.LabDisprobeNumber;
import com.labServer.entity.LabInputParamter;
import com.labServer.entity.LabModify;
import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisprobeNumberManager;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabModifyManager;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		long checkstartTime = System.currentTimeMillis();// 开始计时
		// labModifyManager = new LabModifyManager();
		// List<LabModify> labModify =
		// labModifyManager.getLabModifyByInputProbNum("8AD0000101");

		LabInputParamter labInputParamter = new LabInputParamter();
		labInputParamter.setCreatedOn("2017-01-01 00:00:00");
		labInputParamter.setInputProbeNumber("8AD0000101");
		labInputParamter.setInputTemperature(Double.valueOf("33.33"));
		labInputParamter.setInputHumidity(Double.valueOf("22.22"));
		//labInputParamter.setInputTable("lab_inputparamter0101");
		String inputTable ="lab_inputparamter0101";
		LabInputParamterManager labInputParamterManager = new LabInputParamterManager();
		labInputParamterManager.addLabInputParamterByDynamicTableName(labInputParamter,inputTable);

		long checkendTime = System.currentTimeMillis();// 计时结束
		float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
		System.out.println("插入数据完毕耗时： " + Float.toString(seconds) + " seconds.");

	}

	public static Date getSimpledDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time = null;
		try {
			time = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

}
