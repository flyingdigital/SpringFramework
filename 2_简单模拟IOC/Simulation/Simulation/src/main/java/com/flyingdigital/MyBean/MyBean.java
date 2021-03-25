package com.flyingdigital.MyBean;


public class MyBean{
    // id,clazz 分别记录配置文件里的id class
    private String id;
    private String clazz;

    public MyBean(){}

    public MyBean(String id, String clazz){
        this.id=id;
        this.clazz=clazz;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
