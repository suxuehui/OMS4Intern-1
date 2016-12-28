/**
 * Created by GONG036 on 2016/12/8.
 */
window.onload= inGetnowPage(1);
var inboundArray=new Array();
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
            //关闭数据为空时的提示信息
            document.getElementById("inboundinfordiv").style.display="none"
            //清除母页面信息
            $("#inboundertab tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#inboundertabson tbody tr").eq(0).nextAll().remove();
            document.getElementById("inbtn").disabled=true;
            inboundArray.length=0;//每次分页就将勾选数组初始化
            for(var obj in datalist){
                if(datalist.hasOwnProperty(obj)){
                var  list=datalist[obj];
                var html='<tr><td><input type="checkbox"  id="'+list.oid+'" ' +
                    'onclick="toincheck(this)"  name="inck"></td><td>';
                    html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;"  ondblclick="indblclick(this.id)" onclick="insgclick(this.id)">'+list.oid+'</button> </td><td>'
                        +list.channeloid+'</td><td>'
                    +list.returnedid+'</td><td>'+list.inboundid+'</td><td>'
                    +list.inboundstate+'</td><td>'+list.synchrostate+'</td><td>'
                    +list.warehouse+'</td><td>' +list.createdtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman +'</td></tr>'
                $("#inboundertab tbody ").append(html);
            }}
            //打开数据为空时的提示信息
            if(datalist.length==0){
                document.getElementById("inboundinfordiv").style.display="block"
            }
            //分页设置
            inGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            alert("+++++error++");
        }

    });

}

/*点击checkbox */
function toincheck(element){ //id为checkbox的id 属性值
    var oid=$(element).attr("id");
    var checkedVar = element.checked;
    if (checkedVar==true) {
        inboundArray.push(oid);
    }
    else {
        for (var i = 0; i < inboundArray.length; i++) {
            if (inboundArray[i] == oid) {
                inboundArray.splice(i, 1);
            }
        }
    }
    //判断选中的checkbox
    if (inboundArray.length == 1) {
        document.getElementById("inbtn").disabled = false;
    }
    else{
        document.getElementById("inbtn").disabled = true;
    }
}
//点击查看入库订单进入详情页
function toinOrderdetail(){
    document.getElementById("inbtn").disabled = false;
    var inoid=inboundArray[0];
    window.open("../inboundorder/details?oid=" + inoid);

}


/*单、双击事件跳转*/
 var inisdb;
 function insgclick(oid) {
   inisdb = false;
  window.setTimeout(nn, 250);
   function nn()
   {
    if (inisdb != false)
        return;
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
    oid=oid.substring(2);//截取字符串
    inpageson(oid,1);
}

function inpageson(oid,pagenow){
    oid="OO"+oid;//补全oid字符串
    $.ajax({
        type : 'get',
        url :'/oms/inboundorder/listinodson',
        data : {
            oid:oid,
            currentpage:pagenow
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function (data) {
            var rglist = data.rglist;
            var gdlist = data.gdslist;
            var datapage = data.pagelist;
            //清除子页面信息
            $("#inboundertabson tbody tr").eq(0).nextAll().remove();
            for(var i in rglist)
            {
                    var obj=rglist[i] ;//获取关系表的一个对象
                    var god=gdlist[i];//获取商品表的一个对象
                    var html='<tr><td>' + god.goodsno+'</td><td>'
                        + god.goodsname+'</td><td>'+obj.goodnum +'</td><td>'
                        + obj.goodnum+'</td></tr>'
                    $("#inboundertabson tbody  ").append(html);
            }
            //inboundinfordiv

            inpagelistson(datapage.totalPageCount,datapage.pageNow , oid);
        },
        error:function () {
            alert("error");
        }
    });
}



