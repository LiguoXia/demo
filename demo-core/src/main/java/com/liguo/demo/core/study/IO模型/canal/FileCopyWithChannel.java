package com.liguo.demo.core.study.IO模型.canal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <pre>
 * tag: 文件复制 canal Buffer Unicode(数据结构.md)
 *
 * 代码详解
 * 使用 FileInputStream 和 FileOutputStream：
 *
 * FileInputStream 用来读取源文件。
 * FileOutputStream 用来写入目标文件。
 * 获取 FileChannel：
 *
 * 使用 FileInputStream.getChannel() 获取源文件的通道。
 * 使用 FileOutputStream.getChannel() 获取目标文件的通道。
 * 创建缓冲区（ByteBuffer）：
 *
 * 创建一个 ByteBuffer（大小为 1024 字节）来临时存储读取的数据。
 * 从源文件读取数据：
 *
 * 通过 sourceChannel.read(buffer) 将源文件的数据读取到缓冲区中。
 * read 方法会将源文件的数据填充到 buffer 中，直到文件读取完毕。
 * 写入目标文件：
 *
 * 调用 targetChannel.write(buffer) 将缓冲区的数据写入目标文件。
 * 切换缓冲区模式：
 *
 * 在每次读取后，调用 buffer.flip() 切换 buffer 为读模式。
 * 在每次写入后，调用 buffer.clear() 清空缓冲区，为下一次读取和写入做准备。
 * 关闭流和通道：
 *
 * 使用 try-with-resources 语法自动关闭 FileInputStream、FileOutputStream 和 FileChannel。
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/18 20:16
 * @since 0.0.1
 */
public class FileCopyWithChannel {
    public static void copyFile(String sourcePath, String targetPath) {
        // Try-with-resources 自动管理资源关闭
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(targetPath);
             FileChannel sourceChannel = fis.getChannel();
             FileChannel targetChannel = fos.getChannel()) {

            // 创建一个适当大小的缓冲区
            // 实际创建的是一个 HeapByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(3);

            // 可以理解为两根管道通过一个 一个两面开口的管子传递数据，如果足够大，一次就能传递完，如果小了就要多次传递，传递时需要封不同边的口

            // 从源文件读取数据并写入目标文件
            // 在存储汉字的时候，一个字节8位，用10进制表示，夏字需要三个字节
            // -27  → E5 → 11100101
            // -92  → A4 → 10100100
            // -113 → 8F → 10001111
            while (sourceChannel.read(buffer) != -1) {
                System.out.println("1111");
                // 切换缓冲区为读模式
                buffer.flip();
                // 将缓冲区的数据写入目标文件
                targetChannel.write(buffer);
                // 清空缓冲区，准备下一次读写
                buffer.clear();
            }

            System.out.println("File copied successfully from " + sourcePath + " to " + targetPath);

        } catch (IOException e) {
            System.err.println("File copy failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 源文件路径和目标文件路径
        String sourceFile = "D:" + File.separator + "clsName_FileCopyWithChannel_source.txt";
        String targetFile = "D:" + File.separator + "clsName_FileCopyWithChannel_target.txt";

        System.out.println("sourceFile" + sourceFile);
        System.out.println("targetFile" + targetFile);

        // 在 Unix 或 macOS 上也可以这样
        // String sourceFile = "home" + File.separator + "user" + File.separator + "source.txt";
        // String targetFile = "home" + File.separator + "user" + File.separator + "target.txt";

        copyFile(sourceFile, targetFile);
    }
}
