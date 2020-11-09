package com.racoon.chat.protocol.friend;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class AddFriendResponse extends Packet {

    private String friendId;
    private String friendNickName;
    private String friendHead;

    public AddFriendResponse() {
    }

    public AddFriendResponse(String friendId, String friendNickName, String friendHead) {
        this.friendId = friendId;
        this.friendNickName = friendNickName;
        this.friendHead = friendHead;
    }
    @Override
    public Byte getCommand() {
        return Command.AddFriendResponse;
    }
}
