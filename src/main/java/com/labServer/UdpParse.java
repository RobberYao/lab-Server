package com.labServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisplayParamterManagerImpl;
import com.labServer.manager.LabDisprobeNumberManager;
import com.labServer.manager.LabDisprobeNumberManagerImpl;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabInputParamterManagerImpl;
import com.labServer.manager.LabModifyManager;
import com.labServer.manager.LabModifyManagerImpl;
import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabDisprobeNumber;
import com.labServer.model.LabInputParamter;
import com.labServer.model.LabModify;
import com.labServer.util.RegexUtil;
import com.labServer.util.SCMUtil;

public class UdpParse implements Runnable {

	private BlockingQueue<String> reciverQueue;
	private BlockingQueue<LabDisplayParamter> displayQueue;
	private BlockingQueue<LabInputParamter> inputQueue;
	private LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
	private LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
	// 查找配置信息(预加载).
	private LabModifyManager labModifyManager = new LabModifyManagerImpl();
	private static LabDisprobeNumberManager labDisprobeNumberManager = new LabDisprobeNumberManagerImpl();
	private static Map<String, LabDisprobeNumber> labDisprobeNumber = labDisprobeNumberManager.getSumDisprobeNumber();// 显示数据实例
	// 查找该探头的校准值(预加载)
	Map<String, LabModify> modifys = labModifyManager.getSumLabModify();

	private static int resetInit = 0;// 定时刷新预加载信息
	private final double tempCheck = -25.00;// 设置25摄氏度为最低采集温度
	private final double humCheck = -20.00;// 设置20度为最低采集湿度

	String inputProbNum = "";// 板号+端口号
	String createdOn = "";// 采集时间（单片机端）
	Double temperature;// 采集温度
	Double humidity;// 采集湿度
	String displayTemprature = "";// 显示数据温度
	String displayProbNum = "";// 探头编号（客户定制）
	String displayTabName = "";// 显示表名
	String inputTabName = "";// 原数据表名

	LabInputParamter labInputParamter;
	LabDisplayParamter labDisplayParamter;
	public UdpParse(BlockingQueue<String> reciverQueue, BlockingQueue<LabDisplayParamter> displayQueue,
			BlockingQueue<LabInputParamter> inputQueue) {
		this.reciverQueue = reciverQueue;
		this.displayQueue = displayQueue;
		this.inputQueue = inputQueue;
	}

	@Override
	public void run() {
		System.out.println("Parsing...");
		while (true) {
			try {
				if (reciverQueue.size() > 0) {
					String[] paramterStr = SCMUtil.getArrayFromSCM(RegexUtil.getParams(reciverQueue.take()));// 将长数据按分号分割成数组
					for (int i = 0; i < paramterStr.length; i++) {
						long checkstartTime = System.currentTimeMillis();// 解析开始计时
						String[] paramters = SCMUtil.getParamterFromArray(paramterStr[i]);
						inputProbNum = paramters[0];// 板号+端口号
						createdOn = SCMUtil.getSimpledDateTime();
						temperature = Double.valueOf(paramters[1]);
						humidity = Double.valueOf(paramters[2]);
						// 查找原数据表名
						inputTabName = labDisprobeNumber.get(inputProbNum).getTab_InputName();
						// 查找显示表名
						displayTabName = labDisprobeNumber.get(inputProbNum).getTab_DisplayName();
						// 查找该探头对应的商户自定名
						displayProbNum = labDisprobeNumber.get(inputProbNum).getDisplayProbeNumber();

						if (Double.valueOf(temperature) > tempCheck && Double.valueOf(humidity) > humCheck) {
							// 组装原数据对象
							labInputParamter = new LabInputParamter(inputProbNum, createdOn, temperature, humidity,
									inputTabName);
							// 写入原数据分表（为了数据优化只能舍弃原数据的分表批量业务）
							labInputParamterManager.addLabInputParamter(labInputParamter);//
							// 组装显示显示数据对象
							labDisplayParamter = new LabDisplayParamter(inputProbNum, displayProbNum, createdOn, temperature,
									humidity, displayTabName);
							// AVG for Temperture 10sec
							labDisplayParamter.setDisTemperature(
									labInputParamterManager.getAVGInputTemperatureByCreatedOn(labInputParamter, inputTabName));
							// 校准值计算
							labDisplayParamterManager.calParamterByModify(labDisplayParamter, modifys);
							// 加入原始批量数据
							inputQueue.add(labInputParamter);
							// 加入显示批量数据
							displayQueue.add(labDisplayParamter);
							System.out.println("解析线程：原始数据容器内容  ：" + inputQueue.size() + "条" + " ,显示数据容量内容  ："
									+ displayQueue.size() + "条");
							long checkendTime = System.currentTimeMillis();// 计时结束
							float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
							System.out.println("解析线程：解析耗时： " + Float.toString(seconds) + " 秒");
						}
					}
					resetInit++;
					if (resetInit > 20) {
						init();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 初始化预加载信息
	 */
	private void init() {
		resetInit = 0;
		labDisprobeNumber = labDisprobeNumberManager.getSumDisprobeNumber();// 显示数据实例
		modifys = labModifyManager.getSumLabModify();
		System.out.println("into over");
	}

}
