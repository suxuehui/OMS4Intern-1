/**
 * Created by GONG036 on 2016/12/8.
 */
var outselectmode=0;
var outquerydata="";
var outselectModeTemp=0;
var outqueryDateTemp="";
var outpagenow
var outpagenowTem

/*出库单的分页显示，调取数据*/
window.onload= outGetnowPage(1);
var outboundArray=new Array();//定义全局数组，记录已点击的checkbox

//点击查询时无结果就显示提示
function outGNPage(pagenow){

    //此处不可直接调用outGetnowPage(page)函数，否则第一次进入页面不查寻且无数据也会有提示信息
    outpagenow=pagenow;
    outselectmode =$("#outselectid option:selected").val();//查询条件
    outquerydata=$("#outtxt").val();//查询值

    if(outselectmode==0){
        alert("请选择查询条件")
        $("#outtxt").val("");
        outselectmode=outselectModeTemp;
        outquerydata=outqueryDateTemp;
        outpagenow=outpagenowTem;
        return;
    }
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../outboundorder/listseach',
        data : {
            currentpage: outpagenow,
            toseachid: outselectmode,
            txtvalue: outquerydata
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            //打开数据为空时设置全局变量以提示信息
            if(data.list.length==0){
                alert("查询无结果！")
                $("#outselectid").val(0);
                $("#outtxt").val("");
                outselectmode=outselectModeTemp;
                outquerydata=outqueryDateTemp;
                outpagenow=outpagenowTem;
                return;
            }
            outselectModeTemp=outselectmode ;
            outqueryDateTemp=outquerydata ;
            outpagenowTem=outpagenow;
            outajax(data)
        },
        error:function(){
            self.location="../login/login" ;
            alert("登录已失效，请重新登录！");
        }
    });
}

//显示ajax 成功回调的数据
function outajax(data){
    var datapage = data.pagelist;
    var datalist = data.list;
    //清除母页面信息
    $("#outboundertab tbody tr").eq(0).nextAll().remove();
    //清除子页面信息
    $("#outboundertabson tbody tr").eq(0).nextAll().remove();
    document.getElementById("outbtn").disabled=true;
    outboundArray.length=0;//每次分页就将勾选数组初始化
    var o=0;
    for(o;o<datalist.length;o++) {
            var list = datalist[o];
            //将同步状态的显示格式进行修改
            booleantoString(list);
            var p=o+1;
            var html = '<tr name="'+list.oid+'"><td>'+p+'</td><td><input type="checkbox" id="' + list.oid + '"' +
                'onclick="tooutcheck(this)"  name="outck"></td><td>';
            html += '<button id="' + list.oid + '"style=" border-style:none;outline:none;background: transparent;" ondblclick="outdblclick(this.id)" onclick="outsgclick(this.id)">' + list.oid + '</button></td><td>'
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
    //分页设置
    outGetNavPage(datapage.totalPageCount,datapage.pageNow);
}

function outGetnowPage(pagenow){
    $("#outselectid").val(0);
    $("#outtxt").val("");
    outselectmode=0;
    outquerydata="";
    outselectModeTemp=0;
    outqueryDateTemp="";
    outpagenow=pagenow;
    outpagenowTem=outpagenow;
    //ajax调用后台方法获取数据并展示
    $.ajax({
        type : 'get',
        url :'../outboundorder/listseach',
        data : {
            currentpage: outpagenow,
            toseachid: outselectmode,
            txtvalue: outquerydata
        },
        contentType: "application/json; charset=utf-8",
        dataType:"json",
        success:function(data) {
            outajax(data)
        },
        error:function(){
            self.location="../login/login" ;
            alert("登录已失效，请重新登录！");
        }
    });
}
//将同步状态的显示格式进行修改
function booleantoString(list){
    if(list.synchrostate){
        list.synchrostate="已接收"
    }
    else{
        list.synchrostate="已发送"
    }

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
            //设置标记颜色
            $("#outboundertab tbody tr").eq(0).nextAll().css('background-color','white')
            $("tr[name="+oid+"]").css('background-color','#F4F5F3');

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
                    if (rglist.hasOwnProperty(i)) {
                        var obj = rglist[i];//获取关系表的一个对象
                        var god = gdlist[i];//获取商品表的一个对象
                            var html = '<tr><td><input type="checkbox"/></td><td>' + outboindid + '</td><td>'
                                + warehouseobid + '</td><td>' + god.goodsno + '</td><td>'
                                + god.goodsname + '</td><td>' + obj.goodnum + '</td><td>'
                                + obj.goodnum + '</td></tr>'
                            $("#outboundertabson tbody  ").append(html);
                    }}
                outpagelistson(datapage.totalPageCount,datapage.pageNow , oid);
            },
            error: function () {
                self.location="../login/login" ;
                alert("登录已失效，请重新登录！");
            }
        });
    }


