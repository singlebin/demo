package com.demo.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wub
 * @date 2021/3/7 14:33
 */
public class Test {

    public static void main(String[] args) {
        
        /*
        题目一、一个初始为0的变量，两个线程交替操作，一个加1，一个减1，来5轮

        题目二、
        多线程调用，实现A->B->C
        AA打印5次，BB打印5次，CC打印5次
        紧接着
        AA打印5次，BB打印5次，CC打印5次
        ...
        来10轮
        */
        new ClassPathXmlApplicationContext("a.xml");
    }
}
