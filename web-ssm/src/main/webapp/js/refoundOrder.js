window.onload= GetnowPage(1);//加载页面时就执行函数进入后台

//使用ajax提交数据到后台

function GetnowPage(pagenow){
    var myselect=document.getElementById("selectid");
    var index=myselect.selectedIndex;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("txt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'showRefoundOrderList',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        //dataType:"json",
        success : function(data) {
            //alert(data);
            var data = JSON.parse(data);
            var dataPage = data.pagelist;
            var dataList = eval(data.list);

            $("table tbody tr").eq(0).nextAll().remove();
            for(var obj in dataList){
                var  list=dataList[obj];
                var html='<tr><td><input type="checkbox" value="'+list.oid+'" name="ck" onclick="check()" ></td><td>';
                html+= '<button id="'+list.channeloid+'" style="border-style:none;outline:none;" ' +
                    'ondblclick="dblclick(this.id)" onclick="sgclick(this.id)">'+list.drawbackId+'</button>'+
                    '</td><td>';
                html+='</td><td>'+list.drawbackmoney+'</td><td>'
                    +list.drawbackstatus+'</td><td>'+list.returnedid+'</td><td>' +list.createtime+'</td><td>'
                    +list.modifytime+'</td><td>' +list.modifyman+'</td></tr>'
                $("#table1 tbody ").append(html);
            }
            GetNavPage(dataPage.totalPageCount,dataPage.pageNow,divpage);
        },
        error:function(data){
            alert("+++++error++");
        }
    });


}