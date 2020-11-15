package com.racoon.chat.protocol.friend;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import lombok.Data;

@Data
public class SearchFriendRequest extends Packet {
    private String userId;
    private String searchKey;

    public SearchFriendRequest() {
    }

    public SearchFriendRequest(String userId, String searchKey) {
        this.userId = userId;
        this.searchKey = searchKey;
    }

    @Override
    public Byte getCommand() {
        return Command.SearchFriendRequest;
    }
}
