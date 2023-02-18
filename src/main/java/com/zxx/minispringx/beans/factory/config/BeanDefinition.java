package com.zxx.minispringx.beans.factory.config;

import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.PropertyValues;

// BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型
public class BeanDefinition {

    public static String SCOPE_SINGLETON = "singleton";

    public static String SCOPE_PROTOTYPE = "prototype";

    private PropertyValues propertyValues;

    private Class beanClass;

    private String initMethodName;

    private String destroyMethodName;

    private String scope = SCOPE_PROTOTYPE;

    private Boolean singleton = true;

    private Boolean prototype = false;

    public BeanDefinition(Class beanClass){
        this(beanClass, null);
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues: new PropertyValues();
    }

    public void setScope(String scope){
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public Boolean isSingleton() {
        return this.singleton;
    }

    public Boolean isPrototype(){
        return this.prototype;
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

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public String getInitMethodName() {
        return initMethodName;
    }
}
