/***********************************************************************
 * catalog页面js 声请管理页面js
 * 
 ***********************************************************************/


/************************************************
 * catalog页面js初始化方法
 * 
 ************************************************/
$(function(){//jq初始化
	
	$('ul#data_system').datagrid({//加载左边操作条数据
			url: 'resource_catalog/catalog/ajax/treeModel',
			method: 'post',
			groupField: 'group',
			lines: 'true',
	}).datagrid('setOnclick');//加载自定义setOnclick
	
	$.ajax({//初始化加载
		type : "post",
		url : "resource_catalog/catalog/ajax/getInitData",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : true,
		success : function(msg) {
			modelDatasMap = msg.modelDatasMap;
			
			var dataTypes = msg.dataTypes;
			for(i=0;i<dataTypes.length;i++){
				colDataType.set(dataTypes[i].modelColType, dataTypes[i].javaDatatype); 
			}
			
		},
	});
	
	//console.log(userType);
	if (userType=='admin') {
		//管理员不需要的操作   不需要数据申请
		$('a#apply4getData').remove();
	}
	
		
})

var modelDatas;
var modelDatasMap = new Map();

/**
 * 初始化全局变量选择的模型
 */
var selectModel;

/**
 * 初始化用户类型
 */
var userType = '';

/**
 * 页面初始方法可以从服务端传参
 */
function init(sendUserType){
	this.userType = sendUserType;
}


/************************************************
 * catalog页面js初始化↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
 * 
 ************************************************/


/**
 * 可修改的datagrid,继承表格数据展示
 */
$.extend($.fn.datagrid.methods, {
	setOnclick: function(jq){//自定义setOnclick方法
		return jq.each(function(){
            var opts = $(this).datagrid('options');
            opts.onClickRow = function(index, row){
            	selectModel = modelDatasMap[row.value];
            	$('p#pDescription').html('<font color="#0000E3">'+selectModel.modelDescription+'</font>');
            	$('p#pModelName').html('<font color="#005AB5">'+selectModel.modelName+'</font>');
            	$('p#pSystemName').html('<font color="#005AB5">'+row.group+'</font>');
            	$('p#pModelCreateTime').html('<font color="#005AB5">'+selectModel.modelCreateTime+'</font>');
            	
            }
		});
	}
	
});

/*
 
$("ul#data_system").datalist("getSelected");

onDblClickCell: function (rowIndex, field, value) {
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[rowIndex];
	  var id = row.id;
}

*/

/**
 * 查看模型字段项
 */
function selectModelData(){
	if(selectModel){
		$('#model_col').dialog('center');
		$('#model_col').dialog('open');
		
		$('#model_col_table').datagrid({
			rownumbers:true,
			singleSelect:true,
			
			url:'resource_catalog/catalog/ajax/getModelCol',
		    method:'post',
		    queryParams: {
				modelId: selectModel.modelId,
			},
			
		});
		
	} else {
		$.messager.alert('信息','请选择一项资源查看');
	}
	
}


/**
 * 关闭模型字段查看窗口
 */
function cancelModelCol(){
	$('#model_col').dialog('close');
}

//弹窗关闭了清除窗口文本输入内容
function afterCloseModelCol(){
	//console.log('弹窗关闭了');
	$('#model_col_table').datagrid('load',{});
}


//字段类型
var colDataType = new Map();

//字段类型编码转义
function getModelColType(value,row,index){
	var res = colDataType.get(Number(value));
	return res;
}

//字段长度
function getModelColLenth(value,row,index){
	var res = -1;
	if (value>4000){
		res = 4000;
	} else if (value<=-1) {
		res = null;
	} else {
		res = value;
	}
	return res;
}

//小数长度，只有有小数字段使用
function getModelColDecimalLenth(value,row,index){
	var res = -1;
	if(row.modelColType==2 || row.modelColType==5){
		res = value;
	} else {
		res = null;
	}
	return res;
}

//资源来源,根据模型编号转换
function getModelName(value,row,index){
	var modelData = modelDatasMap[row.modelId];
	return modelData.modelName;
}






/**
 * 查看模型具体数据详情
 */
function selectDetailModelData(){
	
	if(selectModel){
		$('#model_data').dialog('center');
		$('#model_data').dialog('open');
		$.ajax({//获取数据用于展示，前20条。
			type : "post",
			url : "resource_catalog/catalog/ajax/selectDetailModelData",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			dataType : "json",
			async : true,
			data : {modelId:selectModel.modelId},
			success : function(msg) {
				
				var modelDataAttributes = msg.modelDataAttributes;
				var columns = new Array(modelDataAttributes.length);
				for(var i=0;i< modelDataAttributes.length;i++){
					var column = {field:'',title:'',width:''};
					column.field = modelDataAttributes[i].modelColName;
					column.title = modelDataAttributes[i].modelColDisplayName;
					column.width = modelDataAttributes[i].modelColDisplayName.length*12;
					columns[i] = column;
				}
				
				//console.log(columns);
				$('#test_params_table').datagrid({
					columns : [columns], 
					data : JSON.parse(msg.json),
				});
				
			},
			error:function(err){
				$.messager.alert('查询实体模型数据出错', err);
	        }
		});
		
	} else {
		$.messager.alert('信息','请选择一项资源查看');
	}
	
}

/**
 * 关闭查看数据详情
 */
function cancelModelData(){
	$('#model_data').dialog('close');
}

/**
 * 关闭查看数据详情后
 */
function afterCloseModelData(){
	$('#test_params_table').datagrid({//清空数据详情table
		columns : [[]], 
		data : [],
	});
}






/**
 * 数据申请 
 */
function apply4getData(){
	if(selectModel){
		$.messager.prompt('数据申请', '确认是否只需求本资源', function(r){
			if (r){
				if('n'==r){//只需求本资源的情况
					$('div#apply_data').dialog('center');
					$('div#apply_data').dialog('open');
					$('#table_apply_data_west').datagrid({
						url:'resource_catalog/catalog/ajax/getModelCol',
					    method:'post',
					    queryParams: {
					    	modelId: selectModel.modelId,
						},
					});
				} else if ('y'==r) {//需求本资源为主，附加其它资源。
					$('div#apply_data_more_model_list').datalist({
						url: 'resource_catalog/catalog/ajax/treeModel4applyDataMoreModel',
						method: 'post',
						queryParams: {
					    	modelId: selectModel.modelId,
						},
						groupField: 'group',
						checkbox: 'true',
						singleSelect: 'false',
						selectOnCheck: false,
						lines: true,
					});
					
					//需求多模型的字段的判断
					$('div#apply_data_more_model').dialog('center');
					$('div#apply_data_more_model').dialog('open');
					
					
				}
			}
		});
		$(".messager-input").hide();
		$(".messager-input").val('n');
		$(".messager-body").append('<p><input id="isc" type="checkbox" name="box"><font color=#004B97> 需要选择选择其它资源字段组合</font></p>');
		$('#isc').change(function() {
			if($('input#isc').is(':checked')) {
				$(".messager-input").val('y');
			} else {
				$(".messager-input").val('n');
			}
			//console.log($(".messager-input").val());
		});
		
		//初始化数据申请弹窗上界面和按钮；
		$("div#1west").show();
		$("div#2west").hide();
		$("a#nextApplyData").show();
		$("a#commitApplyData").hide();
		$("a#backApplyData").hide();
		
	} else {
		$.messager.alert('信息','请选择一项资源进行申请');
	}
}


/**
 * 左侧待申请资源全选
 */
function allSelectWest(){
	$('#table_apply_data_west').datagrid('selectAll');
}
/**
 * 左侧待申请资源全不选
 */
function allUnSelectWest(){
	$('#table_apply_data_west').datagrid('unselectAll');
}
/**
 * 右侧待申请资源全选
 */
function allSelectEast(){
	$('#table_apply_data_east').datagrid('selectAll');
}
/**
 * 右侧待申请资源全不选
 */
function allUnSelectEast(){
	$('#table_apply_data_east').datagrid('unselectAll');
}


/**
 * 添加按钮
 * 添加申请字段
 */
function addModelDataAttributes(){
	var rows = $('#table_apply_data_west').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		var colIndex = $('#table_apply_data_west').datagrid('getRowIndex',row);
		$('#table_apply_data_west').datagrid('deleteRow',colIndex);
		$('#table_apply_data_east').datagrid('insertRow',{row:row});
	}
	
}

/**
 * 取消按钮
 * 取消已经添加的申请字段
 */
function removeModelDataAttributes(){
	var rows = $('#table_apply_data_east').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		var colIndex = $('#table_apply_data_east').datagrid('getRowIndex',row);
		$('#table_apply_data_east').datagrid('deleteRow',colIndex);
		$('#table_apply_data_west').datagrid('insertRow',{row:row});
	}
	
}

/**
 * 选择完成字段后点击下一步
 */
function nextApplyData(){
	
	
	$("div#1west").hide();
	$("div#2west").show();
	
	$("a#nextApplyData").hide();
	$("a#commitApplyData").show();
	$("a#backApplyData").show();
}

/**
 * 返回上一步选择字段
 */
function backApplyData(){
	
	$("div#1west").show();
	$("div#2west").hide();
	
	$("a#nextApplyData").show();
	$("a#commitApplyData").hide();
	$("a#backApplyData").hide();
}

/**
 * 提交申请
 */
function commitApplyData(){
	var rows = $('#table_apply_data_east').datagrid('getRows');
	
	var applyName = $('input#apply_name').val();
	var applyOrgName = $('input#apply_org_name').val();
	var applyUser = $('input#apply_user').val();
	var applyDirection = $('textarea#apply_direction').val();
	
	$.ajax({
		type : "post",
		url : "resource_catalog/catalog/ajax/commitApplyData",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId,applyName:applyName,applyOrgName:applyOrgName,applyUser:applyUser,applyDirection:applyDirection,json:JSON.stringify(rows)},
		success : function(msg) {
			$.messager.alert('申请提交完成后请等待平台管理员审核',msg);
			if (msg == '完成') {
				$('div#apply_data').dialog('close');
			}
		},
		error:function(err){
			$.messager.alert('审批申请提交错误请联系管理员', err);
        }
	});
	
}

/**
 * 多资源申请
 * 确认所选资源
 */
function confirmSelected(){
	var moreModel = $('div#apply_data_more_model_list').datalist('getChecked');
	if (moreModel.length == 0){
		$.messager.alert('多资源申请错误', '请选择一个其它资源组成多资源申请');
	} else {
		//console.log(moreModel);
		var modelList = new Array();
		modelList.push(selectModel.modelId);
		for(var i=0;i<moreModel.length;i++){
			console.log(moreModel[i].value);
			modelList.push(moreModel[i].value);
		}
		
		$('div#apply_data').dialog('center');
		$('div#apply_data').dialog('open');
		$('#table_apply_data_west').datagrid({
			url:'resource_catalog/catalog/ajax/getModelColMoreModel',
		    method:'post',
		    queryParams: {
		    	modelId: JSON.stringify(modelList),
			},
		});
		
		cancelApplyDataMoreModel();
		
	}
}

/**
 * 关闭数据申请弹窗
 */
function cancelApplyData(){
	//console.log('close');
	$('div#apply_data').dialog('close');
	
}

/**
 * 关闭数据申请弹窗后
 */
function afterCloseApplyData(){
	$('input#apply_name').val('');
	//$('input#apply_org_name').val('');
	//$('input#apply_user').val('');
	$('textarea#apply_direction').val('');
	
	$('#table_apply_data_west').datagrid('load',{});
	$('#table_apply_data_east').datagrid('selectAll');
	var rows = $('#table_apply_data_east').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		$('#table_apply_data_east').datagrid('deleteRow',0);
	}
}


/**
 * 关闭 数据申请更多模型选择 窗
 * 
 */
function cancelApplyDataMoreModel(){
	$('div#apply_data_more_model').dialog('close');
}

/**
 * 关闭数据申请更多模型选择 弹窗后
 */
function afterCloseApplyDataMoreModel(){
	//console.log('更多模型选择弹窗关闭了');
}

