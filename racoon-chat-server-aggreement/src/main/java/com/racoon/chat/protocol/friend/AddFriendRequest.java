package com.racoon.chat.protocol.friend;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class AddFriendRequest extends Packet {
    private String userId;
    private String friendId;
    public AddFriendRequest(){}

    public AddFriendRequest(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    //得到相应的操作指令
    @Override
    public Byte getCommand() {
        return Command.AddFriendRequest;
    }
}
