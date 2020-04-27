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
<link rel="shortcut icon" href="***.ico" type="image/x-icon"> <!-- 页面title角标  -->
<%
request.getSession().setAttribute("ssoUser", null);
String url = "http://".concat(com.wondersgroup.empi.ssofilter.SsoFilter.getSsoUrl()).concat("/sso_login/login/logout?nextURL=").concat(com.wondersgroup.empi.ssofilter.SsoFilter.getLocalUrl());
response.sendRedirect(url);//将注销传给单点登录完成单点登录全部注销
%>
</head>
<body>
	<h1>登录用户注销完成</h1>
</body>
</html>