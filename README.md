# small-spring
简版spring
##constructor_injection
上一章我们已经实现了setter 注入,具体实现如下

新增PropertyValue类来表达<property>标签内容
新增BeanDefinitionValueResolver来区分<property>中的ref属性和value属性
使用jdk的PropertyEditorSupport用于类型转换,因为xml都是字符串类型字面值
新增TypeConverter封装一些列类型转换器
spring配置依赖注入有三种方式,setter注入、constructor注入和注解注入。
我们上一章已实现setter注入，本章继续实现constructor注入。