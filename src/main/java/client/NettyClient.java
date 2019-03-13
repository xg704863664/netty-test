package client;

import client.handler.ClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String args[]){
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        Channel channe=null;
        try {
            bootstrap.
                    group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",8081).sync();
            channe = channelFuture.channel();

            Scanner scanner = new Scanner(System.in);
            while (true){
                System.out.println("请输入内容：");
                channe.writeAndFlush(scanner.next());
            }
        }catch (Exception e){
            e.printStackTrace();
            try {
                channe.closeFuture().sync();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }finally {
            group.shutdownGracefully();
        }

    }
}
