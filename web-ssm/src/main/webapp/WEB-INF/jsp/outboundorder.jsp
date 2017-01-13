<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/29
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>outbounder</title>
</head>
<body>

<div class="relatedOrder hide">
    <div class="orderManage">
        <div class="orderTag">
            <div class="orderTit">出库单列表</div>
            <div class="orderSearch">
                <ul>
                    <li><input type="button" value="查看订单" id="outbtn" name="outorderbtn" onclick="tooutOrderdetail(this.name)" class="btn" disabled="disabled"></li>
                </ul>
                <input type="button" id="outsearch" onclick="outGNPage(1)"  value="查询" class="submitBtn fr">
                <input type="text" class="textArea fr" name="outtxtvalue" id="outtxt" onkeydown="if (event.keyCode == 13){outGNPage(1)}">
                <select class="selectArea fr" name="outselect" id="outselectid">
                    <option value="0" class="select_hide">请选择查询条件</option>
                    <option value="1" >订单号</option>
                    <option value="2">渠道订单号</option>
                    <option value="3">出库单号</option>
                </select>
            </div>
        </div>
        <div class="orderMainCont revise">
            <div class="table revise">
                <table id="outboundertab" cellspacing="0" cellpadding="0" class="w2250">
                    <tr class="tableTit" height="20">
                        <th class="w50">序号</th>
                        <th class="w50">批量</th>
                        <th class="w200">订单号</th>
                        <th class="w200">渠道订单号</th>
                        <th class="w100">订单状态</th>
                        <th class="w200">仓库出库单号 </th>
                        <th class="w200">出库单号</th>
                        <th class="w100">出库单状态</th>
                        <th class="w100">同步状态</th>
                        <th class="w100">收货人</th>
                        <th class="w200">快递公司</th>
                        <th class="w200">快递单号</th>
                        <th class="w350">收货地址</th>
                        <th class="w200">创建时间</th>
                        <th class="w200">修改时间</th>
                        <th class="w100">修改人</th>
                    </tr>
                    </tbody>
                </table>
               </div>

            <%--分页显示加载的信息，该引用只能放在页面下面--%>
            <script src="${pageContext.request.contextPath}/js/outboundpagelist.js"></script>
            <div class="fenpage" id="outdivpage"></div>
        </div>
    </div>
    <div class="orderDetails">
        <div class="tableDetails">
            <table  id="outboundertabson"  cellspacing="0" cellpadding="0">
                <tr class="tableTit">
                    <th class="w50">批量</th>
                    <th>出库单号</th>
                    <th>仓库出库单号</th>
                    <th>商品编码</th>
                    <th>商品名称</th>
                    <th>商品数量</th>
                    <th>出库数量</th>
                </tr>
            </table>
        </div>
        <%--子页面的分页--%>
        <div class="fenpage" id="outsonpl" ></div>
    </div>
</div>

</body>
</html>
