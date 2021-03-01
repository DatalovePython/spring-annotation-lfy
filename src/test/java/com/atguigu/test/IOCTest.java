package com.atguigu.test;

import com.atguigu.bean.Blue;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;
import com.atguigu.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @author luzc
 * @date 2021/1/31 10:53
 * @desc
 */
public class IOCTest {

    @SuppressWarnings("resource")
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        printDefinitionBeansName(applicationContext);
    }


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    private void printDefinitionBeansName(AnnotationConfigApplicationContext applicationContext){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }
//    @SuppressWarnings("resource")
    @Test
    public void test02(){
        // 该方法相当于在容器中有定义的组件，而不是说加入容器的组件
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

        System.out.println("容器创建完成");

//        Object person = applicationContext.getBean("person");
//        Object person2 = applicationContext.getBean("person");
//        System.out.println(person == person2);
    }

    @Test
    public void test03(){
        String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
        // 获取环境变量
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String property = environment.getProperty("os.name");
        System.out.println(property);
        for (String name : beanNamesForType) {
            System.out.println(name);
        }

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);

    }

    @Test
    public void testImport(){
        printDefinitionBeansName(applicationContext);
        Blue bean1 = applicationContext.getBean(Blue.class);
        System.out.println(bean1);

        // 工厂bean获取的是调用getObject创建的对象
        Object bean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean2的类型： "+bean2.getClass());

        Object bean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean3的类型： "+bean3.getClass());
    }
}
