package com.racoon.chat.codec;

import com.racoon.chat.protocol.Packet;
import com.racoon.chat.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        byte[] data = SerializationUtil.serialize(msg);
        out.writeInt(data.length+1);
        out.writeByte(msg.getCommand());
        out.writeBytes(data);
    }
}
