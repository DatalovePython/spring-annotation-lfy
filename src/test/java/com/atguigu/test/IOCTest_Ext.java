package com.atguigu.test;

import com.atguigu.ext.ExtConfig;
import com.atguigu.tx.UserService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author luzc
 * @date 2021/1/31 16:49
 * @desc
 */
public class IOCTest_Ext {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(ExtConfig.class);


    @Test
    public void test01() {

        // 发布事件
        java.lang.String a = "发布了事件";
        applicationContext.publishEvent(new ApplicationEvent(a) {
        });

        applicationContext.close();
    }

}
