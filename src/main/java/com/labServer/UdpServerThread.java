package com.labServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.labServer.model.LabDisplayParamter;
import com.labServer.model.LabInputParamter;

public class UdpServerThread implements Runnable {
	DatagramSocket socket = null;
	DatagramPacket packet = null;
	BlockingQueue<List<LabInputParamter>> listInputItems = new ArrayBlockingQueue<List<LabInputParamter>>(2);// 批量原数据集合
	BlockingQueue<List<LabDisplayParamter>> listDisplayItems = new ArrayBlockingQueue<List<LabDisplayParamter>>(2);// 批量显示数据集合
	Function fc = new Function(listInputItems, listDisplayItems);
	
	public UdpServerThread(DatagramSocket socket, DatagramPacket packet) {
		this.socket = socket;
		this.packet = packet;
	}

	public void run() {
		String info = null;
		InetAddress address = null;
		int port = 808;// 返回客户端时传入的服务器监听端口
		byte[] data2 = null;
		DatagramPacket packet2 = null;
		try {
			System.out.println("Start...");
			info = new String(packet.getData(), 0, packet.getLength());
			System.out.println("服务器收到单片机：" + info);
			fc.loadParamBySCM(info);
			address = packet.getAddress();
			port = packet.getPort();
			data2 = "Get Message!".getBytes();
			packet2 = new DatagramPacket(data2, data2.length, address, port);
			socket.send(packet2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}