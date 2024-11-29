package com.liguo.demo.core.study.IO模型.chat;

import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类名：ChatServer
 * 描述：创建一个网络聊天室服务器程序，描绘服务器界面，并与客户端建立联系
 *
 * @author longluo
 */
public class ChatServer extends Thread {
    ServerFrame serverFrame = null;
    ServerSocket serverSocket = null; // 创建服务器端套接字

    public boolean bServerIsRunning = false;

    public ChatServer() {
        try {
            serverSocket = new ServerSocket(Constants.SERVER_PORT); // 启动服务
            bServerIsRunning = true;

            serverFrame = new ServerFrame();
            getServerIP(); // 得到并显示服务器端IP
            System.out.println("Server Port is:" + Constants.SERVER_PORT);
            serverFrame.taLog.setText("服务器已经启动...");
            while (true) {
                Socket socket = serverSocket.accept(); // 监听客户端的连接请求，并返回客户端socket
                new ServerProcess(socket, serverFrame); // 创建一个新线程来处理与该客户的通讯
            }
        } catch (BindException e) {
            System.out.println("端口使用中....");
            System.out.println("请关掉相关程序并重新运行服务器！");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("[ERROR] Cound not start server." + e);
        }

        this.start(); // 启动线程
    }

    /**
     * 获取服务器的主机名和IP地址
     */
    public void getServerIP() {
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            byte[] ipAddress = serverAddress.getAddress();

            serverFrame.txtServerName.setText(serverAddress.getHostName());
            serverFrame.txtIP.setText(serverAddress.getHostAddress());
            serverFrame.txtPort.setText(String.valueOf(Constants.SERVER_PORT));

            System.out.println("Server IP is:" + (ipAddress[0] & 0xff) + "."
                    + (ipAddress[1] & 0xff) + "." + (ipAddress[2] & 0xff) + "."
                    + (ipAddress[3] & 0xff));
        } catch (Exception e) {
            System.out.println("###Cound not get Server IP." + e);
        }
    }

    /**
     * main方法，实例化服务器端程序
     *
     * @param args
     */
    public static void main(String args[]) {
        new ChatServer();
    }
}
