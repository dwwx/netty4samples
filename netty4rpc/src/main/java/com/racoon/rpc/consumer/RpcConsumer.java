package com.racoon.rpc.consumer;

import com.racoon.rpc.api.IRpcHelloService;
import com.racoon.rpc.api.IRpcService;
import com.racoon.rpc.consumer.proxy.RpcProxy;

public class RpcConsumer {
    public static void main(String[] args) {
        IRpcHelloService rpcHelloService = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHelloService.hello("dwx"));
        IRpcService service = RpcProxy.create(IRpcService.class);

        System.out.println("8 + 2 = "+service.add(8,2));
    }
}
