package com.derek.demo.SelfBatis.Mybatis;


import com.derek.demo.SelfBatis.Entity.Mapper;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;

public class Session {
    private Connection conn;
    private HashMap<String, Mapper> sqlmap;

    public Session(Connection conn, HashMap<String, Mapper> sqlmap) {
        this.conn = conn;
        this.sqlmap = sqlmap;
    }

    //用于返回接口实现类
    public <T> T getMapper(Class<T> type) {
        T o = (T) Proxy.newProxyInstance(
                Session.class.getClassLoader(),
                new Class[]{type},
                new MyInvocationHandler(conn,sqlmap));

        return o;

    }

}
