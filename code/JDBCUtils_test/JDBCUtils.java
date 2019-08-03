package com.derek.demo.JDBCUtils_test;

import java.sql.*;

public class JDBCUtils {
    private static final String CLASS_NAME = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql:///day16";
    private static String user = "root";
    private static String password = "root";

    public static void setUrl(String url) {
        JDBCUtils.url = url;
    }

    public static void setUser(String user) {
        JDBCUtils.user = user;
    }

    public static void setPassword(String password) {
        JDBCUtils.password = password;
    }

    static {
        try {
            Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection cnn = DriverManager.getConnection(url, user, password);
        return cnn;
    }

    public static void close(ResultSet rs, Statement stat,Connection cnn) {
        try {
            if (rs != null)
                rs.close();
            if (stat != null)
                stat.close();
            if (cnn != null)
                cnn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stat,Connection cnn ) {
        close(null, stat, cnn);
    }


}
