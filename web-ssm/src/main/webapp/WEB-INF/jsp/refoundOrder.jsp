<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/29
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="relatedOrder hide">
    <div class="orderManage">
        <div class="orderTag">
            <div class="orderTit">退款单列表</div>
            <div class="orderSearch">
                <ul>
                    <li><input type="button" value="退款"  class="btn" onclick="drawback()"></li>
                    <%--查看按钮  dfdgfg--%>
                    <li><input type="button" value="查看订单"  class="btn beta" disabled="disabled" id="refoundOrder_inbtn" onclick="refoundOrder_details()"></li>
                </ul>
                <input type="button" value="查询" class="submitBtn fr" id="refoundOrderSearch" onclick="refoundPage(1)">
                <input type="text" class="textArea fr" name="refoundTxt" id="refoundOrderTxt">
                <select class="selectArea fr" name="refoundToseachid" id="refoundOrderSelectid">
                    <option value="1" selected>退款单号</option>
                    <option value="2">退款状态</option>
                    <option value="3">退货单号</option>
                </select>
            </div>
        </div>
        <div class="orderMainCont revise">
            <div class="table revise">
                <table cellspacing="0" cellpadding="0" class="w1200" id="refoundOrdertable1">
                    <tr class="tableTit">
                        <th class="w50">序号</th>
                        <th class="w50">批量</th>
                        <th class="w200">退款单号</th>
                        <th class="w100">退款金额</th>
                        <th class="w100">退款状态</th>
                        <th class="w200">退货单号</th>
                        <th class="w200">创建时间</th>
                        <th class="w200">修改时间</th>
                        <th class="w100">修改人</th>
                    </tr>
                </table>
            </div>
            <div id="relatedOrderDivpage" class="fr"></div>
            <%--显示加载的信息，该引用只能放在页面下面--%>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/refoundOrder.js" charset="utf-8"></script>
        </div>
    </div>
    <div class="orderDetails">
        <div class="tableDetails">
            <table cellspacing="0" cellpadding="0" id="refoundOrdertable2">
                <tr class="tableTit">
                    <th class="w50">批量</th>
                    <th class="w350">商品编码</th>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>商品个数</th>
                    <th>商品总价</th>
                </tr>
            </table>
        </div>
        <div id="refoundOrderSonpl" class="fr"></div>
    </div>
</div>
</body>
</html>
