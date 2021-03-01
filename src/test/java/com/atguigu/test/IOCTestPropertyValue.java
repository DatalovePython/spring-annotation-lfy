package com.atguigu.test;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfPropertyValue;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author luzc
 * @date 2021/1/31 16:49
 * @desc
 */
public class IOCTestPropertyValue {

    AnnotationConfigApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(MainConfigOfPropertyValue.class);

    private void printDefinitionBeansName(AnnotationConfigApplicationContext applicationContext) {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

    @Test
    public void test01() {
        // 创建ioc容器
        printDefinitionBeansName(applicationContext);
        Person person = (Person) applicationContext.getBean("person");
        System.out.println("====" + person);

        // 配置文件中的值，默认都加载到环境变量中
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
        // 关闭容器
        applicationContext.close();
    }

}
