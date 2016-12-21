<%--
  Created by IntelliJ IDEA.
  User: MAVI003
  Date: 2016/12/15
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>

    <script>
        $(function () {

            $("#loginsubmit").click(function () {
                var userId = $("#userId");
                var password = $("#password");
                var msg = $("#msg");
                if ($.trim(userId.val()) == "") {
                    userId.focus();
                    msg.html("<br/>用户名不能为空");
                    return false;
                } else if ($.trim(password.val()) == "") {
                    password.focus();
                    msg.html("<br/>密码不能为空");
                    return false;
                } else {
                    var checkUNameUrl = "checkUName?uName=" + userId.val();
                    $.get(checkUNameUrl, function (str) {
                        if (str == 0) {
                            userId.val("");
                            password.val("");
                            userId.focus();
                            msg.html("<br/>用户名不存在");
                            return false;
                        } else {
                            $.ajax({
                                type: "POST",
                                url: "checkUser",
                                data: "uname=" + userId.val() + "&password=" + password.val(),
                                dataType: "json",
                                success: function (data) {
                                    if (data.check == 0) {
                                        alert("请输入正确密码")
                                    } else if (data.check == 1) {
                                        alert("管理员");
                                        window.location.href="index?urole="+1;

                                    } else if (data.check == 2) {
                                        alert("普通用户");
                                        window.location.href="index?urole="+2;
                                    }else {
                                        alert("用户身份异常，此用户失效")
                                    }
                                }
                            });

                        }
                    });
                    return false;
                }
            })
        })

    </script>
</head>

<body>
<div class="wrap">
    <div class="login">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/images/logo.jpg" class="logoImg">
        </div>
        <div class="loginMain">
            <form>
                <div class="formList">
                    <ul>
                        <li>
                            <label class="listName" for="用户名">用户名：</label>
                            <input type="text" name="" class="inputList" id="userId">
                        </li>
                        <li>
                            <label class="listName" for="密码">密码：</label>
                            <input type="password" name="" class="inputList ml20" id="password">
                        </li>
                        <label class="msg" id="msg"></label>
                        <li><input type="submit" value="" class="submitBtn" align="center" id="loginsubmit"></li>
                    </ul>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

