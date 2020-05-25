/***********************************************************************
 * apply页面js 声请管理页面js
 * 
 ***********************************************************************/


/************************************************
 * apply页面js初始化方法
 * 
$(function(){
	
	
	
});
 ************************************************/

/**
 * 初始化系统id
 */
var encryptionType = '';

function setInit(encryptionType){
	this.encryptionType = encryptionType;
	//$("select#encryption_type").combobox('select',encryptionType);
	$(" select#encryption_type option[value='"+encryptionType+"']").attr("selected","selected"); 
	//console.log('encryptionType:'+encryptionType);
}


/************************************************
 * apply页面js初始化↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
 * 
 ************************************************/

