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
<title>基础资源管理平台</title>
<%@ include  file="./common/project.jsp" %><!-- 页面的通用静态文件引用 -->


</head>
<body>

<!-- 页面通用头部引用 -->
<%@ include file="./common/head.jsp" %>
	
<!-- Header -->
<header id="header">
  <div class="intro">
    <div class="overlay">
      <div class="container">
        <div class="row">
          <div class="intro-text">
            <h1>基础资源管理平台</h1>
            <p>四川省卫生健康基础资源管理及服务</p>
            <span id="errorMessage"><p><font color="red">${ requestScope.errorMessage}</font></p></span><br>
            <a href="#about" class="btn btn-custom btn-lg page-scroll">平台描述</a> </div>
        </div>
      </div>
    </div>
  </div>
</header>
	
<!-- About Section -->
<div id="about">
  <div class="container">
    <div class="section-title text-center center">
      <h2>平台描述</h2>
      <hr>
    </div>
    <div class="row">
      <div class="col-xs-12 col-md-6"> <img src="static/common/img/about.jpg" class="img-responsive thumbnail" alt=""> </div>
      <div class="col-xs-12 col-md-6">
        <div class="about-text">
          <h3>服务和运行</h3>
          <p>现已接入    2    个部门，    12   个系统数据资源，      452   个数据模型，总计           55890         条数据记录。</p>
          <p>可能是的经典服饰的会计师的活动快速导航就打发撒旦抠脚大汉饭卡上打开</p>
          <div class="list-style">
            <div class="col-lg-6 col-sm-6 col-xs-12">
              <ul>
                <li>建立模型</li>
                <li>资源标识</li>
                <li>资源可定制</li>
                <li>资源维护</li>
                <li>资源存储</li>
              </ul>
            </div>
            <div class="col-lg-6 col-sm-6 col-xs-12">
              <ul>
                <li>标准服务</li>
                <li>资源查询</li>
                <li>资源身份识别及认证</li>
                <li>资源数据批量获取</li>
                <li>资源同步更新</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>
