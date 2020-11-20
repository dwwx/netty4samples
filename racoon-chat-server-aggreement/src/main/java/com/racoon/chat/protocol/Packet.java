package com.racoon.chat.protocol;

import com.racoon.chat.protocol.friend.AddFriendRequest;
import com.racoon.chat.protocol.friend.AddFriendResponse;
import com.racoon.chat.protocol.friend.SearchFriendRequest;
import com.racoon.chat.protocol.friend.SearchFriendResponse;
import com.racoon.chat.protocol.login.LoginRequest;
import com.racoon.chat.protocol.login.LoginResponse;
import com.racoon.chat.protocol.login.ReconnectRequest;
import com.racoon.chat.protocol.msg.MsgGroupRequest;
import com.racoon.chat.protocol.msg.MsgGroupResponse;
import com.racoon.chat.protocol.msg.MsgRequest;
import com.racoon.chat.protocol.msg.MsgResponse;
import com.racoon.chat.protocol.talk.DelTalkRequest;
import com.racoon.chat.protocol.talk.TalkNoticeRequest;
import com.racoon.chat.protocol.talk.TalkNoticeResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 对传输对象的command和对应的类进行缓存
 */
public abstract class Packet {
    private final static Map<Byte, Class<? extends Packet>> packetType = new ConcurrentHashMap<>();

    static {
        packetType.put(Command.LoginRequest, LoginRequest.class);
        packetType.put(Command.LoginResponse, LoginResponse.class);
        packetType.put(Command.MsgRequest, MsgRequest.class);
        packetType.put(Command.MsgResponse, MsgResponse.class);
        packetType.put(Command.TalkNoticeRequest, TalkNoticeRequest.class);
        packetType.put(Command.TalkNoticeResponse, TalkNoticeResponse.class);
        packetType.put(Command.SearchFriendRequest, SearchFriendRequest.class);
        packetType.put(Command.SearchFriendResponse, SearchFriendResponse.class);
        packetType.put(Command.AddFriendRequest, AddFriendRequest.class);
        packetType.put(Command.AddFriendResponse, AddFriendResponse.class);
        packetType.put(Command.DelTalkRequest, DelTalkRequest.class);
        packetType.put(Command.MsgGroupRequest, MsgGroupRequest.class);
        packetType.put(Command.MsgGroupResponse, MsgGroupResponse.class);
        packetType.put(Command.ReconnectRequest, ReconnectRequest.class);
    }
    public static Class<? extends Packet> get(Byte command) {
        return packetType.get(command);
    }
    /**
     * 获取协议指令
     *
     * @return 返回指令值
     */
    public abstract Byte getCommand();
}
