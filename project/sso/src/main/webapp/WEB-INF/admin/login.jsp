<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 

<!--%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %-->

<!DOCTYPE html>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> -->  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>请登录</title>	
<link rel="shortcut icon" href="static/image/faviconS.ico" type="image/x-icon"> <!-- 页面title角标  -->

<link rel="stylesheet" type="text/css" href="static/css/login/normalize.css" />
<link rel="stylesheet" type="text/css" href="static/css/login/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="static/css/login/component.css" />


</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<h3>欢迎来到单点登录</h3>
					<span id="errorMessage"><font color="red">${ requestScope.errorMessage}</font></span><br>
					<form action="login/verify" method="post" enctype="application/x-www-form-urlencoded">
						<div class="input_outer">
							<span class="u_user"></span>
							<input required name="userName" id="userName" class="text" style="color: #FFFFFF !important" type="text" placeholder="${ requestScope.errorMessage} 请输入用户名">
						</div>
						<div class="input_outer">
							<span class="us_uer"></span>
							<input required name="passWord" id="passWord" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;" type="password" placeholder="请输入密码">
						</div>
						<div class="mb2">
							<input type="submit" value="登录" id="submit" class="act-but submit" style="color: #FFFFFF">
						</div>
						<input type="password" name="test" id="test" hidden="">
						<input type="password" name="nextURL" id="nextURL" hidden="" value="<%=request.getParameter("nextURL")%>" >
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<script type="text/javascript" src="static/js/js_util/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="static/js/js_util/cipher_util/md5.js"></script>
	<script type="text/javascript" src="static/js/js_util/EasePack.min.js"></script>
	<script type="text/javascript" src="static/js/js_util/TweenLite.min.js"></script>
	<script type="text/javascript" src="static/js/login.js"></script>
	
</body>
</html>