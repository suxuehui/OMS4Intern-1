<%--
  Created by IntelliJ IDEA.
  User: ZHAN545
  Date: 2016/12/8
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预检</title>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/angular.min.js"></script>
    <script>
        var orderArray=new Array();
        var order1={"goodsno":"1","goodnum":2,"goodsPrice":2.20};
        orderArray.push(order1);
        var data={"oid":123,"channeloid":123,"goods":orderArray,"returnedOrChange":"returned"};
        $(function () {
            $("#preview").click(function () {
                alert("cc");
                alert(JSON.stringify(orderArray));
                $.ajax({
                    url:"previewOrder",
                    type:"post",
                    data:{jsonStr:JSON.stringify(data)},
                    traditional: true,
                    success:function(data){
                        alert(data);
                    }
                });
            })
        })
    </script>
</head>
<body>
    <input type="button" id="preview" value="预检">
</body>
</html>
