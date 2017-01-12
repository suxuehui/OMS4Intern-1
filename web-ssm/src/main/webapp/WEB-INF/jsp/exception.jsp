<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/29
  Time: 17:32
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
            <div class="orderTit">异常订单列表</div>
            <div class="orderSearch">
                <ul>
                    <li><input type="button" value="处理异常"  class="btn" disabled="disabled" id="handle_inbtn" onclick="handleException()"></li>
                    <li><input type="button" value="取消"  class="btn" disabled="disabled" id="proDel_inbtn" onclick="exception_del()"></li>
                    <%--查看按钮  dfdgfg--%>
                    <li><input type="button" value="查看订单"  class="btn beta" disabled="disabled" id="exception_inbtn" onclick="exception_details()"></li>
                </ul>
                <input type="button" value="查询" class="submitBtn fr" id="search" onclick="return excGetnowPage(1)">
                <input type="text" class="textArea fr" name="txtvalue" id="exception_text">
                <select class="selectArea fr" name="exceptionToseachid" id="exceptionSelectid">
                    <option value="0" class="select_hide">请选择查询条件</option>
                    <option value="1">订单号</option>
                    <option value="2">渠道订单号</option>
                    <option value="3">异常类型</option>
                </select>
            </div>
        </div>
        <div class="orderMainCont revise">
            <div class="table revise" id="#exetable">
                <table cellspacing="0" cellpadding="0" class="w1550" id="exetable">
                    <tr class="tableTit" height="20">
                        <th class="w50">序号</th>
                        <th class="w50">批量</th>
                        <th class="w200">订单号</th>
                        <th class="w200">渠道订单号</th>
                        <th class="w100">订单状态</th>
                        <th class="w100">订单来源</th>
                        <th class="w100">异常类型</th>
                        <th class="w350">异常原因</th>
                        <th class="w100">异常状态</th>
                        <th class="w100">创建时间</th>
                    </tr>

                </table>

            </div>
            <div id="exceptiondivpage" class="fr"></div>
            <%--显示加载的信息，该引用只能放在页面下面--%>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/exception.js" charset="utf-8"></script>
        </div>
    </div>
    <div class="orderDetails">
        <div class="tableDetails">
            <form>
                <table cellspacing="0" cellpadding="0" id="exception_table2">
                    <tr class="tableTit">
                        <th class="w50">批量</th>
                        <th class="w350">商品编码</th>
                        <th>商品名称</th>
                        <th>商品单价</th>
                        <th>商品个数</th>
                        <th>商品总价</th>
                    </tr>
                </table>
            </form>
        </div>
        <div id="exceptionsonpl" class="fr"></div>
    </div>
</div>

</body>
</html>
