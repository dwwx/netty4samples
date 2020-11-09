package com.racoon.chat.protocol.friend;

import com.racoon.chat.protocol.Command;
import com.racoon.chat.protocol.Packet;
import com.racoon.chat.protocol.friend.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class SearchFriendResponse extends Packet {
    private List<UserDto> list;
    @Override
    public Byte getCommand() {
        return Command.SearchFriendResponse;
    }
}
