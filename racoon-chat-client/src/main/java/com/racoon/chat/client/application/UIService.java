package com.racoon.chat.client.application;

import com.racoon.ui.view.achat.IChatMethod;
import com.racoon.ui.view.alogin.ILoginMethod;
import lombok.Data;

@Data
public class UIService {
    private ILoginMethod login;
    private IChatMethod chat;
}
