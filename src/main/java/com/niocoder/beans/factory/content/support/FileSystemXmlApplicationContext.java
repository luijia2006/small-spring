package com.niocoder.beans.factory.content.support;

import com.niocoder.beans.factory.content.ApplicationContext;
import com.niocoder.beans.factory.content.Resource;

public class FileSystemXmlApplicationContext extends AbstractApplicationContext implements ApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }


    @Override
    public Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }

}
