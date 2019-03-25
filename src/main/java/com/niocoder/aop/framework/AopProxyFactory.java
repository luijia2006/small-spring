package com.niocoder.aop.framework;

/**
 * 获取代理类的工厂。
 */
public interface AopProxyFactory {

    /**
     * 获取代理类
     *
     * @return
     */
    Object getProxy();

    /**
     * 根据类加载器获取代理类
     *
     * @param classLoader
     * @return
     */
    Object getProxy(ClassLoader classLoader);
}