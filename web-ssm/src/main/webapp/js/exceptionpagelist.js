/**
 * Created by GONG036 on 2016/12/8.
 */
//totalpages  总页数   currentPage  当前页数  exceptionDivpage  div的id属性
function  exGetNavPage(totalpages,currentPage){
    //var output = "第" + currentPage + "页 / 共" + totalpages + "页";
    var output="";
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='GetnowPage(1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='GetnowPage("+lastpage+")'>上一页</a> ";
        }
        else {
        }
        output += " ";
        var currint = 5;
        var page = 1;
        for (var i = 0; i <= 10; i++) {
            //一共最多显示10个页码，前面5个，后面5个
            page = currentPage + i - currint;
            if ((currentPage + i - currint) >= 1 && (currentPage + i - currint) <= totalpages) {
                if (currint == i) {
                    //当前页处理
                    output += "<a class='cpb' href='javascript:void(0)' onclick='GetnowPage("+currentPage+")'>" + currentPage + "</a> ";
                }
                else {
                    //一般页处理
                    output += "<a class='pageLink' href='javascript:void(0)' onclick='GetnowPage("+page+")'>" + page + "</a> ";
                }
            }
            output += " ";
        }
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='GetnowPage(" + nextpage + ")'>下一页</a> ";
        }
        else {
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='GetnowPage(" + totalpages + ")'>末页</a> ";
        }
        output += " ";
    }
    var div = document.getElementById("exceptiondivpage");
    div.innerHTML = output;
}