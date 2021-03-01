package com.atguigu.condition;

import com.atguigu.bean.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author luzc
 * @date 2021/1/31 16:10
 * @desc
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * Register bean definitions as necessary based on the given annotation metadata of
     * the importing {@code @Configuration} class.
     * <p>Note that {@link BeanDefinitionRegistryPostProcessor} types may <em>not</em> be
     * registered here, due to lifecycle constraints related to {@code @Configuration}
     * class processing.
     *
     * @param importingClassMetadata annotation metadata of the importing class -- 当前类的 注解信息
     * @param registry               current bean definition registry   -- bean定义的注册类
     *                               把所有需要添加到容器中的bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.atguigu.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.atguigu.bean.Blue");
        if (red && blue) {
            // 指定bean定义信息：（bean的类型，bean 的作用域 scope等）
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rainbow.class);
            // 注册一个bean并赋名。
            registry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }
    }
}
