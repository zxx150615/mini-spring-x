package com.zxx.minispringx.beans.factory;

import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.PropertyValues;
import com.zxx.minispringx.beans.factory.beans.Person;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class PopulateBeanWithPropertyValuesTest {


    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception{

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        PropertyValue nameProperty = new PropertyValue("name", "曾祥鑫");
        PropertyValue ageProperty = new PropertyValue("age", 26);
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(nameProperty);
        propertyValues.addPropertyValue(ageProperty);
        beanDefinition.setPropertyValues(propertyValues);
        factory.registryBeanDefinition("person", beanDefinition);
        Person person = (Person)factory.getBean("person");
        System.out.println("name：" + person.getName());
        System.out.println("age：" + person.getAge());
    }
}
