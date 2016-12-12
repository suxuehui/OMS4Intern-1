/**
 * Created by GONG036 on 2016/12/8.
 */
window.onload= GetnowPage(1);

function GetnowPage(pagenow){
    var  myselect=document.getElementById("selectid");
    var index=myselect.selectedIndex ;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("txt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'/oms/outboundorder/listseach',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            var datapage = data.pagelist;
            var datalist = data.list ;

            $("table tbody tr").eq(0).nextAll().remove();
            for(var obj in datalist){
                var  list=datalist[obj];
                var html='<tr><input type="checkbox" name="ck"><td></td><td>';
  html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="dblclick(this.id)" onclick="sgclick(this.id)">'+list.oid+'</button>'+
                    '</td><td>'
                    html+=list.channeloid+'</td><td>'
                    +list.orderstatus+'</td><td>'+list.warehouseobid+'</td><td>'
                    +list.outboundid+'</td><td>'+list.outboundstate+'</td><td>'
                    +list.synchrostate+'</td><td>' +list.receivername+'</td><td>'
                    +list.expresscompany+'</td><td>' +list.expressid+'</td><td>'
                    +list.receiveraddress+'</td><td>'+
                    +list.createdtime+'</td><td>'+list.modifytime+'</td><td>'
                    +list.modifyman +'</td></tr>'
                $("#table1 tbody ").append(html);
            }

            GetNavPage(datapage.totalPageCount,datapage.pageNow,divpage);

        },
        error:function(data){
            alert("+++++error++");
        }
    });

}
/*单击双击事件跳转*/
var isdb;
function sgclick(oid) {
    isdb = false;
    window.setTimeout(cc, 250)
    function cc() {
        if (isdb != false)return;
        alert(isdb + "测试单击" +oid+"--"+isdb)
        postOid(oid);
    }
}

//双击跳转详细页面
function dblclick(oid) {
        isdb = true;
        alert("测试双击"+oid+"--"+isdb)
        window.open("/oms/outboundorder/details?oid="+oid);
    }
    //单击跳转子页面
function  postOid(oid)
{
       //OOYYYYMMDD12345
       oid=oid.substring(10);//1234

       pageson(oid,1);

}

function pageson(oid,pagenow){
    oid="OOYYYYMMDD"+oid;

    $.ajax({
        type : 'get',
        url :'/oms/outboundorder/listobolson',
        data : {
            oid:oid,
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {

            var outboindid=data.obolist[0].outboundid;//出库单号
            var warehouseobid=data.obolist[0].warehouseobid;//仓库出库单号
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
                 if( pagesize*(pagenow-1)<=i && i< pagesize*pagenow) //？？？？？？？
                 {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var num=obj.goodnum;//需要显示在页面的部分
                    var html='<tr><td>'+outboindid +'</td><td>'
                        +warehouseobid +'</td><td>'+ god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+obj.goodnum +'</td><td>'
                        + obj.goodnum+'</td></tr>'
                    $("#table2 tbody  ").append(html);
                 }
            }
            pagelistson(totalpages, pagenow,sonpl,oid);
        },
        error:function (data) {
            alert("error");
        }
    });
}


