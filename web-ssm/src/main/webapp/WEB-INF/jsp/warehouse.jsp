<%--
  Created by IntelliJ IDEA.
  User: GONG036
  Date: 2016/12/29
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>warehouse</title>
</head>
<body>
<div class="order hide">
    <div class="orderList fl">
        <ul>
            <li>
                <ul class="orderListCent">
                    <li class="listTit"><p class="listContTit">仓库管理</p></li>
                    <li class="on"><a onclick="wareGetnowPage(1)" id="">仓库列表</a></li>
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
                    <input type="button" value="查询"   onclick="WarehousegetPage(1)"  class="submitBtn fr">
                    <input type="text"  id="whtxt" value="" class="textArea fr" onkeydown="if (event.keyCode == 13){WarehousegetPage(1)}" >
                    <select id="whselectid" class="selectArea fr">
                        <option value="0" class="select_hide">请选择查询条件</option>
                        <option value="1" >仓库编号</option>
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

               </div>
            <%--分页列出仓库列表只能放在后面--%>
            <script src="${pageContext.request.contextPath}/js/warehouselist.js"></script>
            <div class="fenpage"  id="whdivpage"></div>
        </div>
    </div>
</div>
</body>
</html>
