package com.niocoder.context.support;

import com.niocoder.context.ApplicationContext;
import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }


    @Override
    public Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }

}
