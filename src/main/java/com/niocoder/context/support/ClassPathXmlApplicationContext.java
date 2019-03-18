package com.niocoder.context.support;

import com.niocoder.context.ApplicationContext;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.io.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }


    @Override
    public Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile);
    }

}
