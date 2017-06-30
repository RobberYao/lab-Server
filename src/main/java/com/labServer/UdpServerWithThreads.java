package com.labServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabInputParamter;

public class UdpServerWithThreads {

	public static void main(String[] args) throws IOException {
		BlockingQueue<String> reciverQueue = new LinkedBlockingDeque<>();
		BlockingQueue<LabDisplayParamter> displayQueue = new LinkedBlockingQueue<>();
		BlockingQueue<LabInputParamter> inputQueue = new LinkedBlockingQueue<>();
		List<LabInputParamter> listInputItems = new ArrayList<LabInputParamter>();// 批量原数据集合
		List<LabDisplayParamter> listDisplayItems = new ArrayList<LabDisplayParamter>();// 批量显示数据集合

		DatagramSocket socket = new DatagramSocket(808);//
		// 端口号808、IP地址默认为本地127.0.0.1
		DatagramPacket packet = null;

		System.out.println("***服务器2端启动，等待发送数据***");
		System.out.println("Queue Size " + reciverQueue.size());

		// 接收线程实例化
		UdpReciverFor400 udpReciver400 = new UdpReciverFor400(reciverQueue, socket, packet);
		//UdpReciverFor18 udpReciverFor18= new UdpReciverFor18(reciverQueue, socket, packet);
		UdpParse udpParse1 = new UdpParse(reciverQueue, displayQueue, inputQueue);
		UdpParse udpParse2 = new UdpParse(reciverQueue, displayQueue, inputQueue);
		//UdpParse udpParse3 = new UdpParse(reciverQueue, displayQueue, inputQueue);
		//UdpParse udpParse4 = new UdpParse(reciverQueue, displayQueue, inputQueue);
		UdpStorage udpStorage = new UdpStorage(displayQueue, inputQueue,listDisplayItems,listInputItems);

		// 线程池
		ExecutorService service = Executors.newCachedThreadPool();
		// 启动接收线程
		service.execute(udpReciver400);
		//service.execute(udpReciverFor18);
		service.execute(udpParse1);
		service.execute(udpParse2);
		//service.execute(udpParse3);
		//service.execute(udpParse4);
		service.execute(udpStorage);

	}
}
