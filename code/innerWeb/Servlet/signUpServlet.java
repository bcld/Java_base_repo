package com.Servlet;

import com.Service.userService;
import com.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns ="/signUp" )
public class signUpServlet extends HttpServlet {
    private userService userSer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userSer = new userService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("name");
        String vcode = req.getParameter("vcode");

        if(username==null || username==""){
            resp.getWriter().write("用户名不能为空,请自行返回");
            return;
        }

//        获取会话域验证码并验证
        HttpSession session = req.getSession();
        String vcodeFS = (String) session.getAttribute("vcode");
        if (vcodeFS==null || !vcodeFS.equalsIgnoreCase(vcode)) {
            resp.getWriter().write("<script>alert('验证码错误');history.back();</script>");
            return;
        }


        User exist = userSer.isExist(username);
        if(exist==null){
            String password = req.getParameter("password");
            System.out.println("INSERT INTO user (username,password) values "+username+"#{"+password+"});");
            userSer.storeUser(username, password);
            resp.getWriter().write("欢迎"+username+",注册成功！");
            req.getSession().setAttribute("userinfo",true);
        }else{
            resp.getWriter().write("用户名已存在,请自行返回");
        }
    }
}
