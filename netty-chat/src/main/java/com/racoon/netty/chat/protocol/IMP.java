package com.racoon.netty.chat.protocol;

/**
 * 自定义协议的封装
 */
public enum IMP {
    /**
     * 系统消息
     */
    SYSTEM("SYSTEM"),
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    CHAT("CHAT"),
    FLOWER("FLOWER");

    private String name;
    public static boolean isIMP(String content){
        return content.matches("^\\[(SYSTEM|LOGIN|LOOUT|CHAT)\\]");
    }
    IMP(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
