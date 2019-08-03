package com.derek.demo.auto_Package;

import M1.Utils.JDBCUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Demo11 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Connection cnn = JDBCUtils.getConnection();
        String sql="Select * from employee where id>?;";
        PreparedStatement pstat = cnn.prepareStatement(sql);
        pstat.setInt(1,1);
        ResultSet rs = pstat.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        Class<?> emp_class = Class.forName("com.derek.demo.auto_Package.Employee");

        //使用反射得到进行自动封装
        while (rs.next()){
            Constructor<?> c = emp_class.getConstructor();
            Object e = c.newInstance();
            int count=metaData.getColumnCount();
            for (int i = 1; i < count+1; i++) {
                //通过元数据获得字段名，字段名注意大小写可能有区别
                String cName = metaData.getColumnName(i).toLowerCase();
                //通过 结果集 + 索引 可以获得值
                Object value = rs.getObject(cName);
                //使用反射传入通过元数据集得到的字段名
                Field field = emp_class.getDeclaredField(cName);
                field.setAccessible(true);
                field.set(e,value);
            }
            System.out.println("e = " + e);
        }
        //记得释放资源
        JDBCUtils.close(rs,pstat,cnn);
    }
}
