package com.atguigu.test;

import com.atguigu.ext.ExtConfig;
import com.atguigu.tx.UserService;
import org.junit.Test;
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




        applicationContext.close();
    }

}
