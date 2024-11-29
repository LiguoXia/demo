package com.liguo.demo.core.study.IO模型.NIO.case2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadNioServer {
    public static void main(String[] args) throws IOException {
        // 创建 ServerSocketChannel 并绑定到端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);

        // 创建 Selector
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 创建线程池
        ExecutorService workerPool = Executors.newFixedThreadPool(4);

        System.out.println("Server started on port 8080...");

        while (true) {
            // 阻塞等待事件
            if (selector.select() > 0) {
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    keyIterator.remove(); // 防止重复处理

                    if (key.isAcceptable()) {
                        // 处理新的连接
                        acceptConnection(serverSocketChannel, selector);
                    } else if (key.isReadable()) {
                        // 将读事件交给工作线程处理
                        workerPool.submit(() -> handleReadAndWrite(key));
                    }
                }
            }
        }
    }

    // 处理新连接
    private static void acceptConnection(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        SocketChannel clientChannel = serverSocketChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ); // 注册为读事件
        System.out.println("Accepted connection from: " + clientChannel.getRemoteAddress());
    }

    // 处理客户端的读写操作
    private static void handleReadAndWrite(SelectionKey key) {
        try {
            SocketChannel clientChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 读取数据
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead > 0) {
                buffer.flip(); // 切换为读模式
                String message = new String(buffer.array(), 0, buffer.limit());
                System.out.println("Received from " + clientChannel.getRemoteAddress() + ": " + message);

                // 准备返回消息
                String response = "Echo: " + message;
                buffer.clear();
                buffer.put(response.getBytes());
                buffer.flip(); // 切换为写模式
                clientChannel.write(buffer);

                System.out.println("Response sent to " + clientChannel.getRemoteAddress());
            } else if (bytesRead == -1) {
                // 客户端断开连接
                System.out.println("Connection closed by client: " + clientChannel.getRemoteAddress());
                clientChannel.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                key.channel().close(); // 遇到异常时关闭通道
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
