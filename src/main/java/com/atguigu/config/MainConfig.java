package com.atguigu.config;

import com.atguigu.bean.Person;
import com.atguigu.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author luzc
 * @date 2021/1/31 10:45
 * @desc
 */

@Configuration
@ComponentScan(value = "com.atguigu", includeFilters = {
        // FilterType.ANNOTATION：按照注解
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),
        // FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
        // FilterType.CUSTOM：使用自定义规则
        @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {MyTypeFilter.class})
}
        , useDefaultFilters = false)
// excludeFilters = Filter[]： 指定扫描的时候按照什么规则排除哪些组件
// useDefaultFilters 默认是包含所有 @Repository @Controller @Service @Component 注解的组件
// @ComponentScan  value:指定要扫描的包
// excludeFilters = Filter[] ：指定扫描的时候按照什么规则排除那些组件
// includeFilters = Filter[] ：指定扫描的时候只需要包含哪些组件
// FilterType.ASPECTJ：使用ASPECTJ表达式
// FilterType.REGEX：使用正则指定
public class MainConfig {

    @Bean(value = "person")
    public Person person() {
        return new Person("lisa", 13);
    }
}
