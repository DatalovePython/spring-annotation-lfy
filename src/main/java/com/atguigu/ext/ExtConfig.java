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
 *      3: applicationListener: 监听容器中发布的事件，事件驱动模型开发
 *          public interface ApplicationListener<E extends ApplicationEvent>
 *              监听 ApplicationEvent 及其下面的子事件
 *
 *         步骤：
 *          1 写一个监听器来监听某个事件（ApplicationEvent） 及其子类
 *          2 把监听器加入到容器中
 *          3 只要容器中有相关事件的发布 我们就能监听到这个事件
 *              ContextRefreshedEvent ： 容器刷新完成，所有bean都已经加载完成 会发布这个事件
 *              ContextClosedEvent ： 关闭容器 发布当前事件
 *          4 发布一个事件
 *              原理 ContextRefreshedEvent  IOCTest_Ext$1[source=发布了事件] ContextClosedEvent
 *
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
