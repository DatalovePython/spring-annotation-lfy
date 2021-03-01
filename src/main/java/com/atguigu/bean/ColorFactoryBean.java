package com.atguigu.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author luzc
 * @date 2021/1/31 16:31
 * @desc
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    // 返回一个color 对象，这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean....getObject");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    // 控制是否单例 true -> 单实例，在容器中保存一份
    // false -> 多实例，每次获取重新创建
    @Override
    public boolean isSingleton() {
        return false;
    }
}
