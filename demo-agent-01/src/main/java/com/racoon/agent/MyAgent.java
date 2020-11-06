package com.racoon.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("嗨！javaAgent"+agentArgs);
    }
    public static void premain(String agentArgs){

    }
}
