<%--
  Created by IntelliJ IDEA.
  User: MAVI003
  Date: 2016/12/16
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OMS订单管理系统</title>
    <link rel="stylesheet" type="text/css" href="../css/css.css">
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/index.js"></script>
    <script src="../js/userlistAndGoodslist.js"></script>

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
        <div class="logOut fr"><a>【注销】</a></div>
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
                                    <li><input type="button" value="新增用户" class="btn"></li>
                                    <li><input type="button" value="修改用户" class="btn" id="updateUserBut"
                                               disabled="disabled"></li>
                                    <li><input type="button" value="删除用户" class="btn" id="deleteUser"></li>
                                </ul>
                                <form>
                                    <input type="button" value="查询" class="submitBtn fr" id="userselectbutton">
                                    <input type="text" value="名称" class="textArea fr" id="userselectvalue" onFocus="if(value==defaultValue){value='';}">
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
                                    <ul>
                                        <li><input type="button" value="添加" class="btn"></li>
                                        <li><input type="button" value="删除" class="btn"></li>
                                    </ul>
                                    <input type="button" value="查询" class="submitBtn fr">
                                    <input type="text" class="textArea fr">
                                    <select class="selectArea fr">
                                        <option></option>
                                        <option></option>
                                        <option></option>
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
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td><input type="checkbox"></td>
                                        <td></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>

                                </table>
                            </form>
                        </div>
                        <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
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
                                <li class="on"><a>仓库列表</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!--orderList结束-->
                <div class="orderMain fl">
                    <div class="orderManage">
                        <div class="orderTag">
                            <div class="orderTit">仓库列表</div>
                            <div class="storeSearch">
                                <ul>
                                    <li><input type="button" value="新增" class="btn"></li>
                                    <li><input type="button" value="编辑" class="btn"></li>
                                    <li><input type="button" value="删除" class="btn"></li>
                                </ul>
                                <form>
                                    <input type="button" value="查询" class="submitBtn fr">
                                    <input type="text" class="textArea fr">
                                    <select class="selectArea fr">
                                        <option></option>
                                        <option></option>
                                        <option></option>
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
                                        <th>仓库编号</th>
                                        <th>名称</th>
                                    </tr>
                                    <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td><input type="checkbox"></td>
                                        <td><a></a></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>8</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>9</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>10</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>11</td>
                                        <td><input type="checkbox"></td>
                                        <td><a>8012984120571209241</a></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>12</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>13</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>14</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>15</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>16</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>17</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>18</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>19</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>20</td>
                                        <td><input type="checkbox"></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                    </div>
                </div><!--orderMain结束-->
            </div><!--order结束-->
            <!--仓库模块-->
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
                    <div class="relatedOrder">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit clearFix leading">
                                    <p class="fl">订单列表</p>
                                    <input type="button" value="导入" class="leadingIn fr mt10">
                                </div>
                                <div class="orderSearch beta">
                                    <form>
                                        <ul>
                                            <li><input type="button" value="预检" class="btn beta"></li>
                                            <li><input type="button" value="路由" class="btn beta"></li>
                                            <li><input type="button" value="出库" class="btn beta"></li>
                                            <li><input type="button" value="取消" class="btn beta"></li>
                                            <li><input type="button" value="退货" class="btn beta"></li>
                                            <li><input type="button" value="换货" class="btn beta"></li>
                                        </ul>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="orderMainCont revise">
                                <div class="table revise">
                                    <form>
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
                                            <tbody class="tablelsw">
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
                            <div class="tableDetails">
                                <form>
                                    <table cellspacing="0" cellpadding="0">
                                        <tr class="tableTit">
                                            <th class="w50">批量</th>
                                            <th class="w350">商品编码</th>
                                            <th>商品名称</th>
                                            <th>商品单价</th>
                                            <th>商品个数</th>
                                            <th>商品总价</th>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                        </div>
                    </div>
                    <!--异常订单列表-->
                    <div class="relatedOrder hide">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit">异常订单列表</div>
                                <div class="orderSearch">
                                    <form>
                                        <ul>
                                            <li><input type="button" value="处理异常" class="btn"></li>
                                            <li><input type="button" value="取消" class="btn"></li>
                                        </ul>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="orderMainCont revise">
                                <div class="table revise">
                                    <form>
                                        <table cellspacing="0" cellpadding="0" class="w1550">
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
                                            <tbody>
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
                            <div class="tableDetails">
                                <form>
                                    <table cellspacing="0" cellpadding="0">
                                        <tr class="tableTit">
                                            <th class="w50">批量</th>
                                            <th class="w350">商品编码</th>
                                            <th>商品名称</th>
                                            <th>商品单价</th>
                                            <th>商品个数</th>
                                            <th>商品总价</th>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                        </div>
                    </div>
                    <!--出库单列表-->
                    <div class="relatedOrder hide">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit">出库单列表</div>
                                <div class="orderSearch">
                                    <form>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="orderMainCont revise">
                                <div class="table revise">
                                    <form>
                                        <table cellspacing="0" cellpadding="0" class="w2250">
                                            <tr class="tableTit" height="20">
                                                <th class="w50">序号</th>
                                                <th class="w50">批量</th>
                                                <th class="w200">订单号</th>
                                                <th class="w200">渠道订单号</th>
                                                <th class="w100">订单状态</th>
                                                <th class="w100">出库单号</th>
                                                <th class="w100">同步状态</th>
                                                <th class="w100">收货人</th>
                                                <th class="w200">快递公司</th>
                                                <th class="w200">快递单号</th>
                                                <th class="w350">收货地址</th>
                                                <th class="w200">创建时间</th>
                                                <th class="w200">修改时间</th>
                                                <th class="w100">修改人</th>
                                                <th class="w100">出库单状态</th>
                                            </tr>
                                            <tbody>
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
                            <div class="tableDetails">
                                <form>
                                    <table cellspacing="0" cellpadding="0">
                                        <tr class="tableTit">
                                            <th class="w50">批量</th>
                                            <th>出库单号</th>
                                            <th>仓库出库单号</th>
                                            <th>商品编码</th>
                                            <th>商品名称</th>
                                            <th>商品数量</th>
                                            <th>出库数量</th>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                        </div>
                    </div>
                    <!--入库单列表-->
                    <div class="relatedOrder hide">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit">入库单列表</div>
                                <div class="orderSearch">
                                    <form>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="orderMainCont revise">
                                <div class="table revise">
                                    <form>
                                        <table cellspacing="0" cellpadding="0" class="w1950">
                                            <tr class="tableTit" height="20">
                                                <th class="w50">序号</th>
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
                                            <tbody>
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
                            <div class="tableDetails">
                                <form>
                                    <table cellspacing="0" cellpadding="0">
                                        <tr class="tableTit">
                                            <th class="w50">批量</th>
                                            <th class="w350">商品编码</th>
                                            <th>商品名称</th>
                                            <th>商品数量</th>
                                            <th>入库数量</th>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
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
                                            <li><input type="button" value="创建退款单" class="btn"></li>
                                            <li><input type="button" value="换货发货" class="btn"></li>
                                            <li><input type="button" value="审核" class="btn"></li>
                                            <li><input type="button" value="取消退货" class="btn"></li>
                                        </ul>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
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
                                            <tbody>
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
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
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                        </div>
                    </div>
                    <!--退款单列表-->
                    <div class="relatedOrder hide">
                        <div class="orderManage">
                            <div class="orderTag">
                                <div class="orderTit">退款单列表</div>
                                <div class="orderSearch">
                                    <form>
                                        <ul>
                                            <li><input type="button" value="退款" class="btn"></li>
                                        </ul>
                                        <input type="button" value="查询" class="submitBtn fr">
                                        <input type="text" class="textArea fr">
                                        <select class="selectArea fr">
                                            <option></option>
                                            <option></option>
                                            <option></option>
                                        </select>
                                    </form>
                                </div>
                            </div>
                            <div class="orderMainCont revise">
                                <div class="table revise">
                                    <form>
                                        <table cellspacing="0" cellpadding="0" class="w1200">
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
                                            <tbody id="tablelsw">
                                            <tr>
                                                <td style="text-align:center">1</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td><a>8012984120571209241</a></td>
                                                <td></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">2</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">3</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">4</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">5</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">6</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">7</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">8</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">9</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td style="text-align:center">10</td>
                                                <td align="center"><input type="checkbox"></td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                                <td>&nbsp;</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                            </div>
                        </div>
                        <div class="orderDetails Item">
                            <div class="tableDetails">
                                <form>
                                    <table cellspacing="0" cellpadding="0">
                                        <tr class="tableTit">
                                            <th class="w50">批量</th>
                                            <th class="w350">商品编码</th>
                                            <th>商品名称</th>
                                            <th>商品单价</th>
                                            <th>商品个数</th>
                                            <th>商品总价</th>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center"><input type="checkbox"></td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                            <td>&nbsp;</td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                            <div class="page"><a>尾页</a><a>下一页</a><a>上一页</a><a>首页</a></div>
                        </div>
                    </div>
                </div><!--orderMain结束-->
            </div><!--order结束-->
            <!--订单模块-->
        </div><!--content结束-->
        <!--弹窗模块-->
        <div class="popupAll">
            <div class="customShow">
                <div>
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
                    <form>
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
                    </form>
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
                        <form>
                            <ul>
                                <li>
                                    <label class="listName mt20">仓库编号：</label>
                                    <input type="text" class="inputList">
                                </li>
                                <li>
                                    <label class="listName mt20">仓库名：</label>
                                    <input type="text" class="inputList">
                                </li>
                                <li>
                                    <input type="button" value="保存" class="save mt50">
                                </li>
                            </ul>
                        </form>
                    </div>
                </div>
                <div>
                    <div class="popupTop">
                        <span class="iconClose fr"></span>
                        <p class="popupTopTit">编辑仓库</p>
                    </div>
                    <div class="popupCont store">
                        <form>
                            <ul>
                                <li>
                                    <label class="listName mt20">仓库编号：</label>
                                    <input type="text" class="inputList">
                                </li>
                                <li>
                                    <label class="listName mt20">仓库名：</label>
                                    <input type="text" class="inputList">
                                </li>
                                <li>
                                    <input type="button" value="保存" class="save mt50">
                                </li>
                            </ul>
                        </form>
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
                    <form>
                        <ul>
                            <li>
                                <input type="file" style="font-size:20px">
                                <input type="button" value="导入" style="font-size:20px" class="leadingBtn">
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <!--导入弹框结束-->
        </div>
        <!--弹窗模块-->
        <%--<div class="loading" aria-disabled="true">
            <div class="mask"></div>
            <div class="loading-icon"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
        </div>--%>
    </div><!--wrap结束-->
</body>
</html>

