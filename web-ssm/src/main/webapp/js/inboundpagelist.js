/**
 * Created by GONG036 on 2016/12/8.
 */
window.onload= inGetnowPage(1);
function inGetnowPage(pagenow){
    var  myselect=document.getElementById("inselectid");
    var index=myselect.selectedIndex ;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("intxt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../inboundorder/listseach',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success : function(data) {
            var datapage = data.pagelist;
            var datalist =  data.list ;
            $("#inboundertab tbody tr").eq(0).nextAll().remove();
            document.getElementById("inbtn").disabled=true;
            for(var obj in datalist){
                var  list=datalist[obj];
                var html='<tr><td><input type="checkbox"  id="'+list.oid+'" onclick="toincheck(this.id)"  name="inck"></td><td>';
                    html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;"  ondblclick="indblclick(this.id)" onclick="insgclick(this.id)">'+list.oid+'</button> </td><td>'
                        +list.channeloid+'</td><td>'
                    +list.returnedid+'</td><td>'+list.inboundid+'</td><td>'
                    +list.inboundstate+'</td><td>'+list.synchrostate+'</td><td>'
                    +list.warehouse+'</td><td>' +list.createdtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman +'</td></tr>'
                $("#inboundertab tbody ").append(html);
            }
            inGetNavPage(datapage.totalPageCount,datapage.pageNow,indivpage);
        },
        error:function(data){
            alert("+++++error++");
        }

    });

}

/*点击checkbox */
function toincheck(oid){ //id为checkbox的id 属性值
    var count = 0;
    var checkArry = document.getElementsByName("inck");//ck为checkbox的name属性
    for (var i = 0; i < checkArry.length; i++) {
        if(checkArry[i].checked == true){
            ++count;
            if( count >1){
                alert("只能选中一条订单查看！")
                checkArry[i].checked=false;
                return;
            }
            //选中的操作 inbtn为button 的id
            document.getElementById("inbtn").disabled=false;
            document.getElementById("inbtn").name=checkArry[0];
        }
    }
}
//点击查看入库订单进入详情页
function toinOrderdetail(oid){
    toincheck(oid);
    window.open("../inboundorder/details?oid="+oid);
}


/*单、双击事件跳转*/
 var inisdb;
 function insgclick(oid) {
   inisdb = false;
  window.setTimeout(nn, 250);
   function nn()
   {
    if (inisdb != false)return;
    inpostOid(oid);
   }
 }

function indblclick(oid) {
    inisdb = true;
    window.open("../inboundorder/details?oid="+oid);
}



//单击跳转子页面
function  inpostOid(oid)
{
    inpageson(oid,1);
}

function inpageson(oid,pagenow){
    $.ajax({
        type : 'get',
        url :'/oms/inboundorder/listinodson',
        data : {
            oid:oid,
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
            $("#inboundertabson tbody tr").eq(0).nextAll().remove();
            for(var i in rglist)
            {
                //显示第几页数据
                if( pagesize*(pagenow-1)<=i && i< pagesize*pagenow) //？？？？？？？
                {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var num=obj.goodnum;//需要显示在页面的部分
                    var html='<tr><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+obj.goodnum +'</td><td>'
                        + obj.goodnum+'</td></tr>'
                    $("#inboundertabson tbody  ").append(html);
                }
            }
            inpagelistson(totalpages, pagenow,insonpl,oid);
        },
        error:function () {
            alert("error");
        }
    });
}



