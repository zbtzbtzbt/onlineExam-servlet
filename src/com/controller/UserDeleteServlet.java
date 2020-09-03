package com.controller;

import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        UserDao dao = new UserDao();
        PrintWriter out = null;
        //1.调用请求对象 读取请求参数
        userId = request.getParameter("userId");
        //2.调用DAO将用户编号填充到delete命令中并发送到数据库服务器
        int result = dao.delete(userId);
        //3.调用响应对象将处理的结果以二进制形式写入到响应体中 交给浏览器
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>删除用户编号为"+userId+"的信息成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>删除失败</font>");
        }
    }
}
