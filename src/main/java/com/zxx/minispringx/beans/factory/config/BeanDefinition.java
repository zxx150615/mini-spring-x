package com.zxx.minispringx.beans.factory.config;

import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.PropertyValues;

// BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型
public class BeanDefinition {

    private PropertyValues propertyValues;

    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues: new PropertyValues();
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
