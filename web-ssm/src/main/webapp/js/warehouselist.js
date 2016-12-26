/**
 * Created by GONG036 on 2016/12/20.
 */

window.onload= wareGetnowPage(1);
//定义全局变量 checkbox勾选的Array
var whidArray=new Array();
function wareGetnowPage(pagenow){
    var  myselect=document.getElementById("whselectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("whtxt").value;//查询值
    var s1=pagenow;
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
            $("#warehousetab tbody tr").eq(0).nextAll().remove();

            for(var listindex in datalist){
                var  list=datalist[listindex];
                var html='<tr><td>'+(++listindex)+'</td><td><input type="checkbox" id="'
                    +list.id+'" onclick="towhcheck(this.id)"  name="whck"></td><td>'
                    +list.warehousenum+' </td><td>'+list.warehousename+'</td></tr>'
                $("#warehousetab tbody ").append(html);
            }

            whidArray.length=0;//清空数组，防止操作下一页时，数组不为空导致删除失败
            WareGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            alert("+++++error++");
        }
    });
}
// 页面分页 totalpages  总页数   currentPage  当前页数  waredivpage  div的id属性
function  WareGetNavPage(totalpages,currentPage){
    var output="" ;
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
        var currint = 5;
        var page;
        for (var i = 0; i <= 10; i++) {
            //一共最多显示10个页码，前面5个，后面5个
            page = currentPage + i - currint;
            if ((currentPage + i - currint) >= 1 && (currentPage + i - currint) <= totalpages) {
                if (currint == i) {
                    //当前页处理
                    output += "<a class='cpb' href='javascript:void(0)' onclick='wareGetnowPage("+currentPage+")'>" + currentPage + "</a> ";
                }
                else {
                    //一般页处理
                    output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage("+page+")'>" + page + "</a> ";
                }
            }
            output += " ";
        }
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(" + nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(" + totalpages + ")'>末页</a> ";
        }
        output += " ";
    }
    var div = document.getElementById("whdivpage");
    div.innerHTML = output;
}

//验证格式warehousenum
function  idendifywhnum(id)
{
    var divname;
    if(id=="warenum"){
          divname="whnumdiv";
    }
    else{
          divname="whupnumdiv";
    }
    ifwhnum(id,divname)
}
//验证格式warehousename 参数为该标签的id属性值
function  idendifywhname(id)
{
    var divname;
    if(id=="warename"){
        divname="whnamediv";
    }
    else{
        divname="whupnamediv";
    }
    ifwhname(id,divname)

}
//验证格式warehousenum
function  ifwhnum(id,divname)
{
    var warenum=document.getElementById(id).value;
    var regex1=/^[0-9]{4}$/;
    if (!regex1.test(warenum))
    {
        document.getElementById(divname).innerHTML="仓库编码格式错误，由4位数字组成";
        return false;
    }
    else{
        document.getElementById(divname).innerHTML="";
    }
}


function ifwhname(id,divname){
    var warename=document.getElementById(id).value;
    var regex1=/^[\u4E00-\u9FA5A-Za-z0-9_]{2,16}$/;
    if (!regex1.test(warename))
    {
        document.getElementById(divname).innerHTML="名称格式错误，仓库名称是汉字，数字，字母，下划线组成的2-16位字符";
        return false;
    }
    else{
        document.getElementById(divname).innerHTML="";
    }

}

//当仓库添加成功后就关闭弹窗进入列表页
function cleartext(){
    document.getElementById("warenum").innerHTML="";
    document.getElementById("warename").innerHTML="";
    document.getElementById("whnumdiv").innerHTML="";
    document.getElementById("whnamediv").innerHTML="";
    $(".popupAll .storeShow > div").hide();//关闭弹窗
    wareGetnowPage(1);
}

 //添加仓库save
 function  addwarehouse(){
     var warenum=document.getElementById("warenum").value;
     var warename=encodeURIComponent(document.getElementById("warename").value);
   $.ajax({
     type:'get',
     url:'../warehouse/addwarehouse',
     data:{
           warehousenum:warenum,
           warehousename:warename,
      },
     contentType: "application/json; charset=utf-8",
     dataType:'json',
     success:function(data){
         switch(data) {
             case 1 :
                 alert("仓库已成功添加");
                 cleartext();

                 break;
             case 2 :
                 alert("信息填写有误");
                 break;
             case 3 :
                 alert("添加失败");

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
            switch(data) {
                case 1 :
                    alert("成功编辑");
                    document.getElementById("updatewhnum").value="";
                    document.getElementById("updatewhname").value="";
                    $(".popupAll .storeShow > div").hide();//关闭弹窗
                    wareGetnowPage(1);
                    break;
                case 2 :
                    alert("信息填写有误");
                    break;
                case 3 :
                    alert("编辑失败,数据已存在");
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
                switch (data) {
                    case 1 :
                        alert("成功删除");
                        wareGetnowPage(1);
                        break;
                    default:
                        alert("删除失败");
                }
            },
            error: function () {
                alert("delete warehouse error");
            }
        })

    }
}
