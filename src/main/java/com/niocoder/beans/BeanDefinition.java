package com.niocoder.beans;

/**
 * bean.xml bean的定义
 * @author zhenglongfei
 */
public interface BeanDefinition {
    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";
    /**
     * 多例
     */
    String SCOPE_PROTOTYPE = "prototype";
    /**
     * 默认为空，即位单例模式
     */
    String SCOPE_DEFAULT="";
    /**
     * 是否为单例
     */
    boolean isSingleton();

    /**
     * 是否为多例
     */
    boolean isPrototype();

    /**
     * 获取scope配置
     */
    String getScope();

    /**
     * 设置scope
     */
    void setScope(String scope);

    /**
     * 获取bean.xml中 bean的全名 如 "com.niocoder.service.v1.NioCoderService"
     * @return
     */
    String getBeanClassName();
}
