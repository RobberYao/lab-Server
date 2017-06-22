package com.labServer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisplayParamterManagerImpl;
import com.labServer.manager.LabInputParamterManager;
import com.labServer.manager.LabInputParamterManagerImpl;
import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabInputParamter;

public class UdpStorage implements Runnable {

	private BlockingQueue<LabDisplayParamter> displayQueue;
	private BlockingQueue<LabInputParamter> inputQueue;
	private LabInputParamterManager labInputParamterManager = new LabInputParamterManagerImpl();
	private LabDisplayParamterManager labDisplayParamterManager = new LabDisplayParamterManagerImpl();
	List<LabInputParamter> listInputItems = null;// 批量原数据集合
	List<LabDisplayParamter> listDisplayItems = null;// 批量显示数据集合

	public UdpStorage(BlockingQueue<LabDisplayParamter> displayQueue, BlockingQueue<LabInputParamter> inputQueue,
			List<LabDisplayParamter> listDisplayItems, List<LabInputParamter> listInputItems) {
		this.displayQueue = displayQueue;
		this.inputQueue = inputQueue;
		this.listDisplayItems = listDisplayItems;
		this.listInputItems = listInputItems;

	}

	@Override
	public void run() {

		System.out.println("开始批处理...");
		while (true) {
			if (inputQueue.size() > 8) {
				System.out.println("批处理线程：" + inputQueue.size());
				inputQueue.drainTo(listInputItems);// 从队列中批量取出并存至集合
				displayQueue.drainTo(listDisplayItems);// 从队列中批量取出并存至集合
				long checkstartTime2 = System.currentTimeMillis();// 批处理开始计时
				// 写入显示数据表
				labDisplayParamterManager.addListItemsToDiffDisplay(listDisplayItems);
				// 写入原始数据汇总表
				labInputParamterManager.addListItemsToSumInput(listInputItems);
				// 写入显示数据汇总表
				labDisplayParamterManager.addListItemsToSumDisplay(listDisplayItems);
				listDisplayItems.clear();
				listInputItems.clear();
				long checkendTime2 = System.currentTimeMillis();// 计时结束
				float seconds2 = (checkendTime2 - checkstartTime2) / 1000F;// 计算耗时
				System.out.println("批处理线程：耗时： " + Float.toString(seconds2) + " 秒=====");

			}
		}
	}
}
