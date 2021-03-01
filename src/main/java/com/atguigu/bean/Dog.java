package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author luzc
 * @date 2021/2/1 8:42
 * @desc
 */
@Component
public class Dog implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    public Dog(){
        System.out.println("dog...constructor...");
    }

    // 对象创建 并赋值之后调用
    @PostConstruct
    public void init(){
        System.out.println("dog...@PostConstruct...");
    }

    // bean在容器中被销毁之前
    @PreDestroy
    public void destroy(){
        System.out.println("dog...@PreDestroy...");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;


    }
}

