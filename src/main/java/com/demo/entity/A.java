package com.demo.entity;

/**
 * @author wub
 * @date 2021/1/7 20:57
 */
public class A {

    private B b;

    public A() {
        System.out.println("成功创建A对象..........");
    }

    public void setA(B b) {
        this.b = b;
    }
}
