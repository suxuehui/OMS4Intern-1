<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/29
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>inboundorder</title>
</head>
<body>
<div class="relatedOrder hide">
    <div class="orderManage">
        <div class="orderTag">
            <div class="orderTit">入库单列表</div>
            <div class="orderSearch">
                <ul>

                    <li><input type="button" value="查看订单" id="inbtn" name="inorderbtn" onclick="toinOrderdetail(this.name)" class="btn" disabled="disabled"></li>
                </ul>

                <input type="button" id="insearch" onclick="inGNPage(1)" value="查询" class="submitBtn fr">
                <input type="text" name="intxtvalue" id="intxt" class="textArea fr">
                <select class="selectArea fr" name="inselect" id="inselectid">
                    <option value="0" selected>请选择查询条件</option>
                    <option value="1" >订单号</option>
                    <option value="2">渠道订单号</option>
                    <option value="3">入库单号</option>
                    <option value="4">退货单号</option>
                </select>
            </div>
        </div>
        <div class="orderMainCont revise">
            <div class="table revise">
                <table id="inboundertab" cellspacing="0" cellpadding="0" class="w1950">
                    <tr class="tableTit" height="20">
                        <%--<th class="w50">序号</th>--%>
                        <th class="w50">批量</th>
                        <th class="w200">订单号</th>
                        <th class="w200">渠道订单号</th>
                        <th class="w200">退货单号</th>
                        <th class="w200">入库单号</th>
                        <th class="w100">入库单状态</th>
                        <th class="w100">同步状态</th>
                        <th class="w350">收货仓库</th>
                        <th class="w200">创建时间</th>
                        <th class="w200">修改时间</th>
                        <th class="w100">修改人</th>
                    </tr>
                </table>
               </div>
            <div class="fenpage"  id="indivpage"></div>
            <%--显示加载的信息，该引用只能放在页面下面--%>
            <script src="${pageContext.request.contextPath}/js/inboundpagelist.js"></script>
        </div>
    </div>
    <%--子页面 商品页--%>
    <div class="orderDetails">
        <div class="tableDetails">
            <table id="inboundertabson" cellspacing="0" cellpadding="0">
                <tr class="tableTit">
                    <%--<th class="w50">批量</th>--%>
                    <th class="w350">商品编码</th>
                    <th>商品名称</th>
                    <th>商品数量</th>
                    <th>入库数量</th>
                </tr>
            </table>
        </div>
        <div class="fenpage"  id="insonpl"> </div>
    </div>
</div>
</body>
</html>
