<%--
  Created by IntelliJ IDEA.
  User: 86183
  Date: 2019/6/5
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>修改学生信息</h1>
<form action="mod" method="post">
    <label>学生编号</label><input type="text" name="studentid" value="${single.studentid}">
    <label>学生姓名</label><input type="text" name="studentname" value="${single.studentname}">
    <label>学生成绩</label><input type="text" name="studentresult" value="${single.studentresult}">
    <label>成绩日期</label><input type="date" name="resultdate" value="${single.resultdate}">
    <button>提交</button>
</form>
</body>
</html>
