package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import server.handler.ServerChannelInitializer;


public class NettyServer {

    public static void main(String args[]){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.
                    group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ServerChannelInitializer())
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture channelFuture = serverBootstrap.bind(8081).sync();
            System.out.println("服务器启动。。。。");
            channelFuture.channel().closeFuture().sync();
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
