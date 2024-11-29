package com.liguo.demo.core.study.IO模型.BIO2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/6 16:29
 * @since 0.0.1
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        // 1、创建Socket对象请求服务端链接
        Socket socket = new Socket("127.0.0.1", 9999);
        // 2、从Socket对象中获取一个字节输出流
        OutputStream os = socket.getOutputStream();
        // 3、把字节输出流包装成一个打印流
        PrintStream ps = new PrintStream(os);
        Scanner sc = new Scanner(System.in);
        while (true) {
            log.info("请说:");
            String msg = sc.nextLine();
            ps.println(msg);
            ps.flush();
        }
    }
}
