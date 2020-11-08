package com.racoon.ui.util;

import com.racoon.ui.view.achat.element.ElementTalk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class CacheUtil {
    // 对话框组
    public static Map<String, ElementTalk> talkMap = new ConcurrentHashMap<String, ElementTalk>(16);
}
