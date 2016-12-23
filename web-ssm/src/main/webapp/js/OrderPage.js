/**
 * Created by ZHAN545 on 2016/12/14.
 */
//导入订单
$(function () {
    $("#importBtn").click(function () {
        var formData=new FormData($("#importForm")[0]);
        var fileSize=document.getElementById("orderfile").files[0].size;
        if(fileSize>32000000)
        {
            alert("文件过大");
            return;
        }
        $.ajax({
            url:"../order/importOrder",
            type:"post",
            data:formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success:function (data) {
                if(data==0)
                {
                    alert("请选择文件");
                }
                if(data==2)
                {
                    alert("文件格式不对，请选择Excel文件");
                }
                $(".loading").hide();
                queryOrder(1,10);
            }
        })
    })
})
/*点击checkbox */
var oidArray=new Array();
$(function () {
    if(oidArray.length==0)
    {
        for(var i=0;i<document.getElementsByName("orderBtn").length;i++)
        {
            document.getElementsByName("orderBtn")[i].disabled=true;
        }
    }
})

function ordercheck(oid) {
    if(document.getElementById(oid).checked){
        oidArray.push(oid);
    }
    else{
        for(var i=0;i<oidArray.length;i++)
        {
            if(oidArray[i]==oid)
            {
                oidArray.splice(i,1);
            }
        }
    }
    //查看订单按钮状态控制
    if(oidArray.length==0)
    {
        for(var i=0;i<document.getElementsByName("orderBtn").length;i++)
        {
            document.getElementsByName("orderBtn")[i].disabled=true;
        }
        oidArray=[];
        goodsArray=[];
        return;
    }
    if(oidArray.length==1)
    {
        $("#queryOBtn").attr('disabled',false);
    }
    else
    {
        $("#queryOBtn").attr('disabled',true);
    }
    //预检按钮状态控制
    btnStatus("待预检","previewOrderBtn");
    //路由按钮状态控制
    btnStatus("待路由","routeOrderBtn");
    //出库按钮状态控制
    btnStatus("待出库","outboundOrderBtn");
    //退换货按钮控制
    $(function () {
        if(oidArray.length>1||oidArray.length==0)
        {
            $("#returnedOrderBtn,#exchangeGoodsBtn").attr('disabled',true);
            $("[name='goodscheck']:checkbox").prop("checked",false);
            for(var i=0;i<goodsArray.length;i++)
            {
                $("#n"+goodsArray[i]).attr("readonly","readonly");
                $("#n"+goodsArray[i]).addClass("edit");
            }
            goodsArray=[];
            return;
        }
        var id='S'+oidArray[0];
        var orderstatus=$("#"+id).text();
        $.ajax({
            url:"../order/checkreturn",
            type:"get",
            data:{oid:oidArray[0]},
            success:function (data) {
                if(orderstatus=="已完成"&&data==0)
                {
                    $("#returnedOrderBtn,#exchangeGoodsBtn").attr('disabled',false);
                    return;
                }
            }
        });
        $("#returnedOrderBtn,#exchangeGoodsBtn").attr('disabled',true);
    })
    //取消按钮状态控制
    $(function () {
        for(var i=0;i<oidArray.length;i++)
        {
            var id='S'+oidArray[i];
            var orderstatus=$("#"+id).text();
            if(orderstatus=="待预检"||orderstatus=="待路由"||orderstatus=="待出库"||orderstatus=="已出库")
            {
                continue;
            }
            else
            {
                $("#cancleOrderBtn").attr('disabled',true);
                return;
            }
        }
        $("#cancleOrderBtn").attr('disabled',false);
    })
}
//按钮状态控制
function btnStatus(status,btnId) {
    for(var i=0;i<oidArray.length;i++)
    {
        var id='S'+oidArray[i];
        var orderstatus=$("#"+id).text();
        if(orderstatus==status)
        {
            continue;
        }
        else
        {
            $("#"+btnId).attr('disabled',true);
            return;
        }
    }
    $("#"+btnId).attr('disabled',false);
}
//查看订单按钮
$(function () {
    $("#queryOBtn").click(function () {
        var oid=oidArray[0];
        window.open("../order/orderdetail?oId="+oid);
    })
})
//预检
$(function () {
    $("#previewOrderBtn").click(function () {
        $.ajax({
            url:"../order/previewOrder",
            type:"post",
            data:{oIds:oidArray},
            traditional: true,
            datatype:"json",
            success:function (data) {
                var success=data.success;
                var exception=data.exception;
                var content="success:"+success+"'\n'exception:"+exception;
                if(exception!=0)
                {
                    content+="'\n'请去异常订单页查看";
                }
                alert(content);
                var pageNo=$("#orderPageNo").text();
                queryOrder(pageNo,10);
            }
        })
    })
})
//路由
$(function () {
    $("#routeOrderBtn").click(function () {
        $.ajax({
            url:"../order/routeOrder",
            type:"post",
            data:{oIds:oidArray},
            traditional: true,
            datatype:"json",
            success:function (data) {
                var success=data.success;
                var exception=data.exception;
                alert("success:"+success+"'\n'exception:"+exception);
                var pageNo=$("#orderPageNo").text();
                queryOrder(pageNo,10);
            }
        })
    })
})
//出库
$(function () {
    $("#outboundOrderBtn").click(function () {
        $.ajax({
            url:"../order/outboundOrder",
            type:"post",
            data:{oIds:oidArray},
            traditional: true,
            datatype:"json",
            success:function (data) {
                var success=data.success;
                var exception=data.exception;
                alert("success:"+success+"'\n'exception:"+exception);
                var pageNo=$("#orderPageNo").text();
                queryOrder(pageNo,10);
            }
        })
    })
})
//取消
$(function () {
    $("#cancleOrderBtn").click(function () {
        $.ajax({
            url:"../order/cancleOrder",
            type:"post",
            data:{oIds:oidArray},
            traditional: true,
            datatype:"json",
            success:function (data) {
                var success=data.success;
                var exception=data.exception;
                alert("success:"+success+"'\n'exception:"+exception);
                var pageNo=$("#orderPageNo").text();
                queryOrder(pageNo,10);
            }
        })
    })
})
//商品勾选框
var goodsArray=new Array();
function goodCheck(goodsno) {
    if(document.getElementById(goodsno).checked){
        goodsArray.push(goodsno);
        $("#n"+goodsno).removeAttr("readonly");
        $("#n"+goodsno).removeClass("edit");
    }
    else{
        $("#n"+goodsno).attr("readonly","readonly");
        $("#n"+goodsno).addClass("edit");
        for(var i=0;i<oidArray.length;i++)
        {
            if(goodsArray[i]==goodsno)
            {
                goodsArray.splice(i,1);
            }
        }
    }
}
//退换货
function returnOrExchange(oid,returnedOrChange) {
    if(goodsArray.length==0)
    {
        alert("请选择需退换货的商品");
        return;
    }
    var goodsList=new Array();
    for(var i=0;i<goodsArray.length;i++)
    {
        var num=$("#n"+goodsArray[i]).val();
        if(num==0||num=="")
        {
            alert("商品数目不能为0或为空");
        }
        var fee=$("#f"+goodsArray[i]).text();
        var good={"goodsno":goodsArray[i],"goodNum":num,"divideorderfee":fee};
        goodsList.push(good);
    }
    var jsonStr={"oid":oid,"goods":goodsList,"returnedOrChange":returnedOrChange};
    $.ajax({
        url:"../order/returnGoods",
        type:"post",
        data:{jsonStr:JSON.stringify(jsonStr)},
        success:function(data){
            if(data==1)
            {
                alert("订单退换货成功，可以去退换货页面查看");
            }
            else
            {
                alert("退换货异常");
            }
        }
    });
}
$(function () {
    $("#returnedOrderBtn").click(function () {
        if(oid!=oidArray[0])
        {
            alert("商品与勾选订单不符");
            return;
        }
        returnOrExchange(oidArray[0],"return");
    })
})
$(function () {
    $("#exchangeGoodsBtn").click(function () {
        if(oid!=oidArray[0])
        {
            alert("商品与勾选订单不符");
            return;
        }
        returnOrExchange(oidArray[0],"change");
    })
})