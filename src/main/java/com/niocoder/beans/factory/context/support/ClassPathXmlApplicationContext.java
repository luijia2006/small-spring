package com.niocoder.beans.factory.context.support;

import com.niocoder.beans.factory.context.ApplicationContext;
import com.niocoder.beans.factory.context.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }


    @Override
    public Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile);
    }

}
