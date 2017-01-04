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

    document.getElementById("deleteUser").disabled = false;
    var count = 0;
    var checkArry = document.getElementsByName("usercheck");
    for (var i = 0; i < checkArry.length; i++) {

        if (checkArry[i].checked == true) {
            ++count;
        }
    }

    if (count == 1) {
        document.getElementById("updateUserBut").disabled = false;
    } else if (count == 0) {
        document.getElementById("deleteUser").disabled = true;
        document.getElementById("updateUserBut").disabled = true;
    } else {
        document.getElementById("deleteUser").disabled = false;
        document.getElementById("updateUserBut").disabled = true;
    }


}


function checkboxreturneddis(id) {

    var count = 0;
    var checkArry = document.getElementsByName("returnedcheck");

    for (var i = 0; i < checkArry.length; i++) {

        if (checkArry[i].checked == true) {
            ++count;

        }
    }

    if (count == 1) {
        $('#returnedDetailbut').removeAttr("disabled");
    } else {
        $('#returnedDetailbut').attr('disabled', "true");
    }

}

function returnedGetGoods(returnedid) {


    var returnedid = returnedid;
    var pageNow = $("#returnedPageNow").html();
    var pageSize = 5;

    returngetgoodsfromserver(returnedid, pageNow, pageSize);


}


function returngetgoodsfromserver(returnedid, pageNow, pageSize) {
    $.ajax({
        type: 'get',
        url: '/oms/returned/getGoods',
        data: {
            pageNow: pageNow,
            pageSize: pageSize,
            returnedId: returnedid
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var returnedSonList = data.returnedSonList;
            var totalPage = data.totalPageCount;
            if (pageNow == 1) {
                if (totalPage == 0 || totalPage == 1) {
                    $('#prereturnedGoodsPage').hide();
                    $('#firstreturnedGoodsPage').hide();
                    $('#endreturnedGoodsPage').hide();
                    $('#nextreturnedGoodsPage').hide();

                } else {

                    $('#prereturnedGoodsPage').hide();
                    $('#firstreturnedGoodsPage').hide();
                    $('#endreturnedGoodsPage').show();
                    $('#nextreturnedGoodsPage').show();

                }
            } else if (pageNow < totalPage) {
                if (totalPage == 0 || totalPage == 1) {
                    $('#prereturnedGoodsPage').hide();
                    $('#firstreturnedGoodsPage').hide();
                    $('#endreturnedGoodsPage').hide();
                    $('#nextreturnedGoodsPage').hide();

                } else {
                    $('#prereturnedGoodsPage').show();
                    $('#firstreturnedGoodsPage').show();
                    $('#endreturnedGoodsPage').show();
                    $('#nextreturnedGoodsPage').show();


                }
            } else if (pageNow = totalPage) {

                $('#prereturnedGoodsPage').show();
                $('#firstreturnedGoodsPage').show();
                $('#endreturnedGoodsPage').hide();
                $('#nextreturnedGoodsPage').hide();

            }

            $('#returnedGoodsBody').html("");
            for (var i in returnedSonList) {
                var id = i * 1 + 1 * 1;
                $('#returnedGoodsBody').append("<tr class='a'><td><input type='checkbox'  name='returnedsoncheck'   id='" + returnedSonList[i].goodsno + "returnedson" + "'></td><td><a id='" + returnedSonList[i].goodsno + "'>" + returnedSonList[i].goodsno + "</a></td> <td>&nbsp;" + returnedSonList[i].goodsname + "</td> <td>&nbsp;" + returnedSonList[i].goodnum + "</td> <td>&nbsp;" + returnedSonList[i].goodsprice + "</td></tr>");
                $('#totalreturnedGoodsPage').html(totalPage);

            }
            $("#returnedidongoods").html(returnedid);
        },
        error: function (data) {
            alert("获取退货单商品失败");
        }


    });

}

function showReturnDetail(id) {
    window.open("/oms/returned/returnedDetail?id=" + id);
}

function getreturnedStatus(returnIdArray, statusp) {

    var status;
    var countStatus = 0;
    for (var i = 0; i < returnIdArray.length; i++) {
        var id = returnIdArray[i];

        $.ajax({
            type: 'get',
            url: '/oms/returned/getReturnedStatus',
            data: {
                id: id,
            },
            async: false,//同步
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (data.status == statusp) {
                    countStatus++;
                }
            },
            error: function (data) {
                alert("获取退货单状态失败");
            }
        });
    }
    if (countStatus == returnIdArray.length) {
        return "yes";
    } else {

        return "no";
    }

}

function getUserRole() {
    var urole = $("#urole").html();
    return urole;

}

function getreturnedOrChange(returnIdArray) {
    var returncount = 0;
    for (var i = 0; i < returnIdArray.length; i++) {

        var id = returnIdArray[i];

        $.ajax({
            type: 'get',
            url: '/oms/returned/getReturnedOrChange',
            data: {
                id: id,
            },
            async: false,//同步
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {

                if (data.returnOrChange == "return") {

                    returncount++;
                }

            },
            error: function (data) {
                alert("获取退货单退换货状态失败");
            }


        });

    }
    if (returncount == returnIdArray.length) {
        return "return";
    } else if (returncount == 0) {
        return "change";
    } else {
        return "error";
    }


}

$(
    function () {

        var returnIdArray = new Array();
        $("input:checkbox[name='returnedcheck']:checked").each(function () {
            returnIdArray[i++] = parseInt($(this).attr("id"));
        });


        $('#preUserPage').hide();
        $('#firstUserPage').hide();
        $('#endUserPage').hide();
        $('#nextUserPage').hide();

        $('#preGoodsPage').hide();
        $('#firstGoodsPage').hide();
        $('#endGoodsPage').hide();
        $('#nextGoodsPage').hide();

        $('#preReturnedPage').hide();
        $('#firstReturnedPage').hide();
        $('#endReturnedPage').hide();
        $('#nextReturnedPage').hide();


        $('#prereturnedGoodsPage').hide();
        $('#firstreturnedGoodsPage').hide();
        $('#endreturnedGoodsPage').hide();
        $('#nextreturnedGoodsPage').hide();


        $('#closeAddUser').click(
            function () {
                $(".hbg").hide();
            }
        );

        $('#closeUpdateUser').click(
            function () {
                $(".hbg").hide();
                document.getElementById("updateUserBut").disabled = true;
            }
        );


        var urole = getUserRole();

        if (parseInt(urole) == 1) {
            window.onload = inGetUserNowPage($('#userPageNow').html());

            $('#nextUserPage').click(
                function () {

                    var userpage = parseInt($('#userPageNow').html());
                    var totalPage = parseInt($('#totalUserPage').html());
                    if (userpage < totalPage) {
                        if (userpage * 1 + 1 * 1 == totalPage) {
                            $('#endUserPage').hide();
                            $('#nextUserPage').hide();

                        }
                        $('#preUserPage').show();
                        $('#firstUserPage').show();
                        $('#userPageNow').html(userpage * 1 + 1 * 1);
                        inGetUserNowPage(userpage * 1 + 1 * 1);
                    } else {

                        alert("已到最后一页");
                    }

                }
            );
            $('#preUserPage').click(
                function () {

                    var userpage = parseInt($('#userPageNow').html());
                    if (userpage > 1) {
                        if (userpage * 1 - 1 * 1 == 1) {
                            $('#preUserPage').hide();
                            $('#firstUserPage').hide();
                        }
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
                    $('#preUserPage').hide();
                    $('#firstUserPage').hide();
                    $('#endUserPage').show();
                    $('#nextUserPage').show();

                }
            );

            $('#endUserPage').click(
                function () {
                    $('#preUserPage').show();
                    $('#firstUserPage').show();
                    $('#endUserPage').hide();
                    $('#nextUserPage').hide();
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
                                } else if (data = 0) {
                                    alert("删除失败");
                                } else if (data = -1) {
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
                $(".loading").hide();
                $(".hbg").show();
                var username = $('#addUserName').val().trim();
                var password = $('#addUserPassword').val().trim();
                if (username == '') {
                    alert("请输入用户名");
                    $(".loading").hide();
                } else {
                    if (password == '') {
                        alert("请输入密码");
                        $(".loading").hide();
                    } else {
                        if (password.length < 6 || password.length > 15) {
                            alert("请输入6-15位密码");
                            $(".loading").hide();
                        } else {
                            var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                            if (!zzbds.test(username)) {
                                alert("请输入有效用户名");
                                $(".loading").hide();
                            } else {
                                if (!zzbds.test(password)) {
                                    alert("请输入有效密码");
                                    $(".loading").hide();
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
                                                $(".loading").hide();
                                                $(".hbg").hide();
                                                alert("添加成功");
                                                $("#addUserwindows").hide();

                                                $('#addUserName').val("");
                                                $('#addUserPassword').val("");
                                                inGetUserNowPage(1);

                                            } else {

                                                alert("用户名已存在");

                                                $('#addUserName').val("");
                                                $('#addUserPassword').val("");


                                            }
                                        },
                                        error: function (data) {

                                            alert("添加用户失败");
                                            $("#addUserwindows").hide();
                                            $(".loading").hide();
                                            $(".hbg").hide();
                                        }

                                    });


                                }
                            }
                        }
                    }
                }

            });


            $("#updateUser").click(function () {
                $(".loading").hide();
                $(".hbg").show();
                var username = $('#updateUserName').val().trim();
                var password = $('#updateUserPassword').val().trim();
                var preusername = $('#updateusernamehidden').val();
                var prepassword = $('#updateupasshidden').val();
                if (username == preusername && password == prepassword) {
                    alert("与原用户信息一致，请重新修改");
                    $(".loading").hide();
                } else {
                    var userIdArray = new Array();
                    var i = 0;
                    if (username == '') {
                        alert("请输入用户名");
                        $(".loading").hide();
                    } else {
                        if (password == '') {
                            alert("请输入密码");
                            $(".loading").hide();
                        } else {
                            if (password.length < 6 || password.length > 15) {
                                alert("请输入6-15位密码");
                                $(".loading").hide();
                            } else {
                                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                                if (!zzbds.test(username)) {
                                    alert("请输入有效用户名");
                                    $(".loading").hide();
                                } else {
                                    if (!zzbds.test(password)) {
                                        alert("请输入有效密码");
                                        $(".loading").hide();
                                    } else {
                                        $("input:checkbox[name='usercheck']:checked").each(function () {
                                            userIdArray[i++] = parseInt($(this).attr("id"));
                                        });
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
                                                    $(".loading").hide();
                                                    $(".hbg").hide();
                                                    $("#updateUserBound").hide();
                                                    $('#updateUserName').val("");
                                                    $('#updateUserPassword').val("");
                                                    document.getElementById("updateUserBut").disabled = true;
                                                    inGetUserNowPage(1);

                                                } else if (data == -1) {
                                                    alert("修改成功请重新登陆");
                                                    $('#updateUserName').val("");
                                                    $('#updateUserPassword').val("");
                                                    inGetUserNowPage(1);
                                                    $(".loading").hide();
                                                    window.location.href = "../login/login";
                                                } else {
                                                    alert("用户名已存在");
                                                    var unameid = "#" + userIds + "uname";
                                                    var upassid = "#" + userIds + "upass";

                                                    var uname = $(unameid).html();
                                                    var upass = $(upassid).html();
                                                    $('#updateUserName').val(uname);
                                                    upass = upass.replace("&nbsp;", "");
                                                    $('#updateUserPassword').val(upass);
                                                }
                                            },
                                            error: function (data) {
                                                alert("修改用户失败");
                                                $(".loading").hide();
                                                $(".hbg").hide();
                                                $("#updateUserBound").hide();
                                                document.getElementById("updateUserBut").disabled = true;
                                            }

                                        });


                                    }
                                }
                            }
                        }
                    }
                }
            });

            $('#userselectbutton').click(function () {

                var select = $('#userselectvalue').val().trim();
                if (select.length == 0) {

                    inGetUserNowPage(1);
                } else {
                    var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                    if (!zzbds.test(select)) {
                        alert("请不要输入特殊符号");
                    } else {
                        selectByUserName(1, select);

                    }
                }

            });

            function inGetUserNowPage(pageNow) {
                var page = pageNow;
                var pageSize = 15;
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
                        if (pageNow == 1) {
                            if (totalPage == 0 || totalPage == 1) {
                                $('#preUserPage').hide();
                                $('#firstUserPage').hide();
                                $('#endUserPage').hide();
                                $('#nextUserPage').hide();
                            } else {
                                $('#nextUserPage').show();
                                $('#endUserPage').show();
                            }
                        } else if (pageNow < totalPage) {
                            if (totalPage == 0 || totalPage == 1) {
                                $('#preUserPage').hide();
                                $('#firstUserPage').hide();
                                $('#endUserPage').hide();
                                $('#nextUserPage').hide();
                            } else {
                                $('#nextUserPage').show();
                                $('#endUserPage').show();
                                $('#preUserPage').show();
                                $('#firstUserPage').show();

                            }
                        } else if (pageNow = totalPage) {
                            $('#endUserPage').hide();
                            $('#nextUserPage').hide();
                        }

                        $('#usertbody').html("");
                        for (var i in userList) {
                            var id = i * 1 + 1 * 1;
                            $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onclick='usercheckclick(this.id)'></td><td id='" + userList[i].uid + "uname" + "'>" + userList[i].uname + "</td> <td id='" + userList[i].uid + "upass" + "'>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + userList[i].urole + "</td> </tr>");
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
                        if (userList.length == 0) {
                            alert("该用户不存在");
                            $('#userselectvalue').val("")
                            return false;
                        }
                        $('#usertbody').html("");
                        for (var i in userList) {
                            var id = i * 1 + 1 * 1;
                            $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onclick='usercheckclick(this.id)'></td><td id='" + userList[i].uid + "uname" + "'>" + userList[i].uname + "</td> <td id='" + userList[i].uid + "upass" + "'>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + userList[i].urole + "</td> </tr>");
                            $('#totalUserPage').html(totalPage);
                        }
                    },
                    error: function (data) {
                        alert("查询失败");
                    }

                });
            }

            function selectReturnByvalue(pageNow, select, value) {
                var page = pageNow;
                var select = select;
                var value = value;
                $.ajax({
                    type: 'get',
                    url: '/oms/returned/getReturnedBySelect',
                    data: {
                        select: select,
                        value: value,
                        pageNow: page,
                        pageSize: 10
                    },
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        var returnedList = data.returnedModels;
                        var totalPage = data.totalPage;
                        if (totalPage==0){
                            alert("查询无结果");
                        }
                        $('#returnedBody').html("");
                        for (var i in returnedList) {
                            var id = i * 1 + 1 * 1;
                            $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox' onclick='checkboxreturneddis(this.id)' name='returnedcheck'  id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + returnedList[i].returnedorchange + "</td> <td>&nbsp;" + returnedList[i].returnedstatus + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                            $('#totalReturnedPage').html(totalPage);

                        }
                    },
                    error: function (data) {
                        alert("查询失败");
                    }

                });
            }

            $("#updateUserBut").click(
                function () {
                    $(".hbg").show();


                    var userIds = parseInt($("input:checkbox[name='usercheck']:checked").attr("id"));

                    var unameid = "#" + userIds + "uname";
                    var upassid = "#" + userIds + "upass";

                    var uname = $(unameid).html();
                    var upass = $(upassid).html();
                    $('#updateUserName').val(uname);
                    upass = upass.replace("&nbsp;", "");
                    $('#updateUserPassword').val(upass);
                    $('#updateusernamehidden').val(uname);
                    $('#updateupasshidden').val(upass);

                }
            );

        } else {


        }

        $('#adduserbutindex').click(
            function () {
                $(".hbg").show();

                $('#addUserName').val("");
                $('#addUserPassword').val("");
            }
        );

        window.onload = inGetGoodsNowPage($('#goodsPageNow').html());

        $('#nextGoodsPage').click(
            function () {

                var goodspage = parseInt($('#goodsPageNow').html().trim());
                var totalPage = parseInt($('#totalGoodPage').html().trim());
                if (goodspage < totalPage) {
                    if (goodspage * 1 + 1 * 1 == totalPage) {
                        $('#endGoodsPage').hide();
                        $('#nextGoodsPage').hide();
                    }
                    $('#preGoodsPage').show();
                    $('#firstGoodsPage').show();
                    inGetGoodsNowPage(goodspage * 1 + 1 * 1);
                    $('#goodsPageNow').html(goodspage * 1 + 1 * 1);
                } else if (goodspage == totalPage) {
                    alert("已到最后一页");
                } else if (goodspage > totalPage) {
                    alert('1');
                }


            }
        );


        $('#preGoodsPage').click(
            function () {

                var goodspage = parseInt($('#goodsPageNow').html().trim());

                if (goodspage > 1) {
                    if (goodspage == 2) {
                        $('#pregoodspage').hide();
                        $('#firstGoodsPage').hide();
                    }
                    $('#endGoodsPage').show();
                    $('#nextGoodsPage').show();
                    $('#goodsPageNow').html(goodspage * 1 - 1 * 1);
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
                $('#preGoodsPage').hide();
                $('#firstGoodsPage').hide();
                $('#endGoodsPage').show();
                $('#nextGoodsPage').show();
            }
        );

        $('#endGoodsPage').click(
            function () {
                $('#preGoodsPage').show();
                $('#firstGoodsPage').show();
                $('#endGoodsPagee').hide();
                $('#nextGoodsPage').hide();
                inGetGoodsNowPage($('#totalGoodPage').html());
                $('#goodsPageNow').html($('#totalGoodPage').html());
            }
        );


        function inGetGoodsNowPage(pageNow) {
            var page = pageNow;
            var pageSize = 15;
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

                    if (pageNow == 1) {
                        if (totalPage == 0 || totalPage == 1) {
                            $('#preGoodsPage').hide();
                            $('#firstGoodsPage').hide();
                            $('#endGoodsPage').hide();
                            $('#nextGoodsPage').hide();
                        } else {
                            $('#preGoodsPage').hide();
                            $('#firstGoodsPage').hide();
                            $('#nextGoodsPage').show();
                            $('#endGoodsPage').show();
                        }
                    } else if (pageNow < totalPage) {
                        if (totalPage == 0 || totalPage == 1) {
                            $('#preGoodsPage').hide();
                            $('#firstGoodsPage').hide();
                            $('#endGoodsPage').hide();
                            $('#nextGoodsPage').hide();
                        } else {
                            $('#nextGoodsPage').show();
                            $('#endGoodsPage').show();
                            $('#preGoodsPage').show();
                            $('#firstGoodsPage').show();

                        }
                    } else if (pageNow = totalPage) {
                        $('#endGoodsPage').hide();
                        $('#nextGoodsPage').hide();
                    }

                    $('#goodsbody').html("");
                    for (var i in goodsList) {
                        var id = i * 1 + 1 * 1;
                        $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                        $('#totalGoodPage').html(totalPage);

                    }
                },
                error: function (data) {
                    alert("获取商品列表失败");
                }

            });
        }

        function selectGoodsByValue(pageNow, value, select) {
            var page = pageNow;
            var username = value;
            var select = select;
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
                    if (goodsList.length == 0) {
                        alert("该商品不存在");
                    } else {
                        $('#goodsbody').html("");
                        for (var i in goodsList) {
                            var id = i * 1 + 1 * 1;
                            $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                            $('#totalGoodPage').html(totalPage);

                        }
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
                if(select=="请选择查询条件"){
                    alert("请选择查询条件");
                }else {
                    if (value.length == 0) {
                        inGetGoodsNowPage(1);
                    } else {
                        var zzbds2 = /^([\u4E00-\u9FA5]|\w)*$/;
                        if (!zzbds2.test(value)) {
                            alert("请不要输入特殊符号");
                        } else {

                            if (select == "按名称查询") {
                                selectGoodsByValue(1, value, "name");


                            } else if (select == "按商品编码查询") {
                                selectGoodsByValue(1, value, "id");
                            }
                            $('#preGoodsPage').hide();
                            $('#nextGoodsPage').hide();
                            $('#endGoodsPage').hide();
                        }
                    }
                }


            }
        );


        function inGetReturnedNowPage(pageNow) {
            var page = pageNow;
            var pageSizeR = 10;

            $.ajax({
                type: 'get',
                url: '/oms/returned/getAllReturned',
                data: {
                    pageNow: page,
                    pageSizeR: pageSizeR
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {

                    var returnedList = data.returnedModels;
                    var totalPage = data.totalPage;
                    if (pageNow == 1) {
                        if (totalPage == 0 || totalPage == 1) {

                            $('#preReturnedPage').hide();
                            $('#firstReturnedPage').hide();
                            $('#endReturnedPage').hide();
                            $('#nextReturnedPage').hide();
                        } else {

                            $('#preReturnedPage').hide();
                            $('#firstReturnedPage').hide();
                            $('#endReturnedPage').show();
                            $('#nextReturnedPage').show();
                        }
                    } else if (pageNow < totalPage) {
                        if (totalPage == 0 || totalPage == 1) {
                            $('#preReturnedPage').hide();
                            $('#firstReturnedPage').hide();
                            $('#endReturnedPage').hide();
                            $('#nextReturnedPage').hide();
                        } else {
                            $('#preReturnedPage').show();
                            $('#firstReturnedPage').show();
                            $('#endReturnedPage').show();
                            $('#nextReturnedPage').show();

                        }
                    } else if (pageNow = totalPage) {

                        $('#preReturnedPage').show();
                        $('#firstReturnedPage').show();
                        $('#endReturnedPage').hide();
                        $('#nextReturnedPage').hide();
                    }

                    $('#returnedBody').html("");
                    for (var i in returnedList) {
                        var id = i * 1 + 1 * 1;
                        $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='returnedcheck'  onclick='checkboxreturneddis(this.id)' id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + returnedList[i].returnedorchange + "</td> <td>&nbsp;" + returnedList[i].returnedstatus + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                        $('#totalReturnedPage').html(totalPage);
                        //

                    }


                },
                error: function (data) {
                    alert("获取退货单列表失败");
                }

            });
        }

        window.onload = inGetReturnedNowPage($('#returnedPageNow').html());

        $('#nextReturnedPage').click(
            function () {

                var returnedpage = $('#returnedPageNow').html();
                var totalPage = $('#totalReturnedPage').html();
                if (returnedpage < totalPage) {
                    if (returnedpage * 1 + 1 * 1 == totalPage){
                        $("#endReturnedPage").hide();
                        $("#nextReturnedPage").hide();
                    }
                    $("#preReturnedPage").show();
                    $("#firstReturnedPage").show();
                    inGetReturnedNowPage(returnedpage * 1 + 1 * 1);
                    $('#returnedPageNow').html(returnedpage * 1 + 1 * 1);

                } else {
                    alert("已到最后一页");
                }


            }
        );
        $('#preReturnedPage').click(
            function () {

                var returnedpage = $('#returnedPageNow').html();
                if (returnedpage > 1) {

                    if (returnedpage ==2){
                        $("#preReturnedPage").hide();
                        $("#firstReturnedPage").hide();
                    }
                    $("#endReturnedPage").show();
                    $("#nextReturnedPage").show();
                    $('#returnedPageNow').html(returnedpage * 1 - 1 * 1);
                    inGetReturnedNowPage(returnedpage * 1 - 1 * 1);

                } else {
                    alert("已到第一页");
                }
            }
        );


        $('#firstReturnedPage').click(
            function () {
                inGetReturnedNowPage(1);
                $('#returnedPageNow').html(1);
                $("#preReturnedPage").hide();
                $("#firstReturnedPage").hide();
                $("#endReturnedPage").show();
                $("#nextReturnedPage").show();



            }
        );

        $('#endReturnedPage').click(
            function () {

                inGetReturnedNowPage($('#totalReturnedPage').html());
                $("#preReturnedPage").show();
                $("#firstReturnedPage").show();
                $("#endReturnedPage").hide();
                $("#nextReturnedPage").hide();
                $('#returnedPageNow').html($('#totalReturnedPage').html());
            }
        );



        $('#nextreturnedGoodsPage').click(
            function () {

                var returnedGoodspage = $('#returnedGoodsPageNow').html();
                var totalPage = $('#totalreturnedGoodsPage').html();
                var returnedid = $('#returnedidongoods').html();
                if (returnedGoodspage < totalPage) {
                    if (returnedGoodspage * 1 + 1 * 1 == totalPage){
                        $('#endreturnedGoodsPage').hide();
                        $('#nextreturnedGoodsPage').hide();
                    }
                    $('#prereturnedGoodsPage').show();
                    $('#firstreturnedGoodsPage').show();
                    $('#returnedGoodsPageNow').html(returnedGoodspage * 1 + 1 * 1);
                    returngetgoodsfromserver(returnedid, returnedGoodspage * 1 + 1 * 1, 5);
                } else {
                    alert("已到最后一页");
                }

            }
        );
        $('#prereturnedGoodsPage').click(
            function () {
                var returnedid = $('#returnedidongoods').html();
                var returnedGoodsPageNow = $('#returnedGoodsPageNow').html();
                if (returnedGoodsPageNow > 1) {
                    if (returnedGoodsPageNow ==2){
                        $('#prereturnedGoodsPage').hide();
                        $('#firstreturnedGoodsPage').hide();
                    }
                    $('#endreturnedGoodsPage').show();
                    $('#nextreturnedGoodsPage').show();
                    $('#returnedGoodsPageNow').html(returnedGoodsPageNow * 1 - 1 * 1);
                    returngetgoodsfromserver(returnedid, returnedGoodsPageNow * 1 - 1 * 1, 5);

                } else {
                    alert("已到第一页");
                }
            }
        );


        $('#firstreturnedGoodsPage').click(
            function () {
                var returnedid = $('#returnedidongoods').html();
                $('#prereturnedGoodsPage').hide();
                $('#firstreturnedGoodsPage').hide();
                $('#endreturnedGoodsPage').show();
                $('#nextreturnedGoodsPage').show();
                returngetgoodsfromserver(returnedid, 1, 5);
                $('#returnedGoodsPageNow').html(1);
            }
        );

        $('#endreturnedGoodsPage').click(
            function () {
                $('#prereturnedGoodsPage').show();
                $('#firstreturnedGoodsPage').show();
                $('#endreturnedGoodsPage').hide();
                $('#nextreturnedGoodsPage').hide();
                var returnedid = $('#returnedidongoods').html();
                returngetgoodsfromserver(returnedid, $('#totalreturnedGoodsPage').html(), 5);
                $('#returnedGoodsPageNow').html($('#totalreturnedGoodsPage').html());
            }
        );

        $('#returnedDetailbut').click(
            function () {
                var returnIdArray = new Array();
                var i = 0;
                $("input:checkbox[name='returnedcheck']:checked").each(function () {
                    returnIdArray[i++] = parseInt($(this).attr("id"));
                });
                var returnIds = returnIdArray.join("/");
                window.open("/oms/returned/returnedDetail?id=" + returnIds);
            }
        );

        $('#checkreturnedorder').click(
            function () {
                var successCount = 0;
                var returnIdArray = new Array();
                var i = 0;
                $("input:checkbox[name='returnedcheck']:checked").each(function () {
                    returnIdArray[i++] = parseInt($(this).attr("id"));
                });
                if (returnIdArray.length == 0) {
                    alert("请勾选订单");
                    return false;
                } else {
                    var returnIds = returnIdArray.join("/");
                    var a = getreturnedStatus(returnIdArray, "待审核");

                    if (a == "yes") {
                        for (var j = 0; j < returnIdArray.length; j++) {
                            var id = returnIdArray[j];
                            $.ajax({
                                type: 'get',
                                url: '/oms/returned/createInBoundOrder',
                                data: {
                                    id: id,
                                },
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                success: function (data) {

                                    inGetReturnedNowPage(1);
                                }
                            });
                        }
                    } else {
                        alert('请选择待审核订单');
                        return false;
                    }

                }


            }
        );
        $("#cancelReturnedOrder").click(
            function () {
                var returnIdArray = new Array();
                var i = 0;
                $("input:checkbox[name='returnedcheck']:checked").each(function () {
                    returnIdArray[i++] = parseInt($(this).attr("id"));
                });
                if (returnIdArray.length == 0) {
                    alert("请勾选订单");
                    return false;
                } else {
                    var returnIds = returnIdArray.join("/");
                    var a = getreturnedStatus(returnIdArray, "待审核");
                    if (a == "yes") {
                        for (var j = 0; j < returnIdArray.length; j++) {
                            var id = returnIdArray[j];
                            $.ajax({
                                type: 'get',
                                url: '/oms/returned/cancelReturn',
                                data: {
                                    id: id,
                                },
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                success: function (data) {

                                    inGetReturnedNowPage(1);
                                },
                                error: function (data) {
                                    alert("取消异常");
                                    return false;
                                }

                            });

                        }
                        alert("取消订单成功");
                    } else {
                        alert('请选择待审核订单');
                        return false;
                    }

                }
            }
        );

        $("#returnedCreaterefoundOder").click(function () {
            //创建退款单
            var returnIdArray = new Array();
            var i = 0;
            var successCount = 0;
            var errorcount = 0;
            $("input:checkbox[name='returnedcheck']:checked").each(function () {
                returnIdArray[i++] = parseInt($(this).attr("id"));
            });
            if (returnIdArray.length == 0) {
                alert("请勾选订单");
                return false;
            } else {
                var returnIds = returnIdArray.join("/");
                var a = getreturnedStatus(returnIdArray, "收货成功");
                var returnOrChange = getreturnedOrChange(returnIdArray);
                if (a == "yes") {
                    if (returnOrChange == "return") {

                        for (var j = 0; j < returnIdArray.length; j++) {

                            var id = returnIdArray[j];

                            $.ajax({
                                type: 'get',
                                url: '/oms/returned/createRefoundOrder',
                                data: {
                                    id: id,
                                },
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                async :false,
                                success: function (data) {
                                    if (data.isSuccess > 0) {
                                        successCount++;

                                    } else if (data.isSuccess < 0) {
                                        errorcount++;

                                    }
                                },
                                error: function (data) {
                                    alert("生成退款单异常");
                                    return false;
                                }
                            });
                            inGetReturnedNowPage(1);
                        }
                        if (errorcount>0){
                            alert("退款单已创建，请不要重复提交，共" + returnIdArray.length + "条,成功" + successCount + "条");
                        }else if (successCount > 0) {
                            alert("创建完成,共" + returnIdArray.length + "条,成功" + successCount + "条");
                        }

                    } else {
                        alert('请选择退换货状态为‘return’的退货单');
                        return false;
                    }

                } else {
                    alert('请选择收货成功订单');
                    return false;
                }


            }


        });

        var changesuccessCount = 0;
        var changeErrorCount = 0;
        $("#changeOutBound").click(function () {
            //换货发货
            var changesuccessCount = 0;
            var changeErrorCount = 0;
            var returnIdArray = new Array();
            var i = 0;

            $("input:checkbox[name='returnedcheck']:checked").each(function () {
                returnIdArray[i++] = parseInt($(this).attr("id"));
            });
            if (returnIdArray.length == 0) {
                alert("请勾选订单");
                return false;
            } else {
                var returnIds = returnIdArray.join("/");
                var a = getreturnedStatus(returnIdArray, "收货成功");
                var returnOrChange = getreturnedOrChange(returnIdArray);
                if (a == "yes") {
                    if (returnOrChange == "change") {

                        for (var j = 0; j < returnIdArray.length; j++) {

                            var id = returnIdArray[j];

                            $.ajax({
                                type: 'get',
                                url: '/oms/returned/createOutBoundOrder',
                                data: {
                                    id: id,
                                },
                                contentType: "application/json; charset=utf-8",
                                dataType: "json",
                                async :false,
                                success: function (data) {
                                    if (data.data == "换货成功") {
                                        changesuccessCount =parseInt(changesuccessCount)+1;
                                    }else if (data.data == "-1"){
                                        changeErrorCount++;
                                    }
                                },
                                error: function (data) {

                                    return false;
                                }
                            });

                            inGetReturnedNowPage(1);
                        }
                        if (changeErrorCount>0){
                            alert("订单已换货，请不要重复提交，共" + returnIdArray.length + "条,成功" + changesuccessCount + "条");
                        }else if(changesuccessCount>0){
                            //if
                            alert("发货完成,共" + returnIdArray.length + "条,成功" + changesuccessCount + "条");
                        }


                    } else {
                        alert('请选择退换货状态为‘change’的退货单');
                        return false;
                    }

                } else {
                    alert('请选择收货成功订单');
                    return false;
                }


            }


        });

        $("#serarchReturnedOrder").click(
            function () {
                var select = $('#returnedselect').val().trim();
                var value = $('#returnValue').val().trim();
                if(select=="请选择查询条件"){
                    alert("请选择查询条件");
                }else{
                    if (value.length == 0) {

                        inGetReturnedNowPage(1);
                    } else {
                        var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                        if (!zzbds.test(value)) {
                            alert("请不要输入特殊符号");
                        } else {
                            selectReturnByvalue(1, select, value);

                        }
                    }
                }


            }
        );

        $("tbody").delegate("tr", "click", function () {
            $(this).addClass('changeColor') //为选中项添加高亮
                .siblings().removeClass('changeColor')//去除其他项的高亮形式
                .end();
        });


        $("tbody").on("click", "input[name='returnedcheck']",function () {
            var returnIdArray = new Array();
            var i = 0;

            $("input:checkbox[name='returnedcheck']:checked").each(function () {
                returnIdArray[i++] = parseInt($(this).attr("id"));
            });

            if (returnIdArray.length == 0){
                $('#checkreturnedorder').attr('disabled', "true");
                $('#cancelReturnedOrder').attr('disabled', "true");
                $('#returnedCreaterefoundOder').attr('disabled', "true");
                $('#changeOutBound').attr('disabled', "true");
            }else {
                var daishenghe = getreturnedStatus(returnIdArray,"待审核");
                var shouhuowancheng = getreturnedStatus(returnIdArray,"收货成功");
                var returnOrChange = getreturnedOrChange(returnIdArray);
                if (daishenghe == "yes"){

                    $('#checkreturnedorder').removeAttr("disabled");
                    $('#cancelReturnedOrder').removeAttr("disabled");
                } else {
                    $('#checkreturnedorder').attr('disabled', "true");
                    $('#cancelReturnedOrder').attr('disabled', "true");
                }
                if (shouhuowancheng == "yes"){
                    if (returnOrChange == "return"){
                        $('#returnedCreaterefoundOder').removeAttr("disabled");
                    }else if(returnOrChange =="change"){
                        $('#changeOutBound').removeAttr("disabled");
                    }
                }else {
                    $('#returnedCreaterefoundOder').attr('disabled', "true");
                    $('#changeOutBound').attr('disabled', "true");
                }
            }


        });
    });
