<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${not empty sessionScope.userinfo}">
        <% response.sendRedirect(request.getContextPath()+"/agent_hide");%>
    </c:if>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登陆页面</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="max-width:400px">
    <h3 style="text-align: center">用户登录</h3>
    <form action="login" method="post" id="form">
        <div class="form-group">
            <label for="name">用户名：</label>
            <input type="text" name="name" class="form-control" id="name" placeholder="请输入用户名">
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>
        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="vcode" class="form-control" id="vcode" placeholder="验证码" style="width: 70px"
                   maxlength="4"/>&nbsp;
            <img src="checkcode" onclick="f1(this)" style="width: 90px; height: 30px; cursor: pointer;"
                 title="看不清，点击刷新">
            <script type="text/javascript">
                function f1(imgobj) {
                    imgobj.src = "checkcode?" + new Date().getTime();
                }
            </script>
        </div>
        <div style="text-align: center; padding-top: 20px;">
            <input type="submit" value=" 登 录 " class="btn btn-primary"/>
            <!--注册功能-->
            <input onclick="fsign()" type="button" value=" 注 册 " class="btn btn-primary"/>
            <script>
                function fsign() {
                    //此处地址应该自动获取
                    var byId = window.document.getElementById("form");
                    byId.action = "signUp";
                    byId.submit();
                }
            </script>
        </div>
    </form>
</div>
</body>
</html>
