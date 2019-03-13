package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends SimpleChannelInboundHandler {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        try {
            System.out.println("客户端收到消息："+msg.toString());
        }finally {
            ReferenceCountUtil.release(msg);
        }

    }
}
