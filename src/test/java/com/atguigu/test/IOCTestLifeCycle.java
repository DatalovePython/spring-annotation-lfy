package com.atguigu.test;

import com.atguigu.config.MainConfigLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author luzc
 * @date 2021/1/31 16:49
 * @desc
 */
public class IOCTestLifeCycle {

    @Test
    public void test01(){
        // 创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);
        System.out.println("容器创建完成");

        applicationContext.getBean("car");

        // 关闭容器
        applicationContext.close();
    }
}
