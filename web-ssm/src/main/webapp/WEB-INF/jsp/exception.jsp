<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/6
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<script type="text/javascript" src="../../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../../js/pageList.js"></script>
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
    <!--获取多个checkbox选中项-->
    <script>
    function getOid() {
        var rows = document.getElementById("table1").rows;
        var a = document.getElementsByName("ck");
        var table = document.getElementById("table1");
        for(var i=0;i<a.length;i++)
        {
            if(a[i].checked){
                var row = a[i].parentElement.parentElement.rowIndex;
                var oid = a[i].value;
                return oid;
            }
        }
    }
    function initDel() {
        var oid = getOid();
        var parm = {oid: oid};//将参数传到后台
        $.post("/exceptionOrder/cancelException", parm, function (data) {
            $("input[name='ck']:checked").each(function () {
                n = $(this).parents("tr").index();
                $("#table1").find("tr:eq(" + n + ")").remove();
            });
        });
    }
    </script>

</head>
<body>
<h1>异常订单页面显示</h1>

<div class="orderSearch">
        <ul>
            <li><input type="button" value="查看"  class="btn"></li>
            <li><input type="button" value="取消" id="proDel" onclick="initDel()" class="btn"></li>
            <li><input type="button" value="处理异常"  class="btn"></li>
        </ul>
        <select name="toseachid" id="selectid">
            <option value="1" selected>订单号</option>
            <option value="2">渠道订单号</option>
            <option value="3">异常状态</option>
        </select>
        <input type="text" name="txtvalue" id="txt">
        <input type="button" id="search" onclick="GetnowPage(1)" value="查询" />

</div>
<ul id="ullist"></ul>

<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="table1" >
        <tr>
            <td></td>
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
<script type="text/javascript" src="../../js/exception.js" charset="utf-8"></script>
</body>
</html>
