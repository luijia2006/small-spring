package com.niocoder.beans.factory;

/**
 * 工厂bean
 * @author zhenglongfei
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();
}