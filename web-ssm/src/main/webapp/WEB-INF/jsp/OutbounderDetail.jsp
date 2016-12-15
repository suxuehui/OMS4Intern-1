<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/8
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Details.css">
</head>

<body>
<div class="wrap">
    <div class="header">
        <p class="headerTit">出库订单详情</p>
    </div>
    <div class="details">
        <div class="detailsBox">
            <!--基本信息-->
            <div class="detailsCont fl">
                <div class="contTit">基本信息</div>
                <div class="content revise">
                    <form>
                        <table  cellspacing="0" cellpadding="0" border="0">
                            <tr>
                                <td>订单号:</td>
                                <td>&nbsp;${obol.oid}</td>
                            </tr>
                            <tr>
                                <td>渠道订单号:</td>
                                <td>&nbsp;${obol.channeloid}</td>
                            </tr>
                            <tr>
                                <td>订单状态:</td>
                                <td>&nbsp;${obol.orderstatus}</td>
                            </tr>
                            <tr>
                                <td>出库单号:</td>
                                <td>&nbsp;${obol.outboundid}</td>
                            </tr>
                            <tr>
                                <td>同步状态:</td>
                                <td>&nbsp;${obol.synchrostate}</td>
                            </tr>
                            <tr>
                                <td>收货人:</td>
                                <td>&nbsp;${obol.receivername}</td>
                            </tr>
                            <tr>
                                <td>快递公司:</td>
                                <td>&nbsp;${obol.expresscompany}</td>
                            </tr>
                            <tr>
                                <td>快递单号:</td>
                                <td>&nbsp;${obol.expressid}</td>
                            </tr>
                            <tr>
                                <td>出库单状态:</td>
                                <td>&nbsp;${obol.outboundstate}</td>
                            </tr>
                            <tr>
                                <td>收货地址:</td>
                                <td>&nbsp;${obol.receiveraddress}</td>
                            </tr>
                            <tr>
                                <td>创建时间:</td>
                                <td>&nbsp;${obol.createdtime}</td>
                            </tr>
                            <tr>
                                <td>修改人:</td>
                                <td>&nbsp;${obol.modifyman }</td>
                            </tr>
                            <tr>
                                <td>修改时间:</td>
                                <td>&nbsp;${obol.modifytime}</td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="detailsCont fl">
                <div class="contTit">出库信息</div>
                <div class="content extra">
                    <form>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <c:forEach items="${gods}" var="gd">
                            <tr>
                               <td>
                                <table class="product">
                                    <tr>
                                        <td>出库单号:</td>
                                        <td>&nbsp;${obol.outboundid}</td>
                                    </tr>
                                    <tr>
                                        <td>仓库出库单号:</td>
                                        <td>&nbsp;${obol.warehouseobid}</td>
                                    </tr>
                                    <tr>
                                        <td>商品编码:</td>
                                        <td>&nbsp;${gd.goodsno}  </td>
                                    </tr>
                                    <tr>
                                        <td>商品名称:</td>
                                        <td>&nbsp;${gd.goodsname}</td>
                                    </tr>
                                    <tr>
                                        <td>商品单价:</td>
                                        <td>&nbsp;${gd.goodsprice}</td>
                                    </tr>
                                    <tr>
                                        <td>商品数量:</td>
                                        <td>&nbsp;${gd.goodNum}</td>
                                    </tr>
                                    <tr>
                                        <td>出库数量:</td>
                                        <td>&nbsp;${gd.goodNum}</td>
                                    </tr>
                                </table>
                               </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="detailsMenu"><p class="ml15">详情信息</p></div>
    </div>
</div>

</body>
</html>