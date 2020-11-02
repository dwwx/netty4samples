package com.racoon.rpc.provider;

import com.racoon.rpc.api.IRpcService;

public class RpcServiceImpl implements IRpcService {
    public int add(int a, int b) {
        return a+b;
    }

    public int sub(int a, int b) {
        return a-b;
    }

    public int mul(int a, int b) {
        return a*b;
    }

    public int div(int a, int b) {
        return a/b;
    }
}
