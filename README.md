# small-spring
简版spring
##V3.0,单例模式添加
我们用BeanDefinition表达了<bean>标签中的 id和class属性，对应的<property>标签该如何表达呢？
还有<property>里面的ref和value？
如果你在NioCoderService中新增一个Integer类型的属性，你会发现上面的setter注入失败,这是为什么呢？

因为不管属性是任何类型（double,float,Integer等）在配置文件中都是对应的字符串类型的字面值,
如果想要实现类型转换则需要使用java.beans.PropertyEditor的编辑器。