/**
 * Created by GONG036 on 2016/12/8.
 */
 /*出库单的分页显示，调取数据*/
window.onload= outGetnowPage(1);
var outboundArray=new Array();//定义全局数组，记录已点击的checkbox
function outGetnowPage(pagenow){
    var  myselect=document.getElementById("outselectid");
    var index=myselect.selectedIndex ;
    var optxt=myselect.options[index].value;//查询条件
    var search_value=document.getElementById("outtxt").value;//查询值
    var s1=pagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../outboundorder/listseach',
        data : {
            currentpage: s1,
            toseachid: optxt,
            txtvalue: search_value
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            var datapage = data.pagelist;
            var datalist = data.list;
            //关闭数据为空时的提示信息
            document.getElementById("infodiv").style.display="none"
            //清除母页面信息
             $("#outboundertab tbody tr").eq(0).nextAll().remove();
            //清除子页面信息
            $("#outboundertabson tbody tr").eq(0).nextAll().remove();
            document.getElementById("outbtn").disabled=true;
            outboundArray.length=0;//每次分页就将勾选数组初始化
            for(var obj in datalist) {
                 if(datalist.hasOwnProperty(obj)) {
                     var list = datalist[obj];
                     var html = '<tr><td><input type="checkbox" id="' + list.oid + '"' +
                         'onclick="tooutcheck(this)"  name="outck"></td><td>';
                     html += '<button id="' + list.oid + '" style="border-style:none;outline:none;" ondblclick="outdblclick(this.id)" onclick="outsgclick(this.id)">' + list.oid + '</button></td><td>'
                         + list.channeloid + '</td><td>'
                         + list.orderstatus + '</td><td>' + list.warehouseobid + '</td><td>'
                         + list.outboundid + '</td><td>' + list.outboundstate + '</td><td>'
                         + list.synchrostate + '</td><td>' + list.receivername + '</td><td>'
                         + list.expresscompany + '</td><td>' + list.expressid + '</td><td>'
                         + list.receiveraddress + '</td><td>'
                         + list.createdtime + '</td><td>' + list.modifytime + '</td><td>'
                         + list.modifyman + '</td></tr>'
                     $("#outboundertab tbody ").append(html);
                 }
            }
            //打开数据为空时的提示信息
            if(datalist.length==0){
                document.getElementById("infodiv").style.display="block"
            }
            //分页设置
            outGetNavPage(datapage.totalPageCount,datapage.pageNow);
        },
        error:function(){
            alert("+++++error++");

        }
    });
}

/*点击checkbox */
   function tooutcheck(element)
   { //id为checkbox的id
       var oid=$(element).attr("id");
       var checkedVar = element.checked;
        if (checkedVar==true) {
            outboundArray.push(oid);
        }
        else {
            for (var i = 0; i < outboundArray.length; i++) {
                if (outboundArray[i] == oid) {
                    outboundArray.splice(i, 1);
                }
            }
        }
        //判断选中的checkbox
       if (outboundArray.length == 1) {
        document.getElementById("outbtn").disabled = false;
       }
       else{
           document.getElementById("outbtn").disabled = true;
       }
   }

//点击查看出库订单进入详情页
    function tooutOrderdetail() {
            document.getElementById("outbtn").disabled = false;
            var outoid=outboundArray[0];
            window.open("../outboundorder/details?oid=" + outoid);
    }

    /*出库单 单击双击事件跳转*/
    var outisdb;
    function outsgclick(oid) {
        outisdb = false;
        window.setTimeout(cc, 250);
        function cc() {
            if (outisdb != false)
                return;
            outpostOid(oid);//单击跳转子页面
        }
    }

//双击跳转详细页面
    function outdblclick(oid) {
        outisdb = true;
        window.open("../outboundorder/details?oid=" + oid);
    }

//单击跳转子页面
    function outpostOid(oid) {
        oid=oid.substring(2);//截取字符串
        outpageson(oid, 1);
    }

//子页面分页展示outpageson
    function outpageson(oid, pagenow) {
        oid="OO"+oid;//补全oid字符串
        $.ajax({
            type: 'get',
            url: '../outboundorder/listobolson',
            data: {
                oid: oid,
                currentpage:pagenow,
            },
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                var outboindid = data.obolist[0].outboundid;//出库单号
                var warehouseobid = data.obolist[0].warehouseobid;//仓库出库单号
                var rglist = data.rglist;
                var gdlist = data.gdslist;
                var datapage = data.pagelist;
                $("#outboundertabson tbody tr").eq(0).nextAll().remove();
                for (var i in rglist) {
                        var obj = rglist[i];//获取关系表的一个对象
                        var god = gdlist[i];//获取商品表的一个对象
                        var html = '<tr><td>' + outboindid + '</td><td>'
                            + warehouseobid + '</td><td>' + god.goodsno + '</td><td>'
                            + god.goodsname + '</td><td>' + obj.goodnum + '</td><td>'
                            + obj.goodnum + '</td></tr>'
                        $("#outboundertabson tbody  ").append(html);
                    }
                outpagelistson(datapage.totalPageCount,datapage.pageNow , oid);
            },
            error: function () {
                alert("error");
            }
        });
    }


