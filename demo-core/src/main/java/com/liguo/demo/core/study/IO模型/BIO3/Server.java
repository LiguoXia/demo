package com.liguo.demo.core.study.IO模型.BIO3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多个客户端多个服务端
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/6 16:25
 * @since 0.0.1
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        log.info("==服务端启动===");
        // 1、定文一个ServerSocket对象进行服务端的端口注加
        ServerSocket ss = new ServerSocket(9999);
        while (true) {
            // 阻塞方法
            Socket socket = ss.accept();
            new ServerThreadReader(socket).start();
        }
    }
}
