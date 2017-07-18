package com.labServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class UdpClient implements Runnable {
	public static Logger log = Logger.getLogger(UdpClient.class);
	
	public static void main(String[] args) throws Exception {

	}

	// String msg = "+YAV:0005AABB,000 000 000 007 001 ,000 000 000 007 001 ,007
	// 001 007 000 000 ,009 001 008 000 000 ,000 000 004 000 000 ,004 000 008
	// 001 003 ,001 005 004 000 002 ,008 00C 00B 008 008 ,0 0,0 0,0 0 0
	// 0,00,FF0203FF,V V V V V V V V,8AD00001,X,EEFF";
	String msg = "";

	public UdpClient(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		//PropertyConfigurator.configure("log4j.properties"); 
		DatagramSocket datagramSocket;
		try {
			int i = 1;
			while (true) {
				//String msg1 = "8AD00001,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000,0000;\"x20\"x17\"x06\"x19\"x10\"x38\"x00\"x00\"x00...";
				datagramSocket = new DatagramSocket();
				InetAddress address = InetAddress.getByName("127.0.0.1");
				// 发送数据
				byte[] buffer = msg.getBytes();
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 808);
				datagramSocket.send(packet);
				log.info("send");
				long before = System.currentTimeMillis();
				// System.out.println(before);
				Thread.sleep(1000);
				// System.out.println(System.currentTimeMillis() - before);
				// 接收数据
				// DatagramPacket inputPacket = new DatagramPacket(new
				// byte[512],
				// 512);
				// datagramSocket.receive(inputPacket);
				// System.out.println(new String(inputPacket.getData(), 0,
				// inputPacket.getLength()));
				// Thread.sleep(100);
				// datagramSocket.close();
				i++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
