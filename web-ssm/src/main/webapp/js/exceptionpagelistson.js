function pagelistson(totalpages,currentPage,oid){
    var output = "";
    oid=oid.substring(2);//截取字符串
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a  href='javascript:void(0)' onclick='exceptionpageson("+oid +",1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+lastpage+")'>上一页</a> ";
        }
        output += " ";
        var currint = 5;
        var page = 1;
        for (var i = 0; i <= 10; i++) {
            //一共最多显示10个页码，前面5个，后面5个
            page = currentPage + i - currint;
            if (page>= 1 && page<= totalpages) {
                if (currint == i) {
                    //当前页处理
                    output += "<a class='cpb' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+currentPage+")'>" + currentPage + "</a> ";
                }
                else {
                    //一般页处理
                    output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+page+")'>" + page + "</a> ";
                }
            }
            output += " ";
        }
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+ nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='exceptionpageson("+oid+","+totalpages + ")'>末页</a> ";
        }
        output += " ";
    }
    document.getElementById("exceptionsonpl").innerHTML=output;

}