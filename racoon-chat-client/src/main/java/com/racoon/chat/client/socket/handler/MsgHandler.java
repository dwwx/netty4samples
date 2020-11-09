package com.racoon.chat.client.socket.handler;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.protocol.msg.MsgResponse;
import com.racoon.ui.view.achat.IChatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 */
public class MsgHandler extends SimpleChannelInboundHandler<MsgResponse> {

    private UIService uiService;

    public MsgHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkMsgUserLeft(msg.getFriendId(), msg.getMsgText(), msg.getMsgType(), msg.getMsgDate(), true, false, true);
        });
    }

}
