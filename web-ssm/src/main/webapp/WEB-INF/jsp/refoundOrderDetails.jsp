<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/15
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>refoundOrderDetails</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/refoundDetails.css">
</head>

<body>
<div class="wrap">
    <div class="header">
        <p class="headerTit">退款单详情</p>
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
                                <td>退款单号:</td>
                                <td>&nbsp;${refoundOrderList.drawbackid}</td>
                            </tr>
                            <tr>
                                <td>退款金额:</td>
                                <td>&nbsp;${refoundOrderList.drawbackmoney}</td>
                            </tr>
                            <tr>
                                <td>退款状态:</td>
                                <td>&nbsp;${refoundOrderList.drawbackstatus}</td>
                            </tr>
                            <tr>
                                <td>退款货号:</td>
                                <td>&nbsp;${refoundOrderList.returnedid}</td>
                            </tr>
                            <tr>
                                <td>创建时间:</td>
                                <td>&nbsp;${refoundOrderList.createtime}</td>
                            </tr>
                            <tr>
                                <td>修改时间:</td>
                                <td>&nbsp;${refoundOrderList.modifytime}</td>
                            </tr>
                            <tr>
                                <td>修改人:</td>
                                <td>&nbsp;${refoundOrderList.modifyman}</td>
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
                                                <td>商品单价:</td>
                                                <td>&nbsp;${gd.divideorderfee}</td>
                                            </tr>
                                            <tr>
                                                <td>商品个数:</td>
                                                <td>&nbsp;${gd.goodsnum}</td>
                                            </tr>
                                            <tr>
                                                <td>商品总价:</td>
                                                <td>&nbsp;${gd.goodsnum*gd.divideorderfee}</td>
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

