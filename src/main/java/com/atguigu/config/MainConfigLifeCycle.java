package com.atguigu.config;

import com.atguigu.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author luzc
 * @date 2021/1/31 16:45
 * @desc bean的生命周期
 * 创建
 * 初始化
 * 销毁
 * 容器来管理bean的生命周期
 * 但是我们可以自定义初始化和销毁方法
 * 容器在bean进行到当前生命周期的时候，来调用我们自定义的初始化和销毁方法
 * <p>
 *      顺序如下：
 *
 *     构造（对象创建）
 *          单实例：在容器启动的时候创建对象(容器先创建对象，然后才开始初始化)
 *          多实例：在获取的时候创建对象
 *
 *     postProcessorBeforeInitialization:在任何初始化方法调用之前
 *     @PostConstruct :在bean创建完成并且属性赋值完成，来执行初始化方法
 *     InitializingBean接口（定义初始化逻辑）
 *     init-method
 *     初始化
 *          对象创建完成、并赋值好，调用初始化方法
 *
 *
 *     postProcessorAfterInitialization：在初始化工作之后
 *     销毁：
 *          单实例：容器关闭的 时候
 *          多实例：容器只负责创建bean，不负责销毁bean
 *
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一但返回null，跳出for循环，不会执行后面的BeanPostProcessor.postProcessorsBeforeInitialization
 *
 * BeanPostProcessor原理
 *
 * populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * initializeBean
 * {
 * applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化
 * applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *}
 *
 *
 *  Constructor > @PostConstruct > InitializingBean > init-method
 *  参考：https://blog.csdn.net/qq_35873847/article/details/78969410
 *
 * 1）指定初始化和销毁方法：
 *      通过指定init-method 和 destroy-method
 *
 * 2）通过让bean实现InitializingBean接口（定义初始化逻辑）
 *                DisposableBean接口（定义销毁逻辑)
 * 3) JSR250
 *      @PostConstruct :在bean创建完成并且属性赋值完成，来执行初始化方法
 *      @PreDestroy :在容器销毁bean之前通知我们进行清理工作
 *
 * 4) BeanPostProcessor【interface】: bean后置处理器
 *      处理对象：容器中所有的bean对象，不单指某一个bean
 *      在bean初始化前后进行一些处理工作：
 *      postProcessorBeforeInitialization:在任何初始化方法调用之前
 *      postProcessorAfterInitialization：在初始化工作之后
 *
 *  spring 底层对BeanPostProcessor 的使用
 *      bean 赋值，注入其他组件，@Autowired，生命周期注解功能，@Async，xxx
 *
 */
@ComponentScan(value = "com.atguigu.bean")
@Configuration
public class MainConfigLifeCycle {

//    @Scope(value = "prototype")
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

}
