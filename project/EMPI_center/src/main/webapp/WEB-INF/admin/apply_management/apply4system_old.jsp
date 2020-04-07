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
<title>申请管理</title>
<%@ include  file="../common/project.jsp" %><!-- 页面的通用静态文件引用 -->



</head>
<body>
<!-- 页面通用头部引用 -->
<%@ include file="../common/head.jsp" %>


<!-- Contact Section -->
<div id="contact" class="text-center">
  <div class="container">
    <div class="section-title center">
      <h2>系统接入信息申请</h2>
      <hr>
      <p>系统接入,填写系统的基本信息,供系统接入后建立模型传输数据使用</p>
      <span id="errorMessage"><p><font color="red">${ requestScope.errorMessage}</font></p></span><br>
      <span id="infoMessage"><p><font color="green">${ requestScope.infoMessage}</font></p></span><br>
    </div>
    <div class="col-md-8 col-md-offset-2">
      <form name="sentMessage" id="Form" action="apply_management/apply4system_form" method="post" enctype="application/x-www-form-urlencoded" novalidate>
        <div class="row">
          <div class="col-md-6">
            <div class="form-group">
              <p>系统中文名<span id="sysnameVerify"></span></p>
              <input type="text" id="sys_name" name="sysName" class="form-control" placeholder="接入系统中文名称用于页面展示" required="required" value="${sessionScope.originSystemInfo.originSystemCname}">
              <p class="help-block text-danger"></p>
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <p>接口密码</p>
              <input type="text" id="sys_password" name="sysPassword" class="form-control" placeholder="用于模型建立后传入数据的接口验证" required="required" value="${sessionScope.originSystemInfo.password}">
              <p class="help-block text-danger"></p>
            </div>
          </div>
        </div>
        <div class="form-group">
          <textarea name="systemUrl" id="message" class="form-control" rows="4" placeholder="接入系统接口的URL地址(将来平台中心可能会主动调用系统接口，未完成可为空)" >${sessionScope.originSystemInfo.originSystemUrl}</textarea>
          <p class="help-block text-danger"></p>
        </div>
        <div id="success"></div>
        <button type="submit" class="btn btn-custom btn-lg">提交信息</button>
      </form>
      
    </div>
  </div>
</div>
	

<script type="text/javascript" src="static/apply_management/apply4system.js"></script>
</body>
</html>