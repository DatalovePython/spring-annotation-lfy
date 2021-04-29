package com.atguigu;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author luzc
 * @date 2021/1/31 10:42
 * @desc
 */

@SpringBootApplication
public class MainTest {


    public static void main(String[] args) {
        SpringApplication.run(MainTest.class,args);
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);


//        Person bean = applicationContext.getBean(Person.class);
//        System.out.println(bean);

//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();

//        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
//        for (String name : beanNamesForType) {
//            System.out.println(name);
//        }
    }

}
