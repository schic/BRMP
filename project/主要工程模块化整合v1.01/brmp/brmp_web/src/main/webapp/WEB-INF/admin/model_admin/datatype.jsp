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
<title>数据模型管理</title>
<%@ include  file="../common/project_myhtml.jsp" %><!-- 页面的通用静态文件引用 -->
<link rel="stylesheet" href="static/common/css/myhtml_css/resources.css" />

</head>
<body onload="setInit('${sessionScope["wondersgroup.qyws.curuser"].userType}','${sessionScope.originSystemInfo.originSystemId}')">

<section>
	<!-- 页面通用头部引用 -->
	<%@ include file="../common/head_myhtml.jsp" %>

<div class="main data clearfix">
      <div class="left">
          <div class="leftTitle">
              <img src="static/common/img/myhtml_imgs/indexHeadImg4.png" alt="">
              <label for="">数据模型</label>
          </div>
          <div class="leftFont">
              <div class="fontBg">
                  <div class="fontBg1">
                      <img src="static/common/img/myhtml_imgs/dataImg2.png" alt="">
                      <span>数据模型概述</span>
                  </div>
                  <div class="fontBg2">
                      <img src="static/common/img/myhtml_imgs/dataImg3.png" alt="">
                      <span>数据模型管理</span>
                  </div>
                  <div class="fontBg3">
                        <img src="static/common/img/myhtml_imgs/dataImg4.png" alt="">
                        <span>值域字典管理</span>
                  </div>
              </div>
          </div>
      </div>
      <div class="right">
          <div class="mainOne mainSame">
              <div class="pubTop">
                  <img src="static/common/img/myhtml_imgs/pubTopImg1.png" alt="">
                  <span>您所在的位置：<label for="">数据模型</label><label for="">&nbsp;/&nbsp;数据模型概述</label></span>
              </div>
              <div class="con">
                  <div class="sameCon">
                      <div class="title">
                          <i></i>
                          <span>数据模型概述</span>
                      </div>
                      <div class="det">
                          数据模型是面向<label for="">数据提供单位</label>使用 ，由数据提供单位自行建立提交数据的基础模型。
                      </div>
                  </div>
                  <div class="sameCon conDiff">
                      <div class="title">
                          <i></i>
                          <span>数据模型建立</span>
                      </div>
                      <div class="det">
                          <div class="one">
                              <div class="oneDet">
                                  <span class="sp1">开始</span>
                                  <span class="sp2">建立数据提供模型</span>
                                  <span class="sp3">数据验证</span>
                              </div>
                              <img src="static/common/img/myhtml_imgs/dataGsImg1.png" alt="">
                              <div class="oneDet twoDet">
                                  <span class="sp1">获取账号信息</span>
                                  <span class="sp2">调用公共接口提交数据</span>
                                  <span class="sp3">结束</span>
                              </div>
                          </div>
                          <div class="two">
                              <p>
                                  <label for="">1.</label>
                                  为了确保数据的公平性和正确性，数据提供单位，先需要向四川省卫生信息中心提供申请，建立卫生基础资源目录。
                              </p>
                              <p>
                                  <label for="">2.</label>
                                  四川省卫生信息中心根据申请，为数据提供单位分配账号，建立系统相关权限，同时给单位提供数据提交公共接口服务地址及授权信息。
                              </p>
                              <p>
                                  <label for="">3.</label>
                                  为了确保数据的公平性和正确性，数据提供单位，先需要向四川省卫生信息中心提供申请，建立卫生基础资源目录。
                              </p>
                              <p>
                                  <label for="">4.</label>
                                  四川省卫生信息中心根据申请，为数据提供单位分配账号，建立系统相关权限，同时给单位提供数据提交公共接口服务地址及授权信息。
                              </p>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
        <div class="mainTwo mainSame">
        
       		<table id="model" class="easyui-datagrid" title="数据模型管理" style="width:100%;height:400px"
			        data-options="rownumbers:true,singleSelect:true,url:'model_admin/datatype/ajax/querySelect',method:'post',toolbar:'#tb',footer:'#ft',pagination:'true',pagePosition:'bottom'">
			    <thead>
			        <tr>
			        	<!-- status;//状态  0:停用 1:启用 -->
			        	<!-- auditStatus;//审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过 -->
			            <th data-options="field:'originSystemId',width:80,align:'center'">系统编号</th>
			            <th data-options="field:'modelId',width:220,align:'center'">模型编号</th>
			            <th data-options="field:'modelName',width:120,align:'center'">模型名称</th>
			            <th data-options="field:'modelCreateTime',width:160,align:'center'">创建时间</th>
			            <th data-options="field:'modelUpdeteTime',width:160,align:'center'">更新时间</th>
			            <th data-options="field:'status',formatter:getStatus,width:60,align:'center'">状态</th>
			            <th data-options="field:'auditStatus',formatter:getAuditStatus,width:60,align:'center'">审核状态</th>
			        </tr>
			    </thead>
			</table>
			<div id="tb" style="padding:2px 5px;">
				创建时间: <input id="beginCreatDate" class="easyui-datebox" style="width:110px" editable="false">
				至: <input id="toCreatDate" class="easyui-datebox" style="width:110px" editable="false">
				状态: 
			    <select id="status4select" class="easyui-combobox" panelHeight="auto" style="width:100px" editable="false">
			        <option value="">-请选择-</option><!--状态  0:停用 1:启用-->
			        <option value="0">停用</option>
			        <option value="1">启用</option>
			    </select>
			            审核状态: 
			    <select id="auditStatus4select" class="easyui-combobox" panelHeight="auto" style="width:100px" editable="false">
			        <option value="">-请选择-</option><!-- 0:未设计 1:待审核 2:审核拒绝 9:审核通过 -->
			        <option value="0">未设计</option>
			        <option value="1">待审核</option>
			        <option value="2">审核拒绝</option>
			        <option value="9">审核通过</option>
			    </select>
			    <a class="easyui-linkbutton" iconCls="icon-search" onclick="querySelect()">查找</a>
			    <a class="easyui-linkbutton" iconCls="icon-redo" onclick="reSet4querySelect()">重置</a>
			</div>
			<div id="ft" style="padding:2px 5px;">
				<a id="newModel" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newModel()">新建模型</a>
			    <a id="updateModel" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="updateModel()">修改模型</a>
			    <a id="testSendData" class="easyui-linkbutton" iconCls="icon-help" plain="true" onclick="testSendData()">测试传输与审核</a>
			    <a id="disabledModel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="disabledModel()">停用模型</a>
			    <a id="enabledModel" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="enabledModel()">启用模型</a>
			</div>
					
        </div>
        
        
        <div class="mainThree mainSame">
        	<!-- 
         	<div style="width:202px;height:86px;background:url('static/common/img/my_img/openempi-orig.jpg');"></div>
         	<iframe style="width:100%;height:400px;" src="static/common/img/my_img/test_openempi_html/OpenEMPI Manager.html"></iframe>
        	 -->
        </div>
        
    </div>
</div>



<!-- 修改和新增模型属性的弹出窗口 -->
<div id="model_col" class="easyui-dialog" title="模型设计"  closed="true" data-options="iconCls:'icon-save',onClose:afterCloseModelCol,modal:'false',buttons:'#table_model_col'" style="width:590px;height:550px;padding:10px">
	<div style="width:100%">
		<label>系统编号:</label>&nbsp;
		<input id="originSystemId" class="easyui-textbox easyui-validatebox" type="text" name="originSystemId" editable="false" disabled="true" style="width:220px"/>
	</div>
	<div style="width:100%">
		<label for="name">模型编号:</label>&nbsp;
		<input id="modelId" class="easyui-textbox easyui-validatebox" type="text" name="modelId" editable="false" disabled="true" style="width:220px"/>
	</div>
	<div style="width:100%">
		<label for="name">模型名称:</label>&nbsp;
		<input id="modelName" class="easyui-textbox easyui-validatebox" type="text" name="modelName" required="true" style="width:220px"/>
	</div>
	<div style="width:100%">
		<label for="name">模型描述:</label>&nbsp;
		<input id="modelDescription" class="easyui-textbox easyui-validatebox" type="text" name="modelDescription" style="width:80%"/>
	</div>
    <table id="model_col_table" class="easyui-datagrid" style="width:560px;height:400px">
		    <thead>
		        <tr>
		            <th data-options="field:'displayOrder',width:30,align:'center'">序号</th>
		            <th data-options="field:'pk',width:40,align:'center',formatter:getPk,editor:{
							type:'combobox',
							options:{
								data : [{name:'是',value:'1'},{name:'否',value:'0'}], 
								valueField : 'value',
								textField : 'name',
                    			editable: false,
                    		}	
						}">主键</th>
		            <th data-options="field:'modelColName',width:100,align:'center',editor:'text'">表字段名称</th>
		            <th data-options="field:'modelColDisplayName',width:180,align:'center',editor:'text'">字段通用名称</th>
		            <th data-options="field:'modelColType',width:70,align:'center',formatter:getModelColType,editor:{
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
		<a id="updateModelData" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="updateModelData()" hidden="false">保存修改</a>
		<a id="insertModelData" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="insertModelData()" hidden="true" >确认新增</a>
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelModelCol()">关闭修改</a>
	</div>
	<div id="toolbar_model_col" style="padding:2px 5px;">
		<a id="insertModelCol" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="insertModelCol()">新增字段</a>
	    <a id="deleteModelCol" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteModelCol()">删除字段</a>
	</div>
</div>




<!-- 传输测试的弹出窗口 -->
<div id="transmission_test" class="easyui-dialog" title="传输测试"  closed="true" data-options="iconCls:'icon-help',onClose:afterTransmissionTest,modal:'false',toolbar:'#transmission_test_toolbar',buttons:'#transmission_test_button'" style="width:80%;height:80%;padding:10px">
	<div id="transmission_test_toolbar" style="padding:2px 5px;">
		<a id="start_transmission_test" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="startTransmissionTest()">根据最新设计修改,后台新建立模型开始传输测试</a>
	    
	</div>
	
	<div style="width:100%">
		<label><p>请求URL:</p></label>&nbsp;
		<input id="centerUrl" name="centerUrl" class="easyui-textbox easyui-validatebox" type="text" editable="false" style="width:100%"/>
	</div>
	<p></p>
	<div style="width:100%">
		<label><p>传参样例:采用restful风格传输,使用post方法,参数为一个json字符串的请求</p></label>&nbsp;
		<textarea name="test_params" id="test_params" class="form-control" rows="10" placeholder="传参样例" readonly="true" style="width:100%"></textarea>
	</div>
	<p></p>
	<table id="test_params_table" class="easyui-datagrid" title="webservice传输成功后，验证传输成功后的数据(前20条记录)" data-options="rownumbers:true,singleSelect:true,toolbar:'#toolbar_test_params',"  style="width:98%;height:400px">
	</table>
	<div id="toolbar_test_params" style="padding:2px 5px;">
		<a id="loadEntityModelData" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="loadEntityModelData()">刷新数据</a>
	    <a id="clearEntityModelData" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="clearEntityModelData()">清空数据</a>
	</div>
	<div id="transmission_test_button" style="padding:2px 5px;">
		<a id="auditPass" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="auditPass()">审核通过</a>
		<a id="auditReject" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="auditReject()">审核拒绝</a>
		<a id="testParams" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="commit2audit()" hidden="false">完成测试提交审批</a>
	    <a class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="cancelTransmissionTest()">关闭测试窗</a>
	</div>
	
</div>

</section>

<script type="text/javascript" src="static/model_admin/datatype.js"></script>
<script type="text/javascript" src="static/model_admin/datatype_view.js"></script>

</body>
</html>