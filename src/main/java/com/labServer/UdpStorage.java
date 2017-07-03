package com.labServer;

import java.util.ArrayList;
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

	public UdpStorage(BlockingQueue<LabDisplayParamter> displayQueue, BlockingQueue<LabInputParamter> inputQueue) {
		this.displayQueue = displayQueue;
		this.inputQueue = inputQueue;
	}

	@Override
	public void run() {
		System.out.println("开始批处理...");
		while (true) {
			if (inputQueue.size() > 8) {
				System.out.println("队列中数据已满8条，进行存储 ：" + inputQueue.size());
				List<LabInputParamter> listInputItems = new ArrayList<>();// 批量原数据集合
				List<LabDisplayParamter> listDisplayItems = new ArrayList<>();// 批量显示数据集合

				System.out.println("批处理线程：" + inputQueue.size());
				System.out.println("队列数据转移前，队列长度: " + inputQueue.size() + "," + displayQueue.size());
				inputQueue.drainTo(listInputItems);// 从队列中批量取出并存至集合
				displayQueue.drainTo(listDisplayItems);// 从队列中批量取出并存至集合
				System.out.println("队列数据转移后，队列长度: " + inputQueue.size() + "," + displayQueue.size());
				long checkstartTime2 = System.currentTimeMillis();// 批处理开始计时
				// 写入显示数据表
				labDisplayParamterManager.addListItemsToDiffDisplay(listDisplayItems);
				System.out.println("写入显示数据表耗时 ： " + (System.currentTimeMillis() - checkstartTime2));
				checkstartTime2 = System.currentTimeMillis();
				// 写入原始数据汇总表
				labInputParamterManager.addListItemsToSumInput(listInputItems);
				System.out.println("写入原始数据汇总表耗时 ： " + (System.currentTimeMillis() - checkstartTime2));
				checkstartTime2 = System.currentTimeMillis();
				// 写入显示数据汇总表
				labDisplayParamterManager.addListItemsToSumDisplay(listDisplayItems);
				System.out.println("写入显示数据汇总表耗时 ： " + (System.currentTimeMillis() - checkstartTime2));
				listDisplayItems.clear();
				listInputItems.clear();
			}
		}
	}
}
