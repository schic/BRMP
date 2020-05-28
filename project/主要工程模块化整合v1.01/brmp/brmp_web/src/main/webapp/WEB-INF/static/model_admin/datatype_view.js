/***********************************************************************
 * datatype页面js 数据模型管理 页面  处理视图的js
 * 
 * 
 *
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


var ownceClickModel = 0;
var ownceClickDic = 0;
$(".leftFont .fontBg div").click(function(){
    $(".right .mainSame").eq($(this).index()).show().siblings(".mainSame").hide();
    
    var a = $(this)[0];
    var b = $(".fontBg2")[0];
    if (ownceClickModel == 0 && a===b ){
	    $('table#model').datagrid({
	    	fit: true,//初始化查询一次，使table自适应屏幕
	    	url:'model_admin/datatype/ajax/querySelect',
			method:'post',
		});
	    ownceClickModel++;
    }
    
    var c = $(".fontBg3")[0];
    if (ownceClickDic == 0 && a===c ){
	    $('table#dic').datagrid({
	    	fit: true,//初始化查询一次，使table自适应屏幕
	    	url:'dic_admin/datatype/ajax/dicQuery',
			method:'post',
		});
	    ownceClickDic++;
    }
    
});