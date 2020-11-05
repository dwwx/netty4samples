package com.racoon.spring.framework.aop;

public interface GPAopProxy {
    Object getProxy();
    Object getProxy(ClassLoader classLoader);
}
