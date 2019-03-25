package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.SimpleTypeConverter;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.beans.factory.NoSuchBeanDefinitionException;
import com.niocoder.beans.factory.config.*;
import com.niocoder.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * BeanFactory的默认实现类
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry {

    /**
     * 存放BeanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /**
     * 存放BeanPostProcessor
     */
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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

        for (BeanPostProcessor postProcessor : this.getBeanPostProcessors()) {
            if (postProcessor instanceof InstantiationAwareBeanPostProcessor) {
                ((InstantiationAwareBeanPostProcessor) postProcessor).postProcessPropertyValues(bean, bd.getId());
            }
        }

        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs==null||pvs.isEmpty()) {
            return;
        }

        // 处理 bean.xml 中的 ref 和 value 对应  RuntimeBeanReference TypedStringValue
        //        <property name="itemDao" ref="itemDao"></property>
        //        <property name="url" value="http://niocoder.com/"></property>
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
        // 处理bean.xml中的特殊类型 Integer Boolean Date 等
        //        <property name="birthday" value="2019-01-21"></property>
        //        <property name="flag" value="true"></property>
        //        <property name="version" value="1"></property>
        SimpleTypeConverter converter = new SimpleTypeConverter();
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
                        // 类型转换
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertedValue);
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
        if (bd.hasConstructorArgumentValues()) {
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowireConstructor(bd);
        } else {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            String beanClassName = bd.getBeanClassName();
            try {
                Class<?> clz = cl.loadClass(beanClassName);
                bd.setBeanClass(clz);
                // 使用反射创建bean的实例，需要对象存在默认的无参构造方法
                return clz.newInstance();
            } catch (Exception e) {
                throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
            }
        }
    }

    @Override
    public void registerBeanDefinition(String beanID, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanID, bd);
    }

    @Override
    public Object resolveDependency(DependencyDescriptor descriptor) {
        Class<?> typeToMatch = descriptor.getDependencyType();
        for (BeanDefinition bd : this.beanDefinitionMap.values()) {
            // 确保BeanDefinition 有Class对象,而不是class的字符串
            resolveBeanClass(bd);
            Class<?> beanClass = bd.getBeanClass();
            if (typeToMatch.isAssignableFrom(beanClass)) {
                return this.getBean(bd.getId());
            }
        }
        return null;
    }

    public void resolveBeanClass(BeanDefinition bd) {
        if (bd.hasBeanClass()) {
            return;
        } else {
            try {
                bd.resolveBeanClass(ClassUtils.getDefaultClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("can't load class" + bd.getBeanClassName());
            }
        }
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        this.beanPostProcessors.add(postProcessor);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        BeanDefinition bd = this.getBeanDefinition(name);
        if (null == bd) {
            throw new NoSuchBeanDefinitionException(name);
        }
        resolveBeanClass(bd);

        return bd.getBeanClass();
    }

}
