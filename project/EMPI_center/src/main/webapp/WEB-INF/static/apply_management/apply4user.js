/***********************************************************************
 * 
 * 一般用户
 * apply页面js 声请管理页面js
 * 
 ***********************************************************************/


/************************************************
 * apply页面js初始化方法
 * 
 ************************************************/
$(function(){
	
	
	$.ajax({//初始化加载
		type : "post",
		url : "apply_management/apply/ajax/getInitData",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : false,//同步加载
		success : function(msg) {
			modelDatasMap = msg.modelDatasMap;
			
			var dataTypes = msg.dataTypes;
			for(i=0;i<dataTypes.length;i++){
				colDataType.set(dataTypes[i].modelColType, dataTypes[i].javaDatatype); 
			}
			
			
						
		},
	});
	
	
	
})



var modelDatasMap = new Map();

/**
 * 选择的详情查看
 */
var selectApply;



/************************************************
 * apply页面js初始化↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
 * 
 ************************************************/

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


//资源来源,根据模型编号转换
function getModelName(value,row,index){
	var modelData = modelDatasMap[row.modelId];
	return modelData.modelName;
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
	if (value>500){
		res = 499;
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
 * 模型建立成功后发送测试传数据请求
 */
function getApiSendData(){
	
	selectApply = $('table#apply').datagrid('getSelected');
    if (selectApply){
    	if (selectApply.auditStatus != '9'){
    		$.messager.show({
    			title:'提示',
    			msg:'未审核通过的申请,接口未开放',
    			timeout:15000,
    			showType:'slide'
    		});
    	}
    	
    	loadCenterUrl();
    	loadTestParams();
    	$('input#apiName').textbox('setValue',selectApply.applyName);
    	$('input#passKey').textbox('setValue',selectApply.applyId);
    	
    	$('div#getApi').dialog('center');
    	$('div#getApi').dialog('open');
    	
    	
    	$('#table_apply_data_east').datagrid({
			url:'apply_management/apply/ajax/getApplyCol',
			method:'post',
			queryParams: {
				applyId: selectApply.applyId,
			},
		});
    	
    	
    } else {
    	$.messager.alert('提示', '请选择一个申请');
	}
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
 * 后台生成传参样例
*/
function loadTestParams(){
	$.ajax({
		type : "post",
		url : "apply_management/apply/ajax/getTestParams",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		//data : {modelId:selectModel.modelId},
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
 * 关闭api查看窗口
 */
function cancelApi(){
	$('#getApi').dialog('close');
	//$('#getApi').dialog('open');
}

/**
 *关闭api查看窗口后
 */
function afterCancelApi (){
	$('input#apiName').textbox('setValue','');
	$('input#centerUrl').textbox('setValue','');
	$('input#passKey').textbox('setValue','');
	$('textarea#test_params').val();//传参样例预清空
	
	$('#table_apply_data_east').datagrid({//清空table
		//columns : [[]], 
		data : [],
	});
}


