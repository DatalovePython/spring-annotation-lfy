package com.atguigu.bean;

import org.springframework.stereotype.Component;

/**
 * @author luzc
 * @date 2021/1/31 16:47
 * @desc
 */

@Component
public class Car {
    public Car(){
        System.out.println("car constructor.....");

    }

    // 例如数据源连接
    public void init(){
        System.out.println("car...init...");
    }

    // 例如数据源关闭
    public void destroy(){
        System.out.println("car...destroy...");
    }
}
