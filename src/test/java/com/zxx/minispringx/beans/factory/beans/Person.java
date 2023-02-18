package com.zxx.minispringx.beans.factory.beans;

import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.factory.DisposableBean;
import com.zxx.minispringx.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {

    private String name;

    private int age;

    private Car car;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("InitializingBean init方法");
    }

    public void customInitMethod(){
        System.out.println("自定义初始化方法");
    }

    public void customDestroyMethod(){
        System.out.println("自定义销毁方法");
    }
}
