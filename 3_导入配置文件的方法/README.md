## Spring导入配置文件方法

### 有一个配置文件时

#### 通过ClassPathXmlApplicationContext

假设有一个名为`spring.xml`的配置文件。

```
BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml")
```

### 有多个配置文件时

假设有三个配置文件如下:

spring.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    使用import导入其他配置文件-->
    <import resource="service.xml"/>
    <bean id="user" class="com.flyingdigital.user.User">
    </bean>



</beans>


```

service.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="service" class="com.flyingdigital.service.Service">
    </bean>

</beans>

```

dao.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dao" class="com.flyingdigital.dao.Dao">
    </bean>

</beans>
```



#### 使用import标签导入

可以再某一个配置文件使用`import`标签导入别的配置文件(如上spring.xml, service.xml)，再将这个总的配置文件传给ClassPathXmlApplicationContext。

import标签格式

`<import resource=""></import>`

程序引用(假设二者都有test()方法)

```
BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml");
User user = (User) factory.getBean("user");
Service service = (Service) factory.getBean("service");
user.test();
service.test();
```

#### 向ClassPathXmlApplicationContext传入多个配置文件

操作如下

```
BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml","dao.xml");
User user = (User) factory.getBean("user");
Service service = (Service) factory.getBean("service");
Dao dao = (Dao)factory.getBean("dao");
user.test();
service.test();
dao.test();
```

