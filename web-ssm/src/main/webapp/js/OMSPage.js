// JavaScript Document
//主菜单切换
$(document).ready(function(){
   $(".menu ul li").click(function(){
	   $(this).addClass("on").siblings().removeClass("on"); //切换选中的按钮高亮状态
       var index=$(this).index(); //获取被按下按钮的索引值，需要注意index是从0开始的
       $(".content > div").eq(index).show().siblings().hide(); //在按钮选中时在下面显示相应的内容，同时隐藏不需要的框架内容
     $(".select_hide").hide();
   });
});

//用户弹框
$(document).ready(function(){
	$(".customSearch ul li").click(function(){
		 var index=$(this).index();
		 $(".popupAll .customShow > div").eq(index).show().siblings().hide();
    });
});

$(document).ready(function(){
	$(".customShow .popupTop .iconClose").click(function(){
		 $(".popupAll .customShow > div").hide();	
    });
});

//商品弹框
$(document).ready(function(){
	$(".productSearch ul li:first-child").click(function(){
		 $(".popupAll .productShow").show();
    });
});

$(document).ready(function(){
	$(".productShow .popupTop .iconClose").click(function(){
		 $(".popupAll .productShow").hide();	
    });
});

//仓库弹框
function addware(obj){
	 var index=$(obj).parent().index();
	 $(".popupAll .storeShow > div").eq(index).show().siblings().hide();
	$(".hbg").show();//显示阴影层
};


$(document).ready(function(){
	$(".storeShow .popupTop .iconClose").click(function(){
		$(".inputList").val("");//清空弹框的输入框的值
		$(".lixx").html("")
		$(".popupAll .storeShow > div").hide();
		$(".hbg").hide();//隐藏阴影层
    });
});

//导入订单弹框
$(document).ready(function(){
	$(".orderTit.leading .leadingIn").click(function(){
		 $(".popupAll .leadingInShow").show();
		$(".hbg").show();//显示阴影层
    });
});

$(document).ready(function(){
	$(".leadingInShow .popupTop .iconClose").click(function(){
		 $(".popupAll .leadingInShow").hide();
		$(".hbg").hide();//隐藏阴影层
    });
});

//列表切换
$(document).ready(function(){
    $(".content>.order:last-child .orderListCent a").bind("click",function(){
	    var num=$(this).attr("id");
		$(this).parent().addClass("on").siblings().removeClass("on");
		$(".orderMain>.relatedOrder").eq(num).show().siblings().hide();
	});
});

$(document).ready(function () {
	$("body").click(function () {
		$("#userClass").removeClass("hide");
		$.ajax({
			type:"get",
			url:"/oms/login/checkSession",
			datatype:"json",
			success:function (data) {
				var role=data.urole;
				var name=data.uname;
				if(data=="")
				{
					alert("登录已失效，请重新登录！");
					window.location.href = "/oms/login/logout";
				}
				var urole=$("#urole").text();
				var uname=$("#uname").text();
                $("#urole").text(role);
				if(!(urole==role)&&role==1)
				{
					$("#userListbut").text("用户");
					$("#userListbut").show();
					//$("#userList").load("/oms/login/loadUser");
					loadUser("user");
				}
				else if(!(urole==role)&&role==2)
				{
					if($("#userListbut").hasClass("on"))
					{
						$("#goodsListbut").addClass("on");
						$("#goodsList").show();
					}
					$("#userListbut").text("");
					$("#userListbut").hide();
					$("#userList").html('');
				}
				if(!(name==uname))
				{
					$("#uname").text(name);
				}
			}
		})
	})
})
//加载用户页面
function loadUser(page) {
	$.ajax({
		type:"get",
		url:"/oms/login/loadUser",
		data:{page:page},
		success:function (data) {
			$("#userList").html(data);
			$("#userClass").addClass("hide");
		}
	})
}