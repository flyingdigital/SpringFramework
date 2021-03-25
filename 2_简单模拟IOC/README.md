## 简单模拟IOC

### 一些小点

上承创建一个新的spring项目。

1. 在最后测试的代码里核心部分有两处

   ```
   ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
   HelloWorld hello = (HelloWorld) ac.getBean("hello");
   ```

   1. `ApplicationContext`是一个接口, 他继承了`BeanFactory`, `BeanFactory`里边有getBean方法。

   2. ` ClassPathXmlApplicationContext`是一个具体的类，它本身并没有实现的`ApplicationContext`, 而是它的父类`AbstractApplicationContext`实现了该接口。这个类使用`ClassPathXmlApplicationContext("spring.xml");`来构造,  实际上调用的构造方法过程如下

      ```
      // 第一次调用的构造方法
      public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
      this(new String[]{configLocation}, true, (ApplicationContext)null);
      }
      
      // 第二次调用的构造方法
      public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, @Nullable ApplicationContext parent) throws BeansException {
          super(parent);
          this.setConfigLocations(configLocations);
          if (refresh) {
          this.refresh();
          }
      }
      ```

   3. 以上两点可以知道有一个类实现了`BeanFacotry`接口，定义了`getBean`方法。且这个类，它知道怎么拿到配置文件`spring.xml`,并且过滤出所有*bean*标签, 它还可以根据id，class两个标签重新实例化一个类（反射）。

   4. 那么反射是怎么知道这个配置文件在哪的？（且看下边分析

2. 如果细心观察，可以发现：当程序运行时，项目里多出了一个名为**Target**的目录，如果在有心一些的话可以发现该目录下的**classes**下正好有需要的配置文件。如果熟悉反射的话，如下刚好可以找到**classes**目录

   ```
   this.getClass().getResource("/")     // this 是一个类的对象 
   ```

   那么 如下语句应该可以定位配置文件路径

   ```
   this.getClass().getResource("spring.xml")	//	spring.xml 为配置文件名称
   ```

   

3. 如上两步基本确定还原做法
   1. 首先我们需要一个类，这个类来存储每一个*bean*的*id* 和 *class*,因为只要我们得到了类名，就可已使用Class.forname().newInstance()来实例化。
   2. 其次我们还需要一个接口，这个接口继承了**BeanFactory**,重写了**getBean**
   3. 再者我们同样还需要一个类，这个类实现了我们定义的接口。它可以把获取所有的bean对象，根据这些对象还原Class.forname().newInstance()，且把它们存进一个List里边。它还可以把*id*与Class.forname().newInstance()进行映射，以便**getBean**只需要根据Map的键来找它的值。

4. 结合如上三点，具体实现见**Simulation**项目。