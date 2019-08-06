package com.derek.demo.SelfBatis.Mybatis;

import com.derek.demo.SelfBatis.Entity.Mapper;

import java.sql.Connection;
import java.util.HashMap;

public class SessionFactory {

    private Connection conn;
    private HashMap<String, Mapper> sqlmap;

    public SessionFactory(Connection conn, HashMap<String, Mapper> sqlmap) {
        this.conn = conn;
        this.sqlmap = sqlmap;
    }

    public Session openSession() {
        return new Session(conn,sqlmap);
    }
}
