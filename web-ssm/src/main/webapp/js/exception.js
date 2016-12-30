window.onload= GetnowPage(1);//加载页面时就执行函数进入后台

var exclistnull;
//点击查询时无结果就显示提示
function exceptionPage(pagenow){
    exclistnull=0;//每次调用时初始化全局变量
    GetnowPage(pagenow)
    if(exclistnull==0){//判断是否有订单
        alert("查询无结果！")
    }
}



//使用ajax提交数据到后台
function GetnowPage(pagenow){
    var myselect=document.getElementById("exceptionSelectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("exception_text").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../exceptionOrder/showExceptionList',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            var dataPage = data.pagelist;
            var dataList = data.list;
            //清除母页面信息
            $("#exetable tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#exception_table2 tbody tr").eq(0).nextAll().remove();
            //打开数据为空时设置全局变量以提示信息
            exclistnull=dataList.length;
            var i=0;
            for(var obj in dataList){
                i++;
                var  list=dataList[obj];
                var html='<tr><td>'+i+'</td><td><input type="checkbox" value="'+list.oid+'" name="exceptionck" onclick="getOid()" ></td><td>';
                html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;background-color:transparent" ' +
                    'ondblclick="exceptiondbClick(this.id)" onclick="exceptionsingleClick(this.id)">'+list.oid+'</button>'+
                    '</td>';
                html+='<td>'+list.channeloid+'</td><td>'
                    +list.orderstatus+'</td><td>'+list.orderfrom+'</td><td>'
                    +list.exceptiontype+'</td><td>'+list.expceptioncause+'</td><td>'
                    +list.exceptionstatus+'</td><td>' +list.createtime+'</td></tr>'
                $("#exetable tbody ").append(html);
            }
            exGetNavPage(dataPage.totalPageCount,dataPage.pageNow);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登陆超时，请重新登陆！");
        }
    });
}


/*单、双击事件跳转*/
function exceptionsingleClick(oid) {
    window.setTimeout(cc, 250)
    function cc() {
            return exceptionPostOid(oid);
    }
}

function exceptiondbClick(oid) {
    window.open("../exceptionOrder/details?oid3="+oid);
}


//单击跳转子页面
function  exceptionPostOid(oid)
{
    //$("#"+this.id).parent().css('background-color','#F4F5F3');
    //OOYYYYMMDD12345
    oid=oid.substring(2);//YYYYMMDD12345
    exceptionpageson(oid,1);

}

function exceptionpageson(oid,pagenow){
    oid="OO"+oid;
    $.ajax({
        type : 'get',
        url :'../exceptionOrder/listExceptionSon',
        data : {
            oid3:oid,
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
            $("#exception_table2 tbody tr").eq(0).nextAll().remove();
            for(var i in rglist)
            {
                //显示第几页数据
                if( pagesize*(pagenow-1)<=i && i< pagesize*pagenow) //？？？？？？？
                {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var totalPrice=god.goodsprice*obj.goodnum;//商品总价
                    var html='<tr><td><input type="checkbox" value="" name="" onclick="" ></td><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+god.goodsprice +'</td><td>'
                        + obj.goodnum+'</td><td>'
                        + totalPrice +'</td></tr>'
                    $("#exception_table2 tbody  ").append(html);
                }
            }
            pagelistson(totalpages, pagenow,oid);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登陆超时，请重新登陆！");
        }

    });
}

