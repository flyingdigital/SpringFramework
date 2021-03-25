package com.flyingdigital;

import com.flyingdigital.dao.Dao;
import com.flyingdigital.service.Service;
import com.flyingdigital.user.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *       导入配置文件的方法
 *  1. 只有一个配置文件时，将配置文件名传给ClassPathXmlApplicationContext构造方法
 *  2. 有多个配置文件时，可以将多个配置文件名传给 ClassPathXmlApplicationContext构造方法 逗号分隔
 *  3. 有多个配置文件时，也可以再某一个配置文件使用import标签进行导入
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /*   在配置文件中使用了import标签导入其他配置文件
        BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml");
        User user = (User) factory.getBean("user");
        Service service = (Service) factory.getBean("service");
        user.test();
        service.test();
         */
        /* 把配置文件传给ClassPathXmlApplicationContext构造方法 */
        BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml","dao.xml");
        User user = (User) factory.getBean("user");
        Service service = (Service) factory.getBean("service");
        Dao dao = (Dao)factory.getBean("dao");
        user.test();
        service.test();
        dao.test();

    }
}
