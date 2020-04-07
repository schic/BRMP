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
<title>资源目录</title>
<%@ include  file="../common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用 -->
<link rel="stylesheet" href="static/common/css/myhtml_css/input.css" />

</head>
<body onload="init('${sessionScope.ssoUser.userType}')">
<section>
	<!-- 页面通用头部引用 -->
	<%@ include file="../common/head_myhtml.jsp" %>

	<div class="easyui-layout main clearfix" style="height:650px;width:100%">
		<div id="west" region="west" split="true" title=" " style="width:20%;">
	  		<ul id="data_system" class="easyui-datalist" title="数据提供系统" style="width:100%;height:100%"></ul>
	  	</div>
	  	
	  	<div id="center" region="center" title="" style="padding:2px; background:#E6E6F2;" >
	  		<span id="errorMessage"><p><font color="red">${ requestScope.errorMessage}</font></p></span><br>
	    	<span id="infoMessage"><p><font color="green">${ requestScope.infoMessage}</font></p></span><br>
	    
	  		<div style="height:50px;"></div>
	        <div id="tableMenu" style="padding:2px 5px;background:#84C1FF;" >
			    <a id="selectModelData" class="easyui-linkbutton" iconCls="icon-large-shapes" plain="true" onclick="selectModelData()">数据项查看</a>
			    <a id="selectDetailModelData" class="easyui-linkbutton" iconCls="icon-large-chart" plain="true" onclick="selectDetailModelData()">数据详情</a>
			    <a id="apply4getData" class="easyui-linkbutton" iconCls="icon-man" plain="true" onclick="apply4getData()">数据申请</a>
			</div>
			<div style="height:10px;"></div>
			
	  		<table id="table" border="1" bordercolor="#000093" cellspacing="0" cellpadding="10" align="center">
		        <tr align="center">
		            <td width="120px" height="30px"><p>资源描述：</p></td>
		            <td colspan="3"><p id='pDescription'></p></td>
		        </tr>
		        <tr align="center">
		            <td width="120px" height="30px"><p>资源名称：</p></td>
		            <td width="240px"><p id='pModelName'></p></td>
		            <td width="120px"><p>更新周期：</p></td>
		            <td width="240px"><p id='pCycle'>每天</p></td>
		        </tr>
		        <tr align="center">
		            <td height="30px"><p>数据来源：</p></td>
		            <td><p id='pSystemName'></p></td>
		            <td><p>数据量：</p></td>
		            <td><p id='pSum'></p></td>
		        </tr>
		        <tr align="center">
		            <td height="30px"><p>发布时间：</p></td>
		            <td><p id='pModelCreateTime'></p></td>
		            <td></td>
		            <td></td>
		        </tr>
		    </table>
	  		
	  	</div>
	</div>	
	

	
	
<!-- 查看数据项弹出窗口 -->
<div id="model_col" class="easyui-dialog" title="数据项"  closed="true" data-options="iconCls:'icon-large-shapes',onClose:afterCloseModelCol,modal:'false',buttons:'#table_model_col'" style="width:550px;height:550px;padding:10px">
    <table id="model_col_table" class="easyui-datagrid" style="width:500px;height:400px">
		    <thead>
		        <tr>
		            <th data-options="field:'modelColName',width:100,align:'center',editor:'text'">表字段名称</th>
		            <th data-options="field:'modelColDisplayName',width:180,align:'center',editor:'text'">字段通用名称</th>
		            <th data-options="field:'modelColType',width:50,align:'center',formatter:getModelColType,editor:{
							type:'combobox',
							options:{
								url:'model_admin/datatype/ajax/2',
								valueField:'modelColType',
								textField:'javaDatatype',
								required:true,
                    			editable: false,
							}
						}">字段类型</th>
		            <th data-options="field:'modelColLenth',width:60,align:'center',formatter:getModelColLenth,editor:'numberbox'">字段长度</th>
		            <th data-options="field:'modelColDecimalLenth',width:60,align:'center',formatter:getModelColDecimalLenth,editor:'numberbox'">小数长度</th>
		        </tr>
		    </thead>
	</table>
	<div id="table_model_col" style="padding:2px 5px;">
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelModelCol()">关闭</a>
	</div>
</div>

<!-- 查看数据详情弹出窗口 
-->
<div id="model_data" class="easyui-dialog" title="数据详情"  closed="true" data-options="iconCls:'icon-large-chart',modal:'false',onClose:afterCloseModelData,buttons:'#table_model_data'" style="width:80%;height:550px;padding:10px">
	<table id="test_params_table" class="easyui-datagrid" title="浏览前20条记录" data-options="rownumbers:true,singleSelect:true"  style="width:98%;height:480px"></table>
	<div id="table_model_data" style="padding:2px 5px;">
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelModelData()">关闭</a>
	</div>
</div>


<!-- 数据申请弹出窗口 
-->
<div id="apply_data" class="easyui-dialog" title="数据申请"  closed="true" data-options="iconCls:'icon-man',modal:'false',onClose:afterCloseApplyData,buttons:'#table_apply_data'" style="width:80%;height:600px;padding:10px">
	<div style="width:55%;height:100%;float:left;">
		<div id="1west" style="width:100%;height:100%;"><!-- 当下一步时被覆盖 -->
			<div id="apply_data_west" style="width:82%;height:100%;float:left;">
				<table id="table_apply_data_west" class="easyui-datagrid" title="用于选择申请的资源字段" data-options="rownumbers:false,singleSelect:false,toolbar:'#toolbar_west',"  style="width:100%;height:100%">
					<thead>
				        <tr>
				        	<!-- hidden="hidden" -->
				            <th data-options="field:'modelId',width:100,align:'center',formatter:getModelName">资源来源</th>
				            <th data-options="field:'modelColName',width:100,align:'center'">表字段名称</th>
				            <th data-options="field:'modelColDisplayName',width:180,align:'center'">字段通用名称</th>
				            <th data-options="field:'modelColType',width:50,align:'center',formatter:getModelColType">字段类型</th>
				            <th data-options="field:'modelColLenth',width:60,align:'center',formatter:getModelColLenth">字段长度</th>
				            <th data-options="field:'modelColDecimalLenth',width:60,align:'center',formatter:getModelColDecimalLenth">小数长度</th>
				        </tr>
				    </thead>
				</table>
				<div id="toolbar_west" style="padding:2px 5px;">
					<a id="allSelectWest" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="allSelectWest()">全选</a>
				    <a id="allUnSelectWest" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="allUnSelectWest()">全不选</a>
				</div>
			</div>
			<div id="apply_data_center" style="width:18%;height:100%;float:left;">
				<div style="height:40%;"></div>
				<a class="easyui-linkbutton" style="background-color:#A6FFA6;" iconCls="layout-button-right" plain="true" onclick="addModelDataAttributes()">添加申请字段</a>	
				<div style="height:5px;"></div>
				<a class="easyui-linkbutton" style="background-color:#FFD306;" iconCls="layout-button-left" plain="true" onclick="removeModelDataAttributes()">取消申请字段</a>	
			</div>
		</div>
		 
		<div id="2west" class="zysqqr subBot" style="width:100%;height:100%;background-color:#D2E9FF;" hidden="true">
			
			<div class="contain">
				<div class="top">
					<div class="font" style="">
						<h2>基础资源申请</h2>
					</div>
				
					<div class="topCon">
						<form name="sentMessage" id="Form" action="apply_management/apply4system_form" method="post" enctype="application/x-www-form-urlencoded" novalidate>
							
							<div class="topConOne topConSame clearfix">
					            <div class="leftOne leftSame">
					            	<label for="">申请资源目录名称<span id="applyNameVerify"></span></label>	
					              	<input type="text" id="apply_name" name="applyName" class="form-control" placeholder="【请输入名称】" required="required" value="${sessionScope.originSystemInfo.originSystemCname}">
								</div>
					        </div>

							<div class="topConOne topConSame clearfix">
					            <div class="leftOne leftSame">
					            	<label for="">申请单位名称</label>	
					              	<input type="text" id="apply_org_name" name="applyOrgName" class="form-control" placeholder="【请输入申请单位名称】" required="required" value="${sessionScope.ssoUser.uname}">
								</div>
					        </div>	
							
							<div class="topConOne topConSame clearfix">
					            <div class="leftOne leftSame">
					            	<label for="">申请人<span id="applyUserVerify"></span></label>	
					              	<input type="text" id="apply_user" name="applyUser" class="form-control" placeholder="【请输入申请人姓名】" required="required" value="${sessionScope.ssoUser.uname}">
								</div>
					        </div>
							
							<div class="topConTwo topConSame">
		                    	<label for="">申请资源用途说明<span id="applyDirectionVerify"></span></label>
		                    	<textarea name="applyDirection" id="apply_direction" class="form-control" style="width: 100%" rows="6" placeholder="" ></textarea>
			                </div>
							
						</form>
					</div>
				</div>
		    </div>
		</div>	
		
	</div>
	<div id="apply_data_east" style="width:45%;height:100%;float:right;">
		<table id="table_apply_data_east" class="easyui-datagrid" title="待确认申请的资源字段" data-options="rownumbers:true,singleSelect:false,toolbar:'#toolbar_east'"  style="width:100%;height:100%">
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
		<div id="toolbar_east" style="padding:2px 5px;">
			<a id="allSelectEast" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="allSelectEast()">全选</a>
		    <a id="allUnSelectEast" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="allUnSelectEast()">全不选</a>
		</div>
	</div>
	<div id="table_apply_data" style="padding:2px 5px;">
	    <a id="nextApplyData" class="easyui-linkbutton" style="background-color:#BBFFBB;" iconCls="icon-redo" plain="true" onclick="nextApplyData()">下一步</a>
	    <a id="commitApplyData" class="easyui-linkbutton" style="background-color:#A6FFA6;" iconCls="icon-man" plain="true" onclick="commitApplyData()">确认提交申请</a>
	    <a id="backApplyData" class="easyui-linkbutton" style="background-color:#FFFFAA;" iconCls="icon-undo" plain="true" onclick="backApplyData()">返回上一步</a>
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelApplyData()">关闭</a>
	</div>
</div>

<!-- 多模型来源,申请多模型  -->
<div id="apply_data_more_model" class="easyui-dialog" title="多资源选择"  closed="true"  data-options="iconCls:'icon-man',modal:'false',onClose:afterCloseApplyDataMoreModel,buttons:'#table_apply_data_more_model' " style="width:500px;height:600px;padding:10px">
	<div id="apply_data_more_model_list" class="easyui-datalist" title="数据提供系统" style="width:100%;height:100%"></div>
	<div id="table_apply_data_more_model" style="padding:2px 5px;">
	    <a id="confirmSelected" class="easyui-linkbutton" style="background-color:#A6FFA6;" iconCls="icon-man" plain="true" onclick="confirmSelected()">确认所选资源</a>
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelApplyDataMoreModel()">关闭</a>
	</div>
</div>

</section>	

	<!-- 页面通用底部引用 -->
	<%@ include file="../common/bottom_myhtml.jsp" %>

<script type="text/javascript" src="static/resource_catalog/catalog.js"></script>
</body>
</html>