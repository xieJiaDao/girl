package com.xiejiadao.girl.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NioClient客户端
 * @author: xyf
 * @date: 2019/4/24 23:59
 */
public class NioClient {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);

    /**
     * 启动客户端
     */
    public void start() throws IOException {
        //1. 连接服务端
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8000));
        //2. 接收连接响应
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        threadPool.execute(new NioClientHandler(selector));
        //2. 发送请求到服务端
        Scanner scanner = new Scanner(System.in,"UTF-8");
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            if (StringUtils.isNotEmpty(request)) {
                socketChannel.write(Charset.forName("UTF-8").encode("client1:" + request));
            }
        }
        //3. 接收服务端响应
//        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        String response = "";
//        while (socketChannel.read(byteBuffer) > 0) {
//            byteBuffer.flip();
//            response += Charset.forName("UTF-8").decode(byteBuffer);
//        }
//        if (StringUtils.isNotEmpty(response)) {
//            System.out.println("服务器响应response：" + response);
//        }
    }


    public static void main(String[] args) throws IOException {
        NioClient client = new NioClient();
        client.start();
    }

}
