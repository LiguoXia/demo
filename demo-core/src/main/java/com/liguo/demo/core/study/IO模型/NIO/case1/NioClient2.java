package com.liguo.demo.core.study.IO模型.NIO.case1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/19 15:04
 * @since 0.0.1
 */
public class NioClient2 {
    public static void main(String[] args) throws IOException {
        // 1. 打开 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false); // 设置非阻塞
        socketChannel.connect(new InetSocketAddress("localhost", 8080)); // 连接服务器

        // 等待连接完成
        while (!socketChannel.finishConnect()) {
            System.out.println("NioClient2 Connecting to server...");
        }
        System.out.println("NioClient2 Connected to server!");

        // 2. 发送消息
        Scanner scanner = new Scanner(System.in);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.print("Enter message: ");
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message)) {
                break;
            }

            // 写数据到服务端
            buffer.clear();
            buffer.put(message.getBytes());
            buffer.flip();
            socketChannel.write(buffer);

            // 读取服务端回显
            buffer.clear();
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip();
                String response = new String(buffer.array(), 0, buffer.limit());
                System.out.println("Server response: " + response);
            }
        }

        // 关闭连接
        socketChannel.close();
        System.out.println("NioClient2 disconnected.");
    }
}
