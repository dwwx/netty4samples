package com.racoon.demo.netty.transfer.util;

import com.racoon.demo.netty.transfer.domain.MsgInfo;

public class MsgUtil {
    public static MsgInfo buildMsg(String channelId, String msgContent){
        return new MsgInfo(channelId, msgContent);
    }
}
