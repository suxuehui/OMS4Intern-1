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


//用户列表界面：复选框点击事件控制按钮是否失效
function usercheckclick() {
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

//退货单界面：复选框点击事件控制退款单详情按钮是否失效
function checkboxreturneddis() {

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

//退货单号点击事件：显示此退货单商品
function returnedGetGoods(returnedid) {


    var returnedid2 = returnedid;
    var pageNow = $("#returnedPageNow").html();
    var pageSize = 5;

    returngetgoodsfromserver(returnedid2, pageNow, pageSize);


}

//根据退货单号获取商品信息
function returngetgoodsfromserver(returnedid, pageNow, pageSize) {
    $('#prereturnedGoodsPage').hide();
    $('#firstreturnedGoodsPage').hide();
    $('#endreturnedGoodsPage').hide();
    $('#nextreturnedGoodsPage').hide();
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
                if (returnedSonList.hasOwnProperty(i)) {
                    $('#returnedGoodsBody').append("<tr class='a'><td><input type='checkbox'  name='returnedsoncheck'   id='" + returnedSonList[i].goodsno + "returnedson" + "'></td><td><a id='" + returnedSonList[i].goodsno + "'>" + returnedSonList[i].goodsno + "</a></td> <td>&nbsp;" + returnedSonList[i].goodsname + "</td> <td>&nbsp;" + returnedSonList[i].goodnum + "</td> <td>&nbsp;" + returnedSonList[i].goodsprice + "</td></tr>");
                    $('#totalreturnedGoodsPage').html(totalPage);
                }
            }
            $("#returnedidongoods").html(returnedid);
        }
    });

}

//退货单详情
function showReturnDetail(id) {
    window.open("/oms/returned/returnedDetail?id=" + id);
}

//获得退货单状态
function getreturnedStatus(returnIdArray, statusp) {

    var countStatus = 0;
    var jsonStr = {"returnIds": returnIdArray, "status": statusp};

    $.ajax({
        type: 'post',
        url: '/oms/returned/getReturnedStatus',
        data: {
            json: JSON.stringify(jsonStr)
        },
        async: false,//同步
        dataType: "json",
        success: function (data) {
            countStatus = data.success;
        }
    });

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

//根据退货单id数组判断一批退货单全是return，或全是change，或都有
function getreturnedOrChange(returnIdArray) {
    var returncount = 0;
    var jsonStr = {"returnIds": returnIdArray};

    $.ajax({
        type: 'post',
        url: '/oms/returned/getReturnedOrChange',
        data: {
            json: JSON.stringify(jsonStr)
        },
        async: false,//同步
        dataType: "json",
        success: function (data) {
            returncount = data.success;
        }
    });


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
    $('#preUserPage').hide();
    $('#firstUserPage').hide();
    $('#endUserPage').hide();
    $('#nextUserPage').hide();
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
            if (urole == 2) {
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

                pageShowUser(pageNow, totalPage);//

                userselectValuetemp = userselectValue;
                $('#usertbody').html("");
                var adminstr = "管理员";
                var userstr = "普通用户";
                for (var i in userList) {
                    if (userList.hasOwnProperty(i)) {
                        var id = i * 1 + 1 * 1;
                        var urolestr = parseInt(userList[i].urole) == 1 ? adminstr : userstr;
                        $('#usertbody').append("<tr><td>" + id + "</td><td><input type='checkbox' id='" + userList[i].uid + "user" + "' name='usercheck' onclick='usercheckclick()'></td><td id='" + userList[i].uid + "uname" + "'>" + userList[i].uname + "</td> <td id='" + userList[i].uid + "upass" + "'>&nbsp;" + userList[i].upassword + "</td> <td>&nbsp;" + urolestr + "</td> </tr>");
                        $('#totalUserPage').html(totalPage);
                    }
                }
            }

        }

    });
}

//查询商品，用于刷新显示
function selectGoodsByValue(pageNow) {
    $('#preGoodsPage').hide();
    $('#firstGoodsPage').hide();
    $('#endGoodsPage').hide();
    $('#nextGoodsPage').hide();
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

                pageShowGoods(pageNow, totalPage);//分页按钮显示控制

                $('#goodsbody').html("");
                for (var i in goodsList) {
                    if (goodsList.hasOwnProperty(i)) {
                        var id = i * 1 + 1 * 1;
                        $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                        $('#totalGoodPage').html(totalPage);
                    }
                }
            }

        }

    });
}

//条件查询商品，用于查询
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
                pageShowGoods(pageNow, totalPage);//分页按钮显示控制

                $('#goodsbody').html("");
                for (var i in goodsList) {
                    if (goodsList.hasOwnProperty(i)) {
                        var id = i * 1 + 1 * 1;
                        $('#goodsbody').append("<tr><td>" + id + "</td><td><input type='checkbox'  name='goodscheck' ></td><td><a>" + goodsList[i].goodsno + "</a></td> <td>&nbsp;" + goodsList[i].goodsname + "</td> <td>&nbsp;" + goodsList[i].goodsvnum + "</td> <td>&nbsp;" + goodsList[i].booknum + "</td>  <td>&nbsp;" + goodsList[i].goodstolnum + "</td><td>&nbsp;" + goodsList[i].goodsprice + "</td></tr>");
                        $('#totalGoodPage').html(totalPage);
                    }
                }
            }

        }

    });
}

//查询退款单，用于刷新
function selectReturnByvalue(pageNow) {
    var page = pageNow;
    $('#preReturnedPage').hide();
    $('#firstReturnedPage').hide();
    $('#endReturnedPage').hide();
    $('#nextReturnedPage').hide();
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

            pageShowReturned(pageNow, totalPage);

            returnSelectValueTemp = returnSelectValue;
            returnSelectTemp = returnSelect;
            var returnstr = "退货";
            var changestr = "换货";
            var returnstrEng = "return";
            $('#returnedBody').html("");
            for (var i in returnedList) {
                if (returnedList.hasOwnProperty(i)) {
                    var id = i * 1 + 1 * 1;
                    var a = returnedList[i].returnedorchange == returnstrEng ? returnstr : changestr;
                    $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox' onclick='checkboxreturneddis()' name='returnedcheck'  id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + a + "</td> <td>&nbsp;" + returnedStatusToCN(returnedList[i].returnedstatus) + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                    $('#totalReturnedPage').html(totalPage);
                }
            }

        }

    });
}

//条件查询退款单，用于条件查询
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

            pageShowReturned(pageNow, totalPage);

            returnSelectValueTemp = returnSelectValue;
            returnSelectTemp = returnSelect;
            var returnstr = "退货";
            var changestr = "换货";
            var returnstrEng = "return";
            $('#returnedBody').html("");
            for (var i in returnedList) {
                if (returnedListt.hasOwnProperty(i)) {
                    var id = i * 1 + 1 * 1;
                    var a = returnedList[i].returnedorchange == returnstrEng ? returnstr : changestr;
                    $('#returnedBody').append("<tr><td>" + id + "</td><td><input type='checkbox' onclick='checkboxreturneddis(this.id)' name='returnedcheck'  id='" + returnedList[i].id + "returned" + "'></td><td><a id='" + returnedList[i].returnedid + "' onclick='returnedGetGoods(this.id)' ondblclick='showReturnDetail(" + returnedList[i].id + ")' '>" + returnedList[i].returnedid + "</a></td> <td>&nbsp;" + a + "</td> <td>&nbsp;" + returnedStatusToCN(returnedList[i].returnedstatus) + "</td> <td>&nbsp;" + returnedList[i].oid + "</td>  <td>&nbsp;" + returnedList[i].channeloid + "</td><td>&nbsp;" + returnedList[i].returnedmoney + "</td><td>&nbsp;" + returnedList[i].createtime + "</td><td>&nbsp;" + returnedList[i].modifytime + "</td><td>&nbsp;" + returnedList[i].modifyman + "</td></tr>");
                    $('#totalReturnedPage').html(totalPage);
                }
            }

        }

    });
}

//将退货单状态显示为中文
function returnedStatusToCN(returnedStatus) {

    var statusArr = ["", "待审核", "审核通过", "审核失败", "等待收货", "收货成功", "换货失败", "换货取消", "退货失败", "退货取消","收货失败"];
    if (returnedStatus == "") {
        returnedStatus = "0";
    }
    return statusArr[parseInt(returnedStatus)];

}

function pageShowUser(pageNow, totalPage) {
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
}

function pageShowGoods(pageNow, totalPage) {

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
}

function selectGoods() {
    $("#goodsPageNow").html(1);
    var select = $('#selectGoodssle').val().trim();
    goodselectValue = $('#goodsvaluetxt').val().trim();
    if (select == "请选择查询条件") {
        alert("请选择查询条件");
    } else {
        if (goodselectValue.indexOf("%") >= 0) {
            alert("请不要输入%");
        } else {

            if (select == "按名称查询") {
                goodselect = "name";

                selectGoodsByValue2(1);
                $("#goodsPageNow").html(1);

            } else if (select == "按商品编码查询") {
                goodselect = "id";
                selectGoodsByValue2(1);
                $("#goodsPageNow").html(1);
            }

        }

    }
}

function selectReturned() {
    $("#returnedPageNow").html(1);
    returnSelect = $('#returnedselect').val().trim();
    returnSelectValue = $('#returnValue').val().trim();
    if (returnSelect == "请选择查询条件") {
        alert("请选择查询条件");
    } else {

        if (returnSelectValue.indexOf("%") >= 0) {
            alert("请不要输入%");
        } else {

            selectReturnByvalue2(1);
            $('#returnedPageNow').html(1);

        }
    }
}

function pageShowReturned(pageNow, totalPage) {
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
}

function addUserAjax(username, password) {
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
            if (data == -5) {
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
                selectByUserName(1);
                $("#userPageNow").html("1");

            } else {

                alert("用户名已存在");

                $('#addUserName').val("");
                $('#addUserPassword').val("");


            }
        }

    });
}
function tipsRefound(errorcount,returnIdArray,successCount){
    if (errorcount > 0) {
        alert("退款单已创建，请不要重复提交，共" + returnIdArray.length + "条,成功" + successCount + "条");
    } else if (successCount > 0) {
        alert("创建完成,共" + returnIdArray.length + "条,成功" + successCount + "条");
    }
}


$(
    function () {

        $('#preUserPage').hide();
        $('#firstUserPage').hide();
        $('#endUserPage').hide();
        $('#nextUserPage').hide();

        $('#prereturnedGoodsPage').hide();
        $('#firstreturnedGoodsPage').hide();
        $('#endreturnedGoodsPage').hide();
        $('#nextreturnedGoodsPage').hide();

        $('#preGoodsPage').hide();
        $('#firstGoodsPage').hide();
        $('#endGoodsPage').hide();
        $('#nextGoodsPage').hide();

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

            }
        );


        var urole = getUserRole();

        if (parseInt(urole) == 1) {

            window.onload = selectByUserName(1);
            $("#userPageNow").html("1");
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
                        selectByUserName(userpage * 1 - 1 * 1);

                    } else {

                        alert("已到第一页");
                    }
                }
            );


            $('#firstUserPage').click(
                function () {
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
                                if (data == -5) {
                                    alert("用户无此权限");
                                    window.location.href = "/oms/oms/index";
                                    return false;
                                }
                                if (data > 0) {
                                    selectByUserName(1);
                                    $('#userPageNow').html(1);
                                    alert("删除成功");
                                } else if (data == 0) {
                                    alert("删除失败");
                                } else if (data == -1) {
                                    alert("不可以删除自己");
                                }
                            }
                        });
                    }
                });

            //添加用户
            $("#addUser").click(function () {
                $(".loading").show();
                $(".hbg").show();
                var username = $('#addUserName').val().trim();
                var password = $('#addUserPassword').val().trim();
                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                if (username == '') {
                    $("#addUserMsg").html("请输入用户名");
                    $(".loading").hide();
                } else if (password == '') {
                    $("#addUserMsg").html("请输入密码");
                    $(".loading").hide();
                } else if (password.length < 6 || password.length > 15) {
                    $("#addUserMsg").html("请输入6-15位密码");
                    $(".loading").hide();
                } else if (!zzbds.test(username)) {
                    $("#addUserMsg").html("请输入有效用户名");
                    $(".loading").hide();
                } else if (!zzbds.test(password)) {
                    $("#addUserMsg").html("请输入有效密码");
                    $(".loading").hide();
                } else {
                    addUserAjax(username, password);

                }
            });


            //修改用户
            $("#updateUser").click(function () {
                $(".loading").show();
                $(".hbg").show();
                var username = $('#updateUserName').val().trim();
                var password = $('#updateUserPassword').val().trim();
                var preusername = $('#updateusernamehidden').val().trim();
                var prepassword = $('#updateupasshidden').val().trim();
                var userIdArray = new Array();
                var i = 0;
                var zzbds = /^([\u4E00-\u9FA5]|\w)*$/;
                if (username == preusername && password == prepassword) {
                    $("#updateUserMsg").html("与原用户信息一致，请重新修改");
                    $(".loading").hide();
                } else if (username == '') {
                    $("#updateUserMsg").html("请输入用户名");
                    $(".loading").hide();
                } else if (password == '') {
                    $("#updateUserMsg").html("请输入密码");
                    $(".loading").hide();
                } else if (password.length < 6 || password.length > 15) {
                    $("#updateUserMsg").html("请输入6-15位密码");
                    $(".loading").hide();
                } else if (!zzbds.test(username)) {
                    $("#updateUserMsg").html("请输入有效用户名");
                    $(".loading").hide();
                } else if (!zzbds.test(password)) {
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
                            if (data == -5) {
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
                                selectByUserName(1);
                                $("#userPageNow").html("1");

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
                        }

                    });
                }
            });

            //查询用户
            $('#userselectbutton').click(function selectUser2() {
                $("#userPageNow").html(1);
                userselectValue = $("#userselectvalue").val().trim();

                if (userselectValue.indexOf("%") >= 0) {
                    alert("请不要输入%");
                } else {
                    selectByUserName(1);
                    $("#userPageNow").html("1");

                }
            });

            $('#userselectvalue').bind('keypress', function (event) {

                if (event.keyCode == "13") {
                    $("#userPageNow").html(1);
                    userselectValue = $("#userselectvalue").val().trim();
                    if (userselectValue.indexOf("%") >= 0) {
                        alert("请不要输入%");
                    } else {
                        selectByUserName(1);
                        $("#userPageNow").html("1");

                    }
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

            $('#adduserbutindex').click(
                function () {
                    $(".hbg").show();

                    $('#addUserName').val("");
                    $('#addUserPassword').val("");
                }
            );

        }


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
                selectGoods();
            }
        );


        $('#goodsvaluetxt').bind('keypress', function (event) {

            if (event.keyCode == "13") {
                selectGoods();
            }

        });

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

                var returnIdArray = new Array();
                var i = 0;
                var success = 0;
                $("input:checkbox[name='returnedcheck']:checked").each(function () {
                    returnIdArray[i++] = parseInt($(this).attr("id"));
                });
                if (returnIdArray.length == 0) {
                    alert("请勾选订单");
                    return false;
                } else {
                    var a = getreturnedStatus(returnIdArray, "1");
                    if (a == "yes") {
                        var jsonStr = {"returnIds": returnIdArray};
                        $.ajax({
                            type: 'post',
                            url: '/oms/returned/createInBoundOrder',
                            data: {
                                json: JSON.stringify(jsonStr)
                            },
                            async: false,//同步
                            dataType: "json",
                            success: function (data) {
                                success = data.success;
                            }
                        });
                        selectReturnByvalue(1);
                        $('#returnedPageNow').html(1);
                        alert("审核成功，共" + returnIdArray.length + "条，成功" + success + "条。");
                    } else {
                        alert('请选择待审核订单');
                        return false;
                    }
                }
                $('#checkreturnedorder').attr('disabled', "true");
            }
        );

        //取消订单
        $("#cancelReturnedOrder").click(
            function () {
                var returnIdArray = new Array();
                var i = 0;
                var success = 0
                $("input:checkbox[name='returnedcheck']:checked").each(function () {
                    returnIdArray[i++] = parseInt($(this).attr("id"));
                });
                if (returnIdArray.length == 0) {
                    alert("请勾选订单");
                    return false;
                } else {
                    var a = getreturnedStatus(returnIdArray, "1");
                    if (a == "yes") {
                        var jsonStr = {"returnIds": returnIdArray};
                        $.ajax({
                            type: 'post',
                            url: '/oms/returned/cancelReturn',
                            data: {
                                json: JSON.stringify(jsonStr)
                            },
                            async: false,//同步
                            dataType: "json",
                            success: function (data) {
                                success = data.success;
                            }

                        });
                        selectReturnByvalue(1);
                        $('#returnedPageNow').html(1);
                        alert("取消订单成功，共" + returnIdArray.length + "条，成功" + success + "条。");
                    } else {
                        alert('请选择待审核订单');
                        return false;
                    }

                }
                $('#cancelReturnedOrder').attr('disabled', "true");
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

                var a = getreturnedStatus(returnIdArray, "5");
                var returnOrChange = getreturnedOrChange(returnIdArray);

                if (a == "yes") {
                    if (returnOrChange == "return") {
                        var jsonStr = {"returnIds": returnIdArray};

                        $.ajax({
                            type: 'post',
                            url: '/oms/returned/createRefoundOrder',
                            data: {
                                json: JSON.stringify(jsonStr)
                            },
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                successCount = data.success;
                                errorcount = data.exception;
                            }
                        });
                        selectReturnByvalue(1);
                        $('#returnedPageNow').html(1);
                        $('#returnedCreaterefoundOder').attr('disabled', "true");
                        tipsRefound(errorcount,returnIdArray,successCount);

                    } else {
                        alert('请选择退换货状态为‘退货’的退货单');
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
            var a = getreturnedStatus(returnIdArray, "5");
            var returnOrChange = getreturnedOrChange(returnIdArray);


            if (returnIdArray.length == 0) {
                alert("请勾选订单");
                return false;
            } else if (a == "yes") {
                if (returnOrChange == "change") {

                    var jsonStr = {"returnIds": returnIdArray};
                    $.ajax({
                        type: 'post',
                        url: '/oms/returned/createOutBoundOrder',
                        data: {
                            json: JSON.stringify(jsonStr)
                        },
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            changesuccessCount = data.success
                            changeErrorCount = data.exception;
                        }
                    });
                    if (changeErrorCount > 0) {
                        alert("订单已换货，请不要重复提交，共" + returnIdArray.length + "条,成功" + changesuccessCount + "条");
                    } else if (changesuccessCount > 0) {
                        //if
                        alert("发货完成,共" + returnIdArray.length + "条,成功" + changesuccessCount + "条");
                    }

                } else {
                    alert('请选择退换货状态为‘换货’的退货单');
                    return false;
                }
            } else {
                alert('请选择收货成功订单');
                return false;
            }
            selectReturnByvalue($('#returnedPageNow').html());
            $('#changeOutBound').attr('disabled', "true");
        });

        //退款单查询按钮点击事件
        $("#serarchReturnedOrder").click(
            function () {
                selectReturned();
            }
        );

        $('#returnValue').bind('keypress', function (event) {

            if (event.keyCode == "13") {
                selectReturned();
            }

        });


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
                var daishenghe = getreturnedStatus(returnIdArray, "1");
                var shouhuowancheng = getreturnedStatus(returnIdArray, "5");
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
                    } else if (returnOrChange == "error") {
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

                $('#checkreturnedorder').attr('disabled', "true");
                $('#cancelReturnedOrder').attr('disabled', "true");
                $('#returnedCreaterefoundOder').attr('disabled', "true");
                $('#changeOutBound').attr('disabled', "true");
                $('#returnedDetailbut').attr('disabled', "true");
                returnSelectValue = "";
                returnSelectValueTemp = "";
                returnSelect = "订单号";
                returnSelectTemp = "订单号";
                $("#returnValue").val("");
                $("#returnedselect").val("请选择查询条件");
                selectReturnByvalue(1);
                $('#returnedPageNow').html(1);

            }
        );

        $("#userListbut").click(
            function () {
                if ($("#userListbut").text() == "") {
                    return;
                }
                $('#updateUserBut').attr('disabled', "true");
                $('#deleteUser').attr('disabled', "true");
                userselectValue = "";
                userselectValuetemp = "";
                $("#userselectvalue").val("");
                selectByUserName(1);
                $("#userPageNow").html("1");
            }
        );

        $("#userListSonbut").click(
            function () {
                $('#updateUserBut').attr('disabled', "true");
                $('#deleteUser').attr('disabled', "true");
                userselectValue = "";
                userselectValuetemp = "";
                $("#userselectvalue").val("");
                selectByUserName(1);
                $("#userPageNow").html("1");
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
                $("#goodsPageNow").html(1);
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
                $("#goodsPageNow").html(1);
            }
        );


    });
