//--获取多个checkbox选中项,并且把得到的oid拼接
function getOid() {
    var a = document.getElementsByName("exceptionck");
    var info = "";
    for(var i=0;i<a.length;i++)
    {
        if(a[i].checked){
            var info2 = (info + a[i].value) + (((i + 1)== a.length) ? '':',');
            $("#exception_inbtn").attr("disabled",false);
        }
    }
    return info2;
}

function exception_del() {
    var oid = getOid();
    var parm = {oid: oid};//将参数传到后台
    $.post("../exceptionOrder/cancelException", parm, function () {
        window.onload= GetnowPage(1);
    });
}

//点击查看出库订单进入详情页
function exception_details(){
    var oid2 = getOid();
    var Array = oid2.split(",");
    if(Array.length>1){
        if(Array[1]==[]){//选中第一条数据时，其后会有一个逗号，需将其判断出来
            var oid3 = Array[0];
            window.open("../exceptionOrder/details?oid3="+oid3);
        }else{
            alert("一次只能查看一条订单的信息");
            var excheck = document.getElementsByName("exceptionck");
            for(var i=0;i<excheck .length;i++)
            {
                excheck[i].checked=false;
                $("#exception_inbtn").attr("disabled",true);
            }
        }
    }else
    {
        for(var j=0;j<Array.length;j++){
            oid3 = Array[j];
            window.open("../exceptionOrder/details?oid3="+oid3);
        }
    }
}

//处理异常
function handleException(){
    var oid = getOid();
    var parm = {oid2: oid};//将参数传到后台
    $.post("../exceptionOrder/handleException", parm, function (data) {
        GetnowPage(1);
        var msg=data.msg;
        if(msg==1){
            alert("异常类型不完全相同");
        }
    },"json"
    );
}
