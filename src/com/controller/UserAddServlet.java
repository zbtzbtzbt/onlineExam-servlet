package com.controller;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName,password,sex,email;
        UserDao dao = new UserDao();
        User user = null;
        int result = 0;
        PrintWriter out = null;
        //1.调用【请求对象】读取【请求头】参数信息，得到用户信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");

        //2.调用【userDao】将用户信息以SQL命令并借助JDBC规范发送到数据库服务器
        user = new User(null,userName,password,sex,email);
        result = dao.add(user);
        //System.out.println("结果"+result);

        //3.调用【相应对象】将【处理结果】以二进制形式写入响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
    }
}
