package com.atguigu.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author luzc
 * @date 2021/2/1 17:14
 * @desc
 */

// 默认加在ioc容器中的组件，容器会调用无参构造器，创建对象进行赋值初始化操作
@Component
public class Boss {

    private Car car;

//    @Autowired
    public Boss(
//            @Autowired
                    Car car) {
        this.car = car;
        System.out.println("boss...有参构造器");
    }

    public Car getCar() {
        return car;
    }

//    @Autowired
    // 标注在方法上，spring容器在创建当前对象，就对调用方法，完成赋值
    // 方法使用的参数，自定义类型的值，就回从ioc容器中获取
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }


}
