package com.atguigu.config;

import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author luzc
 * @date 2021/2/1 14:12
 * @desc
 *
 * 自动装配：
 *      spring采用依赖注入（DI)，完成对IOC容器中各个组件的依赖关系赋值
 *
 * 1） @Autowired:自动注入
 *      1) 默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 *      2） 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
 *                                      applicationContext.getBean("bookDao");
 *      3） @Qualifier("bookDao") 使用@Qualifier指定需要装配的组件id，而不是使用属性名
 *      4） 自动装配，默认一定要将属性赋值好，没有就会报错
 *          可以使用	@Autowired(required = false) 能找到就进行装配
 *      5） @Primary :让spring自动装配的时候，默认选择首选的bean
 *          也可以继续使用@Qualifier 指定需要装配的bean的名字
 *      BookService{
 *          @Autowired
 *          BookDao bookDao;
 *      }
 *
 * 2） spring还支持使用@Resouce(JSR250) 和 @Inject(JSR330)[java规范注解]
 *          @Resouce
 *              java规范
 *              和 @Autowired一样实现自动装配功能，默认是按照组件名称进行装配的
 *              不能支持@Primary 和 @Autowired(required = false)
 *          @Inject
 *              java规范
 *              需要导入 javax.inject
 *              支持@Primary
 *          @Autowired
 *              spring定义的
 *
 * 3） @Autowired:可以标在构造器、参数、方法、属性；都是从容器中获取参数位置
 *     1) 方法    @Bean + 方法参数，参数都是从容器中获取；默认不写@Autowired,都能自动装配
 *     2） 构造器 如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件自动从容器中获取
 *     3) 放在参数位置
 *
 * 4） 自定义组件想要使用spring容器底层的一些组件（applicationContext,BeanFactory,xxx)
 *       自定义组件实现xxxAware;在创建对象的时候，会调用接口规定的方法注入相关组件
 *       把spring底层的一些组件注入到自定义的bean中
 *       xxxAware： 功能使用xxxProcessor
 *          ApplicationContextAware => ApplicationContextAwareProcessor(后置处理器);
 *
 *
 */

@Configuration
@ComponentScan({"com.atguigu.service","com.atguigu.dao","com.atguigu.controller","com.atguigu.bean"})
public class MainConfigAutowired {

    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLable("2");
        return bookDao;
    }

    /**
     * @Bean 标注的方法创建对象的时候，方法参数的值从容器中获取
     *
     * @param car
     * @return
     */
    @Bean
    public Color color(Car car){
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
