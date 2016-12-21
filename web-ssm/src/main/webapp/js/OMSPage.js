// JavaScript Document
//主菜单切换
$(document).ready(function(){
   $(".menu ul li").click(function(){
	   $(this).addClass("on").siblings().removeClass("on"); //切换选中的按钮高亮状态
       var index=$(this).index(); //获取被按下按钮的索引值，需要注意index是从0开始的
       $(".content > div").eq(index).show().siblings().hide(); //在按钮选中时在下面显示相应的内容，同时隐藏不需要的框架内容
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
$(document).ready(function(){
	$(".storeSearch ul li").click(function(){
		 var index=$(this).index();
		 $(".popupAll .storeShow > div").eq(index).show().siblings().hide();
    });
});

$(document).ready(function(){
	$(".storeShow .popupTop .iconClose").click(function(){
		 $(".popupAll .storeShow > div").hide();	
    });
});

//导入订单弹框
$(document).ready(function(){
	$(".orderTit.leading .leadingIn").click(function(){
		 $(".popupAll .leadingInShow").show();
    });
});

$(document).ready(function(){
	$(".leadingInShow .popupTop .iconClose").click(function(){
		 $(".popupAll .leadingInShow").hide();	
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

//loading
$(document).ready(function() {
    $(".popupAll .save,.popupAll .leadingBtn").click(function(){
	  $(".loading").show();
	});
});