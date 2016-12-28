/**
 * Created by ZHAN545 on 2016/12/15.
 */
var queryMode=1;
var queryData="";
var orderPageSize=10;
window.onload= queryOrder(1,orderPageSize);
function queryOrder(pageNo,pageSize) {
    $.ajax({
        url:"../order/queryByCondition",
        type:"get",
        data:{
            queryMode:queryMode,
            pageNo:pageNo,
            pageSize:pageSize,
            data:queryData
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            var pageTotal=data.pageTotal;
            $("#orderPageNo").text(data.pageNo);
            $("#orderPageTotal").text(data.pageTotal);
            var orderModels=data.orderModels;
            var html="";
            for(var i=0;i<orderModels.length;i++)
            {
                html+='<tr><td>'+(i+1)+'</td><td><input type="checkbox" id="'+orderModels[i].oid
                    +'" onclick="ordercheck(this.id)" name="orderck"></td><td id="'+orderModels[i].oid
                    +'" onclick="ordersgclick(this.id)" ondblclick="orderdbclick(this.id)">'+orderModels[i].oid
                    +'</td><td>'+orderModels[i].channeloid+'</td><td id="S'+orderModels[i].oid+'">'+orderModels[i].orderstatus+'</td><td>'
                    +orderModels[i].orderform+'</td><td>'+orderModels[i].buyerid+'</td><td>'
                    +orderModels[i].ordertime+'</td><td>'+orderModels[i].basestatus+'</td><td>'
                    +orderModels[i].paystatus+'</td><td>'+orderModels[i].paystyle+'</td><td>'
                    +orderModels[i].paytime+'</td><td>'+orderModels[i].goodstolprice+'</td><td>'
                    +orderModels[i].discountprice+'</td><td>'+orderModels[i].ordertolprice+'</td><td>'
                    +changeNull(orderModels[i].goodswarehouse)+'</td><td>'+changeNull(orderModels[i].logisticscompany)+'</td><td>'
                    +changeNull(orderModels[i].logisticsid)+'</td><td>'+changeNull(orderModels[i].sendtime)+'</td><td>'
                    +changeNull(orderModels[i].remark)+'</td><td>'+orderModels[i].receivername+'</td><td>'
                    +orderModels[i].receivermobel+'</td><td>'+changeNull(orderModels[i].receivertelnum)+'</td><td>'
                    +orderModels[i].receiverprovince+'</td><td>'+orderModels[i].receivercity+'</td><td>'
                    +orderModels[i].receiverarea+'</td><td>'+orderModels[i].detailaddress+'</td><td>'
                    +orderModels[i].zipcode+'</td><td>'+changeNull(orderModels[i].modifytime)+'</td><td>'
                    +changeNull(orderModels[i].modifyman)+'</td></tr>'
            }
            oidArray=[];
            for(var i=0;i<document.getElementsByName("orderBtn").length;i++)
            {
                document.getElementsByName("orderBtn")[i].disabled=true;
            }
            $("#order").html(html);
            if(pageNo==1)
            {
                $("#preorder").hide();
            }
            else
            {
                $("#preorder").show();
            }
            if(pageNo==pageTotal)
            {
                $("#nextorder").hide();
            }
            else
            {
                $("#nextorder").show();
            }
        }
    })
}
$(function () {
    $("#queryOrderBtn").click(function () {
        queryMode=$("#queryMode option:selected").val();
        queryData=$("#queryOrderCon").val();
        queryOrder(1,orderPageSize);
    })
})
$(function () {
    $("#firstorder").click(function () {
        queryOrder(1,orderPageSize);
    });
})
$(function () {
    $("#preorder").click(function () {
        var pageNo=$("#orderPageNo").text()-1;
        queryOrder(pageNo,orderPageSize);
    })
})
$(function () {
    $("#nextorder").click(function () {
        var pageNo=$("#orderPageNo").text();
        queryOrder(++pageNo,orderPageSize);
    })
})
$(function () {
    $("#lastorder").click(function () {
        var pageNo=$("#orderPageTotal").text();
        queryOrder(pageNo,orderPageSize);
    })
})
/*单双击事件*/
var time = null;
var oId=null;

function ordersgclick(oid) {

    // 取消上次延时未执行的方法
    clearTimeout(time);
    //执行延时
    time = setTimeout(function(){
        oId=oid;
        queryGoodsByOid(1,orderPageSize,oid);
    },300);

}
function orderdbclick(oid) {
    // 取消上次延时未执行的方法
    clearTimeout(time);
    window.open("../order/orderdetail?oId="+oid);
}
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
                html+='<tr><td><input type="checkbox" name="goodscheck" onclick="goodCheck(this.id)" id="'
                    +goodsPojos[i].goodsno +'"></td><td>' +goodsPojos[i].goodsno
                    +'</td><td>'+goodsPojos[i].goodsname+'</td><td id="f'+goodsPojos[i].goodsno+'">'
                    +goodsPojos[i].divideorderfee+'</td><td><input type="text" readonly class="goodNum edit" id="n'
                    +goodsPojos[i].goodsno+'"  value="'+goodsPojos[i].goodNum +'"></td><td>'+goodsPojos[i].totalfee
                    +'</td></tr>'
            }
            $("#ogList").html(html);
            $("#goodsOid").text(oid);
            if(pageNo==1)
            {
                $("#ogPrepage").hide();
            }
            else
            {
                $("#ogPrepage").show();
            }
            if(pageNo==pageTotal)
            {
                $("#ogNextpage").hide();
            }
            else
            {
                $("#ogNextpage").show();
            }
            judgeGoodsChecked();
        }
    })
}
//商品跳页
$(function () {
    $("#ogFirstpage").click(function () {
        queryGoodsByOid(1,orderPageSize,oId);
    });
    $("#ogPrepage").click(function () {
        var pageNo=$("#ogPageNo").text()-1;
        queryGoodsByOid(pageNo,orderPageSize,oId);
    });
    $("#ogNextpage").click(function () {
        var pageNo=$("#ogPageNo").text();
        queryGoodsByOid(++pageNo,orderPageSize,oId);
    });
    $("#ogLastpage").click(function () {
        var pageNo=$("#ogPageTotal").text();
        queryGoodsByOid(pageNo,orderPageSize,oId);

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