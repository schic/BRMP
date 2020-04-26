<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.wondersgroup.base.login.model.AuthInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

AuthInfo authInfo = (AuthInfo) request.getSession().getAttribute(com.wondersgroup.base.login.model.AuthConstants.SESSION_USER_CURRENT_INFO);
%> 
<!DOCTYPE html>   
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> -->  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>申请管理</title>
<%@ include  file="../common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用 -->



</head>
<body onload="setInit('<%=authInfo.getUserType()%>')">

	<!-- 页面通用头部引用 -->
	<%@ include file="../common/head_myhtml.jsp" %>

      
    </div>
		<table id="apply" class="easyui-datagrid" title="资源申请管理，管理员管理用户申请的资源,审核用户的操作" style="width:98%;height:450px"
		        data-options="rownumbers:true,singleSelect:true,url:'apply_management/apply/ajax/querySelect',method:'post',toolbar:'#tb',footer:'#ft',pagination:'true',pagePosition:'bottom'">
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
		    <a id="testSendData" class="easyui-linkbutton" iconCls="icon-help" plain="true" onclick="setAudit()">查看详情并审核</a>
		</div>
		
  </div>





<!-- 数据申请审核弹出窗口 
-->
<div id="apply_data" class="easyui-dialog" title="数据申请"  closed="true" data-options="iconCls:'icon-man',modal:'false',onClose:afterCloseApplyData,buttons:'#table_apply_data'" style="width:80%;height:90%;padding:10px">
	
		 
	<div id="2west" style="width:50%;height:100%;background-color:#D2E9FF;float:left;">
		<h2>基础资源申请</h2>
		
		<div class="col-md-8 col-md-offset-2">
		  <form name="sentMessage" id="Form" action="apply_management/apply4system_form" method="post" enctype="application/x-www-form-urlencoded" novalidate>
			<div class="row">
			  <div class="col-md-6">
				<div class="form-group">
				  <p>申请资源目录名称<span id="applyNameVerify"></span></p>
				  <input type="text" id="apply_name" name="applyName" class="form-control" disabled="true">
				  <p class="help-block text-danger"></p>
				</div>
			  </div>
			  
			  <div class="col-md-6">
				<div class="form-group">
				  <p>申请单位名称</p>
				  <input type="text" id="apply_org_name" name="applyOrgName" class="form-control" disabled="true">
				  <p class="help-block text-danger"></p>
				</div>
			  </div>
			  
			  <div class="col-md-6">
				<div class="form-group">
				  <p>申请人<span id="applyUserVerify"></span></p>
				  <input type="text" id="apply_user" name="applyUser" class="form-control" disabled="true">
				  <p class="help-block text-danger"></p>
				</div>
			  </div>
			  
			</div>
			
			<div class="form-group">
			  <p>申请资源用途说明<span id="applyDirectionVerify"></span></p>	
			  <textarea name="applyDirection" id="apply_direction" class="form-control" rows="4" disabled="true"></textarea>
			  <p class="help-block text-danger"></p>
			</div>
			
		  </form>
		</div>
		
	</div>	
	
	<div id="apply_data_east" style="width:50%;height:100%;float:right;">
		<table id="table_apply_data_east" class="easyui-datagrid" title="申请的资源字段" data-options="rownumbers:true,singleSelect:false,"  style="width:100%;height:100%">
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
	<div id="table_apply_data" style="padding:2px 5px;">
	    <a id="auditPass" class="easyui-linkbutton" style="background-color:#BBFFBB;" iconCls="icon-ok" plain="true" onclick="auditPass()">审核通过</a>
	    <a id="auditReject" class="easyui-linkbutton" style="background-color:#A6FFA6;" iconCls="icon-cancel" plain="true" onclick="auditReject()">审核拒绝</a>
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelApplyData()">关闭</a>
	</div>
</div>



	<!-- 页面通用底部引用 -->
	<%@ include file="../common/bottom_myhtml.jsp" %>

<script type="text/javascript" src="static/apply_management/apply.js"></script>
</body>
</html>