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
<body onload="">
<!-- 页面通用头部引用 -->
<%@ include file="../common/head.jsp" %>

<div id="contact" class="text-center" style="background:url('static/common/img/intro-bg.jpg');">
  <div class="container">
    <div class="section-title center">
      <h2>用户的申请资源管理</h2>
      <hr>
      <p>用户申请的资源,用户查看审核状态,并完成api调用</p>
      <span id="errorMessage"><p><font color="red">${ requestScope.errorMessage}</font></p></span><br>
    </div>
		<table id="apply" class="easyui-datagrid" title="资源申请管理" style="width:100%;height:350px"
		        data-options="rownumbers:true,singleSelect:true,url:'apply_management/apply/ajax/querySelectUser',method:'post',toolbar:'#tb',footer:'#ft',pagination:'true',pagePosition:'bottom'">
		    <thead>
		        <tr>
		        	<!-- auditStatus;//审核状态  1:待审核 2:审核拒绝 9:审核通过 -->
		            <th data-options="field:'userName',width:80,align:'center'">申请人账号</th>
		            <th data-options="field:'applyOrgName',width:80,align:'center'">申请机构名称</th>
		            <th data-options="field:'applyOrgName',width:80,align:'center'">申请机构名称</th>
		            <th data-options="field:'modelId',width:220,align:'center', formatter:getModelName">申请的主要资源</th>
		            <th data-options="field:'applyName',width:160,align:'center'">申请资源目录名称</th>
		            <th data-options="field:'applyUser',width:120,align:'center'">申请人</th>
		            <th data-options="field:'applyTime',width:160,align:'center'">申请日期</th>
		            <th data-options="field:'auditStatus',formatter:getAuditStatus,width:60,align:'center'">审核状态</th>
		        </tr>
		    </thead>
		</table>
		<div id="tb" style="padding:2px 5px;">
			创建时间: <input id="beginCreatDate" class="easyui-datebox" style="width:110px" editable="false">
			至: <input id="toCreatDate" class="easyui-datebox" style="width:110px" editable="false">
		            审核状态: 
		    <select id="auditStatus4select" class="easyui-combobox" panelHeight="auto" style="width:100px" editable="false">
		        <option value="">-请选择-</option><!-- 1:待审核 2:审核拒绝 9:审核通过 -->
		        <option value="1">待审核</option>
		        <option value="2">审核拒绝</option>
		        <option value="9">审核通过</option>
		    </select>
		    <a class="easyui-linkbutton" iconCls="icon-search" onclick="querySelect()">查找</a>
		    <a class="easyui-linkbutton" iconCls="icon-redo" onclick="reSet4querySelect()">重置</a>
		</div>
		<div id="ft" style="padding:2px 5px;">
		    <a id="getApiSendData" class="easyui-linkbutton" iconCls="icon-help" plain="true" onclick="getApiSendData()">查看api</a>
		</div>
		
  </div>
</div>

<!-- 查看api的弹出窗口 -->
<div id="getApi" class="easyui-dialog" title="调用的api"  closed="true" data-options="iconCls:'icon-help',modal:'false',onClose:afterCancelApi,toolbar:'#transmission_test_toolbar',buttons:'#transmission_test_button'" style="width:80%;height:80%;padding:10px">
	<div style="width:100%">
		<label><p>接口名称:</p></label>&nbsp;
		<input id="apiName" name="apiName" class="easyui-textbox easyui-validatebox" type="text" editable="false" style="width:100%"/>
	</div>
	
	<div style="width:100%">
		<label><p>请求URL:</p></label>&nbsp;
		<input id="centerUrl" name="centerUrl" class="easyui-textbox easyui-validatebox" type="text" editable="false" style="width:100%"/>
	</div>
	
	<div style="width:100%">
		<label><p>passKey:</p></label>&nbsp;
		<input id="passKey" name="passKey" class="easyui-textbox easyui-validatebox" type="text" editable="false" style="width:100%"/>
	</div>
	
	<p></p>
	<div style="width:100%">
		<label><p>传参样例:采用restful风格传输,使用post方法,参数为一个json字符串的请求</p></label>&nbsp;
		<textarea name="test_params" id="test_params" class="form-control" rows="10" placeholder="传参样例" readonly="true"></textarea>
	</div>
	<p></p>
	<div id="apply_data_east" style="width:100%;height:400px;">
		<table id="table_apply_data_east" class="easyui-datagrid" title="返回资源字段" data-options="rownumbers:true,singleSelect:false,"  style="width:100%;height:100%">
			<thead>
		        <tr>
		        	<th data-options="field:'modelId',width:100,align:'center',formatter:getModelName">资源来源</th>
		            <th data-options="field:'modelColName',width:100,align:'center'">表字段名称</th>
		            <th data-options="field:'modelColDisplayName',width:180,align:'center'">字段通用名称</th>
		            <th data-options="field:'modelColType',width:50,align:'center',formatter:getModelColType">字段类型</th>
		            <th data-options="field:'modelColLenth',width:60,align:'center',formatter:getModelColLenth">字段长度</th>
		            <th data-options="field:'modelColDecimalLenth',width:60,align:'center',formatter:getModelColDecimalLenth">小数长度</th>
		        </tr>
		    </thead>
		</table>
		
	</div>
	
	
	<div id="transmission_test_button" style="padding:2px 5px;">
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelApi()">关闭api查看</a>
	</div>
	
</div>
	

<script type="text/javascript" src="static/apply_management/apply4user.js"></script>
</body>
</html>