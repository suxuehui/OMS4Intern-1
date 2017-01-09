var userselectValue = "";
var userselectValuetemp = "";

var goodselectValue = "";
var goodselectValueTemp = "";

var goodselect = "id";
var goodselectTemp = "id";

var returnSelectValue = "";
var returnSelectValueTemp = "";

var returnSelect = "订单号";
var returnSelectTemp = "订单号";
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
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }


    });

}

//退货单详情
function showReturnDetail(id) {
    window.open("/oms/returned/returnedDetail?id=" + id);
}

//获得退货单状态
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
                alert("登录超时，请重新登录");
                window.location.href = "/oms/login/logout";
            }
        });
    }
    if (countStatus == returnIdArray.length) {
        return "yes";
    } else {

        return "no";
    }

}

//获取用户角色
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
                alert("登录超时，请重新登录");
                window.location.href = "/oms/login/logout";
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

//查询用户
function selectByUserName(pageNow) {
    var page = pageNow;
    var pageSize = 20;
    $.ajax({
        type: 'get',
        url: '/oms/user/selectUserByName',
        data: {
            username: userselectValue,
            nowPage: page,
            pageSize: pageSize
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var userList = data.userList;
            var totalPage = data.totalPage;
            var urole = data.urole;
            if(urole==2){
                alert("用户无此权限");
                window.location.href = "/oms/oms/index";
                return false;
            }
            if (totalPage == 0) {
                alert("查询无结果！");
                $('#userselectvalue').val("");
                userselectValue = userselectValuetemp;

                return false;
            } else {
                if (pageNow == 1) {
                    if (totalPage == 0 || totalPage == 1) {

                        $('#preUserPage').hide();
                        $('#firstUserPage').hide();
                        $('#endUserPage').hide();
                        $('#nextUserPage').hide();
                    } else {

                        $('#preUserPage').hide();
                        $('#firstUserPage').hide();
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
                    $('#preUserPage').show();
                    $('#firstUserPage').show();
                    $('#endUserPage').hide();
                    $('#nextUserPage').hide();
                }
                userselectValuetemp = userselectValue;
                $('#usertbody').html("");
                for (var i in userList) {
                    var id = i * 1 + 1 * 1;
                    $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onclick='usercheckclick(this.id)'></td><td id='" + userList[i].uid + "uname" + "'>" + userList[i].uname + "</td> <td id='" + userList[i].uid + "upass" + "'>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + userList[i].urole + "</td> </tr>");
                    $('#totalUserPage').html(totalPage);
                }
            }

        },
        error: function (data) {
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }

    });
}

//查询商品
function selectGoodsByValue(pageNow) {
    var page = pageNow;
    var pageSize = 20;
    $.ajax({
        type: 'get',
        url: '/oms/goods/selectGoods',
        data: {
            select: goodselect,
            value: goodselectValue,
            nowPage: page,
            pageSize: pageSize
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {

            var goodsList = data.goodsAndStatus;
            var totalPage = data.totalPage;

            if (totalPage == 0) {
                goodselectValue = goodselectValueTemp;
                goodselect = goodselectTemp;
                return false;
            } else {
                goodselectValueTemp = goodselectValue;
                goodselectTemp = goodselect;
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
                    $('#preGoodsPage').show();
                    $('#firstGoodsPage').show();
                    $('#endGoodsPage').hide();
                    $('#nextGoodsPage').hide();
                }

                $('#goodsbody').html("");
                for (var i in goodsList) {
                    var id = i * 1 + 1 * 1;
                    $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                    $('#totalGoodPage').html(totalPage);

                }
            }

        },
        error: function (data) {
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }

    });
}

function selectGoodsByValue2(pageNow) {
    var page = pageNow;
    var pageSize = 20;
    $.ajax({
        type: 'get',
        url: '/oms/goods/selectGoods',
        data: {
            select: goodselect,
            value: goodselectValue,
            nowPage: page,
            pageSize: pageSize
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {

            var goodsList = data.goodsAndStatus;
            var totalPage = data.totalPage;

            if (totalPage == 0) {
                goodselectValue = goodselectValueTemp;
                goodselect = goodselectTemp;
                alert("查询无结果！");
                return false;
            } else {
                goodselectValueTemp = goodselectValue;
                goodselectTemp = goodselect;
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
                    $('#preGoodsPage').show();
                    $('#firstGoodsPage').show();
                    $('#endGoodsPage').hide();
                    $('#nextGoodsPage').hide();
                }

                $('#goodsbody').html("");
                for (var i in goodsList) {
                    var id = i * 1 + 1 * 1;
                    $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                    $('#totalGoodPage').html(totalPage);

                }
            }

        },
        error: function (data) {
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }

    });
}

function selectReturnByvalue(pageNow) {
    var page = pageNow;

    $.ajax({
        type: 'get',
        url: '/oms/returned/getReturnedBySelect',
        data: {
            select: returnSelect,
            value: returnSelectValue,
            pageNow: page,
            pageSize: 10,
            async: false
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var returnedList = data.returnedModels;
            var totalPage = data.totalPage;
            $("#totalReturnedPage2").html(totalPage);
            if (totalPage == 0) {

                $("#totalReturnedPage2").html(0);
                returnSelect = returnSelectTemp;
                returnSelectValue = returnSelectValueTemp;
                return false;
            }
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

            returnSelectValueTemp = returnSelectValue;
            returnSelectTemp = returnSelect;
            $('#returnedBody').html("");
            for (var i in returnedList) {
                var id = i * 1 + 1 * 1;
                $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox' onclick='checkboxreturneddis(this.id)' name='returnedcheck'  id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + returnedList[i].returnedorchange + "</td> <td>&nbsp;" + returnedList[i].returnedstatus + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                $('#totalReturnedPage').html(totalPage);

            }

        },
        error: function (data) {
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }

    });
}

function selectReturnByvalue2(pageNow) {
    var page = pageNow;

    $.ajax({
        type: 'get',
        url: '/oms/returned/getReturnedBySelect',
        data: {
            select: returnSelect,
            value: returnSelectValue,
            pageNow: page,
            pageSize: 10,
            async: false
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            var returnedList = data.returnedModels;
            var totalPage = data.totalPage;
            $("#totalReturnedPage2").html(totalPage);
            if (totalPage == 0) {

                alert("查询无结果!");

                $("#totalReturnedPage2").html(0);
                returnSelect = returnSelectTemp;
                returnSelectValue = returnSelectValueTemp;
                return false;
            }
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

            returnSelectValueTemp = returnSelectValue;
            returnSelectTemp = returnSelect;
            $('#returnedBody').html("");
            for (var i in returnedList) {
                var id = i * 1 + 1 * 1;
                $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox' onclick='checkboxreturneddis(this.id)' name='returnedcheck'  id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + returnedList[i].returnedorchange + "</td> <td>&nbsp;" + returnedList[i].returnedstatus + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                $('#totalReturnedPage').html(totalPage);

            }

        },
        error: function (data) {
            alert("登录超时，请重新登录");
            window.location.href = "/oms/login/logout";
        }

    });
}

$(
    function () {

        $('#prereturnedGoodsPage').hide();
        $('#firstreturnedGoodsPage').hide();
        $('#endreturnedGoodsPage').hide();
        $('#nextreturnedGoodsPage').hide();

        var returnIdArray = new Array();
        $("input:checkbox[name='returnedcheck']:checked").each(function () {
            returnIdArray[i++] = parseInt($(this).attr("id"));
        });

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
            //window.onload = inGetUserNowPage($('#userPageNow').html());
            window.onload = selectByUserName(1);
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
                        //inGetUserNowPage();
                        selectByUserName(userpage * 1 + 1 * 1);

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
                        //inGetUserNowPage(userpage * 1 - 1 * 1);
                        selectByUserName(userpage * 1 - 1 * 1);
                    } else {

                        alert("已到第一页");
                    }
                }
            );


            $('#firstUserPage').click(
                function () {
                    //inGetUserNowPage(1);
                    selectByUserName(1);
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
                    //inGetUserNowPage($('#totalUserPage').html());
                    selectByUserName($('#totalUserPage').html());
                    $('#userPageNow').html($('#totalUserPage').html());
                }
            );


            //删除用户
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
                                if(data==-5){
                                    alert("用户无此权限");
                                    window.location.href = "/oms/oms/index";
                                    return false;
                                }
                                if (data > 0) {
                                    //inGetUserNowPage(1);
                                    selectByUserName(1);
                                    alert("删除成功");
                                } else if (data = 0) {
                                    alert("删除失败");
                                } else if (data = -1) {
                                    alert("不可以删除自己");
                                }
                            },
                            error: function (data) {
                                alert("登录超时，请重新登录");
                                window.location.href = "/oms/login/logout";
                                selectByUserName(1);
                            }

                        });
                    }
                });

            //添加用户
            $("#addUser").click(function () {
                $(".loading").hide();
                $(".hbg").show();
                var username = $('#addUserName').val().trim();
                var password = $('#addUserPassword').val().trim();
                if (username == '') {
                    $("#addUserMsg").html("请输入用户名");
                    $(".loading").hide();
                } else {
                    if (password == '') {
                        $("#addUserMsg").html("请输入密码");
                        $(".loading").hide();
                    } else {
                        if (password.length < 6 || password.length > 15) {
                            $("#addUserMsg").html("请输入6-15位密码");
                            $(".loading").hide();
                        } else {
                            var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                            if (!zzbds.test(username)) {
                                $("#addUserMsg").html("请输入有效用户名");
                                $(".loading").hide();
                            } else {
                                if (!zzbds.test(password)) {
                                    $("#addUserMsg").html("请输入有效密码");
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
                                            if(data==-5){
                                                alert("用户无此权限");
                                                window.location.href = "/oms/oms/index";
                                                return false;
                                            }
                                            if (data == 1) {
                                                $(".loading").hide();
                                                $(".hbg").hide();
                                                alert("添加成功");
                                                $("#addUserwindows").hide();

                                                $('#addUserName').val("");
                                                $('#addUserPassword').val("");
                                                //inGetUserNowPage(1);
                                                selectByUserName(1);

                                            } else {

                                                alert("用户名已存在");

                                                $('#addUserName').val("");
                                                $('#addUserPassword').val("");


                                            }
                                        },
                                        error: function (data) {

                                            alert("登录超时，请重新登录");
                                            window.location.href = "/oms/login/logout";
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


            //修改用户
            $("#updateUser").click(function () {
                $(".loading").hide();
                $(".hbg").show();
                var username = $('#updateUserName').val().trim();
                var password = $('#updateUserPassword').val().trim();
                var preusername = $('#updateusernamehidden').val().trim();
                var prepassword = $('#updateupasshidden').val().trim();
                if (username == preusername && password == prepassword) {
                    $("#updateUserMsg").html("与原用户信息一致，请重新修改");
                    $(".loading").hide();
                } else {
                    var userIdArray = new Array();
                    var i = 0;
                    if (username == '') {
                        $("#updateUserMsg").html("请输入用户名");
                        $(".loading").hide();
                    } else {
                        if (password == '') {
                            $("#updateUserMsg").html("请输入密码");
                            $(".loading").hide();
                        } else {
                            if (password.length < 6 || password.length > 15) {
                                $("#updateUserMsg").html("请输入6-15位密码");
                                $(".loading").hide();
                            } else {
                                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                                if (!zzbds.test(username)) {
                                    $("#updateUserMsg").html("请输入有效用户名");
                                    $(".loading").hide();
                                } else {
                                    if (!zzbds.test(password)) {
                                        $("#updateUserMsg").html("请输入有效密码");
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
                                                if(data==-5){
                                                    alert("用户无此权限");
                                                    window.location.href = "/oms/oms/index";
                                                    return false;
                                                }
                                                if (data == 1) {
                                                    alert("修改成功");
                                                    $(".loading").hide();
                                                    $(".hbg").hide();
                                                    $("#updateUserBound").hide();
                                                    $('#updateUserName').val("");
                                                    $('#updateUserPassword').val("");
                                                    document.getElementById("updateUserBut").disabled = true;
                                                    //inGetUserNowPage(1);
                                                    selectByUserName(1);

                                                } else if (data == -1) {
                                                    alert("修改成功请重新登陆");
                                                    $('#updateUserName').val("");
                                                    $('#updateUserPassword').val("");

                                                    $(".loading").hide();
                                                    window.location.href = "/oms/login/logout";
                                                } else {

                                                    $("#updateUserMsg").html("用户名已存在");
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
                                                alert("登录超时，请重新登录");
                                                window.location.href = "/oms/login/logout";
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

            //查询用户
            $('#userselectbutton').click(function () {
                $("#userPageNow").html(1);
                userselectValue = $("#userselectvalue").val().trim();
                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                if (!zzbds.test(userselectValue)) {
                    alert("请不要输入特殊符号");
                } else {
                    selectByUserName(1);

                }
            });


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

        window.onload = selectGoodsByValue($('#goodsPageNow').html());

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
                    selectGoodsByValue(goodspage * 1 + 1 * 1);
                    $('#goodsPageNow').html(goodspage * 1 + 1 * 1);
                } else if (goodspage == totalPage) {
                    alert("已到最后一页");
                } else if (goodspage > totalPage) {
                    alert('2');
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
                    selectGoodsByValue(goodspage * 1 - 1 * 1);
                } else {
                    alert("已到第一页");
                }
            }
        );


        $('#firstGoodsPage').click(
            function () {
                $('#goodsPageNow').html(1);
                selectGoodsByValue(1);
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
                selectGoodsByValue($('#totalGoodPage').html());
                $('#goodsPageNow').html($('#totalGoodPage').html());
            }
        );


        $('#selectgoodsbut').click(
            function () {
                $("#goodsPageNow").html(1);
                var select = $('#selectGoodssle').val().trim();
                goodselectValue = $('#goodsvaluetxt').val().trim();
                if (select == "请选择查询条件") {
                    alert("请选择查询条件");
                } else {

                    var zzbds2 = /^([\u4E00-\u9FA5]|\w)*$/;
                    if (!zzbds2.test(goodselectValue)) {
                        alert("请不要输入特殊符号");
                    } else {

                        if (select == "按名称查询") {
                            goodselect = "name";

                            selectGoodsByValue2(1);


                        } else if (select == "按商品编码查询") {
                            goodselect = "id";
                            selectGoodsByValue(1);
                        }

                    }

                }


            }
        );


        window.onload = selectReturnByvalue($('#returnedPageNow').html());

        $('#nextReturnedPage').click(
            function () {

                var returnedpage = $('#returnedPageNow').html();
                var totalPage = $('#totalReturnedPage').html();
                if (returnedpage < totalPage) {
                    if (returnedpage * 1 + 1 * 1 == totalPage) {
                        $("#endReturnedPage").hide();
                        $("#nextReturnedPage").hide();
                    }
                    $("#preReturnedPage").show();
                    $("#firstReturnedPage").show();
                    selectReturnByvalue(returnedpage * 1 + 1 * 1);
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

                    if (returnedpage == 2) {
                        $("#preReturnedPage").hide();
                        $("#firstReturnedPage").hide();
                    }
                    $("#endReturnedPage").show();
                    $("#nextReturnedPage").show();
                    $('#returnedPageNow').html(returnedpage * 1 - 1 * 1);
                    selectReturnByvalue(returnedpage * 1 - 1 * 1);

                } else {
                    alert("已到第一页");
                }
            }
        );


        $('#firstReturnedPage').click(
            function () {
                selectReturnByvalue(1);
                $('#returnedPageNow').html(1);
                $("#preReturnedPage").hide();
                $("#firstReturnedPage").hide();
                $("#endReturnedPage").show();
                $("#nextReturnedPage").show();


            }
        );

        $('#endReturnedPage').click(
            function () {

                selectReturnByvalue($('#totalReturnedPage').html());
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
                    if (returnedGoodspage * 1 + 1 * 1 == totalPage) {
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
                    if (returnedGoodsPageNow == 2) {
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

        //退款单详细页面
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

        //审核
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

                                    selectReturnByvalue(1);
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

        //取消订单
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

                                    selectReturnByvalue(1);
                                },
                                error: function (data) {
                                    alert("登录超时，请重新登录");
                                    window.location.href = "/oms/login/logout";
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

        //创建退款单
        $("#returnedCreaterefoundOder").click(function () {

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
                                async: false,
                                success: function (data) {
                                    if (data.isSuccess > 0) {
                                        successCount++;

                                    } else if (data.isSuccess < 0) {
                                        errorcount++;

                                    }
                                },
                                error: function (data) {
                                    alert("登录超时，请重新登录");
                                    window.location.href = "/oms/login/logout";
                                    return false;
                                }
                            });
                            selectReturnByvalue(1);
                        }
                        if (errorcount > 0) {
                            alert("退款单已创建，请不要重复提交，共" + returnIdArray.length + "条,成功" + successCount + "条");
                        } else if (successCount > 0) {
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

        //换货发货
        $("#changeOutBound").click(function () {

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
                                async: false,
                                success: function (data) {
                                    if (data.data == "换货成功") {
                                        changesuccessCount = parseInt(changesuccessCount) + 1;
                                    } else if (data.data == "-1") {
                                        changeErrorCount++;
                                    }
                                },
                                error: function (data) {
                                    alert("登录超时，请重新登录");
                                    window.location.href = "/oms/login/logout";
                                    return false;
                                }
                            });


                        }
                        if (changeErrorCount > 0) {
                            alert("订单已换货，请不要重复提交，共" + returnIdArray.length + "条,成功" + changesuccessCount + "条");
                        } else if (changesuccessCount > 0) {
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

        //退款单查询按钮点击事件
        $("#serarchReturnedOrder").click(
            function () {
                $("#returnedPageNow").html(1);
                returnSelect = $('#returnedselect').val().trim();
                returnSelectValue = $('#returnValue').val().trim();
                if (returnSelect == "请选择查询条件") {
                    alert("请选择查询条件");
                } else {

                    var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                    if (!zzbds.test(returnSelectValue)) {
                        alert("请不要输入特殊符号");
                    } else {

                        selectReturnByvalue2(1);


                    }

                }


            }
        );

        //选择高亮
        $("tbody").delegate("tr", "click", function () {
            $(this).addClass('changeColor') //为选中项添加高亮
                .siblings().removeClass('changeColor')//去除其他项的高亮形式
                .end();
        });

        //退货单按钮控制
        $("tbody").on("click", "input[name='returnedcheck']", function () {
            var returnIdArray = new Array();
            var i = 0;

            $("input:checkbox[name='returnedcheck']:checked").each(function () {
                returnIdArray[i++] = parseInt($(this).attr("id"));
            });

            if (returnIdArray.length == 0) {
                $('#checkreturnedorder').attr('disabled', "true");
                $('#cancelReturnedOrder').attr('disabled', "true");
                $('#returnedCreaterefoundOder').attr('disabled', "true");
                $('#changeOutBound').attr('disabled', "true");
            } else {
                var daishenghe = getreturnedStatus(returnIdArray, "待审核");
                var shouhuowancheng = getreturnedStatus(returnIdArray, "收货成功");
                var returnOrChange = getreturnedOrChange(returnIdArray);
                if (daishenghe == "yes") {

                    $('#checkreturnedorder').removeAttr("disabled");
                    $('#cancelReturnedOrder').removeAttr("disabled");
                } else {
                    $('#checkreturnedorder').attr('disabled', "true");
                    $('#cancelReturnedOrder').attr('disabled', "true");
                }
                if (shouhuowancheng == "yes") {
                    if (returnOrChange == "return") {
                        $('#returnedCreaterefoundOder').removeAttr("disabled");
                        $('#changeOutBound').attr('disabled', "true");
                    } else if (returnOrChange == "change") {
                        $('#changeOutBound').removeAttr("disabled");
                        $('#returnedCreaterefoundOder').attr('disabled', "true");
                    } else if (returnOrChange = "error") {
                        $('#returnedCreaterefoundOder').attr('disabled', "true");
                        $('#changeOutBound').attr('disabled', "true");
                    }
                } else {
                    $('#returnedCreaterefoundOder').attr('disabled', "true");
                    $('#changeOutBound').attr('disabled', "true");
                }
            }


        });


        $("#returnListBut").click(
            function () {
                returnSelectValue = "";
                returnSelectValueTemp = "";
                returnSelect = "订单号";
                returnSelectTemp = "订单号";
                $("#returnValue").val("");
                $("#returnedselect").val("请选择查询条件");
                selectReturnByvalue(1);
            }
        );

        $("#userListbut").click(
            function () {
                userselectValue = "";
                userselectValuetemp = "";
                $("#userselectvalue").val("");
                selectByUserName(1);
            }
        );

        $("#userListSonbut").click(
            function () {
                userselectValue = "";
                userselectValuetemp = "";
                $("#userselectvalue").val("");
                selectByUserName(1);
            }
        );

        $("#goodsListbut").click(
            function () {
                $("#selectGoodssle").val("请选择查询条件");
                $("#goodsvaluetxt").val("");
                goodselectValue = "";
                goodselectValueTemp = "";

                goodselect = "id";
                goodselectTemp = "id";
                selectGoodsByValue(1);
            });

        $("#goodListSonBut").click(
            function () {
                $("#selectGoodssle").val("请选择查询条件");
                $("#goodsvaluetxt").val("");
                goodselectValue = "";
                goodselectValueTemp = "";

                goodselect = "id";
                goodselectTemp = "id";
                selectGoodsByValue(1);
            }
        );

    });
