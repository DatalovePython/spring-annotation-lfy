package com.atguigu.test;

import com.atguigu.aop.MathCalculator;
import com.atguigu.config.MainConfigOfAOP;
import com.atguigu.tx.TxConfig;
import com.atguigu.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author luzc
 * @date 2021/1/31 16:49
 * @desc
 */
public class IOCTest_TX {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(TxConfig.class);


    @Test
    public void test01() {


        UserService userService = applicationContext.getBean(UserService.class);
        userService.insertUser();

        applicationContext.close();
    }

}
