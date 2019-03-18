package com.niocoder.beans.factory.support;

import com.niocoder.beans.factory.config.SingletonBeanRegistry;
import com.niocoder.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例模式注册
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName,"'beanName' must not be null");

        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject!=null) {
            throw new IllegalStateException("Could not register ["+singletonObject+
                    "] under bean name '"+beanName+"': there is a already object [" + oldObject + "] bound");
        }
        this.singletonObjects.put(beanName, singletonObject);

    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
