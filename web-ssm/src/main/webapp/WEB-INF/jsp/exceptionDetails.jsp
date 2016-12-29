<%--
  Created by IntelliJ IDEA.
  User: ZHOU169
  Date: 2016/12/15
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="utf-8">
    <title>exceptionDetails</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/exceptionDetails.css">
</head>

<body>
<div class="wrap">
    <div class="header">
        <p class="headerTit">异常订单详情</p>
    </div>
    <div class="details">
        <div class="detailsBox">
            <!--基本信息-->
            <div class="detailsCont">
                <div class="contTit">基本信息</div>
                <div class="content revise">
                    <form>
                        <table cellspacing="0" cellpadding="0" border="0">
                            <tr>
                                <td>订单号:</td>
                                <td>&nbsp;${exceptionList.oid}</td>
                            </tr>
                            <tr>
                                <td>渠道订单号:</td>
                                <td>&nbsp;${exceptionList.channeloid}</td>
                            </tr>
                            <tr>
                                <td>订单状态:</td>
                                <td>&nbsp;${exceptionList.orderstatus}</td>
                            </tr>
                            <tr>
                                <td>订单来源:</td>
                                <td>&nbsp;${exceptionList.orderfrom}</td>
                            </tr>
                            <tr>
                                <td>异常类型:</td>
                                <td>&nbsp;${exceptionList.exceptiontype}</td>
                            </tr>
                            <tr>
                                <td>异常原因:</td>
                                <td>&nbsp;${exceptionList.expceptioncause}</td>
                            </tr>
                            <tr>
                                <td>异常状态:</td>
                                <td>&nbsp;${exceptionList.exceptionstatus}</td>
                            </tr>
                            <tr>
                                <td>创建时间:</td>
                                <td>&nbsp;${exceptionList.createtime}</td>
                            </tr>
                            <tr>
                                <td>修改时间:</td>
                                <td>&nbsp;${exceptionList.modifytime}</td>
                            </tr>
                            <tr>
                                <td>修改人:</td>
                                <td>&nbsp;${exceptionList.modifyman}</td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
            <div class="detailsCont">
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
                                                <td>&nbsp;${gd.goodsprice}</td>
                                            </tr>
                                            <tr>
                                                <td>商品个数:</td>
                                                <td>&nbsp;${gd.goodsnum}</td>
                                            </tr>
                                            <tr>
                                                <td>商品总价:</td>
                                                <td>&nbsp;${gd.goodsnum*gd.goodsprice}</td>
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

