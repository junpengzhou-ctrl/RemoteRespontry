<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2019/6/5
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="save.jsp">添加新学生</a>
<table>
    <tr>
        <th>学生编号</th>
        <th>学生姓名</th>
        <th>学生成绩</th>
        <th>成绩日期</th>
    </tr>
    <c:forEach items="${infos}" var="us">
    <tr>
        <td>${us.studentid}</td>
        <td>${us.studentname}</td>
        <td>${us.studentresult}</td>
        <td>${us.resultdate}</td>
        <td>
            <a href="single?studentid=${us.studentid}">修改</a>
            <a href="del?studentid=${us.studentid}">删除</a>
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
