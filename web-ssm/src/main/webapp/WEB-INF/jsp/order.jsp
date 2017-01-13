<%--
  Created by IntelliJ IDEA.
  User: ZHAN545
  Date: 2016/12/28
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="relatedOrder">
    <div class="orderManage">
        <div class="orderTag">
            <div class="orderTit clearFix leading">
                <p class="fl">订单列表</p>
                <input type="button" value="导入" class="leadingIn fr mt10">
            </div>
            <div class="orderSearch beta">

                <ul>
                    <li><input type="button" value="预检"  class="btn beta" name="orderBtn" id="previewOrderBtn"></li>
                    <li><input type="button" value="路由"  class="btn beta" name="orderBtn" id="routeOrderBtn"></li>
                    <li><input type="button" value="出库"  class="btn beta" name="orderBtn" id="outboundOrderBtn"></li>
                    <li><input type="button" value="取消"  class="btn beta" name="orderBtn" id="cancleOrderBtn"></li>
                    <li><input type="button" value="退货"  class="btn beta" name="orderBtn" id="returnedOrderBtn"></li>
                    <li><input type="button" value="换货"  class="btn beta" name="orderBtn" id="exchangeGoodsBtn"></li>
                    <li><input type="button" value="查看订单" class="btn" name="orderBtn" id="queryOBtn"></li>
                </ul>
                <input type="button" value="查询" class="submitBtn fr" id="queryOrderBtn" onkeypress="if (event.keyCode == 13)">
                <input type="text" class="textArea fr" id="queryOrderCon"   >
                <select class="selectArea fr" id="queryMode">
                    <option value="" class="select_hide">请选择查询条件</option>
                    <option value="oId">订单号</option>
                    <option value="channelOid">渠道订单号</option>
                    <option value="orderStatus">订单状态</option>
                    <option value="payStyle">支付方式</option>
                    <option value="logisticsCompany">物流公司</option>
                    <option value="receiverProvince">省</option>
                    <option value="receiverCity">市</option>
                    <option value="receiverArea">区</option>
                    <option value="receiverMobel">收货人手机号</option>
                </select>

            </div>
        </div>
        <div class="orderMainCont revise">
            <div class="table revise">

                <table cellspacing="0" cellpadding="0" class="w3600">
                    <tr class="tableTit" height="20">
                        <th class="w50">序号</th>
                        <th class="w50">批量</th>
                        <th class="w250">订单号</th>
                        <th class="w250">渠道订单号</th>
                        <th class="w100">订单状态</th>
                        <th class="w100">订单来源</th>
                        <th class="w100">买家ID</th>
                        <th class="w100">下单时间</th>
                        <th class="w100">基本状态</th>
                        <th class="w100">支付状态</th>
                        <th class="w100">支付方式</th>
                        <th class="w100">付款时间</th>
                        <th class="w100">商品总价</th>
                        <th class="w100">订单折扣</th>
                        <th class="w100">订单总价</th>
                        <th class="w100">发送仓库</th>
                        <th class="w100">物流公司</th>
                        <th class="w250">物流单号</th>
                        <th class="w100">配送时间</th>
                        <th class="w200">备注</th>
                        <th class="w100">收货人</th>
                        <th class="w100">手机</th>
                        <th class="w100">电话</th>
                        <th class="w100">省</th>
                        <th class="w100">市</th>
                        <th class="w100">区</th>
                        <th class="w250">详细地址</th>
                        <th class="w100">邮编</th>
                        <th class="w100">修改时间</th>
                        <th class="w100">修改人</th>
                    </tr>
                    <tbody class="tablelsw" id="order">
                    </tbody>
                </table>
            </div>
            <div class="page">
                <a id="lastorder" style="display: none">尾页</a>
                <a id="nextorder" style="display: none">下一页</a>
                <a id="orderPageNo" style="display: none"></a>
                <a id="preorder" style="display: none">上一页</a>
                <a id="orderPageTotal" style="display: none"></a>
                <a id="firstorder" style="display: none">首页</a>
            </div>
        </div>
    </div>
    <div class="orderDetails">
        <div class="tableDetails">

            <table cellspacing="0" cellpadding="0">
                <tr class="tableTit">
                    <th class="w50">批量</th>
                    <th class="w350">商品编码</th>
                    <th>商品名称</th>
                    <th>商品单价</th>
                    <th>商品个数</th>
                    <th>商品总价</th>
                </tr>
                <tr style="display: none"><td id="goodsOid"></td></tr>
                <tbody class="tablelsw" id="ogList">
                </tbody>
            </table>
        </div>
        <div class="page">
            <a id="ogPageNo" style="display: none"></a>
            <a id="ogPageTotal" style="display: none"></a>
            <a id="ogLastpage" style="display: none">尾页</a><a id="ogNextpage" style="display: none">下一页</a>
            <a id="ogPrepage" style="display: none">上一页</a><a id="ogFirstpage" style="display: none">首页</a></div>
    </div>
</div>
