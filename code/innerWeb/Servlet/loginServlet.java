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

@WebServlet(urlPatterns = "/login")
public class loginServlet extends HttpServlet {

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

        resp.setContentType("text/html;charset=utf8");
//        得到输入的数据
        String username = req.getParameter("name");
        String password = req.getParameter("password");
        String vcode = req.getParameter("vcode");

//        获取会话域验证码并验证
        HttpSession session = req.getSession();
        String vcodeFS = (String) session.getAttribute("vcode");
        if (vcodeFS==null || !vcodeFS.equalsIgnoreCase(vcode)) {
            resp.getWriter().write("<script>alert('验证码错误');history.back();</script>");
            return;
        }

        //调用服务层方法进一步验证
        User user = userSer.login(username, password);
        if (user == null) {
            System.out.println("控制台打印信息：登陆失败。");
//            请求跳转
//            todo 三秒后返回登陆界面
            req.getRequestDispatcher("/failture.html").forward(req, resp);
        } else {
            System.out.println("控制台打印信息：登陆成功。");
            req.getSession().setAttribute("userinfo",true);
            req.getRequestDispatcher("/resources.demo").forward(req, resp);
        }

    }
}
