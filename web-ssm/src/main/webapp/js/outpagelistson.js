/**
 * Created by GONG036 on 2016/12/14.
 */

/*出库单子页面的分页js*/
function outpagelistson(totalpages,currentPage,outsonpl,oid){
    var output = "<h5>第" + currentPage + "页 / 共" + totalpages + "页</h5>";
    oid=oid.substring(10);//截取字符串
    if (totalpages > 1) {
        if (currentPage != 1) {
            //处理首页连接
            output += "<a  href='javascript:void(0)' onclick='outpageson("+oid +",1)'>首页</a> ";
        }
        if (currentPage > 1) {
            var lastpage = currentPage - 1;
            //处理上一页的连接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outpageson("+oid+","+lastpage+")'>上一页</a> ";
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
                    output += "<a class='cpb' href='javascript:void(0)' onclick='outpageson("+oid+","+currentPage+")'>" + currentPage + "</a> ";
                }
                else {
                    //一般页处理
                    output += "<a class='pageLink' href='javascript:void(0)' onclick='outpageson("+oid+","+page+")'>" + page + "</a> ";
                }
            }
            output += " ";
        }
        if (currentPage < totalpages) {
            var nextpage = currentPage + 1;
            //处理下一页的链接
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outpageson("+oid+","+ nextpage + ")'>下一页</a> ";
        }
        output += " ";
        if (currentPage != totalpages) {
            output += "<a class='pageLink' href='javascript:void(0)' onclick='outpageson("+oid+","+totalpages + ")'>末页</a> ";
        }
        output += " ";
    }
    document.getElementById("outsonpl").innerHTML=output;

}