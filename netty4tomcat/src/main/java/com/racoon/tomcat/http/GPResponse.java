package com.racoon.tomcat.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

public class GPResponse {
    //SocketChannel的封装
    private ChannelHandlerContext ctx;
    private HttpRequest req;
    public GPResponse(ChannelHandlerContext ctx, HttpRequest req){
        this.ctx = ctx;
        this.req = req;
    }
    public void write(String out) throws Exception{
        try{
            if(out == null || out.length() == 0){
                return;
            }
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(out.getBytes("UTF-8"))
            );
            response.headers().set("Content-Type", "text/html");
            //进行消息回写
            ctx.write(response);
        }finally {
            ctx.flush();
            ctx.close();
        }
    }
}
