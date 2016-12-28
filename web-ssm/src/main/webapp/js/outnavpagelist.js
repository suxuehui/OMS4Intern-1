/**
 * Created by GONG036 on 2016/12/14.
 */

/*出库单 的分页
* totalpages  总页数   currentPage  当前页数  outdivpage  div的id属性
* */

function  outGetNavPage(totalpages,currentPage){
    var output=" ";
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output = "<a class='pageLink' href='javascript:void(0)' onclick='outGetnowPage(1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outGetnowPage("+lastpage+")'>上一页</a> ";
        }
        output += " ";

        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outGetnowPage(" + nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outGetnowPage(" + totalpages + ")'>尾页</a> ";
        }
        output += " ";
    }
    var div = document.getElementById("outdivpage");
    div.innerHTML = output;
}