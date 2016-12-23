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
            for(var w in datalist){
                var  list=datalist[w];
                var html='<tr><td>'+(++w)+'</td><td><input type="checkbox" id="'
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
    var output = "<h5>第" + currentPage + "页 / 共" + totalpages + "页</h5>";
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='wareGetnowPage(1)'>首页</a> ";
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
function  idendifywhnum()
{
    var warenum=document.getElementById("warenum").value;

    var regex1=/^[0-9]{4}$/;
    if (!regex1.test(warenum))
    {
        alert("亲，你的仓库编码格式错误，仓库编码格式由4位数字组成");
        return false;
    }
}
//验证格式warehousename
function  idendifywhname()
{
    var warename=document.getElementById("warename").value;
    var regex1=/^[\\u4e00-\\u9fff\\w]{2,16}$/;
    if (!regex1.test(warename))
    {
        alert("亲，你的仓库名称格式错误，仓库名称是2-16位字符组成");
        return false;
    }
}

 //添加仓库
 function  addwarehouse(){
     var warenum=document.getElementById("warenum").value;
     var warename=document.getElementById("warename").value;
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
         alert("测试仓库2" +data);
         switch(data) {
             case "1" :
                    alert("成功添加");break;
             case "2" :
                    alert("信息填写有误");break;
             default:
                    alert("添加失败");break;

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
                    wareGetnowPage(1);
                    break;
                case 2 :
                    alert("信息填写有误");break;
                case 3 :
                    alert("编辑失败,数据已存在");break;
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
        alert("ceshi ddddddddd")
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
