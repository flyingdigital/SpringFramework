package com.flyingdigital.MyClassPathXmlApplicationContext;

import com.flyingdigital.MyBean.MyBean;
import com.flyingdigital.MyBeanFactory.MyBeanFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyClassPathXmlApplicationContext implements MyBeanFactory {

    private List<MyBean> l= null;
    private Map<String,Object> m = new HashMap<>();


    public MyClassPathXmlApplicationContext(){}
    public MyClassPathXmlApplicationContext(String file){
        this.parse(file);
        this.insertInstance();

    }

    /**
     *
     * 实例化 并放入map
     */
    public void insertInstance() {
        if(l!=null && l.size()>0){
            for(MyBean e: l){
                String id = e.getId();
                try {
                    Object obj = Class.forName(e.getClazz()).newInstance();
                    m.put(id, obj);
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        }
    }

    /**
     * 解析配置xml文件
     * @param file
     */
    public void parse(String file){
        SAXReader reader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(file);
//        System.out.println(this.getClass());
//        System.out.println(this.getClass().getClassLoader());
        System.out.println(this.getClass());
        System.out.println(this.getClass().getResource("/"));
        System.out.println(url); // 这里打印的路径 与JVM有关系
//        URL url=this.getClass().getClassLoader().getResource(file);
        try{
            Document document = reader.read(url);  // 这里实际返回的是一个DefaultDocument对象 DefaultDocument的父类AbstractDocument实现了document接口
            System.out.println((reader.read(url)).getClass());
            XPath xPath = document.createXPath("beans/bean");
            List<Element> elementList = xPath.selectNodes(document);   // 返回配置文件里的每一个bean
            // 判断是否存在
            if(elementList!=null && elementList.size()>0) {
                this.l = new ArrayList<>();
                for (Element e : elementList) {
                    String id = e.attributeValue("id");
                    String clazz = e.attributeValue("class");

                    MyBean mb = new MyBean(id,clazz);
                    l.add(mb);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public Object getBean(String id) {
        return m.get(id);
    }
}
