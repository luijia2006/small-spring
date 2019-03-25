package com.niocoder.context.support;

import com.niocoder.beans.factory.NoSuchBeanDefinitionException;
import com.niocoder.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.niocoder.beans.factory.config.ConfigurableBeanFactory;
import com.niocoder.context.ApplicationContext;
import com.niocoder.core.io.Resource;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;

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
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setBeanFactory(factory);
        beanFactory.addBeanPostProcessor(processor);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return this.factory.getType(name);
    }
}
