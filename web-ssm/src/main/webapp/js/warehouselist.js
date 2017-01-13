/**
 * Created by GONG036 on 2016/12/20.
 */
//定义全局变量 checkbox勾选的Array
var whidArray=new Array();

var wareselectmode=0;
var warequerydata="";
var wareselectModeTemp=0;
var warequeryDateTemp="";
var warepagenow
var warepagenowTem

window.onload= wareGetnowPage(1);

$(".wareliclick").on('click',function(){
    wareGetnowPage(1)
})

//点击查询时无结果就显示提示
function WarehousegetPage(pagenow){
    warepagenow=pagenow;
    wareselectmode =$("#whselectid option:selected").val();//查询条件
    warequerydata=encodeURIComponent($("#whtxt").val());//查询值

    //在未勾选checkbox 置灰编辑和删除按钮
    document.getElementById("wareupdate").disabled=true;
    document.getElementById("waredelete").disabled=true;

    if(wareselectmode==0){
        alert("请选择查询条件")
        $("#whselectid").val(0);
        $("#whtxt").val("");
        wareselectmode=wareselectModeTemp;
        warequerydata=warequeryDateTemp;
        warepagenow=warepagenowTem;
        return;
    }
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../warehouse/listsearch',
        data : {
            currentpage: warepagenow,
            toseachid: wareselectmode,
            txtvalue: warequerydata
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            if(data.warelist.length==0){//判断是否有订单
                alert("查询无结果！")
                $("#whselectid").val(0);
                $("#whtxt").val("");
                wareselectmode=wareselectModeTemp;
                warequerydata=warequeryDateTemp;
                warepagenow=warepagenowTem;
                return;
            }
            wareselectModeTemp=wareselectmode ;
            warequeryDateTemp=warequerydata ;
            warepagenowTem=warepagenow;
            ware(data);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登录已失效，请重新登录！");
        }
    });
}

function ware(data){
    var datapage = data.pagelist;
    var datalist = data.warelist;
    //清空数组，防止操作下一页时，数组不为空导致删除失败
    whidArray.length=0;
    //清除原先的数据
    $("#warehousetab tbody tr").eq(0).nextAll().remove();
    var pp=0;
    for( pp ; pp< datalist.length;pp++) {
            var list = datalist[pp];
        var e=pp+1;
            var html = '<tr><td>' +e+ '</td><td><input type="checkbox" id="'
                + list.id + '" onclick="towhcheck(this.id)"  name="whck"></td><td>'
                + list.warehousenum + ' </td><td>' + list.warehousename + '</td></tr>'
            $("#warehousetab tbody ").append(html);

    }
    WareGetNavPage(datapage.totalPageCount,datapage.pageNow);
}




function wareGetnowPage(pagenow){
    warepagenow=pagenow;
    warepagenowTem=warepagenow;
      wareselectmode=0;
      warequerydata="";
      wareselectModeTemp=0;
      warequeryDateTemp="";
      $("#whselectid").val(0);
      $("#whtxt").val("");

    //在未勾选checkbox 置灰编辑和删除按钮
    document.getElementById("wareupdate").disabled=true;
    document.getElementById("waredelete").disabled=true;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../warehouse/listsearch',
        data : {
            currentpage: warepagenow,
            toseachid: wareselectmode,
            txtvalue: warequerydata
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            ware(data);
        },
        error:function(){
            self.location="../login/login" ;
            alert("登录已失效，请重新登录！");
        }
    });
}
// 页面分页 totalpages  总页数   currentPage  当前页数  waredivpage  div的id属性
function  WareGetNavPage(totalpages,currentPage){
    var output=" " ;
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output  = "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage("+lastpage+")'>上一页</a> ";
        }
        output += " ";
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(" + nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(" + totalpages + ")'>尾页</a> ";
        }
        output += " ";
    }
    var div = document.getElementById("whdivpage");
    div.innerHTML = output;
}

var regex1=/^[A-Za-z0-9]{4}$/;
var regex2=/^[\u4E00-\u9FA5A-Za-z0-9_]{1,}$/;
//正则判断
function ifzenze(warenum,warename,warnli){
        if (!regex2.test(warename))
        {
            $("#"+warnli).html("请输入有效仓库名")
            return false;
        }
        else{
            if (!regex1.test(warenum))
            {
                $("#"+warnli).html("请输入4位有效仓库编号")
                return false;
            }
        }
}
//验证格式
function ifden(warenum,warename,warnli){
    if(warenum.trim()==""){
            $("#"+warnli).html("请输入仓库编号")
            return false;
    }
    else{
        if (!regex1.test(warenum))
        {
            $("#"+warnli).html("请输入4位有效仓库编号")
            return false;
        }
        if(warename.trim()==""){
            $("#"+warnli).html("请输入仓库名")
            return false;
        }
        else{
            ifzenze(warenum,warename,warnli)
        }
    }
}

//当仓库添加成功后就关闭弹窗进入列表页
function cleartext(){
    $(".popupAll .storeShow > div").hide();//关闭弹窗
    $(".inputList").val("");
    $(".lixx").html("")
   //阴影层将其他页面隐藏
    $(".hbg").hide();
    wareselectmode=0;
    warequerydata="";
    wareselectModeTemp=0;
    warequeryDateTemp="";
    wareGetnowPage(1);
}

 //添加仓库save
 function  addwarehouse(){
     var warenum=document.getElementById("warenum").value;
     var warename=document.getElementById("warename").value ;
     var warnli="addwarnli"
     //验证格式
     ifden(warenum,warename,warnli)
     //回调函数处理数据
     $.ajax({
         type:'get',
         url:'../warehouse/addwarehouse',
         data:{
             warehousenum:warenum,
             warehousename:encodeURIComponent(warename),
         },
         contentType: "application/json; charset=utf-8",
         dataType:'json',
         success:function(data){
             //显示loading图标
             $(".loading").show();
             switch(data) {
                 case 1 :
                     alert("添加成功");
                     cleartext();
                     //隐藏loading
                     $(".loading").hide();
                     break;
                 case 2 :
                     //隐藏loading
                     $(".loading").hide();
                     break;
                 case 3 :
                     alert("仓库编号已存在");
                     //隐藏loading
                     $(".loading").hide();
                     break;
                 case 4 :
                     //隐藏loading
                     $(".loading").hide();
                     break;
                 case 5 :
                     $("#"+warnli).html("请输入有效仓库名")
                     //隐藏loading
                     $(".loading").hide();
                     break;
                 default:
             }

         },
         error:function(){
             self.location="../login/login" ;
             alert("登录已失效，请重新登录！");
         }
     })
 }

//checkbox的点击处理
function towhcheck(warehouseid) {

    if (document.getElementById(warehouseid).checked) {
        whidArray.push(warehouseid);
    }
    else {
        for (var i = 0; i < whidArray.length; i++) {
            if (whidArray[i] == warehouseid) {
                whidArray.splice(i, 1);
            }
        }
    }
    //编辑按钮置灰或亮
    if(whidArray.length==1){

        document.getElementById("wareupdate").disabled=false;
        document.getElementById("waredelete").disabled=false

    }
    else if(whidArray.length<1){
        document.getElementById("wareupdate").disabled=true;
        document.getElementById("waredelete").disabled=true

    }
    else {
        document.getElementById("wareupdate").disabled=true;
        document.getElementById("waredelete").disabled=false

    }
}

//设置编辑弹窗的输入框的值
function warehouseupdate(obj) {
    if(whidArray.length==1){
        var selectupdateid=whidArray[0];//获取选中的仓库id
        $.ajax({
            type: 'get',
            url: '../warehouse/listupdateware',
            data: {
                id: selectupdateid,
            },
            success: function (data) {
                 document.getElementById("saveupdatewh").name=data.id;//将id保存为save按钮的name值
                 document.getElementById("updatewhnum").value=data.warehousenum;
                 document.getElementById("updatewhname").value=data.warehousename;
            },
            error: function () {
                self.location="../login/login" ;
                alert("登录已失效，请重新登录！");
            }
        })
        //显示弹窗
        var index=$(obj).parent().index();
        $(".popupAll .storeShow > div").eq(index).show().siblings().hide();
        //将其他页面隐藏
        $(".hbg").show();
    }
    else{
        alert("请选中一条信息进行编辑");
    }
}

//保存编辑的信息updateware()
function updateware(wareid){
    //获取输入框的值
    var warenum=document.getElementById("updatewhnum").value;
    var warename=document.getElementById("updatewhname").value;
    var warnli="updatewarnli";
    //验证格式
    ifden(warenum,warename,warnli)
    $.ajax({
        type:'get',
        url:'../warehouse/toupdatewh',
        data:{
            id:wareid,
            warehousenum:warenum,
            warehousename:encodeURIComponent(warename),
        },
        contentType: "application/json; charset=utf-8",
        dataType:'json',
        success:function(data){
            //显示loading图标
            $(".loading").show();
            switch(data) {
                case 1 :
                    alert("保存成功");
                    cleartext();
                    //隐藏loading
                    $(".loading").hide();
                    break;
                case 2 :
                    //隐藏loading
                    $(".loading").hide();
                    break;
                case 3 :
                    alert("仓库编号已存在");
                    //隐藏loading
                    $(".loading").hide();
                    break;
                case 4 :
                    //隐藏loading
                    $(".loading").hide();
                    break;
                case 5 :
                    $("#"+warnli).html("请输入有效仓库名")
                    //隐藏loading
                    $(".loading").hide();
                    break;
                default:
                    //隐藏loading
                    $(".loading").hide();
                    break;
            }
        },
        error:function(){
            self.location="../login/login" ;
            alert("登录已失效，请重新登录！");
        }
    })
}

//批量删除仓库
function deleteware() {
    if(whidArray.length>0) {
        $.ajax({
            type: 'get',
            url: '../warehouse/deleteWarehouse',
            traditional: true,
            data: {
                id: whidArray,
            },
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (data) {
                //隐藏loading
                $(".loading").hide();
                switch (data) {
                    case 1 :
                        alert("成功删除");
                        wareGetnowPage(1);
                        break;
                    default:
                        alert("删除失败,请刷新页面");
                        break;
                }
            },
            error: function () {
                self.location="../login/login" ;
                alert("登录已失效，请重新登录！");
            }
        })

    }
}
