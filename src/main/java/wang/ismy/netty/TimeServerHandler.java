package wang.ismy.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author MY
 * @date 2020/5/18 15:28
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
    * 连接建立时被调用
    * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 申请一块内存以写入时间数据
        final var time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis()/1000L + 2208988800L));
        ChannelFuture future = ctx.writeAndFlush(time);
        // 写入数据完成后关闭channel
        future.addListener(f -> {
            if (f == future) {
                ctx.close();
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
