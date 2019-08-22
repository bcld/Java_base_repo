package com.Servlet;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet(urlPatterns = "/resources.demo")
public class resources extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //        文件和文件夹的初始变量
        Map<String, String> filelsit = new HashMap<>();
        Map<String, String> dirs = new HashMap<>();

//        获取请求地址（会话域中）
        String fpath = (String) req.getSession().getAttribute("fpath");

//        若初次访问则初始化并判断资源是否存在
        if (fpath == null)
            fpath = "D:/develop_java/JavaBase/innerWeb/web/film";
        System.out.println("控制台打印信息：进入到资源界面了");

        File files = new File(fpath);
        System.out.println("控制台打印信息：" + files);

//        获得资源及路径的map
        boolean isNull = filesearch(files, filelsit, dirs);
        if (isNull) {
            resp.getWriter().write("没有任何资源");
            return;
        }
        req.getSession().setAttribute("filelsit", filelsit);
        req.getSession().setAttribute("dirs", dirs);

//        通过jsp显示资源
        String[] split = fpath.split("innerWeb(.)web");
        String passPath = "/innerWeb" + split[1];
        req.getSession().setAttribute("passPath", passPath);
        req.getRequestDispatcher("/jsp/show.jsp").forward(req, resp);

    }

    private boolean filesearch(File files, Map<String, String> filelsit, Map<String, String> dirs) {

        int num_file = 0;
//        声明文件名及路径变量
        String name = "";
        String filePath = "";

        //遍历目录，分派文件和文件夹
        File[] fileList = files.listFiles();
        if (fileList == null)
            return true;

//        得到当前目录
        String filesPath = files.getPath();

        for (File elements : fileList) {
            num_file++;
            name = elements.getName();
//            由于使用了固定变量所以才能放前面
            filePath = filesPath + "\\" + name;
            if (elements.isFile()) {
                filelsit.put(name, filePath);
//                后台打印文件名，带格式
                System.out.println("[" + num_file + "]" + " " + name);
            } else if (elements.isDirectory()) {
                dirs.put(name, filePath);
                System.out.println("[" + num_file + "]" + " " + name);
            }
        }
        return false;
    }
}

//找不到资源看看是不是没有写反斜杠，是不是没放在正确的文件夹下
//注意使用<%=??%>的条件，不通过尝试重新build项目，可能编译出现了问题
//使用正则表达式，匹配斜杆时注意window系统的特殊性