package com.niocoder.beans.factory.support;

import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.config.TypedStringValue;

/**
 * 将<property name ="accountDao" ref="accountDao"></property>
 * accountDao 转换成实例bean
 */
public class BeanDefinitionValueResolver {
    //含factory 因为factory有getBean方法
    private final DefaultBeanFactory factory;
    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }
    public Object resolveValueIfNecessary(Object value) {
        //判断是ref还是value
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.factory.getBean(refName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            // TODO
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
