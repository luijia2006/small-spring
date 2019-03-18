package com.niocoder.beans.factory.config;

public interface SingletonBeanRegistry {
    /**
     * singleton 注册
     * @param beanName
     * @param singletonObject
     */
    void registerSingleton(String beanName,Object singletonObject);

    /**
     * 获取singleton
     * @param beanName
     * @return
     */
    Object getSingleton(String beanName);
}
