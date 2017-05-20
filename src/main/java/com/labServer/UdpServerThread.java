package com.labServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.labServer.Util.RegexUtil;
import com.labServer.Util.SCMUtil;
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

public class UdpServerThread implements Runnable {
	private LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
	private LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
	private LabModifyManager labModifyManager = new LabModifyManagerImpl();
	// 查找配置信息(预加载)
	private static LabDisprobeNumberManager labDisprobeNumberManager = new LabDisprobeNumberManagerImpl();
	private static Map<String, LabDisprobeNumber> labDisprobeNumber = labDisprobeNumberManager.getSumDisprobeNumber();// 显示数据实例
	// 查找该探头的校准值(预加载)
	Map<String, LabModify> modifys = labModifyManager.getSumLabModify();
	DatagramSocket socket = null;
	DatagramPacket packet = null;
	private final double tempCheck = -10.00;
	private final double humCheck = 10.00;
	private int itemsSize = 4;// 批处理量
	int w = 1;
	BlockingQueue<List<LabInputParamter>> queuelistInputItems = new ArrayBlockingQueue(2);
	BlockingQueue<List<LabDisplayParamter>> queuelistDisplayItems = new ArrayBlockingQueue(2);

	List<LabInputParamter> listInputItems =null;// 批量原数据集合
	List<LabDisplayParamter> listDisplayItems =null;// 批量显示数据集合

	public UdpServerThread(DatagramSocket socket, DatagramPacket packet,
			BlockingQueue<List<LabInputParamter>> queuelistInputItems,
			BlockingQueue<List<LabDisplayParamter>> queuelistDisplayItems, 
			List<LabInputParamter> listInputItems,
			List<LabDisplayParamter> listDisplayItems) {
		this.socket = socket;
		this.packet = packet;
		this.queuelistInputItems = queuelistInputItems;
		this.queuelistDisplayItems = queuelistDisplayItems;
		this.listInputItems= listInputItems;
		this.listDisplayItems=listDisplayItems;

	}

	public void run() {
		String info = null;
		InetAddress address = null;
		int port = 808;// 返回客户端时传入的服务器监听端口
		byte[] data2 = null;
		DatagramPacket packet2 = null;
		try {
			info = new String(packet.getData(), 0, packet.getLength());
			System.out.println("服务器收到单片机：" + info);
			loadParamBySCM(info);
			address = packet.getAddress();
			port = packet.getPort();
			data2 = "Get Message!".getBytes();
			packet2 = new DatagramPacket(data2, data2.length, address, port);
			socket.send(packet2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 传入未解析数据，解析后存数据库（单片机用）
	 * 
	 * @param str
	 */
	public void loadParamBySCM(String str) {

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

		try {
			String[] paramterStr = SCMUtil.getArrayFromSCM(RegexUtil.getParams(str));// 将长数据按分号分割成数组
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
					listInputItems.add(labInputParamter);
					// 加入显示批量数据
					listDisplayItems.add(labDisplayParamter);
					System.out.println(
							"原始队列大小    ：" + queuelistInputItems.size() + "   原始数据容量  ：" + listInputItems.size());
					System.out.println(
							"显示队列大小    ：" + queuelistDisplayItems.size() + "   原始数据容量  ：" + listDisplayItems.size());
					long checkendTime = System.currentTimeMillis();// 计时结束
					float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
					System.out.println("第" + w + "批数据解析耗时： " + Float.toString(seconds) + " 秒");
					//System.out.println("queusSize ==" + queuelistInputItems.take().size());
					if (listInputItems.size() >= itemsSize) {
						System.out.println("inputSize :" + listInputItems.size());
						System.out.println("DisSize ：" + listDisplayItems.size());
						long checkstartTime2 = System.currentTimeMillis();// 批处理开始计时
						// 写入显示数据表
						labDisplayParamterManager.addListItemsToDiffDisplay(listDisplayItems );
						// 写入原始数据汇总表
						labInputParamterManager.addListItemsToSumInput(listInputItems );
						// 写入显示数据汇总表
						labDisplayParamterManager.addListItemsToSumDisplay(listDisplayItems );
						listDisplayItems.clear();
						listInputItems.clear();
						long checkendTime2 = System.currentTimeMillis();// 计时结束
						float seconds2 = (checkendTime2 - checkstartTime2) / 1000F;// 计算耗时
						System.out.println("=======" +createdOn + "  批处理耗时： " + Float.toString(seconds2) + " 秒=======");
						w++;

					} else {
						// 小于则存入队列继续使用
//						queuelistInputItems.take().add(labInputParamter);
//						queuelistDisplayItems.take().add(labDisplayParamter);
						// queuelistInputItems.add(listInputItems);
						// queuelistDisplayItems.add(listDisplayItems);
						// System.out.println(
						// "===原始队列大小 ：" + queuelistInputItems.size() + " 原始数据容量
						// ：" + listInputItems.size());
						// System.out.println("===显示队列大小 ：" +
						// queuelistDisplayItems.size() + " 原始数据容量 ："
						// + listDisplayItems.size());
					}
				}
			}

		} catch (Exception e) {
			System.out.println("异常原因： " + e);
		}
	}

}