package com.util;

import java.sql.*;

public class JdbcUtil {
    final String URL = "jdbc:mysql://localhost:3306/powernode";
    final String USERNAME = "root";
    final String PASSWORD = "980409";
    PreparedStatement ps = null;
    Connection conn = null;

    //将jar包中driver类加载到JVM中
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //封装连接通道创建细节
    public Connection getCon(){
        try{
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    //封装交通工具创建细节
    public PreparedStatement createStatement(String sql){
        try{
            ps = getCon().prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    //ps与conn的销毁
    public void close(){
        if(ps!=null){
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public  void close(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
