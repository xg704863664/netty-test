package server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
       ChannelPipeline channelPipeline = socketChannel.pipeline();
       channelPipeline.addLast("server-decoder",new StringDecoder());
        channelPipeline.addLast("server-encoder",new StringEncoder());
        channelPipeline.addLast("server-handler",new ServerHandler());
    }
}
