package com.racoon.rpc.registry;

import com.racoon.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//拦截入站的IO进行读取
public class RegistryHandler extends ChannelInboundHandlerAdapter {
    //保存所有可用的服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<String, Object>();
    //保存所有相关的服务类
    public List<String> classNames = new ArrayList<String>();

    public RegistryHandler(){
        scannerClass("com.racoon.rpc.provider");
        doRegister();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        //接收到消息，进行接收处理
        InvokerProtocol request = (InvokerProtocol) msg;
        //当客户端建立连接时，需要从自定义协议中获取信息，拿到具体的服务和实参，使用反射调用
        //registryMap中存的是接口的名称和相应接口的实例对象
        if(registryMap.containsKey(request.getClassName())){
            //根据接口名称获取类的实例对象
            Object clazz = registryMap.get(request.getClassName());
            //反射调用方法
            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParames());
            //反射调用方法得到方法的返回对象
            result = method.invoke(clazz, request.getValues());
        }
        //再把消息传送回去
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for(File file:dir.listFiles()){
            if(file.isDirectory()){
                scannerClass(packageName+"."+file.getName());
            }else {
                classNames.add(packageName+"."+file.getName().replace(".class","").trim());
            }
        }
    }
    //完成注册
    private void doRegister() {
        if(classNames.size() ==0) return;
        for (String className:classNames){
            try{
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                registryMap.put(i.getName(), clazz.newInstance());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
