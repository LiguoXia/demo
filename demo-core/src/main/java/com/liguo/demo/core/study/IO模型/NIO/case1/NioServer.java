package com.liguo.demo.core.study.IO模型.NIO.case1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/19 15:03
 * @since 0.0.1
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        // 1. 打开 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080)); // 监听端口 8080
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞模式

        // 2. 创建 Selector
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 注册监听连接事件

        System.out.println("NIO Server started on port 8080...");

        while (true) {
            // 3. 检查事件
            if (selector.select() > 0) { // 阻塞直到至少有一个事件就绪
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove(); // 移除已处理的事件

                    // 4. 处理事件
                    if (key.isAcceptable()) {
                        // 客户端连接事件
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false); // 设置非阻塞
                        socketChannel.register(selector, SelectionKey.OP_READ); // 注册读事件
                        System.out.println(Thread.currentThread().getName() + "Accepted new connection from: " + socketChannel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        // 读事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = socketChannel.read(buffer);

                        if (bytesRead > 0) {
                            buffer.flip(); // 准备读取数据
                            String message = new String(buffer.array(), 0, buffer.limit());
                            System.out.println(Thread.currentThread().getName() + "Received: " + message);

                            // 回显消息
                            buffer.clear();
                            buffer.put((Thread.currentThread().getName() + "Echo: " + message).getBytes());
                            buffer.flip();
                            socketChannel.write(buffer);
                        } else if (bytesRead == -1) {
                            // 客户端关闭连接
                            System.out.println(Thread.currentThread().getName() + "Connection closed by client: " + socketChannel.getRemoteAddress());
                            socketChannel.close();
                        }
                    }
                }
            }
        }
    }
}
