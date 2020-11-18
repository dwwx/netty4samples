package com.racoon.ui.util;

import com.racoon.ui.view.achat.element.group_bar_chat.ElementTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class CacheUtil {
    // 对话框组,全局金泰的Map集合做一个缓存
    public static Map<String, ElementTalk> talkMap = new ConcurrentHashMap<String, ElementTalk>(16);
}
