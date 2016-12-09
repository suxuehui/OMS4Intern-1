<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/8
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>

<html>
<head>
    <title>outboundorder</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/pagelist.js"></script>

    <style type="text/css">
        table {
            border-collapse: collapse;
            border: solid #000 1px;
            width: 100%;
        }
        td{ width: 11%; }
    </style>
</head>
<body>
${list}

<h1>出库单页面显示</h1>
<div id="div"></div>

<div>
    <select name="toseachid" id="selectid">
        <option value="1" selected>订单号</option>
        <option value="2">渠道订单号</option>
        <option value="3">入库单号</option>
        <option value="4">退货单号</option>
    </select>
    <input type="text" name="txtvalue" id="txt">
    <input type="button" id="search" onclick="GetnowPage(1)" value="查询" />

</div>


<div style=" overflow-y:auto; overflow-x:auto; width:1200px; height:400px;">
    <table id="table1" >
        <tr>
            <td>         </td>
            <td>订单号    </td>
            <td>渠道订单号</td>
            <td>退货单号 </td>
            <td>入库单号 </td>
            <td>入库状态 </td>
            <td>同步状态 </td>
            <td>收货仓库 </td>
            <td>创建时间 </td>
            <td>修改时间 </td>
            <td>修改人   </td>

        </tr>

    </table>
</div>
<div id="divpage"></div>
<%--显示加载的信息，该引用只能放在页面下面--%>
 <script src="${pageContext.request.contextPath}/js/inboundpagelist.js"></script>

</body>
</html>

