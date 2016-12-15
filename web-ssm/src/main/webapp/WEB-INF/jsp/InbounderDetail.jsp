<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/9
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Details.css">
</head>

<body>
<div class="wrap" style="overflow:scroll">
    <div class="header">
        <p class="headerTit">入库单详情</p>
    </div>
    <div class="details">
        <div class="detailsBox">
            <!--基本信息-->
            <div class="detailsCont fl">
                <div class="contTit">基本信息</div>
                <div class="content revise">
                    <form>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <tr>
                                <td>订单号:</td>
                                <td>&nbsp;${obol.oid}</td>
                            </tr>
                            <tr>
                                <td>渠道订单号:</td>
                                <td>&nbsp;${obol.channeloid}</td>
                            </tr>
                            <tr>
                                <td>退货单号:</td>
                                <td>&nbsp;${obol.returnedid}</td>
                            </tr>
                            <tr>
                                <td>入库单号:</td>
                                <td>&nbsp;${obol.inboundid}</td>
                            </tr>
                            <tr>
                                <td>入库单状态:</td>
                                <td>&nbsp;${obol.inboundstate}</td>
                            </tr>
                            <tr>
                                <td>同步状态:</td>
                                <td>&nbsp;${obol.synchrostate}</td>
                            </tr>
                            <tr>
                                <td>收货仓库:</td>
                                <td>&nbsp;${obol.warehouse}</td>
                            </tr>
                            <tr>
                                <td>创建时间:</td>
                                <td>&nbsp;${obol.createdtime}</td>
                            </tr>
                            <tr>
                                <td>修改人:</td>
                                <td>&nbsp;${obol.modifyman}</td>
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
                <div class="contTit">商品信息</div>
                <div class="content extra">
                    <form>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <c:forEach items="${gods}" var="gd">
                                <tr>
                                    <td>
                                    <table class="product">
                                        <tr>
                                            <td>商品编码:</td>
                                            <td>&nbsp;${gd.goodsno}</td>
                                        </tr>
                                        <tr>
                                            <td>商品名称:</td>
                                            <td>&nbsp;${gd.goodsname}</td>
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