package com.xiejiadao.girl.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端接收响应线程
 * @author: xyf
 * @date: 2019/4/25 1:37
 */
public class NioClientHandler implements Runnable{

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {

        try {
            for (; ; ) {

                int readyChannels = selector.select();//这个方法是阻塞方法，只有监听的注册的事件已经就绪了，才会返回。
                if (readyChannels == 0) continue;
                //获取可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //SelectionKey实例
                    SelectionKey selectionKey = (SelectionKey) iterator.next();
                    //移除set中的当前selectionKey
                    iterator.remove();
                    //如果是可读事件
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可读事件处理器
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) throws IOException {
        //要从selectionKey中获取已经就绪的channel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //只有Buffer能控制channel的读写，所以创建一个buffer,分配1024
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //使用buffer循环读取服务端发来的信息
        String request = "";
        while (socketChannel.read(byteBuffer) > 0) {//channel读取了多少数据写到buffer中
            //此时byteBuffer是写模式，所以我们得把它转换成读模式
            byteBuffer.flip();

            //读取byte人Buffer的内容
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }
        //将channel再次注册到selector上，监听它的可读事件
        socketChannel.register(selector, SelectionKey.OP_READ);
        if (StringUtils.isNotEmpty(request)) {
            System.out.println("request:" + request);
        }

    }
}
