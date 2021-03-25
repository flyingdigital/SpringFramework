## 实例化Bean对象方法

会用到如下代码:

- user.java

  ```
  package com.flyingdigital.user;
  
  public class User {
  
      public  User(){
  
      }
      public User(String s){
  
      }
      public void test(){
          System.out.println("User Class...");
      }
  }
  
  ```

- StaticFactory.java

  ```
  package com.flyingdigital.factory;
  
  import com.flyingdigital.user.User;
  
  public class StaticFactory {
      public static User createUser(){
          return new User();
      }
  }
  
  ```

- NormalFactory.java

  ```
  package com.flyingdigital.factory;
  
  import com.flyingdigital.user.User;
  
  public class NormalFactory {
  
      public User createUser(){
          return new User();
      }
  }
  
  ```

- spring.xml

  ```
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans
          https://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <!-- 构造器实例化
      <bean id="user" class="com.flyingdigital.user.User">
      </bean>
      -->
      <!-- 静态工厂实例化
      <bean id="user" class="com.flyingdigital.factory.StaticFactory" factory-method="createUser"></bean>
      -->
      <!-- 实例化工厂实例化，其实跟静态工厂实例化原理一样-->
      <bean id="factory" class="com.flyingdigital.factory.NormalFactory"></bean>
      <bean id="user" factory-bean="factory" factory-method="createUser"></bean>
  </beans>
  
  
  ```

- 实例化代码

  ```
  BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml");
  User user = (User) factory.getBean("user");
  user.test();
  ```

  

### 构造器实例化

构造器实例化配置文件见`spring.xml`。需要说明的是，在使用构造器实例化必须保证**有空构造方法(默认有)**，如果有带参构造方法，则需另外声明一个空构造方法，否则无法通过编译。

构造器实例化为最常用的实例化方法。



### 静态工厂实例化

它的做法是再一个类里边声明一个静态方法，这个静态方法会返回一个需要的对象，见`StaticFactory.java`。

其中配置文件的标签写法需要为bean标签增加一个`factory-method`属性，其值为静态实例化方法名。

```
<bean id="..." class="..." factory-method="..."></bean>
```



### 实例化工厂实例化

这个其实跟静态工厂实例化很像，它是先声明一个对象，这个对象里边包含了一个返回目标对象的方法。



```
<bean id="factory" class="com.flyingdigital.factory.NormalFactory"></bean>
```

这个其实是实例化一个(NormalFactory)bean对象

```
<bean id="user" factory-bean="factory" factory-method="createUser"></bean>
```

这个才是NormalFactory实例化(User)bean对象



