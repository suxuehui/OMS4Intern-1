$.extend({
    getUrlVars: function () {
        var vars = [], hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        for (var i = 0; i < hashes.length; i++) {
            hash = hashes[i].split('=');
            vars.push(hash[0]);
            vars[hash[0]] = hash[1];
        }
        return vars;
    },
    getUrlVar: function (name) {
        return $.getUrlVars()[name];
    }
});

//获取参数对象
//alert($.getUrlVars());
//获取参数a的值
//alert($.getUrlVar('urole'));


function usercheckclick(userid) {


    var count = 0;
    var checkArry = document.getElementsByName("usercheck");
    for (var i = 0; i < checkArry.length; i++) {

        if (checkArry[i].checked == true) {
            ++count;
        }
    }
    if (count == 1) {
        document.getElementById("updateUserBut").disabled = false;
    } else {
        document.getElementById("updateUserBut").disabled = true;
    }


}

$(document).ready(function () {


    var urole = $.getUrlVar('urole');
    if (urole == 1) {
        window.onload = inGetUserNowPage($('#userPageNow').html());
        $('#nextUserPage').click(
            function () {

                var userpage = $('#userPageNow').html();
                var totalPage = $('#totalUserPage').html();
                if (userpage < totalPage) {
                    $('#userPageNow').html(userpage * 1 + 1 * 1);
                    inGetUserNowPage(userpage * 1 + 1 * 1);
                } else {
                    alert("已到最后一页");
                }

            }
        );
        $('#preUserPage').click(
            function () {

                var userpage = $('#userPageNow').html();
                if (userpage > 1) {
                    $('#userPageNow').html(userpage * 1 - 1 * 1);
                    inGetUserNowPage(userpage * 1 - 1 * 1);
                } else {
                    alert("已到第一页");
                }
            }
        );


        $('#firstUserPage').click(
            function () {
                inGetUserNowPage(1);
                $('#userPageNow').html(1);
            }
        );

        $('#endUserPage').click(
            function () {
                inGetUserNowPage($('#totalUserPage').html());
                $('#userPageNow').html($('#totalUserPage').html());
            }
        );


        $('#deleteUser').click(
            function () {
                var userIdArray = new Array();
                var i = 0;
                $("input:checkbox[name='usercheck']:checked").each(function () {
                    userIdArray[i++] = parseInt($(this).attr("id"));
                });
                if (userIdArray.length == 0) {
                    alert("请选择要删除的用户");
                } else {

                    var userIds = userIdArray.join("/");
                    $.ajax({
                        type: 'get',
                        url: '/oms/user/deleteUserByIds',
                        data: {
                            userIdList: userIds
                        },
                        contentType: "application/json; charset=utf-8",
                        dataType: "json",
                        success: function (data) {
                            if (data > 0) {
                                inGetUserNowPage(1);
                                alert("删除成功");
                            } else if(data=0){
                                alert("删除失败");
                            }else if(data=-1){
                                alert("不可以删除自己");
                            }
                        },
                        error: function (data) {
                            alert("删除失败");
                            inGetUserNowPage(1);
                        }

                    });
                }
            });

        $("#addUser").click(function () {

            var username = $('#addUserName').val().trim();
            var password = $('#addUserPassword').val().trim();
            if (username == '') {
                alert("请输入用户名");
            } else {
                if (password == '') {
                    alert("请输入密码");
                } else {
                    if (password.length < 6) {
                        alert("密码不得少于6位数");
                    } else {
                        var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                        if (!zzbds.test(username)) {
                            alert("请输入有效用户名");
                        } else {
                            if (!zzbds.test(password)) {
                                alert("请输入有效密码");
                            } else {
                                $.ajax({
                                    type: 'get',
                                    url: '/oms/user/addUser',
                                    data: {
                                        userName: username,
                                        password: password
                                    },
                                    contentType: "application/json; charset=utf-8",
                                    dataType: "json",
                                    success: function (data) {
                                        if (data == 1) {
                                            alert("添加成功");
                                            $('#addUserName').val("");
                                            $('#addUserPassword').val("");
                                            inGetUserNowPage($('#totalUserPage').html());
                                        } else {
                                            alert("用户名已存在");
                                            $('#addUserName').val("");
                                            $('#addUserPassword').val("");
                                        }
                                    },
                                    error: function (data) {
                                        alert("添加用户失败");
                                    }

                                });
                            }
                        }
                    }
                }
            }

        });


        $("#updateUser").click(function () {

            var username = $('#updateUserName').val().trim();
            var password = $('#updateUserPassword').val().trim();
            var userIdArray = new Array();
            var i = 0;
            $("input:checkbox[name='usercheck']:checked").each(function () {
                userIdArray[i++] = parseInt($(this).attr("id"));
            });
            if (username == '') {
                alert("请输入用户名");
            } else {
                if (password == '') {
                    alert("请输入密码");
                } else {
                    if (password.length < 6) {
                        alert("密码不得少于6位数");
                    } else {
                        var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                        if (!zzbds.test(username)) {
                            alert("请输入有效用户名");
                        } else {
                            if (!zzbds.test(password)) {
                                alert("请输入有效密码");
                            } else {
                                var userIds = userIdArray.join("/");
                                $.ajax({
                                    type: 'get',
                                    url: '/oms/user/updateUser',
                                    data: {
                                        uid: userIds,
                                        userName: username,
                                        password: password
                                    },
                                    contentType: "application/json; charset=utf-8",
                                    dataType: "json",
                                    success: function (data) {
                                        if (data == 1) {
                                            alert("修改成功");
                                            $('#updateUserName').val("");
                                            $('#updateUserPassword').val("");
                                            inGetUserNowPage(1);
                                        } else {
                                            alert("用户名已存在");
                                            $('#updateUserName').val("");
                                            $('#updateUserPassword').val("");
                                        }
                                    },
                                    error: function (data) {
                                        alert("修改用户失败");
                                    }

                                });
                            }
                        }
                    }
                }
            }
        });

        $('#userselectbutton').click(function () {

            var select = $('#userselectvalue').val().trim();
            if (select.length == 0) {

                alert("请输入查询内容");
            } else {
                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                if (!zzbds.test(select)) {
                    alert("请不要输入特殊符号");
                } else {
                    selectByUserName(1, select);
                    $('#preUserPage').hide();
                    $('#nextUserPage').hide();
                    $('#endUserPage').hide();
                }
            }

        });

        function inGetUserNowPage(pageNow) {
            var page = pageNow;
            var pageSize = 20;
            $.ajax({
                type: 'get',
                url: '/oms/user/getAllUsers',
                data: {
                    nowPage: page,
                    pageSize: pageSize
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    //alert(data.userList[0].uid);
                    var userList = data.userList;
                    var totalPage = data.totalPage;
                    $('#usertbody').html("");
                    for (var i in userList) {
                        var id = i * 1 + 1 * 1;
                        $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onchange='usercheckclick(this.id)'></td><td><a>" + userList[i].uname + "</a></td> <td>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + userList[i].urole + "</td> </tr>");
                        $('#totalUserPage').html(totalPage);
                    }


                },
                error: function (data) {
                    alert("获取用户列表失败");
                }

            });
        }

        function selectByUserName(pageNow, userName) {
            var page = pageNow;
            var username = userName;
            var pageSize = 20;
            $.ajax({
                type: 'get',
                url: '/oms/user/selectUserByName',
                data: {
                    username: username,
                    nowPage: page,
                    pageSize: pageSize
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    var userList = data.userList;
                    var totalPage = data.totalPage;
                    $('#usertbody').html("");
                    for (var i in userList) {
                        var id = i * 1 + 1 * 1;
                        $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onclick='usercheckclick(this.id)'></td><td><a>" + userList[i].uname + "</a></td> <td>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + userList[i].urole + "</td> </tr>");
                        $('#totalUserPage').html(totalPage);
                    }
                },
                error: function (data) {
                    alert("查询失败");
                }

            });
        }

    } else {


    }

    window.onload = inGetGoodsNowPage($('#goodsPageNow').html());

    $('#nextGoodsPage').click(
        function () {

            var goodspage = $('#goodsPageNow').html();
            var totalPage = $('#totalGoodPage').html();
            if (goodspage < totalPage) {
                inGetGoodsNowPage(goodspage * 1 + 1 * 1);
                $('#goodsPageNow').html(goodspage * 1 + 1 * 1);
            } else {
                alert("已到最后一页");
            }


        }
    );
    $('#preGoodsPage').click(
        function () {

            var goodspage = $('#goodsPageNow').html();
            if (goodspage > 1) {
                $('#userPageNow').html(goodspage * 1 - 1 * 1);
                inGetGoodsNowPage(goodspage * 1 - 1 * 1);
            } else {
                alert("已到第一页");
            }
        }
    );


    $('#firstGoodsPage').click(
        function () {
            $('#goodsPageNow').html(1);
            inGetGoodsNowPage(1);

        }
    );

    $('#endGoodsPage').click(
        function () {
            inGetGoodsNowPage($('#totalGoodPage').html());
            $('#goodsPageNow').html($('#totalGoodPage').html());
        }
    );

    function inGetGoodsNowPage(pageNow) {
        var page = pageNow;
        var pageSize = 20;
        $.ajax({
            type: 'get',
            url: '/oms/goods/getAllGoods',
            data: {
                page: page,
                pageSize: pageSize
            },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                //alert(data.userList[0].uid);
                var goodsList = data.goodsAndStatus;
                var totalPage = data.pageTotal;
                $('#goodsbody').html("");
                for (var i in goodsList) {
                    var id = i * 1 + 1 * 1;
                    $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice+ "</td></tr>");
                    $('#totalGoodPage').html(totalPage);

                }
            },
            error: function (data) {
                alert("获取商品列表失败");
            }

        });
    }

    function selectGoodsByValue(pageNow, value ,select) {
        var page = pageNow;
        var username = value;
        var select =select;
        var pageSize = 20;
        $.ajax({
            type: 'get',
            url: '/oms/goods/selectGoods',
            data: {
                select: select,
                value: value,
                nowPage: page,
                pageSize: pageSize
            },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                var goodsList = data.goodsAndStatus;
                var totalPage = data.pageTotal;
                $('#goodsbody').html("");
                for (var i in goodsList) {
                    var id = i * 1 + 1 * 1;
                    $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice+ "</td></tr>");
                    $('#totalGoodPage').html(totalPage);

                }
            },
            error: function (data) {
                alert("查询商品失败");
            }

        });
    }



    $('#selectgoodsbut').click(
        function () {
            var select = $('#selectGoodssle').val();
            var value = $('#goodsvaluetxt').val();
            if (select=="按名称查询"){
                selectGoodsByValue(1,value,"name");


            }else if(select=="按商品编码查询"){
                selectGoodsByValue(1,value,"id");
            }
        }
    );
});
