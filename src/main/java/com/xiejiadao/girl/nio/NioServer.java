package com.xiejiadao.girl.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Nio服务器端
 * @author: xyf
 * @date: 2019/4/24 23:58
 */
public class NioServer {

    /**
     * 启动服务器端的方法
     *   七步
     */
    public void start() throws IOException {
        //1. 创建Selector
        Selector selector = Selector.open();

        //2. 通过 ServerSocketChannel 创建 channel 通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //3. 为通道绑定监听端口
        serverSocketChannel.bind(new InetSocketAddress(8000));

        //4. 设置channel为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //5. 将channel注册到selector上，监听连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功！/n 监听连接，端口号：8000");

        //6. 无限循环等待新接入的连接
        for (; ; ) { //while(true)之前写c的，for(;;)优化为一条，而while三条，但是现在java优化都一样了
            //TODO 获取可用的channel个数
            int readyChannels = selector.select();//这个方法是阻塞方法，只有监听的注册的事件已经就绪了，才会返回。
            //TODO 为什么要这样？？
            if (readyChannels == 0) continue;

            //获取可用channel的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                //SelectionKey实例
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                //移除set中的当前selectionKey
                iterator.remove();

                //TODO 7. 根据就绪状态，调用对应方法处理业务逻辑
                //如果是接入事件
                if (selectionKey.isAcceptable()) {
                    acceptHandler(serverSocketChannel, selector);
                }
                //如果是可读事件
                if (selectionKey.isReadable()) {
                    readHandler(selectionKey, selector);
                }
            }
        }
    }

    /**
     * 接入事件处理
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) throws IOException {
        //创建socketChannel
        SocketChannel socketChannel = serverSocketChannel.accept();
        //设置socketChannel为非阻塞
        socketChannel.configureBlocking(false);
        //注册到selector上，监听可读事件
        socketChannel.register(selector,SelectionKey.OP_READ);
        //回应客户端提示已经建立连接
        socketChannel.write(Charset.forName("UTF-8").encode("已经建立连接，并注册可读事件，尽快发消息来！"));
    }

    /**
     * 可读事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        //要从selectionKey中获取已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //只有Buffer能控制channel的读写，所以创建一个buffer,分配1024
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //使用buffer循环读取客户端发来的信息
        String request = "";
        while (socketChannel.read(byteBuffer) > 0) {//channel读取了多少数据写到buffer中
            //此时byteBuffer是写模式，所以我们得把它转换成读模式
            byteBuffer.flip();

            //读取byte人Buffer的内容
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //将channel再次注册到selector上，监听它的可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        //将客户端发送的信息，广播给其它客户端
        if (StringUtils.isNotEmpty(request)) {
            //广播
            System.out.println("request:" + request);
            broadCast(selector,socketChannel,request);
        }

    }

    /**
     * 广播方法
     */
    private void broadCast(Selector selector, SocketChannel sourceChannel, String request) {
        //1. 获取所有已经接入的客户端channel
        Set<SelectionKey> selectionKeySet = selector.keys();
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            //剔除发消息的channel
            if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel) {
                try {
                    //将消息发送到targetChannel客户端
                    ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //2. 循环向所有channel广播信息
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }
}
