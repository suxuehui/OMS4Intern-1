<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/12
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" isELIgnored="false"%>
<script type="text/javascript" src="../../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../../js/pageList.js" ></script>

<html>
<head>
    <title>refoundOrder</title>
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
</head>
<body>
<h1>退款单页面显示</h1>

<div class="orderSearch">
    <ul>
        <li><input type="button" value="查看"  class="btn"></li>
        <li><input type="button" value="取消"  class="btn"></li>
        <li><input type="button" value="处理异常"  class="btn"></li>
    </ul>
    <select name="toseachid" id="selectid">
        <option value="1" selected>退款单号</option>
        <option value="2">退款状态</option>
        <option value="3">退货单号</option>
    </select>
    <input type="text" name="txtvalue" id="txt">
    <input type="button" id="search" onclick="GetnowPage(1)" value="查询" />

</div>
<ul id="ullist"></ul>

<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="table1" >
        <tr>
            <td></td>
            <td>退款单号</td>
            <td>退款金额</td>
            <td>退款状态</td>
            <td>退货单号</td>
            <td>创建时间</td>
            <td>修改时间</td>
            <td>修改人</td>

        </tr>
    </table>
</div>
<div id="divpage"></div>
<%--显示加载的信息，该引用只能放在页面下面--%>
<script type="text/javascript" src="../../js/refoundOrder.js" charset="utf-8"></script>
</body>
</html>
