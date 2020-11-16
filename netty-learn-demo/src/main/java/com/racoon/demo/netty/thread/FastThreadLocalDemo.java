package com.racoon.demo.netty.thread;

import io.netty.util.concurrent.FastThreadLocal;

import java.util.Objects;

public class FastThreadLocalDemo {

    public FastThreadLocalDemo( ) {
        fastThreadLocalTest = new FastThreadLocalTest();
    }

    final class FastThreadLocalTest extends FastThreadLocal<Object>{
        @Override
        protected Object initialValue() throws Exception {
            return new Object();
        }
    }
    private final FastThreadLocalTest fastThreadLocalTest;

    public static void main(String[] args) {
        final FastThreadLocalDemo fastThreadLocalDemo = new FastThreadLocalDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Object object = fastThreadLocalDemo.fastThreadLocalTest.get();
                try {
                    for(int i=0; i<10; i++){
                        fastThreadLocalDemo.fastThreadLocalTest.set(new Object());
                        Thread.sleep(1000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Object o = fastThreadLocalDemo.fastThreadLocalTest.get();
                    for(int i=0; i<10; i++){
                        System.out.println(o == fastThreadLocalDemo.fastThreadLocalTest.get());
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
