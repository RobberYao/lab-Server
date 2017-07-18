package com.labServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class TcpClient implements Runnable {
	public static Logger log = Logger.getLogger(TcpClient.class);

	String msg = "";
	Socket socket = null;

	OutputStream socketOut = null;
	BufferedReader br = null;

	public TcpClient(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		try {
			while (true) {
				socket = new Socket("localhost", 808);
				// 发送消息
				log.info("send : " + msg);
				socketOut = socket.getOutputStream();
				socketOut.write(msg.getBytes());
				socketOut.flush();
				// 接收服务器的反馈
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String res = br.readLine();
				if (res != null) {
					log.info("get message	:" + res);
				}
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
