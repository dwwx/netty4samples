package com.racoon.rpc.protocol;
import lombok.Data;
import java.io.Serializable;

@Data
public class InvokerProtocol implements Serializable {
    //类名
    private String className;
    //函数名称
    private String methodName;
    //形参列表
    private Class<?>[] parames;
    //实参列表
    private Object[] values;
}
