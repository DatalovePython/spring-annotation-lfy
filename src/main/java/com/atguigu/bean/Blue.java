package com.atguigu.bean;

/**
 * @author luzc
 * @date 2021/1/31 16:02
 * @desc
 */
public class Blue {

    public Blue(){
        System.out.println("Blue constructor.....");

    }

    // 例如数据源连接
    public void init(){
        System.out.println("Blue...init...");
    }

    // 例如数据源关闭
    public void destroy(){
        System.out.println("Blue...destroy...");
    }
}
