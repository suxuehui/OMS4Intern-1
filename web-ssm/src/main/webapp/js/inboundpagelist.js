/**
 * Created by GONG036 on 2016/12/8.
 */
window.onload= inGetnowPage(1);
var inboundArray=new Array();
var inlistnull;

//点击查询时无结果就显示提示
function inGNPage(pagenow){

    inlistnull=0;//每次调用时初始化全局变量
    //此处不可直接调用inGetnowPage(page)函数，否则第一次进入页面不查寻且无数据也会有提示信息
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
            //清除母页面信息
            $("#inboundertab tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#inboundertabson tbody tr").eq(0).nextAll().remove();
            document.getElementById("inbtn").disabled=true;
            inboundArray.length=0;//每次分页就将勾选数组初始化
            //打开数据为空时设置全局变量以提示信息
            inlistnull=datalist.length;
            if(inlistnull==0){//判断是否有订单
                alert("查询无结果！")
            }
            for(var obj in datalist){
                if(datalist.hasOwnProperty(obj)){
                    var  list=datalist[obj];
                    //将同步状态的显示格式进行修改
                    bytetoString(list)
                    var html='<tr name="'+list.oid+'"><td><input type="checkbox"  id="'+list.oid+'" ' +
                        'onclick="toincheck(this)"  name="inck"></td><td>';
                    html+= '<button id="'+list.oid+'" style=" border-style:none;outline:none;background: transparent;"  ondblclick="indblclick(this.id)" onclick="insgclick(this.id)">'+list.oid+'</button> </td><td>'
                        +list.channeloid+'</td><td>'
                        +list.returnedid+'</td><td>'+list.inboundid+'</td><td>'
                        +list.inboundstate+'</td><td>'+list.synchrostate+'</td><td>'
                        +list.warehouse+'</td><td>' +list.createdtime+'</td><td>'
                        +list.modifytime+'</td><td>' +list.modifyman +'</td></tr>'
                    $("#inboundertab tbody ").append(html);
                }}

            //分页设置
            inGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登陆超时，请重新登陆！");
        }
    });
}
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
            //清除母页面信息
            $("#inboundertab tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#inboundertabson tbody tr").eq(0).nextAll().remove();
            document.getElementById("inbtn").disabled=true;
            inboundArray.length=0;//每次分页就将勾选数组初始化
            for(var obj in datalist){
                if(datalist.hasOwnProperty(obj)){
                var  list=datalist[obj];
                   //将同步状态的显示格式进行修改
                    bytetoString(list)
                var html='<tr name="'+list.oid+'"><td><input type="checkbox"  id="'+list.oid+'" ' +
                    'onclick="toincheck(this)"  name="inck"></td><td>';
                    html+= '<button id="'+list.oid+'"style=" border-style:none;outline:none;background: transparent;" ondblclick="indblclick(this.id)" onclick="insgclick(this.id)">'+list.oid+'</button> </td><td>'
                        +list.channeloid+'</td><td>'
                    +list.returnedid+'</td><td>'+list.inboundid+'</td><td>'
                    +list.inboundstate+'</td><td>'+list.synchrostate+'</td><td>'
                    +list.warehouse+'</td><td>' +list.createdtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman +'</td></tr>'
                $("#inboundertab tbody ").append(html);
            }}

            //分页设置
            inGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登陆超时，请重新登陆！");
        }
    });

}
//将同步状态的显示格式进行修改
function bytetoString(list) {
    if (list.synchrostate) {
        list.synchrostate = "已接收"
    }
    else {
        list.synchrostate = "已发送"
    }
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
       //设置标记颜色
       $("#inboundertab tbody tr").eq(0).nextAll().css('background-color','white')
       $("tr[name="+oid+"]").css('background-color','#F4F5F3');
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


            inpagelistson(datapage.totalPageCount,datapage.pageNow , oid);
        },
        error:function () {
            self.location="../login/login" ;
            alert("登陆超时，请重新登陆！");
        }
    });
}



