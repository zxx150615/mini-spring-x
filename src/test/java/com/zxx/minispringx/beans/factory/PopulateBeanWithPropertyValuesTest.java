package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.PropertyValues;
import com.zxx.minispringx.beans.factory.beans.Car;
import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanReference;
import com.zxx.minispringx.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class PopulateBeanWithPropertyValuesTest {


    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        PropertyValue nameProperty = new PropertyValue("name", "曾祥鑫");
        PropertyValue ageProperty = new PropertyValue("age", 26);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(nameProperty);
        propertyValues.addPropertyValue(ageProperty);
        beanDefinition.setPropertyValues(propertyValues);
        factory.registryBeanDefinition("person", beanDefinition);
        Person person = (Person) factory.getBean("person");
        System.out.println("name：" + person.getName());
        System.out.println("age：" + person.getAge());
    }

    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class);
        PropertyValues carPropertyValues = new PropertyValues();
        PropertyValue brandProperty = new PropertyValue("brand", "兰博基尼");
        //PropertyValue personProperty = new PropertyValue("person", new BeanReference("person"));
        carPropertyValues.addPropertyValue(brandProperty);
        // carPropertyValues.addPropertyValue(personProperty);
        carBeanDefinition.setPropertyValues(carPropertyValues);
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class);
        PropertyValues personPropertyValues = new PropertyValues();
        PropertyValue namePropertyValue = new PropertyValue("name", "曾祥鑫");
        PropertyValue agePropertyValue = new PropertyValue("age", "26");
        PropertyValue carPropertyValue = new PropertyValue("car", new BeanReference("car"));
        personPropertyValues.addPropertyValue(namePropertyValue);
        personPropertyValues.addPropertyValue(agePropertyValue);
        personPropertyValues.addPropertyValue(carPropertyValue);
        personBeanDefinition.setPropertyValues(personPropertyValues);
        factory.registryBeanDefinition("person", personBeanDefinition);
        factory.registryBeanDefinition("car", carBeanDefinition);
        Person person = (Person) factory.getBean("person");
        System.out.println("姓名：" + person.getName());
        System.out.println("年龄：" + person.getAge());
        System.out.println("开什么车：" + person.getCar().getBrand());
    }
}
