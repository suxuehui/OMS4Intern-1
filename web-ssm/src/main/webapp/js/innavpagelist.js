/**
 * Created by GONG036 on 2016/12/8.
 *
 * 入库单主页的分页
 *  totalpages  总页数   currentPage  当前页数  indivpage  div的id属性
 *
  */

function  inGetNavPage(totalpages,currentPage,indivpage){
   var output="";
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output  = "<a class='pageLink' href='javascript:void(0)' onclick='inGetnowPage(1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='inGetnowPage("+lastpage+")'>上一页</a> ";
        }
        output += " ";
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='inGetnowPage(" + nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='inGetnowPage(" + totalpages + ")'>尾页</a> ";
        }
        output += " ";
    }
    var div = document.getElementById("indivpage");
    div.innerHTML = output;
}