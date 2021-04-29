package com.atguigu.test;

import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.config.MainConfigAutowired;
import com.atguigu.config.MainConfigOfAOP;
import com.atguigu.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author luzc
 * @date 2021/1/31 16:49
 * @desc
 */
public class IOCTestAOP {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

    private void printDefinitionBeansName(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    @Test
    public void test01() {

        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        int div = mathCalculator.div(1, 0);

        applicationContext.close();
    }

}
