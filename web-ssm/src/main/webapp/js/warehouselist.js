/**
 * Created by GONG036 on 2016/12/20.
 */

window.onload= wareGetnowPage(1);
function wareGetnowPage(pagenow){
    var  myselect=document.getElementById("whselectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("whtxt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    alert("测试仓库"+optxt);
    $.ajax({
        type : 'get',
        url :'/oms/warehouse/listsearch',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            var datapage = data.pagelist;
            var datalist = data.list;

            alert("测试仓库"+datalist);

            $("#warehousetab tbody tr").eq(0).nextAll().remove();
            for(var obj in datalist){

                var  list=datalist[obj];
                var html='<tr><td></td><td><input type="checkbox" id="'
                    +list.warehousenum+'" onclick="towhcheck(this.id)"  name="whck"></td><td>'
                    +list.warehousenum+' </td><td>'+list.warehousename+'</td></tr>'
                $("#warehousetab tbody ").append(html);
            }
            WareGetNavPage(datapage.totalPageCount,datapage.pageNow,whdivpage);
        },
        error:function(){
            alert("+++++error++");
        }
    });
}


// 页面分页 totalpages  总页数   currentPage  当前页数  waredivpage  div的id属性
function  WareGetNavPage(totalpages,currentPage,whdivpage){
    var output = "<h1>第" + currentPage + "页 / 共" + totalpages + "页</h1>";
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