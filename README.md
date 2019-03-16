# small-spring
简版spring
##BeanFactory(二)
在Small Spring系列一：
BeanFactory(一)中，我们用DefaultBeanFactory读取bean.xlm中的bean信息，并且也实现了BeanFactory的getBean()方法。
但是实现的方式有些不友好，本章，我们将优化和完善BeanFactory。


你会发现在DefaultBeanFactory中存在读取bean.xml和反射创建对象两种操作，这不符合设计模式的单一职责。
因此我们新增加一个XmlBeanDefinitionReader来读取bean.xml。
但解析的BeanDefinition如何交给BeanFactory处理呢？
这里我们可以在BeanFactory接口中增加registerBeanDefinition(String beanID, BeanDefinition bd)方法，
将XmlBeanDefinitionReader解析的BeanDefinition注册到BeanFactory中。
(此时XmlBeanDefinitionReader中需要持有一个BeanFactory的实例)。
类图如下: 
https://raw.githubusercontent.com/longfeizheng/longfeizheng.github.io/master/images/spring/beanfactory_v2_1.png