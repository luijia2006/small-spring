package com.niocoder.beans.factory.content.support;

import com.niocoder.beans.factory.content.ApplicationContext;
import com.niocoder.beans.factory.content.Resource;
import com.niocoder.beans.factory.support.DefaultBeanFactory;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }


    @Override
    public Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile);
    }

}
