package com.niocoder.beans.factory;

import com.niocoder.beans.BeanDefinition;

/**
 * 注册BeanDefinition接口
 */
public interface BeanDefinitionRegistry {

    /**
     * 获取bean的定义
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);


    /**
     * 注册 BeanDefinition
     * @param beanID
     * @param bd
     */
    void registerBeanDefinition(String beanID, BeanDefinition bd);

}
