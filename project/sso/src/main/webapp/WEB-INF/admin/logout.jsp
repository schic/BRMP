<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> -->  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>单点登录系统 注销</title>
<link rel="shortcut icon" href="static/image/faviconS.ico" type="image/x-icon"> <!-- 页面title角标  -->

<script type="text/javascript" src="static/js/js_util/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

$(function() {
	var urls = <%=request.getAttribute("URLS")%>;
	
	for(var i=0;i<urls.length;i++) {
		
		url = urls[i];
		/*
		 * 注销各个已登录的系统
		 */
		console.log(url); 
		//alert(url); 
		 
		$.ajax({
			type : "post",
			url : url+"/ssoLogout",
			error:function(err){
				$.ajax({
					type : "post",
					url : url+"/logout.jsp",
				});
	        }
		});
		
		if(i==urls.length-1){
			//console.log("到这里设置页面跳转");
			var nextURL0 = "<%=basePath%>";
			var nextURL1 = "login/after_logout?nextURL=";
			var nextURL2 = "<%=request.getParameter("nextURL")%>"; 
			var nextURL = nextURL0 + nextURL1 + nextURL2;
			//console.log(nextURL);
			$("a#nextURL").attr("href",nextURL);
			window.location.href=nextURL;
		}
	}

});
</script>

</head>
<body>
	<h1>单点登录注销中，完成后自动跳转。 或者<a id="nextURL" href="<%=basePath.concat("login/after_logout?nextURL=").concat(request.getParameter("nextURL"))%>">点此跳转</a></h1>
</body>
</html>