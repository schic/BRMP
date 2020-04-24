/***********************************************************************
 * datatype页面js 数据模型管理 页面  处理视图的js
 * 
 ***********************************************************************/

$(".leftFont .fontBg .fontBg2 span").click(function(){
    $(this).addClass("change");
    $(this).siblings("img").show();
});

$(".leftFont .fontBg .fontBg3 span").click(function(){
    $(this).addClass("change").parents().siblings(".fontBg2").children("span").addClass("change");
    $(this).siblings("img").show();
    $(this).parents().siblings(".fontBg2").children("img").show();
});

$(".leftFont .fontBg div").click(function(){
    $(".right .mainSame").eq($(this).index()).show().siblings(".mainSame").hide();
});