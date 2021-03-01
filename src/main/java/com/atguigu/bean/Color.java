package com.atguigu.bean;

/**
 * @author luzc
 * @date 2021/1/31 15:49
 * @desc
 */
public class Color {

    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }

}
