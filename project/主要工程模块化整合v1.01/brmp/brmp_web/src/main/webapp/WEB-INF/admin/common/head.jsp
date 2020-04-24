<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<nav id="menu" class="navbar navbar-default navbar-fixed-top">
  <div class="container"> 
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand page-scroll" href="#page-top"><i class="fa fa-bookmark-o"></i> 基础资源管理平台</a> </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#home" class="page-scroll">首页</a></li>
        <li><a href="resource_catalog/catalog" class="page-scroll">资源目录</a></li>
        <li><a href="model_admin/datatype" class="page-scroll">数据模型</a></li>
        <li><a href="#home" class="page-scroll">开放统计</a></li>
        <li><a href="apply_management/apply" class="page-scroll">申请管理</a></li>
        <li><a onclick="longinUser('${sessionScope.ssoUser.uname}')" class="page-scroll"><font color="blue">用户 ${sessionScope.ssoUser.uname}</font></a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>