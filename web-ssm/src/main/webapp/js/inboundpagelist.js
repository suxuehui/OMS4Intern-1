/**
 * Created by GONG036 on 2016/12/8.
 */
window.onload= GetnowPage(1);

function GetnowPage(pagenow){
    var  myselect=document.getElementById("selectid");
    var index=myselect.selectedIndex ;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("txt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'/oms/inboundorder/listseach',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        success : function(data) {
            alert(data)
            var data = JSON.parse(data);
            var datapage = data.pagelist;
            var datalist = eval(data.list);

            $("table tbody tr").eq(0).nextAll().remove();
            for(var obj in datalist){
                var  list=datalist[obj];
                var html='<tr><td><input type="checkbox" name="ck"></td><td>';
                    html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="dblclick(this.id)" onclick="sgclick(this.id)">'+list.oid+'</button>'+
                    '</td><td>';
                    html+='</td><td>'+list.channeloid+'</td><td>'
                    +list.returnedid+'</td><td>'+list.inboundid+'</td><td>'
                    +list.inboundstate+'</td><td>'+list.synchrostate+'</td><td>'
                    +list.warehouse+'</td><td>' +list.createdtime+'</td><td>'
                    +list.modifyTime+'</td><td>' +list.modifyMan +'</td></tr>'
                $("#table1 tbody ").append(html);
            }
            GetNavPage(datapage.totalPageCount,datapage.pageNow,divpage);
        },
        error:function(data){
            alert("+++++error++");
        }

    });

}

/*单击双击事件跳转*/
var isdb;
function sgclick(oid) {
    isdb = false;
    window.setTimeout(cc, 250)
    function cc() {
        if (isdb != false)return;
        alert("测试单击" +oid+"--"+isdb)
        document.getElementById("d").innerText = 909090 + "" + isdb + oid;
    }
}
function dblclick(oid) {
    isdb = true;
    alert("测试双击"+oid+"--"+isdb)
    window.open("/oms/inboundorder/details?oid="+oid);
}