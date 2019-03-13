package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("12312");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("异常断线："+ctx.channel().id());
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
                System.out.println("服务器接收消息："+msg.toString());
                channelHandlerContext.pipeline().write("收到了！");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("来自："+ctx.channel().remoteAddress());
        ctx.writeAndFlush("连接成功！"+ctx.channel().id());
        super.channelActive(ctx);
    }
}
