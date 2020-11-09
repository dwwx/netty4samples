package com.racoon.chat.client.socket.handler;

import com.racoon.chat.client.application.UIService;
import com.racoon.chat.protocol.talk.TalkNoticeResponse;
import com.racoon.ui.view.achat.IChatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;


/**
 * 博  客：http://bugstack.cn
 * 公众号：bugstack虫洞栈 | 沉淀、分享、成长，让自己和他人都能有所收获！
 * create by 小傅哥 on @2020
 * <p>
 * 对话通知应答
 */
public class TalkNoticeHandler extends SimpleChannelInboundHandler<TalkNoticeResponse> {

    private UIService uiService;

    public TalkNoticeHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TalkNoticeResponse msg) throws Exception {
        IChatMethod chat = uiService.getChat();
        Platform.runLater(() -> {
            chat.addTalkBox(-1, 0, msg.getTalkId(), msg.getTalkName(), msg.getTalkHead(), msg.getTalkSketch(), msg.getTalkDate(), false);
        });
    }

}
