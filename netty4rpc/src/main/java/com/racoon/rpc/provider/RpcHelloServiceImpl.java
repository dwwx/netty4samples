package com.racoon.rpc.provider;

import com.racoon.rpc.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {
    public String hello(String name) {
        return "Hello" + name+"!";
    }
}
