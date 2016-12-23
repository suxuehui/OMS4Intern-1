window.onload= GetnowPage(1);//加载页面时就执行函数进入后台

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
            //var data = JSON.parse(data);
            var dataPage = data.pagelist;
            var dataList = eval(data.list);
            $("#exetable tbody tr").eq(0).nextAll().remove();
            var i=0;
            for(var obj in dataList){
                i++;
                var  list=dataList[obj];
                var html='<tr><td>'+i+'</td><td><input type="checkbox" value="'+list.oid+'" name="exceptionck" onclick="getOid()" ></td><td>';
                html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="exceptiondbClick(this.id)" onclick="exceptionsingleClick(this.id)">'+list.oid+'</button>'+
                    '</td>';
                html+='<td>'+list.channeloid+'</td><td>'
                    +list.orderstatus+'</td><td>'+list.orderfrom+'</td><td>'
                    +list.exceptiontype+'</td><td>'+list.expceptioncause+'</td><td>'
                    +list.exceptionstatus+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#exetable tbody ").append(html);
            }
            exGetNavPage(dataPage.totalPageCount,dataPage.pageNow);
        },
        error:function(data){
            alert("+++++error++");
        }
    });
}


/*单、双击事件跳转*/
var exceptionDb;
function exceptionsingleClick(oid) {
    exceptionDb = false;
    window.setTimeout(cc, 250)
    function cc() {
        if (exceptionDb != false)return;
        alert("测试单击" +oid+"--"+exceptionDb)
        exceptionPostOid(oid);
    }
}

function exceptiondbClick(oid) {
    exceptionDb = true;
    alert("测试双击"+oid+"--"+exceptionDb)
    window.open("../exceptionOrder/details?oid3="+oid);
}


//单击跳转子页面
function  exceptionPostOid(oid)
{
    //OOYYYYMMDD12345
    oid=oid.substring(10);//12345
    exceptionpageson(oid,1);
}

function exceptionpageson(oid,pagenow){
    oid="OOYYYYMMDD"+oid;
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
                    var num=obj.goodnum;//需要显示在页面的部分
                    var html='<tr><td><input type="checkbox" value="" name="" onclick="" ></td><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+god.goodsprice +'</td><td>'
                        + obj.goodnum+'</td><td>'
                        + totalPrice +'</td></tr>'
                    $("#exception_table2 tbody  ").append(html);
                }
            }
            pagelistson(totalpages, pagenow,sonpl,oid);
        },
        error:function (data) {
            alert("error");
        }
    });
}

