/***********************************************************************
 * datatype页面js 数据模型管理页面js
 * 
 ***********************************************************************/


/************************************************
 * datatype页面js初始化方法
 * 
 ************************************************/
$(function(){
	$('#model_col_table').datagrid().datagrid('enableCellEditing');//加载模型修改框的可修改程序
	
	/*
	$('#model').datagrid({
	    url:'model_admin/datatype/ajax/querySelect',
	    method:'post',
	    columns:[[
			{field:'originSystemId',width:80,align:'center',title:'系统编号'},
			{field:'modelId',width:220,align:'center',title:'模型编号'},
			{field:'modelName',width:120,align:'center',title:'模型名称'},
			{field:'modelCreateTime',width:160,align:'center',title:'创建时间'},
			{field:'modelUpdeteTime',width:160,align:'center',title:'更新时间'},
			{field:'status',formatter:getStatus,width:60,align:'center',title:'状态'},
			{field:'auditStatus',formatter:getAuditStatus,width:60,align:'center',title:'审核状态'},
	    ]],
	});
	*/
	
	$.ajax({//初始化加载dataTypes，用于选择不同字段类型
		type : "post",
		url : "model_admin/datatype/ajax/2",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : true,
		//data : {tableNameA:selectModel.modelName,beginDate:beginDate,endDate:endDate},
		success : function(dataTypes) {
			//colDataTypes = dataTypes;
			for(i=0;i<dataTypes.length;i++){
				colDataType.set(dataTypes[i].modelColType, dataTypes[i].javaDatatype); 
			}
		},
	});
	
	/* 根据权限，控制按钮显示初始化
	<a id="newModel" //新建模型
    <a id="updateModel" //修改模型
    <a id="testSendData"  //测试传输与审核
    <a id="disabledModel" //停用模型
    <a id="enabledModel"  //启用模型	
    
    <a id="updateModelData" //保存修改
	<a id="insertModelData" //确认新增
	
	<a id="insertModelCol" //插入字段
    <a id="deleteModelCol" //删除字段
    
    <a id="start_transmission_test" //根据最新设计,后台新建立模型开始传输测试
    
    <a id="loadEntityModelData" //刷新数据
    <a id="clearEntityModelData" //清除数据
    
    <a id="auditPass" //审核通过
	<a id="auditReject" //审核拒绝
	<a id="testParams" //完成测试提交审批
	*/
	//console.log(userType);
	if (userType=='admin') {
		//管理员不需要的操作
		$('a#newModel').remove();
		$('a#start_transmission_test').remove();
		$('a#clearEntityModelData').remove();
		$('a#testParams').remove();
	} else if (userType=='system') {
		//系统接入不需要的操作
		$('a#disabledModel').remove();
		$('a#enabledModel').remove();
		$('a#auditPass').remove();
		$('a#auditReject').remove();
	}
	
})

/**
 * 初始化用户类型
 */
var userType = '';

/**
 * 初始化全局变量选择的模型
 */
var selectModel;

/**
 * 初始化系统id
 */
var initOriginSystemId = '';

function setInit(sendUserType,sendOriginSystemId){
	this.userType = sendUserType;
	this.initOriginSystemId = sendOriginSystemId;
//	console.log('sendUserType'+sendUserType);
//	console.log('sendOriginSystemId'+sendOriginSystemId);
}

/************************************************
 * datatype页面js初始化↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
 * 
 ************************************************/

/**
 * 查找
 */
function querySelect(){
    var beginDate = $('input#beginCreatDate').combo('getText');
    var endDate = $('input#toCreatDate').combo('getText');
    var status = $('select#status4select').combo('getValue');
    var auditStatus = $('select#auditStatus4select').combo('getValue');
	$('table#model').datagrid({
		url:'model_admin/datatype/ajax/querySelect',
		method:'post',
		queryParams: {beginDate:beginDate,endDate:endDate,status:status,auditStatus:auditStatus},
	});
}
/**
 * 重置查找
 */
function reSet4querySelect(){
	$('input#beginCreatDate').combo('setText','');
	$('input#toCreatDate').combo('setText','');
	$('select#status4select').combobox('select', '');
	$('select#auditStatus4select').combobox('select', '');
}

//修改选择的模型
function updateModel(){
	$('a#updateModelData').show();
	$('a#insertModelData').hide();
	
    var row = $('#model').datagrid('getSelected');
    selectModel = row;
    if (row){
    	$('#model_col').dialog('center');
    	$('#model_col').dialog('open');
    	$('#model_col_table').datagrid({
    		rownumbers:false,
    		singleSelect:true,
    		toolbar:'#toolbar_model_col',
    		
    		url:'model_admin/datatype/ajax/1',
    	    method:'post',
    	    queryParams: {
    			modelId: row.modelId
    		},
    		
    		//排序
    		sortName:'displayOrder',    
            sortOrder:'asc',
            remoteSort:false,
            
            
    	});
    	
    	$('input#originSystemId').textbox('setValue',row.originSystemId);
    	$('input#modelId').textbox('setValue',row.modelId);
    	$('input#modelName').textbox('setValue',row.modelName);
    	$('input#modelDescription').textbox('setValue',row.modelDescription);
    	
    } else {
    	$.messager.alert('提示', '请选择一个模型');
    }
}


//新建立模型
function newModel (){
	if (userType=='admin') {
		$.messager.alert('提示', 'admin用户不需要新建模型，请使用系统接入用户建立模型接入数据');
		return; 
	}
	
	$('#model').datagrid('clearSelections');//取消选择
	
	$('a#updateModelData').hide();
	$('a#insertModelData').show();
	$('#model_col').dialog('center');
	$('#model_col').dialog('open');
	
	$('#model_col_table').datagrid({
		rownumbers:false,
		singleSelect:true,
		toolbar:'#toolbar_model_col',
		
		url:'static/common/json/newModel.json',
	    method:'get',
	    
	    queryParams: {
	    	originSystemId: initOriginSystemId,
		},
		
		//排序
		sortName:'displayOrder',    
        sortOrder:'asc',
        remoteSort:false,
        
        
	});
	
	$('input#originSystemId').textbox('setValue',initOriginSystemId);//系统id,jsp页面初始化传入
	$('input#modelId').textbox('setValue','系统稍后生成..');
	$('input#modelName').textbox('setValue','newModel');
	$('input#modelDescription').textbox('setValue','模型描述');
	
	
}

/**
 * 可修改的datagrid,继承表格数据展示
 */
$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
            var ed = $(this).datagrid('getEditor', param);
            if (ed){
                if ($(ed.target).hasClass('textbox-f')){
                    $(ed.target).textbox('textbox').focus();
                } else {
                    $(ed.target).focus();
                }
            }
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	},
    enableCellEditing: function(jq){
        return jq.each(function(){
            var dg = $(this);
            var opts = dg.datagrid('options');
            opts.oldOnClickCell = opts.onClickCell;
            opts.onClickCell = function(index, field){
                if (opts.editIndex != undefined){
                    if (dg.datagrid('validateRow', opts.editIndex)){
                        dg.datagrid('endEdit', opts.editIndex);
                        opts.editIndex = undefined;
                    } else {
                        return;
                    }
                }
                
                if (index<4) {//添加此判断作用前4行不可修改
                	return;
                }
                
                dg.datagrid('selectRow', index).datagrid('editCell', {
                    index: index,
                    field: field
                });
                opts.editIndex = index;
                opts.oldOnClickCell.call(this, index, field);
            }
        });
    }
});

//停用模型按钮
function disabledModel(){
	$.messager.confirm('请确认','停用模型后,将导致已经获取模型数据接口的用户,接口失效;   并且在将来再次启用时,模型将交由系统接入方重新设计',function(r){
		if(r){
			$.messager.alert('停用模型','按钮接口未完成');
			
		}
		
	});
	
}

//启用模型按钮
function enabledModel(){
	$.messager.alert('启用模型','按钮接口未完成');
}



/**
 * 状态  0:停用 1:启用
 * @param value
 * @param row
 * @param index
 * @returns
 */
function getStatus(value,row,index){
	var res ='';
    switch (value){
        case 0:
            res = '<font style="color:red;">停用</font>';
            break;
        case 1:
            res = '<font style="color:green;">启用</font>';
            break;
        default:
            break;
    }
    return res;

}

/**
 * 审核状态  0:未设计 1:待审核 2:审核拒绝 9:审核通过
 * @param value
 * @param row
 * @param index
 * @returns
 */
function getAuditStatus(value,row,index){
	var res ='';
    switch (value){
        case 0:
            res = '<font style="color:orange;">未设计</font>';
            break;
        case 1:
            res = '<font style="color:purple;">待审核</font>';
            break;
        case 2:
        	res = '<font style="color:red;">审核拒绝</font>';
        	break;
        case 9:
        	res = '<font style="color:green;">审核通过</font>';
        	break;
        default:
            break;
    }
    return res;
}











/***********************************************************************************
 * 测定传数据窗口
 * 
 ***********************************************************************************/


/**
 * 模型建立成功后发送测试传数据请求
 */
function testSendData(){
	var row = $('#model').datagrid('getSelected');
    selectModel = row;//全局selectModel为选中的模型
    if (row){
    	
    	if (userType=='admin') {//是管理员
    		if (this.selectModel.auditStatus != 1) {//不是待审核
    			$('a#auditPass').hide();
    			$('a#auditReject').hide();
    		} else {
    			$('a#auditPass').show();
    			$('a#auditReject').show();
    		}
    	}
    	
    	
    	getColumnStr();//获取模型同时获取全局模型的字段属性，用于生成table的列
    	
    	$('textarea#test_params').val();//传参样例预清空
    	
    	$('#transmission_test').dialog('center');
    	$('#transmission_test').dialog('open');//开启弹窗 测试传输数据
    	
    	loadCenterUrl();
    	loadTestParams();
    	
    	
    } else {
    	$.messager.alert('提示', '请选择一个模型');
	}
}

//全局模型属性字段生成table的field
var columns; 
//获取模型属性
function getColumnStr(){
	var columnStr = '';
	$.ajax({//获取模型属性
		type : "post",
		url : "model_admin/datatype/ajax/modelDataAttributes",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId},
		success : function(msg) {
			var modelDataAttributes = msg.modelDataAttributes;
			columns = new Array(modelDataAttributes.length);
			for(var i=0;i< modelDataAttributes.length;i++){
				var column = {field:'',title:'',width:''};
				if(msg.dataBase==1){//见服务端
					column.field = modelDataAttributes[i].modelColName.toUpperCase();
				} else {
					column.field = modelDataAttributes[i].modelColName;
				}
				column.title = modelDataAttributes[i].modelColDisplayName;
				column.width = modelDataAttributes[i].modelColDisplayName.length*12;
				columns[i] = column;
			}
		},
		error:function(err){
			$.messager.alert('获取模型属性错误', err);
        }
	});
	
	
}

/**
 * 测试传输数据表格展示后台获取的数据
 */
function loadEntityModelData(){
	
	$.ajax({//获取数据用于展示，前20条。
		type : "post",
		url : "model_admin/datatype/ajax/entityModelData",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId},
		success : function(msg) {
			//console.log(columns);
			$('#test_params_table').datagrid({
				columns : [columns], 
				data : JSON.parse(msg),
			});
			
		},
		error:function(err){
			$.messager.alert('查询实体模型数据出错', err);
        }
	});
	
}


/**
 * 开始传入数据测试，后台将生成模型。
 */
function startTransmissionTest (){
	$.messager.confirm('请确认','系统会按照最新修改的模型设计,在后台建立新的模型用于数据传输。',function(r){
		if(r){
			$('a#start_transmission_test').hide();
			//console.log(selectModel.auditStatus);
			$.ajax({//验证模型并后台建立模型
				type : "post",
				url : "model_admin/datatype/ajax/testSendData",
				contentType:"application/x-www-form-urlencoded; charset=utf-8",
				dataType : "json",
				async : true,
				data : {modelId:selectModel.modelId},
				success : function(msg) {
					$.messager.alert('后台模型建立信息', msg);
				},
				error:function(err){
					$.messager.alert('后台模型建立错误', err);
		        }
			});
			loadTestParams();
		} else {
			//console.log('否');
			return
		}
	});
	
	
	
	
}

/**
 * 清除模型数据
 */
function clearEntityModelData(){
	$.messager.confirm('请确认','清空了数据后,模型将无任何数据,需重新传输数据测试',function(r){
		if(r){
			$.ajax({//清除模型数据
				type : "post",
				url : "model_admin/datatype/ajax/clearEntityModelData",
				contentType:"application/x-www-form-urlencoded; charset=utf-8",
				dataType : "json",
				async : true,
				data : {modelId:selectModel.modelId},
				success : function(msg) {
					if(msg != "清空表完成") {
						$.messager.alert('信息',msg);
					} else {
						loadEntityModelData();
					}
				},
				error:function(err){
					$.messager.alert('清除模型数据出错', err);
		        }
			});
			
		} else {
			//console.log('否');
			return
			
		}
		
	});
}

/**
 * 后台生成传参样例
*/
function loadTestParams(){
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/getTestParams",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId},
		success : function(msg) {
			$('textarea#test_params').val(msg);
			//$('#model_col_table').datagrid('reload',{modelId:selectModel.modelId});
		},
		error:function(err){
			$.messager.alert('测试参数获取错误请联系管理员', err);
			//$('#model_col_table').datagrid('reload',{modelId:selectModel.modelId});
        }
	});
}

/**
 * 获取中心webservice的Url
 */
function loadCenterUrl(){
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/getCenterUrl",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		//data : {modelId:selectModel.modelId},
		success : function(msg) {
			//console.log(msg)
			$('input#centerUrl').textbox('setValue',msg);
		},
		error:function(err){
			$.messager.alert('测试参数获取中心服务地址错误请联系管理员', err);
        }
	});
}

/**
 * 确认测试数据无误后提交审批
 */
function commit2audit(){
	var colData = $('#test_params_table').datagrid('getRows');
	//console.log(colData);
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/commit2audit",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId,json:JSON.stringify(colData)},
		success : function(msg) {
			$.messager.alert('审核提交',msg);
			$('#model').datagrid('reload',{});//模型数据更新
		},
		error:function(err){
			$.messager.alert('审批提交错误请联系管理员', err);
        }
	});
}



/**
 * 关闭弹出测试传数据窗口
 */
function cancelTransmissionTest(){
	$('#transmission_test').dialog('close');
}

//测试传数据窗口关闭后
function afterTransmissionTest (){
	$('a#start_transmission_test').show();//重新建立模型按钮显示
	$('#test_params_table').datagrid({//清空测试传参table
		columns : [[]], 
		data : [],
	});
}


//审核拒绝
function auditReject(){
	//console.log(selectModel);
	
	$('a#auditPass').hide();
	$('a#auditReject').hide();
	
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/auditReject",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId},
		success : function(msg) {
			$.messager.alert('审核拒绝',msg);
			$('#model').datagrid('reload',{});//模型数据更新
		},
		error:function(err){
			$.messager.alert('审批提交错误请联系管理员', err);
			$('a#auditPass').show();
			$('a#auditReject').show();
        }
	});
	
}

//审核通过
function auditPass(){
	$('a#auditPass').hide();
	$('a#auditReject').hide();
	
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/auditPass",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {modelId:selectModel.modelId},
		success : function(msg) {
			$.messager.alert('审核通过',msg);
			$('#model').datagrid('reload',{});//模型数据更新
		},
		error:function(err){
			$.messager.alert('审批提交错误请联系管理员', err);
			$('a#auditPass').show();
			$('a#auditReject').show();
        }
	});
	
}








/**********************************************************************
 * 弹出窗口模型
 * 
 * 用于设计(新增和修改)模型
 * 
 **********************************************************************/


/**
 * 关闭弹出模型修改窗口
 */
function cancelModelCol(){
	$('#model_col').dialog('close');
}

//弹窗关闭了清除窗口文本输入内容
function afterCloseModelCol(){
	//console.log('弹窗关闭了');
	$('input#originSystemId').textbox('setValue','');
	$('input#modelId').textbox('setValue','');
	$('input#modelName').textbox('setValue','');
	$('input#modelDescription').textbox('setValue','');
	$('#model_col_table').datagrid('load',{});
}

//是否主键
function getPk(value,row,index){
	var res = '';
	if(value=='1'){
		res = '是';
	}
	return res;
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

/**
 * 新增字段
 */
function insertModelCol(){
	$('#model_col_table').datagrid('insertRow',{row:{displayOrder:null,modelColName:'new',modelColDisplayName:'新增',modelColType:0,modelColLenth:1}});
}


/**
 * 删除字段
 */
function deleteModelCol(){
	var colRow = $('#model_col_table').datagrid('getSelected');
	if (colRow) {
		if (!colRow.displayOrder || colRow.displayOrder > 4) {
			var colIndex = $('#model_col_table').datagrid('getRowIndex',colRow);
			$('#model_col_table').datagrid('deleteRow', colIndex);
		} else if (colRow.displayOrder && colRow.displayOrder <= 4 ){
			$.messager.alert('提示', '前4列为固定字段,不能修改');
		} 
	} else {
		$.messager.alert('提示', '请选择一行进行删除');
	}
}


/**
 * 保存模型数据
 */
function updateModelData(){
	var modelDescription = $('input#modelDescription').textbox('getValue');
	var modelName = $('input#modelName').textbox('getValue');
	
	
	var opts = $('#model_col_table').datagrid('options');//获取模型表数据设置
	$('#model_col_table').datagrid('endEdit',opts.editIndex);//结束当前编辑
	$('#model_col_table').datagrid('clearSelections');//取消选择
	var data = $('#model_col_table').datagrid('getRows');
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/updateModelData",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {json:JSON.stringify(data),originSystemId:selectModel.originSystemId,modelId:selectModel.modelId,modelName:modelName,modelDescription:modelDescription},
		success : function(msg) {
			$.messager.alert('保存修改信息', msg);
			//$('#model_col_table').datagrid('reload',{modelId:selectModel.modelId});
			$('#model').datagrid('reload',{});
		},
		error:function(err){
			$.messager.alert('保存出现错误', err);
			//$('#model_col_table').datagrid('reload',{modelId:selectModel.modelId});
        }
	});
}

/**
 * 新增数据模型
 */
function insertModelData(){
	var originSystemId = $('input#originSystemId').textbox('getValue');
	var modelDescription = $('input#modelDescription').textbox('getValue');
	var modelName = $('input#modelName').textbox('getValue');
	
	var jsonModelData = '{"originSystemId":"'+ originSystemId +'", "modelName":"'+ modelName + '", "modelDescription":"' + modelDescription + '" }';
	
	var opts = $('#model_col_table').datagrid('options');//获取模型表数据设置
	$('#model_col_table').datagrid('endEdit',opts.editIndex);//结束当前编辑
	$('#model_col_table').datagrid('clearSelections');//取消选择
	var colData = $('#model_col_table').datagrid('getRows');
	$.ajax({
		type : "post",
		url : "model_admin/datatype/ajax/newModel",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		//modelName:modelName,modelDescription:modelDescription
		data : {json:JSON.stringify(colData), jsonModelData: jsonModelData},
		success : function(msg) {
			$.messager.alert('信息', msg);
			$('#model').datagrid('reload',{});
			if(msg == '新增完成'){
				$('#model_col').dialog('close');
			}
		},
		error:function(err){
			$.messager.alert('新增出现错误', err);
        }
	});
	
}



