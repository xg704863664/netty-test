package client;

import client.handler.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String args[]){
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.
                group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());
        Channel channel = bootstrap.connect("127.0.0.1",8080).channel();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入内容：");
            channel.writeAndFlush(scanner.next());
        }
    }
}
