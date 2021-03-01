package com.atguigu.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * @author luzc
 * @date 2021/1/31 15:54
 * @desc
 */

@Component
public class Red implements ApplicationContextAware , BeanNameAware , EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    // 设置bean对象对应的ioc
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc：" + applicationContext);
        this.applicationContext = applicationContext;
    }

    // 设置组件在ioc中的名字
    @Override
    public void setBeanName(String name) {
        System.out.println("当前bean的名字："+name);
    }

    // 值解析器解析占位符等
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("你好${os.name} 我是#{20-2}");
        System.out.println("解析的字符串："+resolveStringValue);
    }
}
