package com.niocoder.context.support;

import com.niocoder.aop.aspectj.AspectJAutoProxyCreator;
import com.niocoder.beans.factory.NoSuchBeanDefinitionException;
import com.niocoder.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;
import com.niocoder.context.ApplicationContext;
import com.niocoder.core.io.Resource;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.List;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory = null;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
        registerBeanPostProcessors(factory);
    }

    public abstract Resource getResourceByPath(String configFile);

    protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {

        {
            //aop的后置处理器
            AspectJAutoProxyCreator postProcessor = new AspectJAutoProxyCreator();
            postProcessor.setBeanFactory(beanFactory);
            beanFactory.addBeanPostProcessor(postProcessor);
        }

        {
            //autowire的后置处理器
            AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
            processor.setBeanFactory(factory);
            beanFactory.addBeanPostProcessor(processor);
        }
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return this.factory.getType(name);
    }

    @Override
    public List<Object> getBeansByType(Class<?> type) {
        return this.factory.getBeansByType(type);
    }
}
