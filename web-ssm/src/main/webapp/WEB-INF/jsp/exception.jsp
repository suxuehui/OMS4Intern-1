<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/exception.js"></script>
<html>
<head>
    <title>exceptionOrder</title>
    <style type="text/css">
        table {
            border-collapse: collapse;
            border: solid #000 1px;
            width: 100%;
        }
        td{
            width: 11%;
        }
    </style>

    <script>
        function pageNow(){
            pageNow=document.getElementById("pagenow").value;
            document.getElementById("itpn").href="?pageNow="+pageNow;
        }
    </script>

</head>
<body>
<h1>异常订单页面显示</h1>

<div class="orderSearch">
    <form action="queryExceptionOrder" method="post">
        <ul>
            <li><input type="button" value="查看"  class="btn"></li>
            <li><input type="button" value="取消"  class="btn"></li>
            <li><input type="button" value="处理异常"  class="btn"></li>
        </ul>
        <select name="toseachid" id="selectid">
            <option value="1">订单号</option>
            <option value="2">渠道订单号</option>
            <option value="3">异常状态</option>
        </select>
        <input type="text" name="txtvalue" id="txt">
        <input type="button" id="search" onclick="GetnowPage(1)" value="查询" />
    </form>




</div>
<ul id="ullist"></ul>

<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="table1" >
        <tr>
            <td>序号</td>
            <td>订单号</td>
            <td>渠道订单号</td>
            <td>订单状态</td>
            <td>订单来源</td>
            <td>异常类型</td>
            <td>异常原因</td>
            <td>异常状态</td>
            <td>创建时间</td>
            <td>修改时间</td>
            <td>修改人</td>
        </tr>
        <c:forEach items="${list}" var="lt1">
            <tr>
                <td ><input type="checkbox" name="ck"></td>
                <td>${lt1.oid}</td>
                <td>${lt1.channeloid}</td>
                <td>${lt1.orderstatus}</td>
                <td>${lt1.orderfrom}</td>
                <td>${lt1.exceptiontype}</td>
                <td>${lt1.expceptioncause}</td>
                <td>${lt1.exceptionstatus}</td>
                <td>${lt1.createtime}</td>
                <td>${lt1.modifytime}</td>
                <td>${lt1.modifyman}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<div align="center">
    <font size="2">共 ${page.totalPageCount} 页</font>
    <font size="2">第
        <a href="#" id="itpn">
            <input type="text" style="width:15px" id="pagenow" value="${page.pageNow}"  onChange="pageNow()">
            -></a>
        页</font>
    <a href=" ?pageNow=1">首页</a>
    <c:choose>
        <c:when test="${page.pageNow - 1 > 0}">
            <a href=" ?pageNow=${page.pageNow - 1}">上一页</a>
        </c:when>
        <c:when test="${page.pageNow - 1 <= 0}">
            <a href=" ?pageNow=1">上一页</a>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <a href=" ?pageNow=${page.pageNow}">下一页</a>
        </c:when>
        <c:when test="${page.pageNow + 1 < page.totalPageCount}">
            <a href=" ?pageNow=${page.pageNow + 1}">下一页</a>
        </c:when>
        <c:when test="${page.pageNow + 1 >= page.totalPageCount}">
            <a href=" ?pageNow=${page.totalPageCount}">下一页</a>
        </c:when>
    </c:choose>
    <c:choose>
        <c:when test="${page.totalPageCount==0}">
            <a href=" ?pageNow=${page.pageNow}">尾页</a>
        </c:when>
        <c:otherwise>
            <a href=" ?pageNow=${page.totalPageCount}">尾页</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
