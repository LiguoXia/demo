package com.liguo.demo.core.study.IO模型.NIO.case2;

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
 * @date 2024/11/19 15:12
 * @since 0.0.1
 */
public class NioClient2 {
    public static void main(String[] args) throws IOException {
        // 创建 SocketChannel 并连接到服务器
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true); // 使用阻塞模式便于简单测试
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        System.out.println("Connected to server. Type messages to send:");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String message = scanner.nextLine();

                // 发送消息到服务器
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                socketChannel.write(buffer);

                // 接收服务器的回显消息
                buffer.clear();
                socketChannel.read(buffer);
                buffer.flip();
                System.out.println("Server response: " + new String(buffer.array(), 0, buffer.limit()));
            }
        }
    }
}
