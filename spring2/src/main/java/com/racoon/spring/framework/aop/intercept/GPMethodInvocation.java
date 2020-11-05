package com.racoon.spring.framework.aop.intercept;

import com.racoon.spring.framework.aop.aspect.GPJoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPMethodInvocation implements GPJoinPoint {
    private Object proxy;
    private Method method;
    private Object target;
    private Object[] arguments;
    private List<Object> interceptorsAndDynamicMethodMatchers;
    private Class<?> targetClass;
    private Map<String, Object> userAttributes;

    //定义一个索引，从-1开始来记录当前拦截器执行的位置
    private int currentInterceptorIndex = -1;
    public GPMethodInvocation(Object proxy, Object target, Method method, Object[] arguments,
                              Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers){
        this.proxy =proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }
    public Object proceed() throws Throwable{
        if(this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size()-1){
            return this.method.invoke(this.target, this.arguments);
        }
        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        if(interceptorOrInterceptionAdvice instanceof GPMethodInterceptor){
            GPMethodInterceptor mi = (GPMethodInterceptor)interceptorOrInterceptionAdvice;
            return mi.invoke(this);
        }else {
            return proceed();
        }
    }
    public Object getThis() {
        return this.target;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public Method getMethod() {
        return this.method;
    }

    public void setUserAttribute(String key, Object value) {
        if(value != null){
            if(this.userAttributes == null){
                this.userAttributes = new HashMap<String, Object>();
            }
            this.userAttributes.put(key, value);
        }else {
            if(this.userAttributes != null){
                this.userAttributes.remove(key);
            }
        }
    }

    public Object getUserAttribute(String key) {
        return (this.userAttributes != null ? this.userAttributes.get(key):null);
    }
}
