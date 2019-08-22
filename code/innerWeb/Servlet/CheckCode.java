package com.Servlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/checkcode")
public class CheckCode extends HttpServlet {

    private Random random;

    //初始化随机对象
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        random = new Random();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取画布和画笔
        BufferedImage image = new BufferedImage(150, 50, BufferedImage.TYPE_INT_RGB);
        Graphics gra = image.getGraphics();
//        白色填充画布
        gra.fillRect(0, 0, 150, 50);
//        画一个框
        gra.setColor(Color.black);
        gra.drawRect(5, 5, 145, 45);

//        生成随机的干扰线
        for (int i = 0; i < 4; i++) {
//            设置干扰线颜色
            gra.setColor(randCole());
//            得到随机线坐标
            int x1 = random.nextInt(140) + 5;
            int x2 = random.nextInt(140) + 5;
            int y1 = random.nextInt(140) + 5;
            int y2 = random.nextInt(140) + 5;
            gra.drawLine(x1, y1, x2, y2);
        }

//        得到4个字符的验证码
//        设置画笔大小
        gra.setFont(new Font("微软黑体", Font.BOLD, 24));
        String check = "qwertyuiopasdfghjklzxcvbnm1234567890";

//        存储用于验证步骤
        StringBuilder vcode = new StringBuilder();
//        随机生成四个字母
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(check.length());
            char font = check.charAt(a);
            gra.drawString(font + "", (32 * i + 20), (random.nextInt(2) + 26));
            vcode.append(font);
        }
//        存储服务器端vcode到会话域中
        req.getSession().setAttribute("vcode", vcode.toString());


//        将缓存的图片输出到浏览器
        boolean png = ImageIO.write(image, "png", resp.getOutputStream());


    }

    //    生成随机颜色的方法
    private Color randCole() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}
