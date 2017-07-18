package com.labServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

/**
 * 
 */
public class TcpReciverFor18 implements Runnable {
	public static Logger log = Logger.getLogger(TcpReciverFor18.class);

	private BlockingQueue<String> reciverQueue;
	Socket socket = null;

	public TcpReciverFor18(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			
			
			
			
			// 获取Socket的输出流，用来向客户端（单片机）发送数据
			PrintStream out = new PrintStream(socket.getOutputStream());
			// 获取Socket的输入流，用来接收从客户端（单片机）发送过来的数据
			BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			boolean flag = true;
			while (flag) {
				// 接收从客户端发送过来的数据
				String messageInfo = buf.readLine();
				if (messageInfo == null || "".equals(messageInfo)) {
					log.info("str is null");
					flag = false;
				} else {
					if ("bye".equals(messageInfo)) {
						flag = false;
					} else {
						log.info("TcpReciver18 :" + messageInfo);
						reciverQueue.add(messageInfo);// 加入队列
						log.info("接收队列数：" + reciverQueue.size());
						
						// 原封不懂回送到对应的客户端
						out.println(messageInfo);
					}
				}
			}
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}