package com.labServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;

public class UdpReciverFor400 implements Runnable {

	private BlockingQueue<String> reciverQueue;
	DatagramSocket socket = null;
	DatagramPacket packet = null;

	public UdpReciverFor400(BlockingQueue<String> reciverQueue, DatagramSocket socket, DatagramPacket packet) {
		this.reciverQueue = reciverQueue;
		this.socket = socket;
		this.packet = packet;
	}

	@Override
	public void run() {
		System.out.println("***服务器端启动，等待发送数据***");
		while (true) {
			try {
				//long checkstartTime = System.currentTimeMillis();// 解析开始计时
				String info = null;
				InetAddress address = null;
				int port = 808;// 返回客户端时传入的服务器监听端口
				byte[] data2 = null;
				DatagramPacket packet2 = null;
				byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
				packet = new DatagramPacket(data, data.length);
				socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
				
				String messageInfo = new String(packet.getData(), 0, packet.getLength());
				System.out.println("接收线程：服务器收到单片机：" + messageInfo);
				
				reciverQueue.add(messageInfo);// 加入队列
				System.out.println("接收队列数：" + reciverQueue.size());

				address = packet.getAddress();
				port = packet.getPort();
				data2 = "Get Message!".getBytes();
				packet2 = new DatagramPacket(data2, data2.length, address, port);
				socket.send(packet2);// 返回至单片机
				//long checkendTime = System.currentTimeMillis();// 计时结束
				//float seconds = (checkendTime - checkstartTime) / 1000F;// 计算耗时
				//System.out.println("~~~~~~接收耗时： " + Float.toString(seconds) + " 秒");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
