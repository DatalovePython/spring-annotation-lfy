package com.atguigu.config;

import com.atguigu.bean.Color;
import com.atguigu.bean.ColorFactoryBean;
import com.atguigu.bean.Person;
import com.atguigu.bean.Red;
import com.atguigu.condition.LinuxCondition;
import com.atguigu.condition.MyImportBeanDefinitionRegistrar;
import com.atguigu.condition.MyImportSelector;
import com.atguigu.condition.WindowsCondition;
import org.springframework.context.annotation.*;

/**
 * @author luzc
 * @date 2021/1/31 14:24
 * @desc
 */
// @Conditional:如果放在类中，则是满足条件，这个类中配置的所有bean才会生效---类中组件统一设置
@Configuration
@Import(value = {Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
// 导入组件：id默认是组件的全类名
public class MainConfig2 {

    //默认是单实例的
    /**
     * ConfigurableBeanFactory#SCOPE_PROTOTYPE
     *
     * @Scope:调整作用域 prototype：多实例的：ioc容器启动并不会去调用方法创建对象放在容器中。
     * 每次获取的时候才会调用方法创建对象；
     * singleton：单实例的（默认值）：ioc容器启动会调用方法创建对象放到ioc容器中。
     * 以后每次获取就是直接从容器（map.get()）中拿，
     * request：同一次请求创建一个实例
     * session：同一个session创建一个实例
     * <p>
     * 懒加载：
     * 单实例bean：默认在容器启动的时候创建对象；
     * 懒加载：容器启动不创建对象。第一次使用(获取)Bean创建对象，并初始化；
     */
//    @Scope("prototype")
    @Lazy
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加person");
        return new Person("张三", 26);
    }

    /**
     * @Conditonal: 按照一定条件判断，满足条件的注册bean
     * 根部不同的操作系统，注入不同的bean对象
     */
    @Conditional(value = {WindowsCondition.class})
    @Bean("bill")
    public Person person01() {
        return new Person("bill gates", 62);
    }

    @Conditional(value = {LinuxCondition.class})
    @Bean("linus")
    public Person person02() {
        return new Person("linus", 48);
    }

    /**
     * 给容器中注册组件
     * 1）、包扫描+组件标注注解（@Controller+@Repository+@Service+@Component)--局限于字节写的类，上面加注解就好
     * 2)、 @Bean 用域导入的第三方包，例如上述的Person类如果是第三方包，则可以通过@Bean加入，比较简单的组件可以
     * 3）、@Import【快速的给容器中导入一个组件】
     *      1) @Import(要导入到容器中的组件）：容器中就会自动注册这个组件，id默认是全类名
     *      2） ImportSelector: 返回需要导入的组件的全类名 ，在springboot中用的特别多
     *      3) ImportBeanDefinitionRegistrar 手动注册bean到容器中
     * 4） 使用spring提供的FactoryBean(工厂bean）
     *      1) 默认获取的是工厂bean调用getObject创建的对象
     *      2） 获取工厂bean本身，需要给id前加“&”标识
     *
     *
     *
     *
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }

}
