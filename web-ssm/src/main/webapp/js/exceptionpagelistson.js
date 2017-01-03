function pagelistson(totalpages,currentPage,oid){
    var output = "";
    oid=oid.substring(2);//截取字符串
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a  class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid +",1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+lastpage+")'>上一页</a> ";
        }
        output += " ";
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+ nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+totalpages + ")'>尾页</a> ";
        }
        output += " ";
    }
    var div=document.getElementById("exceptionsonpl");
    div.innerHTML=output;
}