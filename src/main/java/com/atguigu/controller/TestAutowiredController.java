package com.atguigu.controller;

import com.atguigu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzc
 * @date 2021/4/29 10:06
 * @desc 测试 自动装配
 *
 * 测试结果：当装配过程中发现有多个实现类时，需要通过@Qualifier 指定装配的对象
 */

@RestController
public class TestAutowiredController {

    @Qualifier("testServiceAImpl")
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/testAutoWired",method = RequestMethod.GET)
    public String test(){
        return testService.getString();
    }
}
