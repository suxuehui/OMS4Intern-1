window.onload= refoundGetnowPage(1);//加载页面时就执行函数进入后台

//使用ajax提交数据到后台

function refoundGetnowPage(pagenow){
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
            $("#refoundOrdertable1 tbody tr").eq(0).nextAll().remove();
            var i=0;
            for(var obj in dataList){
                i++;
                var  list=dataList[obj];
                var html='<tr><td>'+i+'</td><td><input type="checkbox" value="'+list.returnedid+'" name="refoundOrder_ck" onclick="refoundOrder_getReturnedId()" ></td><td>';
                html+= '<button id="'+list.returnedid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="refounddbClick(this.id)" onclick="refoundsingleClick(this.id)">'+list.drawbackid+'</button>'+
                    '</td>';
                html+='<td>'+list.drawbackmoney+'</td><td>'
                    +list.drawbackstatus+'</td><td>'+list.returnedid+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#refoundOrdertable1 tbody ").append(html);
            }
            refoundGetPage(dataPage.totalPageCount,dataPage.pageNow);
        }
    });


}

/*单、双击事件跳转*/
var exceptionDb;
function refoundsingleClick(returnedId) {
    window.setTimeout(cc, 250)
    function cc() {
        refoundpostReturnedId(returnedId);
    }
}

function refounddbClick(returnedId) {
    window.open("../refoundOrder/details?returnedId="+returnedId);
}


//单击跳转子页面
function  refoundpostReturnedId(returnedId)
{
    //RTOOYYYYMMDD12341
    returnedId=returnedId.substring(2);//12341
    refoundpageson(returnedId,1);
}

function refoundpageson(returnedId,pagenow){
    returnedId="RT"+returnedId;
    $.ajax({
        type : 'get',
        url :'../refoundOrder/listRefoundOrderSon',
        data : {
            returnedId:returnedId,
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
                    var totalPrice=god.goodsprice*obj.goodnum;//商品总价
                    var html='<tr><td><input type="checkbox" value="" name="" onclick="" ></td><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+god.goodsprice +'</td><td>'
                        + obj.goodnum+'</td><td>'
                        + totalPrice +'</td></tr>'
                    $("#refoundOrdertable2 tbody  ").append(html);
                }
            }
            refoundpagelistson(totalpages, pagenow,returnedId);
        }
    });
}

//获得退款单号
function refoundOrder_getReturnedId() {
    var a = document.getElementsByName("refoundOrder_ck");
    var info = "";
    for(var i=0;i<a.length;i++)
    {
        if(a[i].checked){
            var info = (info + a[i].value) + (((i + 1)== a.length) ? '':',');
            $("#refoundOrder_inbtn").attr("disabled",false);
        }
    }
    return info;
}

//点击查看出库订单进入详情页
function refoundOrder_details(){
    var returnedId2 = refoundOrder_getReturnedId();
    var Array = returnedId2.split(",");
    if(Array.length>1){
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            var returnedId3 = Array[0];
            window.open("../refoundOrder/details?returnedId="+returnedId3);
        }else{
            alert("一次只能查看一条订单的信息");
            var excheck = document.getElementsByName("refoundOrder_ck");
            for(var i=0;i<excheck .length;i++)
            {
                excheck[i].checked=false;
                $("#refoundOrder_inbtn").attr("disabled",true);
            }
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            returnedId2 = Array[i];
            window.open("../refoundOrder/details?returnedId="+returnedId2);
        }
    }
}

//退款操作
function drawback(){
    var returnedId3 = refoundOrder_getReturnedId();
    var Array = returnedId3.split(",");
    if(Array.length>1){
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            returnedId3 = Array[0];
            var parm = {returnedId3: returnedId3};//将参数传到后台
            $.post("../refoundOrder/drawback", parm, function (data) {
                    window.onload= refoundGetnowPage(1);
                    var msg=data.msg;
                    if(msg==666){
                        alert("退款成功");
                    }else if(msg==777){
                        alert("随机数退款失败");
                    }else if(msg==123){
                        alert("退款失败");
                    }
                },"json"
            );
        }else{
            alert("一次只能操作一条退款单");
            var excheck = document.getElementsByName("refoundOrder_ck");
            for(var i=0;i<excheck .length;i++)
            {
                excheck[i].checked=false;
                $("#refoundOrder_inbtn").attr("disabled",true);
            }
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            returnedId3 = Array[i];
            var parm2 = {returnedId3: returnedId3};//将参数传到后台
            $.post("../refoundOrder/drawback", parm2, function (data) {
                    window.onload= refoundGetnowPage(1);
                    var msg=data.msg;
                    if(msg==666){
                        alert("退款成功");
                    }else if(msg==777){
                        alert("随机数退款失败");
                    }else if(msg==123){
                        alert("退款失败");
                    }
                },"json"
            );
        }
    }
}