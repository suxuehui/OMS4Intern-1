window.onload= GetnowPage(1);//加载页面时就执行函数进入后台

//使用ajax提交数据到后台

function GetnowPage(pagenow){
    var  myselect=document.getElementById("selectid");
    var index=myselect.selectedIndex ;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("txt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'/exceptionOrder/queryExceptionOrder',
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
                var html='<tr><input type="checkbox" name="ck"><td></td><td>';
                html+= '<button id="'+list.oid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="dblclick(this.id)" onclick="sgclick(this.id)">'+list.oid+'</button>'+
                    '</td><td>'
                html+=list.channeloid+'</td><td>'
                    +list.orderstatus+'</td><td>'+list.orderfrom+'</td><td>'
                    +list.exceptiontype+'</td><td>'+list.expceptioncause+'</td><td>'
                    +list.exceptionstatus+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#table1 tbody ").append(html);
            }
            GetNavPage(datapage.totalPageCount,datapage.pageNow,divpage);

        },
        error:function(data){
            alert("+++++error++");
        }
    });

}




