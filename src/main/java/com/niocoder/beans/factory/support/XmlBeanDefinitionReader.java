package com.niocoder.beans.factory.support;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanDefinitionRegistry;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.BeanFactory;
import com.niocoder.beans.factory.content.Resource;
import com.niocoder.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

public class XmlBeanDefinitionReader {

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    BeanDefinitionRegistry beanFactory;
    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanFactory) {
        this.beanFactory = beanFactory;

    }
    /**
     * 具体解析bean.xml的方法 使用dom4j
     */
    public void loadBeanDefinition(Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();
            while (elementIterator.hasNext()) {
                Element ele = elementIterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                this.beanFactory.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document", e);
        }
    }
}
