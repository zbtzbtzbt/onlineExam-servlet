package com.controller;

import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password;
        UserDao dao = new UserDao();
        int result = 0;
        //1.调用请求对象对请求体使用utf-8字符集进行重写编写
        request.setCharacterEncoding("utf-8");
        //2.调用请求对象读取请求体中的参数信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        //3.调用DAO将查询验证信息推送给数据库服务器上
        result = dao.loginCheck(userName,password);
        //4.调用相应对象，根据验证结果不同，将不同的资源文件返回给浏览器
        System.out.println(result);
        if(result==1){
            response.sendRedirect("/myWeb/index.html");
        }
        else{
            response.sendRedirect("/myWeb/login_err.html");
        }
    }
}
