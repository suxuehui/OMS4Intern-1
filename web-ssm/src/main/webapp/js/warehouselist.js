/**
 * Created by GONG036 on 2016/12/20.
 */

window.onload= wareGetnowPage(1);
//定义全局变量 checkbox勾选的Array
var whidArray=new Array();
var warelistnull;
//点击查询时无结果就显示提示
function WarehousegetPage(pagenow){
    warelistnull=0;//每次调用时初始化全局变量
    wareGetnowPage(pagenow)
    if(warelistnull==0){//判断是否有订单
        alert("查询无结果！")
    }
}
function wareGetnowPage(pagenow){
    var  myselect=document.getElementById("whselectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("whtxt").value;//查询值
    var s1=pagenow;
    //在未勾选checkbox 置灰编辑和删除按钮
    document.getElementById("wareupdate").disabled=true;
    document.getElementById("waredelete").disabled=true;

    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../warehouse/listsearch',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            var datapage = data.pagelist;
            var datalist = data.warelist;
            //清空数组，防止操作下一页时，数组不为空导致删除失败
            whidArray.length=0;
            //打开数据为空时设置全局变量以提示信息
            warelistnull=datalist.length;
            //清除原先的数据
            $("#warehousetab tbody tr").eq(0).nextAll().remove();
            for(var listindex in datalist) {
                if (datalist.hasOwnProperty(listindex)) {
                    var list = datalist[listindex];
                    var html = '<tr><td>' +(++listindex)+ '</td><td><input type="checkbox" id="'
                        + list.id + '" onclick="towhcheck(this.id)"  name="whck"></td><td>'
                        + list.warehousenum + ' </td><td>' + list.warehousename + '</td></tr>'
                    $("#warehousetab tbody ").append(html);
                }
            }

            WareGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            alert("error");
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
//验证格式
function ifden(warenum,warename){
    var regex1=/^[A-Za-z0-9]{4}$/;
    var regex2=/^[\u4E00-\u9FA5A-Za-z0-9_]{1,}$/;
    if (!regex1.test(warenum))
    {
         alert("请输入4位有效仓库编号")
         return false;
    }
    else{
        if (!regex2.test(warename))
        {
            alert("请输入有效仓库名")
             return false;
        }

    }

}

//当仓库添加成功后就关闭弹窗进入列表页
function cleartext(){
    $(".popupAll .storeShow > div").hide();//关闭弹窗
   //将其他页面隐藏
    $(".hbg").hide();
    wareGetnowPage(1);
}

 //添加仓库save
 function  addwarehouse(){
     var warenum=document.getElementById("warenum").value;
     var warename=document.getElementById("warename").value ;
     //清除上一次的显示数据
     $(".inputList").val("");
     //显示loading图标
     $(".loading").show();
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
         //隐藏loading
         $(".loading").hide();
         switch(data) {
             case 1 :
                 alert("添加成功");
                 cleartext();
                 break;
             case 2 :
                 //验证格式
                 ifden(warenum,warename)
                 break;
             case 3 :
                 alert("仓库编号已存在");
                 break;
             case 4 :
                 alert("请输入仓库编号,请输入仓库名");
                 break;
             default:
        }
      },
      error:function(){
            alert("error");
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
                alert("warehouse error");
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
    //显示loading图标
    $(".loading").show();
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
            //隐藏loading
            $(".loading").hide();
            switch(data) {
                case 1 :
                    alert("添加成功");
                    cleartext();
                    break;
                case 2 :
                    //验证格式
                    ifden(warenum,warename)
                    break;
                case 3 :
                    alert("仓库编号已存在");
                    break;
                case 4 :
                    alert("请输入仓库编号,请输入仓库名");
                    break;
                default:

            }
        },
        error:function(){
            alert("error");
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
                alert("delete warehouse error");
            }
        })

    }
}
