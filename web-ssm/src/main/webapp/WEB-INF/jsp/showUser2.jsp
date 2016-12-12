<%--
  Created by IntelliJ IDEA.
  User: MAVI003
  Date: 2016/12/6
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
<div>
    <c:if test="${!empty as}">
        <c:forEach var="user" items="${as}">
            姓名：${user.uname} &nbsp;&nbsp;<br>
        </c:forEach>
    </c:if>
    ${i}
    ${session}
</div>
</body>
</html>
