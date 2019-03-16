package com.niocoder.test.v1;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.BeanDefinitionRegistry;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.content.ApplicationContext;
import com.niocoder.beans.factory.content.support.ClassPathXmlApplicationContext;
import com.niocoder.beans.factory.content.support.FileSystemXmlApplicationContext;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.support.XmlBeanDefinitionReader;
import com.niocoder.service.v1.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BeanFactory 测试类
 */
public class ApplicationContextTest {

    @Test
    public void testGetBeanFromClassPathContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) context.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/bean-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) context.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }
}
