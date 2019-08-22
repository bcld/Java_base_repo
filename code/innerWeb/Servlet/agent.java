package com.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = "/agent_hide")
public class agent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        获取传入的文件夹名，并取得地址参数，存储更新fpath
        String dirName = req.getParameter("dirName");
        Map<String, String> dirs = (Map<String, String>) req.getSession().getAttribute("dirs");
        dirName = dirs.get(dirName);
        req.getSession().setAttribute("fpath", dirName);

        System.out.println("agent内部打印：" + dirName);

//        进行隐藏地址跳转
        req.getRequestDispatcher("/resources.demo").forward(req, resp);
        System.out.println("后台打印信息：跳转成功");
    }
}
