<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/orderDetails.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/orderdetail.js"></script>
</head>

<body>
<div class="wrap">
  <div class="header">
    <p class="headerTit fl">订单详情</p>
  </div>
  <div class="details">
    <div class="detailsBox">
      <!--基本信息-->
      <div>
        <div class="detailsCent ml15 mb5">
          <div class="detailsCont">
            <div class="contTit">基本信息</div>
            <div class="content">
              <form>
                <table cellspacing="0" cellpadding="0" border="0" class="table">
                  <tr>
                    <td>订单号:</td>
                    <td>${orderModel.getOid()}</td>
                  </tr>
                  <tr>
                    <td>渠道订单号:</td>
                    <td>${orderModel.getChanneloid()}</td>
                  </tr>
                  <tr>
                    <td>订单状态:</td>
                    <td id="orderStatus" hidden>${orderModel.getOrderstatus()}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==1?"缺货异常":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==2?"订单异常":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==3?"待预检":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==4?"待路由":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==5?"已出库":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==6?"已完成":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==7?"缺货等待":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==8?"待出库":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==9?"已取消":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==10?"出库异常":""}</td>
                    <td name="orderStatus">${orderModel.getOrderstatus()==11?"已发货":""}</td>
                  </tr>
                  <tr>
                    <td>订单来源:</td>
                    <td>${orderModel.getOrderform()}</td>
                  </tr>
                  <tr>
                    <td>买家ID:</td>
                    <td>${orderModel.getBuyerid()}</td>
                  </tr>
                  <tr>
                    <td>下单时间:</td>
                    <td>${orderModel.getOrdertime()}</td>
                  </tr>
                  <tr>
                    <td>基本状态:</td>
                    <td>${orderModel.getBasestatus()=="1"?"活动":"冻结"}</td>
                  </tr>
                  <tr>
                    <td>修改人:</td>
                    <td>${orderModel.getModifyman()}</td>
                  </tr>
                  <tr>
                    <td>修改时间:</td>
                    <td>${orderModel.getModifytime()}</td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
          <div class="detailsCont">
            <div class="contTit">支付信息</div>
            <div class="content">
              <form>
                <table cellspacing="0" cellpadding="0" border="0" class="table">
                  <tr>
                    <td>支付状态:</td>
                    <td>${orderModel.getPaystatus()=="1"?"已支付":"未支付"}</td>
                  </tr>
                  <tr>
                    <td>支付方式:</td>
                    <td>${orderModel.getPaystyle()==1?"支付宝":""}</td>
                  </tr>
                  <tr>
                    <td>付款时间:</td>
                    <td>${orderModel.getPaytime()}</td>
                  </tr>
                  <tr>
                    <td>商品总价:</td>
                    <td>${orderModel.getGoodstolprice()}</td>
                  </tr>
                  <tr>
                    <td>订单折扣:</td>
                    <td>${orderModel.getDiscountprice()}</td>
                  </tr>
                  <tr>
                    <td>订单总价:</td>
                    <td>${orderModel.getOrdertolprice()}</td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
        <div class="detailsCent mr20 ml20">
          <div class="detailsCont">
            <div class="contTit">收货人信息</div>
            <div class="content">
              <form name="edit" method="get">
                <div class="borderBottom">
                  <input type="button" value="编辑"  class="contentBtn" id="editOrder">
                  <input type="button" value="取消编辑"  class="contentBtn cancle" onclick="cancel()" id="cancleOrderBtn">
                  <input type="button" value="保存"  class="contentBtn" id="save">
                </div>
                <table cellspacing="0" cellpadding="0" border="0" class="table edit">
                  <tr><td><input type="text" name="oId" value="${orderModel.getOid()}" style="display: none"></td></tr>
                  <tr>
                    <td>收货人:</td>
                    <td><input type="text" readonly class="editArea edit" name="receiverName" id="receiverName" value="${orderModel.getReceivername()}"></td>
                  </tr>
                  <tr>
                    <td>手机:</td>
                    <td><input type="text" readonly class="editArea edit" name="receiverMobel" id="receiverMobel" value="${orderModel.getReceivermobel()}"></td>
                  </tr>
                  <tr>
                    <td>联系电话:</td>
                    <td><input type="text" readonly class="editArea edit" name="receiverTelnum" id="receiverTelnum" value="${orderModel.getReceivertelnum()}"></td>
                  </tr>
                  <tr class="place">
                    <td>省:</td>
                    <td><input type="text" readonly class="editArea edit revise" name="receiverProvince" id="receiverProvince" value="${orderModel.getReceiverprovince()}"></td>
                    <td>市:</td>
                    <td><input type="text" readonly class="editArea edit revise" name="receiverCity" id="receiverCity" value="${orderModel.getReceivercity()}"></td>
                    <td>区:</td>
                    <td><input type="text" readonly class="editArea edit revise" name="receiverArea" id="receiverArea" value="${orderModel.getReceiverarea()}"></td>
                  </tr>
                  <tr>
                    <td>详细地址:</td>
                    <td><input type="text" readonly class="editArea edit" name="detailAddress" id="detailAddress" value="${orderModel.getDetailaddress()}"></td>
                  </tr>
                  <tr>
                    <td>邮编:</td>
                    <td><input type="text" readonly class="editArea edit" name="zipCode" id="zipCode" value="${orderModel.getZipcode()}"></td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
          <div class="detailsCont">
            <div class="contTit">配送信息</div>
            <div class="content">
              <form>
                <table cellspacing="0" cellpadding="0" border="0" class="table">
                  <tr>
                    <td>发货仓库:</td>
                    <td id="wareHouse">${orderModel.getGoodswarehouse()}</td>
                  </tr>
                  <tr>
                    <td>物流公司:</td>
                    <td>${orderModel.getLogisticscompany()}</td>
                  </tr>
                  <tr>
                    <td>物流单号:</td>
                    <td>${orderModel.getLogisticsid()}</td>
                  </tr>
                  <tr>
                    <td>配送时间:</td>
                    <td>${orderModel.getSendtime()}</td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
        <div class="detailsCent mt100">
          <div class="detailsCont">
            <div class="contTit">商品信息</div>
            <div class="content extra">
              <form>
                <table cellspacing="0" cellpadding="0" border="0">
                  <c:forEach items="${goodsPojoList}" var="goods">
                    <tr>
                      <td>
                          <table class="product">
                            <tr>
                              <td>商品编码：</td>
                              <td>${goods.getGoodsno()}</td>
                            </tr>
                            <tr>
                              <td>商品名称：</td>
                              <td>${goods.getGoodsname()}</td>
                            </tr>
                            <tr>
                              <td>商品单价：</td>
                              <td>${goods.getDivideorderfee()}</td>
                            </tr>
                            <tr>
                              <td>商品个数：</td>
                              <td>${goods.getGoodNum()}</td>
                            </tr>
                            <tr>
                              <td>商品总价：</td>
                              <td>${goods.getTotalfee()}</td>
                            </tr>
                          </table>
                      </td>
                    </tr>
                  </c:forEach>
                </table>
              </form>
            </div>
          </div>
          <div class="detailsCont">
            <div class="contTit">备注</div>
            <div class="content">
              <form>
                <table cellspacing="0" cellpadding="0" border="0">
                  <tr>
                    <td align="center" valign="top">买家备注:</td>
                    <td><textarea class="seller">${orderModel.getRemark()}</textarea></td>
                  </tr>
                </table>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="detailsMenu"><p class="ml15">详情信息</p></div>
  </div>
  <div class="loading">
    <div class="mask"></div>
    <div class="loading-icon"><img src="${pageContext.request.contextPath}/images/loader.gif"></div>
  </div>
</div>
</body>
</html>
