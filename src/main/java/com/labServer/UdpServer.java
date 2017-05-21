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

public class UdpServer {

	public static void main(String[] args) throws IOException {
		List<LabInputParamter> listInputItems = new ArrayList<LabInputParamter>();// 批量原数据集合
		List<LabDisplayParamter> listDisplayItems = new ArrayList<LabDisplayParamter>();// 批量显示数据集合

		DatagramSocket socket = new DatagramSocket(808);// 端口号9080、IP地址默认为本地127.0.0.1
		DatagramPacket packet = null;
		byte[] data = null;
		System.out.println("***服务器端启动，等待发送数据***");
		while (true) {
			data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
			packet = new DatagramPacket(data, data.length);
			socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
			UdpServerThread udp = new UdpServerThread(socket, packet, listInputItems, listDisplayItems);
			new Thread(udp).start();
			// System.out.println("服务器端被连接过的次数：" + count);
			InetAddress address = packet.getAddress();
			// System.out.println("当前客户端的IP为：" + address.getHostAddress());
		}
	}
}
