package com.racoon.chat.codec;

import com.racoon.chat.protocol.Packet;
import com.racoon.chat.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        //对象转换为字节数组
        byte[] data = SerializationUtil.serialize(msg);
        //先写一个长度
        out.writeInt(data.length+1);
        //再写一个command
        out.writeByte(msg.getCommand());
        //再写数据
        out.writeBytes(data);
    }
}
