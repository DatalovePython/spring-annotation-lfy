package com.atguigu.test;

import com.atguigu.bean.Yellow;
import com.atguigu.config.MainConfigAutowired;
import com.atguigu.config.MainConfigLifeCycle;
import com.atguigu.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author luzc
 * @date 2021/2/2 9:32
 * @desc
 */
public class IOCTestProfile {

    // 1 使用命令行动态参数，切换环境，在虚拟机参数位置加载 -Dspring.profiles.active=test
    // 2 用代码的方式激活某种环境，如下
    @Test
    public void test01(){

//        AnnotationConfigApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext(MainConfigOfProfile.class);

        // 创建一个applicationContext
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        // 注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        // 启动刷新容器
        applicationContext.refresh();

        String[] names = applicationContext.getBeanNamesForType(DataSource.class);
        for (String name : names) {
            System.out.println(name);
        }

        Yellow yellow = (Yellow)applicationContext.getBean("yellow");
        System.out.println(yellow);

        applicationContext.close();
    }
}
