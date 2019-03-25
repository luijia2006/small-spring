package com.niocoder.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    /**
     * 给定一个方法判断是否匹配
     *
     * @param method
     * @return
     */
    boolean matches(Method method /*,Class<?> targetClass*/);
}