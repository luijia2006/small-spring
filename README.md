# small-spring
简版spring
##AOP
在Small Spring系列八：
aop (一)中,我们实现了Pointcut和MethodLocatingFactory,
Pointcut根据给定一个类的方法判断是否符合expression表达式,
MethodLocatingFactory更具targetBeanName和methodName返回一个Method对象。
本章我们来实现aop的链式调用和Cglib的动态代理。
关于更详细的解析aop链式调用可参考：spring aop 之链式调用

https://niocoder.com/2019/02/19/spring-aop-%E9%93%BE%E5%BC%8F%E8%B0%83%E7%94%A8/