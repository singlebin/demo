package com.demo.entity;

/**
 * @author wub
 * @date 2021/1/7 20:59
 */
public class B {

    private A a;

    public B() {
        System.out.println("成功创建B对象..........");
    }

    public void setA(A a) {
        this.a = a;
    }
}
