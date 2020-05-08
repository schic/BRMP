/**
 * 通用js
 */

//easyui日期格式
function myformatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}

//去空格,去换行
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,'').replace(/[\r\n]/g,'');
}



/***************************************************************
 * 通用页面的js
 **************************************************************/
function longinUser(userName){
	//alert(userName);
	if (userName) {
		$.messager.confirm('注销','是否要注销该用户?',function(r){
			if(r){
				window.location.href = 'http://192.168.1.114:8980/brmp-web/ssoLogout';
			}	
		});
	} else {
		window.location.href = 'http://192.168.1.114:8980/brmp-web/index';
	}
	
	
}

