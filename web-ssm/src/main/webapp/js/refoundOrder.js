window.onload= refoundGetnowPage(1);//加载页面时就执行函数进入后台

var reflistnull;
//使用ajax提交数据到后台
function refoundGetnowPage(pagenow){
    $("#refoundOrderSelectid").val(0);
    $("#refoundOrderTxt").val("");
    var myselect=document.getElementById("refoundOrderSelectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("refoundOrderTxt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../refoundOrder/showRefoundOrderList',
        data : {
            currentpage: s1,
            refoundToseachid: optxt,
            refoundOrderTxtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            var dataPage = data.pagelist;
            var dataList = eval(data.list);
            //清除母页面信息
            $("#refoundOrdertable1 tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#refoundOrdertable2 tbody tr").eq(0).nextAll().remove();
            var i=0;
            for(var obj in dataList){
                i++;
                var  list=dataList[obj];
                var html='<tr><td>'+i+'</td><td><input type="checkbox" value="'+list.drawbackid+'" name="refoundOrder_ck" onclick="refoundOrder_getDrawbackid()" ></td><td id="'+list.drawbackid+'" ' +
                    'ondblclick="refounddbClick(this.id)" onclick="refoundsingleClick(this.id)">'+list.drawbackid+'</td>';
                html+='<td>'+list.drawbackmoney+'</td><td>'
                    +list.drawbackstatus+'</td><td>'+list.returnedid+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#refoundOrdertable1 tbody ").append(html);
            }
            refoundGetPage(dataPage.totalPageCount,dataPage.pageNow);
        },
        error:function(){
            alert("登录已失效，请重新登录！");
            window.location.href = "/oms/login/logout";
        }
    });
}

function refGetnowPage(pagenow){
    reflistnull=0;//每次调用时初始化全局变量
    var myselect=document.getElementById("refoundOrderSelectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("refoundOrderTxt").value;//查询值
    var s1=pagenow;
    if(optxt==0){
        alert("请选择查询条件")
        $("#refoundOrderSelectid").val(0);
        $("#refoundOrderTxt").val("");
        return;
    }
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../refoundOrder/showRefoundOrderList',
        data : {
            currentpage: s1,
            refoundToseachid: optxt,
            refoundOrderTxtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            var dataPage = data.pagelist;
            var dataList = eval(data.list);

            //打开数据为空时设置全局变量以提示信息
            reflistnull=dataList.length;
            if(reflistnull==0){//判断是否有退款单
                alert("查询无结果！")
                $("#refoundOrderTxt").val("");
                return;
            }

            //清除母页面信息
            $("#refoundOrdertable1 tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#refoundOrdertable2 tbody tr").eq(0).nextAll().remove();
            var i=0;
            for(var obj in dataList){
                i++;
                var  list=dataList[obj];
                var html='<tr><td>'+i+'</td><td><input type="checkbox" value="'+list.drawbackid+'" name="refoundOrder_ck" onclick="refoundOrder_getDrawbackid()" ></td><td id="'+list.drawbackid+'" ' +
                    'ondblclick="refounddbClick(this.id)" onclick="refoundsingleClick(this.id)">'+list.drawbackid+'</td>';
                html+='<td>'+list.drawbackmoney+'</td><td>'
                    +list.drawbackstatus+'</td><td>'+list.returnedid+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#refoundOrdertable1 tbody ").append(html);
            }
            refoundGetPage(dataPage.totalPageCount,dataPage.pageNow);
        },
        error:function(){
            alert("登录已失效，请重新登录！");
            window.location.href = "/oms/login/logout";
        }
    });
}

//回车键绑定查询事件
$(document).keydown(function(event){
    if(event.keyCode==13){
        $("#refoundOrderSearch").click();
    }
});

/*单、双击事件跳转*/
var exceptionDb;
function refoundsingleClick(drawbackId) {
    window.setTimeout(cc, 250)
    function cc() {
        refoundpostDrawbackId(drawbackId);
    }
}

function refounddbClick(drawbackId) {
    window.open("../refoundOrder/details?drawbackId="+drawbackId);
}


//单击跳转子页面
function  refoundpostDrawbackId(drawbackId)
{
    //RTOOYYYYMMDD12341
    drawbackId=drawbackId.substring(2);//12341
    refoundpageson(drawbackId,1);
}

function refoundpageson(drawbackId,pagenow){
    drawbackId="RF"+drawbackId;
    $.ajax({
        type : 'get',
        url :'../refoundOrder/listRefoundOrderSon',
        data : {
            drawbackId:drawbackId,
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            var rglist=data.rglist;
            var gdlist=data.goods;
            var listtotalcount =rglist.length;//数据的总数
            var pagesize=5;//每页展示行数
            var totalpages;
            var count=listtotalcount%pagesize;//判断奇偶数
            totalpages=parseInt(listtotalcount/pagesize);//共多少页数
            if(count!=0){
                totalpages+=1;
            }
            $("#refoundOrdertable2 tbody tr").eq(0).nextAll().remove();
            for(var i in rglist)
            {
                //显示第几页数据
                if( pagesize*(pagenow-1)<=i && i< pagesize*pagenow) //
                {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var totalPrice=rglist[i].divideorderfee*obj.goodnum;//商品总价
                    var html='<tr><td><input type="checkbox" value="" name="" onclick="" ></td><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+rglist[i].divideorderfee +'</td><td>'
                        + obj.goodnum+'</td><td>'
                        + totalPrice +'</td></tr>'
                    $("#refoundOrdertable2 tbody  ").append(html);
                }
            }
            refoundpagelistson(totalpages, pagenow,drawbackId);
        }

    });
}
//获得退款单号
function refoundOrder_getDrawbackid() {
    var a = document.getElementsByName("refoundOrder_ck");
    var info = "";
    var j=0;
    for(var i=0;i<a.length;i++)
    {
        if(a[i].checked){
            j++;
            var info = (info + a[i].value) + (((i + 1)== a.length) ? '':',');
            var drawback = a[i].value;
            var parm = {drawbackId3: drawback};//将参数传到后台
            $.post("../refoundOrder/drawback", parm, function (data) {
                var msg=data.msg;
                if(msg==111)
                {
                    $("#drawback_inbtn").attr("disabled",true);
                }else
                {
                    $("#drawback_inbtn").attr("disabled",false);
                }
            },"json"
            );
            $("#refoundOrder_inbtn").attr("disabled",false);
        }
        if(j==0){
            $("#refoundOrder_inbtn").attr("disabled",true);
            $("#drawback_inbtn").attr("disabled",true);
        }

    }
    return info;
}

//点击查看出库订单进入详情页
function refoundOrder_details(){
    var drawbackId2 = refoundOrder_getDrawbackid();
    var Array = drawbackId2.split(",");
    if(Array.length>1){
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            var drawbackId3 = Array[0];
            window.open("../refoundOrder/details?drawbackId="+drawbackId3);
        }else{
            var excheck = document.getElementsByName("refoundOrder_ck");
            for(var i=0;i<excheck .length;i++)
            {
                excheck[i].checked=false;
                $("#refoundOrder_inbtn").attr("disabled",true);
                $("#drawback_inbtn").attr("disabled",true);
            }
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            drawbackId2 = Array[i];
            window.open("../refoundOrder/details?drawbackId="+drawbackId2);
        }
    }
}

//退款操作
function drawback(){
    var drawbackId3 = refoundOrder_getDrawbackid();
    var Array = drawbackId3.split(",");
    if(Array.length>1){
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            drawbackId3 = Array[0];
            var parm = {drawbackId3: drawbackId3};//将参数传到后台
            $.post("../refoundOrder/drawback", parm, function (data) {
                    var msg=data.msg;
                    if(msg==666){
                        alert("退款成功");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }else if(msg==777){
                        alert("退款失败");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }else if(msg==123){
                        alert("信息发送失败，请稍后再试");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }
                    refoundGetnowPage(1);
                },"json"
            );
        }else{
            var excheck = document.getElementsByName("refoundOrder_ck");
            for(var i=0;i<excheck .length;i++)
            {
                excheck[i].checked=false;
                $("#refoundOrder_inbtn").attr("disabled",true);
                $("#drawback_inbtn").attr("disabled",true);
            }
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            drawbackId3 = Array[i];
            var parm2 = {drawbackId3: drawbackId3};//将参数传到后台
            $.post("../refoundOrder/drawback", parm2, function (data) {
                    window.onload= refoundGetnowPage(1);
                    var msg=data.msg;
                    if(msg==666){
                        alert("退款成功");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }else if(msg==777){
                        alert("退款失败");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }else if(msg==123){
                        alert("信息发送失败，请稍后再试");
                        $("#refoundOrder_inbtn").attr("disabled",true);
                        $("#drawback_inbtn").attr("disabled",true);
                    }
                },"json"
            );
        }
    }
}