package com.racoon.demo.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 自定义解码器
 * 02；开始位
 * 03；结束位
 * 34；变量，内容长度位
 *
 * 第一组；整包测试数据：
 * 02 34 68 69 68 69 03
 *
 * 第二组；半包测试数据
 * 02 34 68 69 68 69
 * 03
 *
 * 第三组：粘包测试数据
 * 02 34 68 69 68 69 03 02 34
 * 68 69 68 69 03
 */
public class MyDecoder extends ByteToMessageDecoder {
    private final int BASE_LENGTH = 4;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < BASE_LENGTH){
            return;
        }
        int beginIdx;
        while (true){
            //获取包头开始的Index
            beginIdx = in.readerIndex();
            //标记开始的index
            in.markReaderIndex();
            if(in.readByte() == 0x02){
                break;
            }
            // 未读到包头，略过一个字节
            // 每次略过，一个字节，去读取，包头信息的开始标记
            in.resetReaderIndex();
            in.readByte();
            // 当略过，一个字节之后，
            // 数据包的长度，又变得不满足
            // 此时，应该结束。等待后面的数据到达
            if(in.readableBytes() < BASE_LENGTH){
                return;
            }
        }
        //剩余长度不足可读取数量[没有内容长度位]
        int readableCount = in.readableBytes();
        if(readableCount <=1){
            in.readerIndex(beginIdx);
            return;
        }
        //长度域占4个字节读取int
        ByteBuf byteBuf = in.readBytes(1);
        String msgLengthStr = byteBuf.toString(Charset.forName("GBK"));
        int msgLength = Integer.parseInt(msgLengthStr);

        //剩余长度不足可读取数量[没有结尾标识]
        readableCount = in.readableBytes();
        if(readableCount < msgLength +1){
            in.readerIndex(beginIdx);
            return;
        }
        ByteBuf msgContent = in.readBytes(msgLength);
        //如果没有结尾标识，还原指针位置[其他标识结尾]
        byte end = in.readByte();
        if(end != 0x03){
            in.readerIndex(beginIdx);
            return;
        }
        out.add(msgContent.toString(Charset.forName("GBK")));
    }
}
