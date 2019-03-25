package com.niocoder.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Created on 2019/2/17.
 * Advice通知接口,内含有getPointcut()方法，返回Pointcut判断方法是否匹配
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public interface Advice extends MethodInterceptor {

    /**
     * 获取Pointcut
     * @return
     */
    Pointcut getPointcut();
}