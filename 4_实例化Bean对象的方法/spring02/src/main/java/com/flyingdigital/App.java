package com.flyingdigital;

import com.flyingdigital.user.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  实例化bean对象
 *  class一定要有空构造方法(默认有)，如果只有一个带参构造方法 报错
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User) factory.getBean("user");
        user.test();
    }
}
