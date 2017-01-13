<%--
  Created by IntelliJ IDEA.
  User: ZHAN545
  Date: 2017/1/13
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="order" id="userClass">
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
                
                        <input type="button" value="查询" class="submitBtn fr" id="userselectbutton">
                        <input type="text" class="textArea fr" id="userselectvalue"
                               onFocus="if(value==defaultValue){value='';}">
                    
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
