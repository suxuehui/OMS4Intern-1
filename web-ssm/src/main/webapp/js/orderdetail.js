// JavaScript Document
// $(document).ready(function() {
//     $(".borderBottom .contentBtn:last-child").click(function(){
// 	  $(".loading").show();
// 	});
// });

$(document).ready(function(){
    $(".borderBottom .contentBtn:first-child").click(function(){
	  $(".table.edit .editArea").removeAttr("readonly");
	  $(".table.edit .editArea").removeClass("edit");
        $("#cancleOrderBtn,#save").attr("disabled",false);
	});
});

$(document).ready(function(){
    $(".borderBottom .contentBtn.cancle").click(function(){
	  $(".table.edit .editArea").attr("readonly","readonly");
	  $(".table.edit .editArea").addClass("edit");
	});
});
function cancel() {
	document.edit.action="cancelEdit";
	document.edit.submit();
}

$(function () {
    $("#save").click(function () {
            var receiverName=$("#receiverName").val();
            if(receiverName=="")
            {
                alert("收货人不能为空!");
                return;
            }
            var receiverMobel=$("#receiverMobel").val();
            if(receiverMobel=="")
            {
                alert("手机号不能为空!");
                return;
            }
            var reg = /^[1][3,5,7,8]\w{9}$/;
            if(!reg.test(receiverMobel))
            {
                alert("手机号格式不正确!");
                return;
            }
            var receiverProvince=$("#receiverProvince").val();
            if(receiverProvince=="")
            {
                alert("省不能为空!");
                return;
            }
            var receiverCity=$("#receiverCity").val();
            if(receiverCity=="")
            {
                alert("市不能为空!");
                return;
            }
            var receiverArea=$("#receiverArea").val();
            if(receiverArea=="")
            {
                alert("区不能为空!");
                return;
            }
            var detailAddress=$("#detailAddress").val();
            if(detailAddress=="")
            {
                alert("详细地址不能为空!");
                return;
            }
            var zipCode=$("#zipCode").val();
            if(zipCode=="")
            {
                alert("邮编不能为空!");
                return;
            }
        document.edit.action="modifyInfo";
        document.edit.submit();
    });
})
$(function () {
    $("#cancleOrderBtn,#save").attr("disabled",true);
    var status=$("#orderStatus").text();
    var statusArray=["已发货","已完成","已取消"];
    if(status=="已发货"||status=="已完成"||status=="已取消")
    {
        $("#editOrder").attr('disabled',true);
    }
})