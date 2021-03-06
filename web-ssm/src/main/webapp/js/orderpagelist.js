/**
 * Created by ZHAN545 on 2016/12/15.
 */
var queryMode="";
var queryModeTemp="";
var queryData="";
var queryDateTemp="";
var orderPageSize=10;
var orderGoodsPageSize=5;
var oidArray=[];
var goodsArray=[];
//页面加载时查询
function  queryAllOrder(pageNo) {
    $.ajax({
        url:"../order/queryByCondition",
        type:"get",
        data: {
            queryMode: queryMode,
            pageNo: pageNo,
            pageSize: orderPageSize,
            data: queryData
        },
        cache:false,
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            translationOrder(data);
        },
        error: function () {
            alert("登录已失效，请重新登录！");
            window.location.href = "/oms/login/logout";
        }
    })
}

//点击导航栏刷新页面
$(function () {
    //左侧导航栏订单列表
    $(".orderClick").click(function () {
        queryMode="";
        queryModeTemp="";
        queryData="";
        queryDateTemp="";
        $("#queryOrderCon").val("");
        $("#queryMode").val("");
        queryAllOrder(1);
        $("#ogList").html("");//清空子页的商品信息
        outGetnowPage(1);
        inGetnowPage(1);
    })
    //上方导航栏订单
    $("#0").click(function () {
        queryMode="";
        queryModeTemp="";
        queryData="";
        queryDateTemp="";
        $("#queryOrderCon").val("");
        $("#queryMode").val("");
        queryAllOrder(1);
        $("#ogList").html("");
    })
})

//条件查询
function queryOrder(pageNo) {
    $.ajax({
        url:"../order/queryByCondition",
        type:"get",
        data:{
            queryMode:queryMode,
            pageNo:pageNo,
            pageSize:orderPageSize,
            data:queryData
        },
        cache:false,
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            var orderModels=data.orderModels;
            if(orderModels=="")
            {
                alert("查询无结果!");
                queryMode=queryModeTemp;
                queryData=queryDateTemp;
                return;
            }
            queryModeTemp=queryMode;
            queryDateTemp=queryData;
            translationOrder(data);
        },
        error: function () {
            alert("登录已失效，请重新登录！");
            window.location.href = "/oms/login/logout";
        }
    })
}
//将订单list数组转成页面的table
function translationOrder(data)
{
    var pageTotal=data.pageTotal;
    var pageNo=data.pageNo;
    $("#orderPageNo").text(pageNo);
    $("#orderPageTotal").text(pageTotal);
    var orderModels=data.orderModels;
    var html="";
    for(var i=0;i<orderModels.length;i++)
    {
        html+='<tr class="orderTr" id="tr'+orderModels[i].oid+'"><td>'+(i+1)+'</td><td><input type="checkbox" id="'+orderModels[i].oid
            +'" onclick="ordercheck(this.id)" name="orderck"></td><td name="oid1">'+orderModels[i].oid
            +'</td><td>'+orderModels[i].channeloid+'</td><td id="S'+orderModels[i].oid+'">'+matchOrderStatus(orderModels[i].orderstatus)+'</td><td>'
            +orderModels[i].orderform+'</td><td>'+orderModels[i].buyerid+'</td><td>'
            +orderModels[i].ordertime+'</td><td>'+matchBaseStatus(orderModels[i].basestatus)+'</td><td>'
            +matchPayStatus(orderModels[i].paystatus)+'</td><td>'+matchPayStytle(orderModels[i].paystyle)+'</td><td>'
            +orderModels[i].paytime+'</td><td>'+orderModels[i].goodstolprice+'</td><td>'
            +orderModels[i].discountprice+'</td><td>'+orderModels[i].ordertolprice+'</td><td id="ware'+i+'">'
            +matchWareHouse(orderModels[i].goodswarehouse,i)+'</td><td>'+changeNull(orderModels[i].logisticscompany)+'</td><td>'
            +changeNull(orderModels[i].logisticsid)+'</td><td>'+changeNull(orderModels[i].sendtime)+'</td><td>'
            +changeNull(orderModels[i].remark)+'</td><td>'+orderModels[i].receivername+'</td><td>'
            +orderModels[i].receivermobel+'</td><td>'+changeNull(orderModels[i].receivertelnum)+'</td><td>'
            +orderModels[i].receiverprovince+'</td><td>'+orderModels[i].receivercity+'</td><td>'
            +orderModels[i].receiverarea+'</td><td>'+orderModels[i].detailaddress+'</td><td>'
            +orderModels[i].zipcode+'</td><td>'+changeNull(orderModels[i].modifytime)+'</td><td>'
            +changeNull(orderModels[i].modifyman)+'</td></tr>'
    }
    oidArray=[];
    for(var j=0;j<document.getElementsByName("orderBtn").length;j++)
    {
        document.getElementsByName("orderBtn")[j].disabled=true;
    }
    $("#order").html(html);
    if(pageNo==1)
    {
        $("#firstorder").hide();
        $("#preorder").hide();
    }
    else
    {
        $("#firstorder").show();
        $("#preorder").show();
    }
    if(pageNo==pageTotal)
    {
        $("#nextorder").hide();
        $("#lastorder").hide();
    }
    else
    {
        $("#nextorder").show();
        $("#lastorder").show();
    }
}

$(function () {
    $('#queryOrderCon').bind('keypress',function(event){
        if(event.keyCode == "13")
        {
            queryOrderByCondition();
        }
    });
})

function queryOrderByCondition() {
    queryMode=$("#queryMode option:selected").val();
    if(queryMode=="")
    {
        alert("请选择查询条件");
        $("#queryOrderCon").val("");
        return;
    }
    queryData=orderStatusTranslation($("#queryOrderCon").val());
    queryOrder(1);
}
//首页
$(function () {
    $("#firstorder").click(function () {
        queryOrder(1);
    });
})
//上一页
$(function () {
    $("#preorder").click(function () {
        var pageNo=$("#orderPageNo").text()-1;
        queryOrder(pageNo);
    })
})
//下一页
$(function () {
    $("#nextorder").click(function () {
        var pageNo=$("#orderPageNo").text();
        queryOrder(++pageNo);
    })
})
//尾页
$(function () {
    $("#lastorder").click(function () {
        var pageNo=$("#orderPageTotal").text();
        queryOrder(pageNo);
    })
})
/*单双击事件*/
var time = null;
var oId=null;

$(function () {
    $("#order").delegate("td[name='oid1']","click",function () {
        // 取消上次延时未执行的方法
        clearTimeout(time);
        var oid=$(this).text();
        oId=oid;
        time = setTimeout(function(){
            queryGoodsByOid(1,orderGoodsPageSize,oid);
        },300);
    })
})

$(function () {
    $("#order").delegate("td[name='oid1']","dblclick",function () {
        // 取消上次延时未执行的方法
        clearTimeout(time);
        var oid=$(this).text();
        window.open("../order/orderdetail?oId="+oid);
    })
})

//查看商品信息
function queryGoodsByOid(pageNo,pageSize,oid) {
    $.ajax({
        url:"../order/queryGoods",
        type:"get",
        data:{
            pageNo:pageNo,
            pageSize:pageSize,
            oId:oid
        },
        cache:false,
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        success:function (data) {
            var pageTotal=data.pageTotal;
            $("#ogPageNo").text(data.pageNo);
            $("#ogPageTotal").text(data.pageTotal);
            var goodsPojos=data.goodsPojos;
            var html="";
            for(var i=0;i<goodsPojos.length;i++)
            {
                html+='<tr><td><input type="checkbox" name="goodsOrderCk" onclick="orderGoodCheck(this.id)" id="'
                    +goodsPojos[i].goodsno +'"></td><td>' +goodsPojos[i].goodsno
                    +'</td><td>'+goodsPojos[i].goodsname+'</td><td id="f'+goodsPojos[i].goodsno+'">'
                    +goodsPojos[i].divideorderfee+'</td><td><input type="text" readonly class="goodNum edit" id="n'
                    +goodsPojos[i].goodsno+'"  value="'+goodsPojos[i].goodNum +'"/> <span id="temp'+goodsPojos[i].goodsno
                    +'" hidden>'+goodsPojos[i].goodNum +'</span></td><td>'+goodsPojos[i].totalfee +'</td></tr>'
            }
            $("#ogList").html(html);
            $("#goodsOid").text(oid);
            if(pageNo==1)
            {
                $("#ogFirstpage").hide();
                $("#ogPrepage").hide();
            }
            else
            {
                $("#ogFirstpage").show();
                $("#ogPrepage").show();
            }
            if(pageNo==pageTotal)
            {
                $("#ogNextpage").hide();
                $("#ogLastpage").hide();
            }
            else
            {
                $("#ogNextpage").show();
                $("#ogLastpage").show();
            }
            judgeGoodsChecked();
        },
        error: function () {
            alert("登录已失效，请重新登录！");
            window.location.href = "/oms/login/logout";
        }
    })
}
//商品跳页
$(function () {
    $("#ogFirstpage").click(function () {
        queryGoodsByOid(1,orderGoodsPageSize,oId);
    });
    $("#ogPrepage").click(function () {
        var pageNo=$("#ogPageNo").text()-1;
        queryGoodsByOid(pageNo,orderGoodsPageSize,oId);
    });
    $("#ogNextpage").click(function () {
        var pageNo=$("#ogPageNo").text();
        queryGoodsByOid(++pageNo,orderGoodsPageSize,oId);
    });
    $("#ogLastpage").click(function () {
        var pageNo=$("#ogPageTotal").text();
        queryGoodsByOid(pageNo,orderGoodsPageSize,oId);

    });
})
//判断商品是否选中
function judgeGoodsChecked() {
    if(goodsArray.length==0)
    {
        return;
    }
    for(var i=0;i<goodsArray.length;i++)
    {
        $("#"+goodsArray[i]).attr("checked",true);
        $("#n"+goodsArray[i]).removeAttr("readonly");
        $("#n"+goodsArray[i]).removeClass("edit");
    }
}

//将null改为空字符串
function changeNull(str) {
    return str==null?"":str;
}

//将订单状态显示为中文
function matchOrderStatus(status) {
    switch(parseInt(status))
    {
        case 1:
            return "缺货异常";
        case 2:
            return "订单异常";
        case 3:
            return "待预检";
        case 4:
            return "待路由";
        case 5:
            return "已出库";
        case 6:
            return "已完成";
        case 7:
            return "缺货等待";
        case 8:
            return "待出库";
        case 9:
            return "已取消";
        case 10:
            return "出库异常";
        case 11:
            return "已发货";
        default:
            return "";
    }
}
//将中文状态对应为数字
function orderStatusTranslation(status) {
    switch(status)
    {
        case "缺货异常":
        case "已支付":
            return 1;
        case "订单异常":
        case "未支付":
            return 2;
        case "待预检":
            return 3;
        case "待路由":
            return 4;
        case "已出库":
            return 5;
        case "已完成":
            return 6;
        case "缺货等待":
            return 7;
        case "待出库":
            return 8;
        case "已取消":
            return 9;
        case "出库异常":
            return 10;
        case "已发货":
            return 11;
        default:
            return status;
    }
}
//将订单基本状态显示为中文
function matchBaseStatus(status) {
    return status=="1"?"活动":"冻结";
}
//将支付状态显示中文
function matchPayStatus(status) {
    return status=="1"?"已支付":"未支付";
}
//将付款方式显示为中文
function matchPayStytle(status) {
    switch (parseInt(status))
    {
        case 1:
            return "支付宝";
        default:
            return "";
    }
}
//将仓库显示为中文
function matchWareHouse(num,i) {
    if(num==""||num==null)
    {
        return "";
    }
    $.ajax({
        url:"../warehouse/getName",
        type:"get",
        data:{num:num},
        datatype:"json",
        cache:false,
        success:function (data) {
            if(data=="")
            {
                $("#ware"+i).text("");
                return;
            }
            $("#ware"+i).text(data.wareName);
        }
    });
}