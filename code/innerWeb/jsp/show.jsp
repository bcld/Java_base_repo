<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SHOW RESOURCES</title>
</head>
<body>
<table align="center" cellpadding="auto" cellspacing="auto">
    <tr>
        <td><font color="blue" style="border: 5px">资源</font></td>
    </tr>
    <c:forEach items="${sessionScope.dirs}" var="entry" varStatus="status">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/agent_hide?dirName=${entry.key}"> ${entry.key}</a>
            </td>
        </tr>
    </c:forEach>
    <c:forEach items="${sessionScope.filelsit}" var="entry" varStatus="status">
        <tr>
            <td>
                <a href="${sessionScope.passPath}/${entry.key}"> ${entry.key}</a>
            </td>
        </tr>
    </c:forEach>



</table>
</body>
</html>

