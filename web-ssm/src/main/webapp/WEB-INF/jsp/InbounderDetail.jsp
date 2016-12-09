<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/9
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>入库单详情页</h1>
oid=${oid}
<br>
rg=${rog}
<br>
gods=${gods}
<br>
<table>
    <tr>
        <td> 商品编码</td>
        <td>商品名称</td>
    </tr>

    <c:forEach items="${gods}" var="gd">
    <tr>
        <td> ${gd.goodsno} </td>
        <td>${gd.goodsname}</td>
    </tr>
    </c:forEach>
</body>
</html>
