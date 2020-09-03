package com.controller;

import com.dao.UserDao;
import com.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        PrintWriter out = null;
        //1.调用【DAO】将查询命令推送到数据库服务器上，得到所有用户信息【list】
        List<User> userlist = dao.find();

        //2.调用相应对象将用户信息结合<table>标签命令以二进制形式写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.print("<table border='2' align='center'>");
            out.print("<tr>");
                out.print("<td>用户编号</td>");
                out.print("<td>用户姓名</td>");
                out.print("<td>用户密码</td>");
                out.print("<td>用户性别</td>");
                out.print("<td>用户邮箱</td>");
            out.print("</tr>");
            for(User user:userlist){
                out.print("<tr>");
                    out.print("<td>"+user.getUserId()+"</td>");
                    out.print("<td>"+user.getUserName()+"</td>");
                    out.print("<td>******</td>");
                    out.print("<td>"+user.getSex()+"</td>");
                    out.print("<td>"+user.getEmail()+"</td>");
                    out.print("<td><a href='/myWeb/user/delete?userId="+user.getUserId()+"'>删除</a></td>");
                out.print("</tr>");
            }
        out.print("</table>");

    }
}
