package com.niocoder.context.support;

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
    }

    public abstract Resource getResourceByPath(String configFile);

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}