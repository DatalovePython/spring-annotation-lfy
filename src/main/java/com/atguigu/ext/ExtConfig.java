package com.atguigu.ext;

import com.atguigu.bean.Blue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luzc
 * @date 2021/3/12 15:19
 * @desc 扩展原理
 *
 * BeanFactoryPostProcessor :BeanFactory 的后置处理器
 *      在BeanFactory在标准初始化之后调用，所有bean的定义已经加载到beanFactory,但是bean的实例还未创建
 *
 *      流程
 *      1：
 *      1） ioc 容器创建对象
 *      2） invokeBeanFactoryPostProcessors(beanFactory); 执行 BeanFactoryPostProcessors
 *          如何 找到所有的 BeanFactoryPostProcessors 并执行他们的方法
 *              1） 直接在 BeanFactory 中找到所有类型是  BeanFactoryPostProcessors 的组件，并执行他们的方法
 *              2） 在初始化创建其他组件前面执行
 *     2： BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor 在所有bean定义信息 将要被加载
 *          但是bean实例还未创建的时候执行
 *
 *          优先于 BeanFactoryPostProcessor 执行，利用 BeanDefinitionRegistryPostProcessor 给容器中再额外添加一些组件
 *
 *      原理：
 *          1） ioc容器创建对象
 *          2） refresh -> invokeBeanFactoryPostProcessors(beanFactory)
 *          3)  先从容器中获取到所有的 BeanDefinitionRegistryPostProcessor 组件，
 *              1 依次出触发所有的 postProcessBeanDefinitionRegistry
 *              2 再来触发 postProcessBeanFactory
 *
 */
@ComponentScan("com.atguigu.ext")
@Configuration
public class ExtConfig {

    @Bean
    public Blue blue(){
        return new Blue();
    }
}
