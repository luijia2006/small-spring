package com.niocoder.aop;

/**
 * 获取expression和MethodMatcher
 */
public interface Pointcut {

    /**
     * 获取MethodMatcher 判断方法时候匹配
     *
     * @return
     */
    MethodMatcher getMethodMatcher();

    /**
     * 获取expression表达式
     *
     * @return
     */
    String getExpression();
}