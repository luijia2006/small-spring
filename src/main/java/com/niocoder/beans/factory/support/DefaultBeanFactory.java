package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * BeanFactory的默认实现类
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory,BeanDefinitionRegistry {

    /**
     * 存放BeanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (bd == null) {
            return null;
        }
        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean==null) {
                bean = this.createBean(bd);
                this.registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean(bd);
    }
    private Object createBean(BeanDefinition bd) {
        // 1. 创建实例
        Object bean = instantiateBean(bd);
        // 2. 设置属性
        populateBean(bd, bean);

        return bean;
    }
    private void populateBean(BeanDefinition bd,Object bean) {
        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs==null||pvs.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
        try{
            for (PropertyValue pv : pvs) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                pv.setConvertedValue(resolvedValue);
                // set 注入 使用java BeanInfo 实现
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {
                    if (pd.getName().equals(propertyName)) {
                        pd.getWriteMethod().invoke(bean, resolvedValue);
                        break;
                    }
                }
            }
        }catch (Exception e){
            throw new BeanCreationException("Failed to obtain BeanInfo for class ["
                    + bd.getBeanClassName() + "]", e);
        }

    }
    private Object instantiateBean(BeanDefinition bd) {
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }

    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }
}
