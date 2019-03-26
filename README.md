# small-spring
简版spring
##AOP
在前四篇,我们已经实现了使用Cglib实现了aop动态代理。
但是在spring中如果代理对象实现了接口，则默认使用jdk动态代理，也可以通过配置强制使用cglib代理。
本篇，我们使用jdk动态代理来完善aop
接下来只剩下最后一个工作，读取bean-v5.xml创建BeanDefinition。