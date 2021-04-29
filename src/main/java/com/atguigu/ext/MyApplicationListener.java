package com.atguigu.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author luzc
 * @date 2021/3/13 13:59
 * @desc
 *
 *
 */

@Component
public class MyApplicationListener implements ApplicationListener {

    // 当容器中发布此事件以后 方法会得到触发
    @Override
    public void onApplicationEvent(ApplicationEvent event) {


        System.out.println("收到事件："+event);
    }
}
