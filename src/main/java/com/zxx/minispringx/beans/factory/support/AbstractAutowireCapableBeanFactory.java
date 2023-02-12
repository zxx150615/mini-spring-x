package com.zxx.minispringx.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.zxx.minispringx.beans.BeansException;
import com.zxx.minispringx.beans.PropertyValue;
import com.zxx.minispringx.beans.factory.config.AutowireCapableBeanFactory;
import com.zxx.minispringx.beans.factory.config.BeanDefinition;
import com.zxx.minispringx.beans.factory.config.BeanPostProcessor;
import com.zxx.minispringx.beans.factory.config.BeanReference;
import org.springframework.context.annotation.Bean;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements
        AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    // 只独立出创建Bean的逻辑
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
            // 实例化Bean 之后，下一步就是填充Bean属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行BeanPostProcessor的前置执行方法
        Object wrappedBean = applyBeanPostProcessorBeforeInitializeBean(bean, beanName);

        // 执行Bean初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 执行BeanPostProcessor的前置执行方法
        wrappedBean = applyBeanPostProcessorAfterInitializeBean(bean, beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorAfterInitializeBean(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (current == null){
                return result;
            }
            result = current;
        }
        return result;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

        // TODO 后面会实现
        System.out.println("执行bean[" + beanName + "]的初始化方法");
    }

    private Object applyBeanPostProcessorBeforeInitializeBean(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 现在填充属性，需要再考虑下属性本身是Bean的情况
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 如果是BeanReference，则通过先实例化另外一个Bean，再注入
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }
}
