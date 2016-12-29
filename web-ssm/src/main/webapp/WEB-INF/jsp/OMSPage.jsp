<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/13
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OMS订单管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/css.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagelink.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/OMSPage.js"></script>
    <script src="${pageContext.request.contextPath}/js/userlistAndGoodslist.js"></script>
    <script src="${pageContext.request.contextPath}/js/OrderPage.js"></script>
    <script src="${pageContext.request.contextPath}/js/orderpagelist.js"></script>
    <script src="${pageContext.request.contextPath}/js/innavpagelist.js"></script>
    <script src="${pageContext.request.contextPath}/js/outnavpagelist.js"></script>
    <script src="${pageContext.request.contextPath}/js/outpagelistson.js"></script>
    <script src="${pageContext.request.contextPath}/js/inpagelistson.js"></script>
    <script src="${pageContext.request.contextPath}/js/exceptionpagelist.js"></script>
    <script src="${pageContext.request.contextPath}/js/exceptionpagelistson.js"></script>
    <script src="${pageContext.request.contextPath}/js/deleteExceptionOrder.js"></script>
    <script src="${pageContext.request.contextPath}/js/refoundpagelist.js"></script>
    <script src="${pageContext.request.contextPath}/js/refoundpagelistson.js"></script>

</head>

<body>
<div class="wrap">
    <div class="header clearFix">
        <div class="menu fl">
            <div class="menuTit">OMS</div>
            <ul>
                <c:if test="${urole == 1}">
                    <li class="on">用户</li>
                    <li>商品</li>
                    <li>仓库</li>
                    <li>订单</li>
                </c:if>

                <c:if test="${urole == 2}">
                    <li></li>
                    <li class="on">商品</li>
                    <li>仓库</li>
                    <li>订单</li>
                </c:if>
            </ul>
        </div>
        <div class="logOut fr"><a href="login">【注销】</a></div>
    </div><!--header结束-->
    <div class="content">

        <c:if test="${urole == 1}">
            <!--用户模块-->
            <div class="order">
                <div class="orderList fl">
                    <ul>
                        <li>
                            <ul class="orderListCent">
                                <li class="listTit"><p class="listContTit">用户管理</p></li>
                                <li class="on"><a>用户列表</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!--orderList结束-->

                <div class="orderMain fl">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">用户列表</div>
                            <div class="customSearch">
                                <ul>
                                    <li><input type="button" value="新增用户" class="btn" id="adduserbutindex"></li>
                                    <li><input type="button" value="修改用户" class="btn" id="updateUserBut"
                                               disabled="disabled"></li>
 <span id="updateusernamehidden" hidden></span>
                                    <span id="updateupasshidden" hidden></span>
                                    <li><input type="button" value="删除用户" class="btn" id="deleteUser"></li>
                                </ul>
                                <form>
                                    <input type="button" value="查询" class="submitBtn fr" id="userselectbutton">
                                    <input type="text" value="名称" class="textArea fr" id="userselectvalue"
                                           onFocus="if(value==defaultValue){value='';}">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="orderDetails">
                        <div class="tableDetails">
                            <form>
                                <table cellspacing="0" cellpadding="0">
                                    <tr class="tableTit">
                                        <th class="w50">序号</th>
                                        <th class="w50">批量</th>
                                        <th>用户名</th>
                                        <th>密码</th>
                                        <th>用户角色</th>
                                    </tr>
                                    <tbody id="usertbody">
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div class="page"><span id="userPageNow" hidden>1</span> <span id="totalUserPage"
                                                                                       hidden>0</span><a
                                id="endUserPage">尾页</a><a id="nextUserPage">下一页</a><a id="preUserPage">上一页</a><a
                                id="firstUserPage">首页</a></div>
                    </div>
                </div><!--orderMain结束-->
            </div>
            <!--order结束-->
            <!--用户模块-->
        </c:if>

        <c:if test="${urole == 2}">
            <!--用户模块-->
            <div class="order">
            </div>
            <!--order结束-->
            <!--用户模块-->
        </c:if>

        <!--商品模块-->
        <c:if test="${urole == 2}">
        <div>
            </c:if>
            <c:if test="${urole == 1}">
            <div class="order hide">
                </c:if>
                <div class="orderList fl">
                    <ul>
                        <li>
                            <ul class="orderListCent">
                                <li class="listTit"><p class="listContTit">商品管理</p></li>
                                <li class="on"><a>商品列表</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!--orderList结束-->
                <div class="orderMain fl">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">商品列表</div>
                            <div class="productSearch">
                                <form>
                                    <input type="button" value="查询" class="submitBtn fr" id="selectgoodsbut">
                                    <input type="text" class="textArea fr" id="goodsvaluetxt">
                                      <select class="selectArea fr revise" id="selectGoodssle">
                                        <option id="selectGoodsByNameop">按名称查询</option>
                                        <option id="selectGoodsByIdop">按商品编码查询</option>

                                    </select>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="orderDetails">
                        <div class="tableDetails">
                            <form>
                                <table cellspacing="0" cellpadding="0">
                                    <tr class="tableTit">
                                        <th class="w50">序号</th>
                                        <th class="w50">批量</th>
                                        <th>商品编码</th>
                                        <th>商品名称</th>
                                        <th>可用库存</th>
                                        <th>锁定库存</th>
                                        <th>总库存</th>
                                        <th>价格</th>
                                    </tr>
                                    <tbody id="goodsbody">

                                    </tbody>

                                </table>
                            </form>
                        </div>
                        <div class="page"><span id="goodsPageNow" hidden>1</span> <span id="totalGoodPage"
                                                                                        hidden>0</span><a
                                id="endGoodsPage">尾页</a><a id="nextGoodsPage">下一页</a><a id="preGoodsPage">上一页</a><a
                                id="firstGoodsPage">首页</a></div>
                    </div>
                </div>
            <!--orderMain结束-->
        </div>
        <!--order结束-->
        <!--商品模块-->
        <!--仓库模块-->
        <div class="order hide">
            <div class="orderList fl">
                <ul>
                    <li>
                        <ul class="orderListCent">
                            <li class="listTit"><p class="listContTit">仓库管理</p></li>
                            <li><a id="">仓库列表</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="orderMain fl">
                <div class="orderManage">
                    <div class="orderTag">
                        <div class="orderTit">仓库列表</div>
                        <div class="storeSearch">
                            <ul>
                                <li><input type="button" value="新增"  id="wareadd" class="btn" onclick="addware(this)" ></li>
                                <li><input type="button" value="编辑"   id="wareupdate" onclick="warehouseupdate(this)" class="btn"></li>
                                <li><input type="button" value="删除"  id="waredelete" onclick="deleteware()" class="btn"></li>
                            </ul>
                            <input type="button" value="查询" onclick="wareGetnowPage(1)"  class="submitBtn fr">
                            <input type="text"  id="whtxt" value="" class="textArea fr" >
                            <select id="whselectid" class="selectArea fr">
                                <option value="1" selected="selected">仓库编号</option>
                                <option value="2" >名称</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="orderDetails">
                    <div class="tableDetails">
                        <table id="warehousetab" cellspacing="0" cellpadding="0">
                            <tr class="tableTit">
                                <th class="w50">序号</th>
                                <th class="w50">批量</th>
                                <th>仓库编号</th>
                                <th>名称</th>
                            </tr>
                        </table>
                        <div id="whinfdiv" style="margin: auto;font-size: 40px;color: cornflowerblue">该仓库信息不存在</div>
                    </div>
                    <%--分页列出仓库列表只能放在后面--%>
                    <script src="${pageContext.request.contextPath}/js/warehouselist.js"></script>
                    <div class="fenpage"  id="whdivpage"></div>
                </div>
            </div>
        </div>
        <!--订单模块-->
        <div class="order hide">
            <div class="orderList fl">
                <ul class="orderListCent">
                    <li class="listTit"><p class="listContTit">订单管理</p></li>
                    <li class="on"><a id="0">订单列表</a></li>
                    <li><a id="1">异常订单列表</a></li>
                    <li class="listTit"><p class="listContTit">出入库单管理</p></li>
                    <li><a id="2">出库单列表</a></li>
                    <li><a id="3">入库单列表</a></li>
                    <li class="listTit"><p class="listContTit">退货单管理</p></li>
                    <li><a id="4">退货单列表</a></li>
                    <li><a id="5">退款单列表</a></li>
                </ul>
            </div><!--orderList结束-->
            <div class="orderMain fl">
                <!--订单列表-->
                <jsp:include page="order.jsp"></jsp:include>
                <!--异常订单列表-->
                <div class="relatedOrder hide">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">异常订单列表</div>
                            <div class="orderSearch">
                                    <ul>
                                        <li><input type="button" value="处理异常"  class="btn" onclick="handleException()"></li>
                                        <li><input type="button" value="取消"  class="btn" id="proDel" onclick="exception_del()"></li>
                                        <%--查看按钮  dfdgfg--%>
                                        <li><input type="button" value="查看订单"  class="btn beta" disabled="disabled" id="exception_inbtn" onclick="exception_details()"></li>
                                    </ul>
                                    <input type="button" value="查询" class="submitBtn fr" id="search" onclick="GetnowPage(1)">
                                    <input type="text" class="textArea fr" name="txtvalue" id="exception_text">
                                    <select class="selectArea fr" name="exceptionToseachid" id="exceptionSelectid">
                                        <option value="1" selected>订单号</option>
                                        <option value="2">渠道订单号</option>
                                        <option value="3">异常状态</option>
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
                                            <th class="w100">修改时间</th>
                                            <th class="w100">修改人</th>
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
                <!--出库单列表-->
                <div class="relatedOrder hide">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">出库单列表</div>
                            <div class="orderSearch">
                                <ul>

                                    <li><input type="button" value="查看订单" id="outbtn" name="outorderbtn" onclick="tooutOrderdetail(this.name)" class="btn" disabled="disabled"></li>
                                </ul>
                                    <input type="button" id="outsearch" onclick="outGetnowPage(1)"  value="查询" class="submitBtn fr">
                                    <input type="text" class="textArea fr" name="outtxtvalue" id="outtxt">
                                    <select class="selectArea fr" name="outselect" id="outselectid">
                                                <option value="1" selected>订单号</option>
                                                <option value="2">渠道订单号</option>
                                                <option value="3">出库单号</option>
                                    </select>
                            </div>
                        </div>
                        <div class="orderMainCont revise">
                            <div class="table revise">
                                    <table id="outboundertab" cellspacing="0" cellpadding="0" class="w2250">
                                        <tr class="tableTit" height="20">
                                            <%--<th class="w50">序号</th>--%>
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
                                <div id="infodiv" style="margin: auto;font-size: 40px;color: cornflowerblue">查询的出库单不存在</div>
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
                                       <%-- <th class="w50">批量</th>--%>
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
                <!--入库单列表-->
                <div class="relatedOrder hide">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">入库单列表</div>
                            <div class="orderSearch">
                                <ul>

                                    <li><input type="button" value="查看订单" id="inbtn" name="inorderbtn" onclick="toinOrderdetail(this.name)" class="btn" disabled="disabled"></li>
                                </ul>

                                    <input type="button" id="insearch" onclick="inGetnowPage(1)" value="查询" class="submitBtn fr">
                                    <input type="text" name="intxtvalue" id="intxt" class="textArea fr">
                                        <select class="selectArea fr" name="inselect" id="inselectid">
                                            <option value="1" selected>订单号</option>
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
                                <div id="inboundinfordiv" style="margin: auto;font-size: 40px;color: cornflowerblue">查询的入库单不存在</div>

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
                <!--退货单列表-->
                <div class="relatedOrder hide">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">退货单列表</div>
                            <div class="orderSearch">
                                <form>
                                      <ul>
                                            <li><input type="button" value="创建退款单" class="btn"
                                                       id="returnedCreaterefoundOder"></li>
                                            <li><input type="button" value="换货发货" class="btn" id="changeOutBound"></li>
                                            <li><input type="button" value="审核" class="btn" id="checkreturnedorder">
                                            </li>
                                            <li><input type="button" value="取消退货" class="btn" id="cancelReturnedOrder">
                                            </li>
                                            <%--查看按钮  dfdgfg--%>
                                            <li><input type="button" value="查看订单" class="btn beta" disabled="disabled"
                                                       id="returnedDetailbut">
                                            </li>
                                        </ul>
                                    <input type="button" value="查询" class="submitBtn fr" id="serarchReturnedOrder">
                                    <input type="text" class="textArea fr" id="returnValue">
                                    <select class="selectArea fr" id="returnedselect">
                                        <option>退货单号</option>
                                        <option>订单号</option>
                                        <option>退货状态</option>
                                        <option>渠道订单号</option>
                                    </select>
                                </form>
                            </div>
                        </div>
                        <div class="orderMainCont revise">
                            <div class="table revise">
                                <form>
                                    <table cellspacing="0" cellpadding="0" class="w1500">
                                        <tr class="tableTit" height="20">
                                            <th class="w50">序号</th>
                                            <th class="w50">批量</th>
                                            <th class="w200">退货单号</th>
                                            <th class="w100">退货/换货</th>
                                            <th class="w100">退货状态</th>
                                            <th class="w200">订单号</th>
                                            <th class="w200">渠道订单号</th>
                                            <th class="w100">退货金额</th>
                                            <th class="w200">创建时间</th>
                                            <th class="w200">修改时间</th>
                                            <th class="w100">修改人</th>
                                        </tr>
                                      <tbody id="returnedBody">


                                            </tbody>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><span id="returnedPageNow" hidden>1</span> <span
                                        id="totalReturnedPage" hidden>0</span><a
                                        id="endReturnedPage">尾页</a><a id="nextReturnedPage">下一页</a><a
                                        id="preReturnedPage">上一页</a><a
                                        id="firstReturnedPage">首页</a></div>
                            </div>
                    </div>
                    <div class="orderDetails">
                        <div class="tableDetails">
                            <form>
                                <table cellspacing="0" cellpadding="0">
                                    <tr class="tableTit">
                                        <th class="w50">批量</th>
                                        <th class="w350">商品编码</th>
                                        <th>商品名称</th>
                                        <th>退货数量</th>
                                        <th>退货金额</th>
                                     </tr>
                                        <tbody id="returnedGoodsBody">


                                        </tbody>
                                        </table>
                            </form>
                        </div>
                        <div class="page"><span id="returnedidongoods" hidden>null</span> <span
                                    id="returnedGoodsPageNow" hidden>1</span> <span
                                    id="totalreturnedGoodsPage" hidden>0</span><a
                                    id="endreturnedGoodsPage">尾页</a><a id="nextreturnedGoodsPage">下一页</a><a
                                    id="prereturnedGoodsPage">上一页</a><a
                                    id="firstreturnedGoodsPage">首页</a></div>
                        </div>
                </div>
                <!--退款单列表-->
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
                                    <input type="button" value="查询" class="submitBtn fr" id="refoundOrderSearch" onclick="refoundGetnowPage(1)">
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
            </div><!--orderMain结束-->
        </div><!--order结束-->
        <!--订单模块-->
    </div><!--content结束-->
    <!--弹窗模块-->
        <div class="popupAll">
            <div class="customShow">
                 <div id="addUserwindows">
                    <div class="popupTop">
                        <span class="iconClose fr"></span>
                        <p class="popupTopTit">新增用户</p>
                    </div>
                    <div class="popupCont">
                        <form>
                            <ul>
                                <li>
                                    <label class="listName mt20">用户名：</label>
                                    <input type="text" class="inputList" id="addUserName">
                                </li>
                                <li>
                                    <label class="listName mt20">密码：</label>
                                    <input type="text" class="inputList" id="addUserPassword">
                                </li>
                                <li>
                                    <input type="button" value="保存" class="save mt50" id="addUser">
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
                <div id="updateUserBound">
                    <div class="popupTop">
                        <span class="iconClose fr"></span>
                        <p class="popupTopTit">修改用户</p>
                    </div>
                    <div class="popupCont">
                        <form>
                            <ul>
                                <li>
                                    <label class="listName mt20">新用户名：</label>
                                    <input type="text" class="inputList" id="updateUserName">
                                </li>
                                <li>
                                    <label class="listName mt20">新密码：</label>
                                    <input type="text" class="inputList" id="updateUserPassword">
                                </li>
                                <li>
                                    <input type="button" value="保存" class="save mt50" id="updateUser">
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        <!--customShow结束-->
        <div class="productShow revise">
            <div class="popupTop">
                <span class="iconClose fr"></span>
                <p class="popupTopTit">添加商品</p>
            </div>
            <div class="popupCont revise">

                    <ul>
                        <li>
                            <label class="listName">商品编号：</label>
                            <input type="text" class="inputList">
                        </li>
                        <li>
                            <label class="listName">商品名称：</label>
                            <input type="text" class="inputList">
                        </li>
                        <li>
                            <label class="listName">总库存：</label>
                            <input type="text" class="inputList">
                        </li>
                        <li>
                            <label class="listName">价格：</label>
                            <input type="text" class="inputList">
                        </li>
                        <li>
                            <input type="button" value="保存" class="save">
                        </li>
                    </ul>
            </div>
        </div>
        <!--productShow结束-->
        <div class="storeShow">
            <div>
                <div class="popupTop">
                    <span class="iconClose fr"></span>
                    <p class="popupTopTit">新增仓库</p>
                </div>
                <div class="popupCont store">
                    <ul>
                        <li>
                            <label class="listName mt20">仓库编号：</label>
                            <input type="text" class="inputList"   id="warenum"   >
                        </li>

                        <li>
                            <label class="listName mt20">仓库名：</label>
                            <input type="text" class="inputList"   id="warename"  >
                        </li>
                        <li class="warn">
                            <span id="whnumdiv" class="lixx" style="font-size:10px;color: red"></span>
                        </li>
                        <li class="warn">
                            <span id="whnamediv" class="lixx" style="font-size:10px;color: red"></span>
                        </li>
                        <li>
                            <input type="button" value="保存" onclick="addwarehouse()" class="save mt50">
                        </li>
                    </ul>
                </div>
            </div>
            <div >
                <div class="popupTop">
                    <span class="iconClose fr"></span>
                    <p class="popupTopTit">编辑仓库</p>
                </div>
                <div class="popupCont store">
                    <ul>
                        <li>
                            <label class="listName mt20">仓库编号：</label>
                            <input type="text" class="inputList"  id="updatewhnum"  >
                        </li>
                        <li>
                            <label class="listName mt20">仓库名：</label>
                            <input type="text" class="inputList"   id="updatewhname"  >
                        </li>
                        <li class="warn">
                            <span id="whupnumdiv" class="lixx" style="font-size:10px;color: red"></span>
                        </li>
                        <li class="warn">
                            <span id="whupnamediv" class="lixx" style="font-size:10px;color: red"></span>
                        </li>
                        <li>
                            <input type="button" id="saveupdatewh"   value="保存" onclick="updateware(this.name)" class="save mt50">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--storeShow结束-->
        <div class="leadingInShow">
            <div class="popupTop">
                <span class="iconClose fr"></span>
                <p class="popupTopTit">导入订单</p>
            </div>
            <div class="popupCont">
                <form id="importForm" action="importOrder" method="post" enctype="multipart/form-data">
                    <ul>
                        <li>
                            <input type="file" style="font-size:20px" name="file" id="orderfile">
                            <input type="button" value="导入" style="font-size:20px" class="leadingBtn" id="importBtn">
                        </li>
                    </ul>
                </form>
            </div>
        </div>
        <!--导入弹框结束-->
    </div>
            <%--阴影层--%>
            <div class="hbg"></div>
    <div>
        <div class="loading"  >
            <div class="mask"></div>
            <div class="loading-icon"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
        </div>
    </div>
    </div>
</body>
</html>

