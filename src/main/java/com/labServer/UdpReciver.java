package com.labServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.BlockingQueue;

public class UdpReciver implements Runnable {

	private BlockingQueue<String> reciverQueue;
	DatagramSocket socket = null;
	DatagramPacket packet = null;

	public UdpReciver(BlockingQueue<String> reciverQueue, DatagramSocket socket, DatagramPacket packet) {
		this.reciverQueue = reciverQueue;
		this.socket = socket;
		this.packet = packet;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("***服务器端启动，等待发送数据***");
				byte[] data = new byte[512];// 创建字节数组，指定接收的数据包的大小
				packet = new DatagramPacket(data, data.length);
				socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
				String messageInfo = new String(packet.getData(), 0, packet.getLength());
				reciverQueue.add(messageInfo);// 加入队列
				System.out.println("接收线程：服务器收到单片机：" + messageInfo);
				System.out.println("接收线程队列数："+reciverQueue.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
