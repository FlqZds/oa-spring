package com.fcfz.oas;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class springApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext springApplication = new ClassPathXmlApplicationContext("springApplication.xml");
//        springApplication.getBean()
    }
}
