package com.derek.demo.SelfBatis.Mybatis;

import com.derek.demo.SelfBatis.Entity.Mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class MyInvocationHandler implements InvocationHandler {
    private Connection conn;
    private HashMap<String, Mapper> sqlmap;
    private ArrayList list=new ArrayList();

    public MyInvocationHandler(Connection conn, HashMap<String, Mapper> sqlmap) {
        this.conn = conn;
        this.sqlmap = sqlmap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //得到被调用方法的名字
        String methodName = method.getName();
        //得到这个方法声明时的Class对象
        String className = method.getDeclaringClass().getName();//得到类名
        String allName = className + "." + methodName;
        //通过键找到值,得到sql语句
        Mapper mapper = sqlmap.get(allName);
        String sql = mapper.getSql();
        //执行sql语句
        PreparedStatement pstat = conn.prepareStatement(sql);
        ResultSet resultSet = pstat.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        //通过mapper里的resulttype反射得到类
        Class<?> aCls = Class.forName(mapper.getResultType());
        Constructor<?> constructor = aCls.getConstructor();

        //对得到的数据进行封装
        while (resultSet.next()) {
            int count = metaData.getColumnCount();
            Object o = constructor.newInstance();
            for (int i = 1; i <= count; i++) {
                String cName = metaData.getColumnName(i);
                Object cValue = resultSet.getObject(i);

                //通过反射得到字段名
                Field dField = aCls.getDeclaredField(cName);
                dField.setAccessible(true);
                dField.set(o, cValue);
            }
            list.add(o);
        }
        //关闭资源
        resultSet.close();
        pstat.close();
        conn.close();
        return list;
    }
}
