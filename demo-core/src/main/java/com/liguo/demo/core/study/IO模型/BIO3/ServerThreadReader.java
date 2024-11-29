package com.liguo.demo.core.study.IO模型.BIO3;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/6 22:08
 * @since 0.0.1
 */
@Slf4j
public class ServerThreadReader extends Thread {
    // 定义一个成员变量
    private Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 3、从socket管道中得一个字节输入流对象
            InputStream is = socket.getInputStream();
            // 4、把字节输人流包装成一个缓冲字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                log.info("服务端接收到:{}", msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
