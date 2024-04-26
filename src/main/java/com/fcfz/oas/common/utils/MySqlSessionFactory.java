package com.fcfz.oas.common.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MySqlSessionFactory {
    //1 为了同一个事务【线程中】对数据库n次操作 建立在对同一次数据库[SqlSession]的连接之上
    private static ThreadLocal<SqlSession> local;
    //2 session 工厂[myatis 框架提供]
    private static SqlSessionFactory factory;


    static {
        local = new ThreadLocal<SqlSession>();
        //1 丛配置文件中 读取 对数据库的连接的信息
        InputStream in = null;
        InputStreamReader isr = null;
        try {
            in = Resources.getResourceAsStream("mb.xml");
            //通过读出来的 连接信息 去初始化 session工厂
//            isr=new InputStreamReader(in,"UTF-8");
            factory = new SqlSessionFactoryBuilder().build(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //获得session的方法
    public static SqlSession getSession() {
        // 1 首先从线程容器中取
        SqlSession session = local.get();
        if (session == null) {
            session = factory.openSession();
            local.set(session);
        }
        return session;
    }


    //关闭session
    public static void closeSession() {
        SqlSession session = local.get();
        if (session != null) {
            session.close();
            local.remove();
            local.set(null);
        }
    }
}
