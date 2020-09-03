package com.dao;

import com.entity.User;
import com.util.JdbcUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    JdbcUtil util = new JdbcUtil();
    //用户注册
    public int add(User user){
        String sql = "insert into user(userName,password,sex,email)"+
                "values(?,?,?,?)";
        int result=0;
        try {
            PreparedStatement ps = util.createStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getPassWord());
            result = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            util.close();
        }
        return result;

    }

    //查询所有用户信息
    public List<User> find(){
        List<User> userList = new ArrayList<>();
        String sql = "select * from user";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while(rs.next()){
                Integer userId = rs.getInt("userId");
                String  userName = rs.getString("userName");
                String  password = rs.getString("password");
                String  sex = rs.getString("sex");
                String email = rs.getString("email");
                User user = new User(userId,userName,password,sex,email);
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return userList;
    }

    //根据用户编号删除用户信息
    public  int delete(String userId){
        String sql = "delete from user where userId=?";
        int result=0;
        try {
            PreparedStatement ps = util.createStatement(sql);
            ps.setInt(1,Integer.valueOf(userId));
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    //验证登陆的用户名和密码是否匹配
    public int loginCheck(String userName,String password){
        String sql  = "select count(*) from user where userName=? and password=?";
        int result=0;
        //声明车车  ,create 车车
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        try {
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            while(rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }
}
