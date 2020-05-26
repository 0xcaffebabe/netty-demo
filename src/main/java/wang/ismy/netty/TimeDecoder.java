package wang.ismy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author MY
 * @date 2020/5/25 15:38
 */
public class TimeDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 如果缓冲区没有足够的数据，不进行处理，只有缓冲区累积一定的数据时，才将数据添加到out
        if (in.readableBytes() < 4){
            return;
        }
        // 添加到out后，代表解码器成功解码了一条消息
        out.add(in.readBytes(4));
    }
}
