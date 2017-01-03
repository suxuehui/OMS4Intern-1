function refoundpagelistson(totalpages,currentPage,oid){
    var output = " ";
    oid=oid.substring(2);//截取字符串
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a  id="+oid+" href='javascript:void(0)' onclick='refoundpageson(this.id,1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a id="+oid+" class='pageLink' href='javascript:void(0)' onclick='refoundpageson(this.id,"+lastpage+")'>上一页</a> ";
        }
        output += " ";
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a id="+oid+" class='pageLink'  href='javascript:void(0)' onclick='refoundpageson(this.id,"+ nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a id="+oid+" class='pageLink' href='javascript:void(0)' onclick='refoundpageson(this.id,"+totalpages + ")'>末页</a> ";
        }
        output += " ";
    }
    document.getElementById("refoundOrderSonpl").innerHTML=output;

}