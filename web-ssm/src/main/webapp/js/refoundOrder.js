window.onload= GetnowPage(1);//加载页面时就执行函数进入后台

//使用ajax提交数据到后台

function GetnowPage(pagenow){
    var myselect=document.getElementById("selectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("txt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'showRefoundOrderList',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            //alert(data);
            //var data = JSON.parse(data);
            var dataPage = data.pagelist;
            var dataList = eval(data.list);

            $("table tbody tr").eq(0).nextAll().remove();
            for(var obj in dataList){
                var  list=dataList[obj];
                var html='<tr><td><input type="checkbox" value="'+list.returnedid+'" name="refoundOrder_ck" onclick="refoundOrder_getReturnedId()" ></td><td>';
                html+= '<button id="'+list.returnedid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="dbClick(this.id)" onclick="singleClick(this.id)">'+list.drawbackid+'</button>'+
                    '</td><td>';
                html+='</td><td>'+list.drawbackmoney+'</td><td>'
                    +list.drawbackstatus+'</td><td>'+list.returnedid+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#table1 tbody ").append(html);
            }
            GetNavPage(dataPage.totalPageCount,dataPage.pageNow,divpage);
        },
        error:function(data){
            alert("+++++error++");
        }
    });


}

/*单、双击事件跳转*/
var exceptionDb;
function singleClick(returnedId) {
    exceptionDb = false;
    window.setTimeout(cc, 250)
    function cc() {
        if (exceptionDb != false)return;
        //alert("测试单击" +returnedId+"--"+exceptionDb)
        postReturnedId(returnedId);
    }
}

function dbClick(returnedId) {
    exceptionDb = true;
    //alert("测试双击"+returnedId+"--"+exceptionDb)
    window.open("details?returnedId="+returnedId);
}


//单击跳转子页面
function  postReturnedId(returnedId)
{
    //RTOOYYYYMMDD12341
    returnedId=returnedId.substring(12);//12341
    alert(returnedId);
    pageson(returnedId,1);
}

function pageson(returnedId,pagenow){
    returnedId="RTOOYYYYMMDD"+returnedId;
    $.ajax({
        type : 'get',
        url :'listRefoundOrderSon',
        data : {
            returnedId:returnedId,
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            var rglist=data.rglist;
            var gdlist=data.goods;
            var listtotalcount =rglist.length;//数据的总数
            var pagesize=2;//每页展示行数
            var totalpages;
            var count=listtotalcount%pagesize;//判断奇偶数
            totalpages=parseInt(listtotalcount/pagesize);//共多少页数
            if(count!=0){
                totalpages+=1;
            }
            $("#table2 tbody tr").eq(0).nextAll().remove();
            for(var i in rglist)
            {
                //显示第几页数据
                if( pagesize*(pagenow-1)<=i && i< pagesize*pagenow) //
                {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var totalPrice=god.goodsprice*obj.goodnum;//商品总价
                    var num=obj.goodnum;//需要显示在页面的部分
                    var html='<tr><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+god.goodsprice +'</td><td>'
                        + obj.goodnum+'</td><td>'
                        + totalPrice +'</td></tr>'
                    $("#table2 tbody  ").append(html);
                }
            }
            pagelistson(totalpages, pagenow,sonpl,returnedId);
        },
        error:function (data) {
            alert("error");
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
            //alert(info);
        }
    }
    return info;
}

//点击查看出库订单进入详情页
function refoundOrder_details(){
    var returnedId2 = refoundOrder_getReturnedId();
    var Array = returnedId2.split(",");
    if(Array.length>1){
        //alert("s="+Array[1]);
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            returnedId3 = Array[0];
            window.open("/RefoundOrder/details?returnedId="+returnedId3);
        }else{
            alert("一次只能查看一条订单的信息");
            //document.getElementById("exception_inbtn").disabled=false;
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            //alert(Array[i]);;
            returnedId2 = Array[i];
            window.open("/RefoundOrder/details?returnedId="+returnedId2);
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
            //alert("returnedId3:"+returnedId3);
            window.open("/RefoundOrder/drawback?returnedId3="+returnedId3);
        }else{
            alert("一次只能操作一条退款单");
            //document.getElementById("exception_inbtn").disabled=false;
        }
    }
    else
    {
        for(var i=0;i<Array.length;i++){
            //alert(Array[i]);;
            returnedId3 = Array[i];
            window.open("/RefoundOrder/drawback?returnedId3="+returnedId3);
        }
    }
}