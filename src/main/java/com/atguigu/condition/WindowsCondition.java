package com.atguigu.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

/**
 * @author luzc
 * @date 2021/1/31 15:31
 * @desc
 */

// 判断是否是windows 系统
public class WindowsCondition implements Condition {
    /**
     * Determine if the condition matches.
     *
     * @param context  the condition context,判断条件能否使用的上下文环境
     * @param metadata metadata of the {@link AnnotationMetadata class}
     *                 or {@link MethodMetadata method} being checked.
     *                 注释信息
     * @return {@code true} if the condition matches and the component can be registered
     * or {@code false} to veto registration.
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // ioc 当前的bean工厂-用于创建对象以及装配
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        // 类加载器
        ClassLoader classLoader = context.getClassLoader();
        // 获取环境，环境变量 以及JVM变量
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");
        if(property.contains("Windows")){
            return true;
        }
        // 获取bean定义的注册类，bean都是在该类中进行注册
        BeanDefinitionRegistry registry = context.getRegistry();
        return false;
    }
}
