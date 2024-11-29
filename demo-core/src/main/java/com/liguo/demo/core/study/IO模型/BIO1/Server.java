package com.liguo.demo.core.study.IO模型.BIO1;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * dsc
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
        // 2、监所客广端的socket链接请求
        Socket socket = ss.accept();
        // 3、从socket管道中得一个字节输入流对象
        InputStream is = socket.getInputStream();
        // 4、把字节输人流包装成一个缓冲字符输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String msg;
        if ((msg = br.readLine()) != null) {
            log.info("服务端接收到:{}", msg);
        }
    }
}
