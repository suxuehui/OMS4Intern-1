<%--
  Created by IntelliJ IDEA.
  User: MAVI003
  Date: 2016/12/27
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Details</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/returnDetail.css">
</head>

<body>
<div class="wrap">
    <div class="header">
        <p class="headerTit">退货单详情</p>
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
                                <td>退货单号:</td>
                                <td>&nbsp;${a.returnedModel.returnedid}</td>
                            </tr>
                            <tr>
                                <td>退货/换货:</td>
                                <td>&nbsp;${a.returnedModel.returnedorchange}</td>
                            </tr>
                            <tr>
                                <td>退货状态:</td>
                                <td>&nbsp;${a.returnedModel.returnedstatus}</td>
                            </tr>
                            <tr>
                                <td>订单号:</td>
                                <td>&nbsp;${a.returnedModel.oid}</td>
                            </tr>
                            <tr>
                                <td>渠道订单号</td>
                                <td>&nbsp;${a.returnedModel.channeloid}</td>
                            </tr>
                            <tr>
                                <td>退货金额:</td>
                                <td>&nbsp;${a.returnedModel.returnedmoney}</td>
                            </tr>
                            <tr>
                                <td>创建时间:</td>
                                <td>&nbsp;${a.returnedModel.createtime}</td>
                            </tr>
                            <tr>
                                <td>修改人:</td>
                                <td>&nbsp;${a.returnedModel.modifytime}</td>
                            </tr>
                            <tr>
                                <td>修改时间:</td>
                                <td>&nbsp;${a.returnedModel.modifyman}</td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="detailsCont fr">
                <div class="contTit">商品信息</div>
                <div class="content extra">
                    <form>
                        <table cellspacing="0" cellpadding="0" border="0">

                            <c:forEach items="${a.returnedSonList}" var="entry">
                                <tr>
                                    <table class="product">

                                        <tr>
                                            <td>商品编码:</td>
                                            <td>&nbsp;<c:out value="${entry.goodsno}"/></td>
                                        </tr>
                                        <tr>
                                            <td>商品名称:</td>
                                            <td>&nbsp;<c:out value="${entry.goodsname}"/></td>
                                        </tr>
                                        <tr>
                                            <td>退货数量:</td>
                                            <td>&nbsp;<c:out value="${entry.goodnum}"/></td>
                                        </tr>
                                        <tr>
                                            <td>退货金额:</td>
                                            <td>&nbsp;<c:out value="${entry.goodsprice}"/></td>
                                        </tr>

                                    </table>
                                </tr>
                            </c:forEach>


                        </table>
                        </tr>
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
