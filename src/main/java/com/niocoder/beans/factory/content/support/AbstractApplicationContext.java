package com.niocoder.beans.factory.content.support;

import com.niocoder.beans.factory.content.ApplicationContext;
import com.niocoder.beans.factory.content.Resource;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.support.XmlBeanDefinitionReader;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory factory = null;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinition(resource);
    }

    public abstract Resource getResourceByPath(String configFile);

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}