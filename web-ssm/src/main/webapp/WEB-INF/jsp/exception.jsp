<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pageList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/deleteExceptionOrder.js"></script>
<html>
<head>
    <title>exceptionOrder</title>
</head>
<body>
<h1>异常订单页面显示</h1>

<div class="orderSearch">
        <ul>
            <li><input type="button" value="查看" id="exception_inbtn" onclick="exception_details()" class="btn"></li>
            <li><input type="button" value="取消" id="proDel" onclick="exception_del()" class="btn"></li>
            <li><input type="button" value="处理异常"  class="btn" onclick="handleException()"></li>
        </ul>
        <select name="toseachid" id="selectid">
            <option value="1" selected>订单号</option>
            <option value="2">渠道订单号</option>
            <option value="3">异常状态</option>
        </select>
        <input type="text" name="txtvalue" id="exception_text">
        <input type="button" id="search" onclick="GetnowPage(1)" value="查询" />

</div>
<ul id="ullist"></ul>

<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="exception_table1" >
        <tr>
            <td>序号</td>
            <td>批量</td>
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
    </table>
</div>
<div id="divpage"></div>
<%--显示加载的信息，该引用只能放在页面下面--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/exception.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pagelistson.js"></script>
<h1>商品页数据</h1>
<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="exception_table2" >
        <tr>
            <td>批量</td>
            <td>商品编码</td>
            <td>商品名称</td>
            <td>商品单价</td>
            <td>商品个数</td>
            <td>商品总价</td>
        </tr>
    </table>
</div>
<div id="sonpl"></div>
</body>
</html>
