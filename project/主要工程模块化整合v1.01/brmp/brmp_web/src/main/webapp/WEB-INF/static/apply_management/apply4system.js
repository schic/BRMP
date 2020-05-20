/***********************************************************************
 * apply页面js 声请管理页面js
 * 
 ***********************************************************************/


/************************************************
 * apply页面js初始化方法
 * 
 ************************************************/
$(function(){
	
	/**
	$.ajax({//初始化加载
		type : "post",
		url : "",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : true,
		//data : {tableNameA:selectModel.modelName,beginDate:beginDate,endDate:endDate},
		success : function(msg) {
			
		},
	});
	
	$("input#sys_name").bind("keyup blur",function() {//验证系统名称
		var sysName = $("input#sys_name").val();//获取文本框的值
		//到服务器做验证
		$.ajax({
			type : "post",
			url : "apply_management/verify_sysname",
			data : "sysName=" + sysName,
			success : function(nameVerify) {
				//收到服务端的验证结果，给出正确的提示	
				//alert(nameVerify);
				$("span#sysnameVerify").html(nameVerify);
			},
		});
	});
	 */
	
	
});

/**
 * 初始化系统id
 */
var encryptionType = '';

function setInit(encryptionType){
	this.encryptionType = encryptionType;
	$(" select option[value='"+encryptionType+"']").attr("selected","selected"); 
	console.log('encryptionType:'+encryptionType);
}


/************************************************
 * apply页面js初始化↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
 * 
 ************************************************/

