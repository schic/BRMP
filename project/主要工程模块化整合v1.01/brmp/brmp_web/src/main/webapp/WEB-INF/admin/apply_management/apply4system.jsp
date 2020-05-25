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
<%@ include  file="../common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用
<link rel="stylesheet" href="static/common/css/myhtml_css/resources.css" />
 -->
<link rel="stylesheet" href="static/common/css/myhtml_css/input.css" />


</head>
<body onload="setInit('${sessionScope.originSystemInfo.encryptionType}')">
<!-- 页面通用头部引用 -->
<%@ include file="../common/head_myhtml.jsp" %>

<div class="zysqqr subBot">
    <div class="contain">
        <div class="top">
			<div class="font">
				<h5>系统接入信息申请</h5>
				<hr>
				<p>系统接入,填写系统的基本信息,供系统接入后建立模型传输数据使用</p>
				<span id="errorMessage"><p><font color="red">${ requestScope.errorMessage}</font></p></span><br>
				<span id="infoMessage"><p><font color="green">${ requestScope.infoMessage}</font></p></span><br>
		    </div>
            <div class="topCon">
             	<form name="sentMessage" id="Form" action="apply_management/apply4system_form" method="post" enctype="application/x-www-form-urlencoded" novalidate>
			        <div class="topConOne topConSame clearfix">
			            <div class="leftOne leftSame">
			            	<label for="">设置系统中文名：<span id="sysnameVerify"></span></label>	
			              	<input type="text" id="sys_name" name="sysName" class="form-control" placeholder="接入系统中文名称用于页面展示" required="required" value="${sessionScope.originSystemInfo.originSystemCname}">
			            </div>
			            <div class="leftTwo leftSame">
	                        <label for="">设置接口通过码：</label>
              				<input type="text" id="sys_password" name="sysPassword" class="form-control" placeholder="用于模型建立后传入数据的接口验证" required="required" value="${sessionScope.originSystemInfo.password}">
	                    </div>
			        </div>
			        <div class="topConOne topConSame clearfix">
			            <div class="leftOne leftSame">
			            	<label for="">设置加密方式：</label>	
			              	<select id="encryption_type" name="encryptionType" class="easyui-combobox" panelHeight="auto" style="width:120px" editable="false" >
						        <option value="0">无</option>
						        <option value="1">AES</option>
						        <option value="2">AESVi</option>
						        <option value="3">DES</option>
						        <option value="4">ThreeDES</option>
						        <option value="5">AES变化</option>
						        <option value="6">AESVi变化</option>
						        <option value="7">DES变化</option>
			              	</select>
			              	
			            </div>
			            
			        </div>
			        <div class="topConTwo topConSame">
	                    <label for="">接入系统接口的URL地址：</label>
	                    <div>
	                        <textarea style="width:100%" name="systemUrl" id="message" class="form-control" rows="4" placeholder="接入系统接口的URL地址(将来平台中心可能会主动调用系统接口，未完成可为空)" >${sessionScope.originSystemInfo.originSystemUrl}</textarea>
	                    </div>
	                </div>
			        <div id="success"></div>
			        <button type="submit" class="btn2 btn" style="background: #ebaa3d;">提交信息</button>
				</form>
            </div>
        </div>
    </div>
</div>
	
	<!-- 页面通用底部引用 -->
	<%@ include file="../common/bottom_myhtml.jsp" %>		

<script type="text/javascript" src="static/apply_management/apply4system.js"></script>
</body>
</html>