package com.flyingdigital;

import com.flyingdigital.MyBeanFactory.MyBeanFactory;
import com.flyingdigital.MyClassPathXmlApplicationContext.MyClassPathXmlApplicationContext;
import com.flyingdigital.User.User;

public class Test {

    public static void main(String[] args){
        MyBeanFactory mbf = new MyClassPathXmlApplicationContext("spring.xml");
        User user= (User)mbf.getBean("user");
        user.test();
    }
}
