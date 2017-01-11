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
<span id="urole" hidden>${urole}</span>
<span id="uname" hidden>${uname}</span>
<span id="name"></span>
<div class="wrap">
    <div class="header clearFix">
        <div class="menu fl">
            <div class="menuTit">OMS</div>
            <ul>
                <c:if test="${urole == 1}">
                    <li class="on" id="userListbut">用户</li>
                    <li id="goodsListbut">商品</li>
                    <li class="wareliclick">仓库</li>
                    <li class="orderClick">订单</li>
                </c:if>

                <c:if test="${urole == 2}">
                    <li></li>
                    <li class="on" id="goodsListbut">商品</li>
                    <li class="wareliclick">仓库</li>
                    <li class="orderClick">订单</li>
                </c:if>
            </ul>
        </div>
        <div class="logOut fr"><a href="${pageContext.request.contextPath }/login/logout">【注销】</a></div>
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
                            <li class="on" id="userListSonbut"><a>用户列表</a></li>
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
                                <li><input type="button" value="删除用户" class="btn" id="deleteUser"
                                           disabled="disabled"></li>
                            </ul>
                            <form>
                                <input type="button" value="查询" class="submitBtn fr" id="userselectbutton">
                                <input type="text" class="textArea fr" id="userselectvalue"
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
                                                                                   hidden>1</span><a
                            id="endUserPage" hidden>尾页</a><a id="nextUserPage" hidden>下一页</a><a id="preUserPage" hidden>上一页</a><a
                            id="firstUserPage" hidden>首页</a></div>
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
                                <li class="on" id="goodListSonBut"><a>商品列表</a></li>
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
                                    <select class="selectArea fr revise" id="selectGoodssle" value="1">
                                        <option class="select_hide">请选择查询条件</option>
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
                                                                                        hidden>1</span><a
                                id="endGoodsPage" hidden>尾页</a><a id="nextGoodsPage" hidden>下一页</a><a id="preGoodsPage"
                                                                                                      hidden>上一页</a><a
                                id="firstGoodsPage" hidden>首页</a></div>
                    </div>
                </div>
                <!--orderMain结束-->
            </div>
            <!--order结束-->
            <!--商品模块-->
            <!--仓库模块-->
            <jsp:include page="warehouse.jsp"></jsp:include>
            <!--订单模块-->
            <div class="order hide">
                <div class="orderList fl">
                    <ul class="orderListCent">
                        <li class="listTit"><p class="listContTit">订单管理</p></li>
                        <li class="on"><a id="0">订单列表</a></li>
                        <li onclick="GetnowPage(1)"><a id="1">异常订单列表</a></li>
                        <li class="listTit"><p class="listContTit">出入库单管理</p></li>
                        <li onclick="outGetnowPage(1)"><a id="2">出库单列表</a></li>
                        <li onclick="inGetnowPage(1)"><a id="3">入库单列表</a></li>
                        <li class="listTit"><p class="listContTit">退货单管理</p></li>
                        <li id="returnListBut"><a id="4">退货单列表</a></li>
                        <li onclick="refoundGetnowPage(1)"><a id="5">退款单列表</a></li>
                    </ul>
                </div><!--orderList结束-->
                <div class="orderMain fl">
                    <!--订单列表-->
                    <jsp:include page="order.jsp"></jsp:include>
                    <!--异常订单列表-->
                    <jsp:include page="exception.jsp"></jsp:include>
                    <!--出库单列表-->
                    <jsp:include page="outboundorder.jsp"></jsp:include>
                    <!--入库单列表-->
                    <jsp:include page="inboundorder.jsp"></jsp:include>

                    <!--退货单列表-->
                    <div class="relatedOrder hide">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit">退货单列表</div>
                                <span id="returnedCheck"></span>
                                <div class="orderSearch">
                                    <form>
                                        <ul>
                                            <li><input type="button" value="创建退款单" class="btn"
                                                       id="returnedCreaterefoundOder" disabled="disabled"></li>
                                            <li><input type="button" value="换货发货" class="btn" id="changeOutBound"
                                                       disabled="disabled"></li>
                                            <li><input type="button" value="审核" class="btn" id="checkreturnedorder"
                                                       disabled="disabled">
                                            </li>
                                            <li><input type="button" value="取消退货" class="btn" id="cancelReturnedOrder"
                                                       disabled="disabled">
                                            </li>
                                            <%--查看按钮  dfdgfg--%>
                                            <li><input type="button" value="查看订单" class="btn beta" disabled="disabled"
                                                       id="returnedDetailbut">
                                            </li>
                                        </ul>
                                        <input type="button" value="查询" class="submitBtn fr" id="serarchReturnedOrder">
                                        <input type="text" class="textArea fr" id="returnValue">
                                        <select class="selectArea fr revise" id="returnedselect">
                                            <option class="select_hide" id="returnSelect1">请选择查询条件</option>
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
                                        id="totalReturnedPage" hidden>1</span><a
                                        id="endReturnedPage" hidden>尾页</a><a id="nextReturnedPage" hidden>下一页</a><a
                                        id="preReturnedPage" hidden>上一页</a><a
                                        id="firstReturnedPage" hidden>首页</a></div>
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
                                    id="totalreturnedGoodsPage" hidden>1</span><a
                                    id="endreturnedGoodsPage" hidden>尾页</a><a id="nextreturnedGoodsPage"
                                                                              hidden>下一页</a><a
                                    id="prereturnedGoodsPage" hidden>上一页</a><a
                                    id="firstreturnedGoodsPage" hidden>首页</a></div>

                        </div>
                    </div>
                    <!--退款单列表-->
                    <jsp:include page="refoundOrder.jsp"></jsp:include>

                </div><!--orderMain结束-->
            </div><!--order结束-->
            <!--订单模块-->
        </div><!--content结束-->
        <!--弹窗模块-->
        <div class="popupAll">
            <div class="customShow">
                <div id="addUserwindows">
                    <div class="popupTop">
                        <span class="iconClose fr" id="closeAddUser"></span>
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
                                <li class="warn" id="addUserMsg"></li>
                                <li>
                                    <input type="button" value="保存" class="save mt50" id="addUser">
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
                <div id="updateUserBound">
                    <div class="popupTop">
                        <span class="iconClose fr" id="closeUpdateUser"></span>
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
                                <li class="warn" id="updateUserMsg"></li>
                                <li>
                                    <input type="button" value="保存" class="save mt50" id="updateUser">
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>

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
                                <input type="text" class="inputList" id="warenum">
                            </li>

                            <li>
                                <label class="listName mt20">仓库名：</label>
                                <input type="text" class="inputList" id="warename">
                            </li>
                            <%--添加仓库时信息成功与否显示--%>

                            <li class="warn">
                                <span id="addwarnli" class="lixx"></span>
                            </li>

                            <li>
                                <input type="button" value="保存" onclick="addwarehouse()" class="save mt50">
                            </li>
                        </ul>
                    </div>
                </div>
                <div>
                    <div class="popupTop">
                        <span class="iconClose fr"></span>
                        <p class="popupTopTit">编辑仓库</p>
                    </div>
                    <div class="popupCont store">
                        <ul>
                            <li>
                                <label class="listName mt20">仓库编号：</label>
                                <input type="text" class="inputList" id="updatewhnum">
                            </li>
                            <li>
                                <label class="listName mt20">仓库名：</label>
                                <input type="text" class="inputList" id="updatewhname">
                            </li>

                            <li class="warn">
                                <span id="updatewarnli" class="lixx"></span>
                            </li>
                            <li>
                                <input type="button" id="saveupdatewh" value="保存" onclick="updateware(this.name)"
                                       class="save mt50">
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
                                <input type="button" value="导入" style="font-size:20px" class="leadingBtn"
                                       id="importBtn">
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
            <div class="loading">
                <div class="mask"></div>
                <div class="loading-icon"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
            </div>
        </div>

</body>
</html>

