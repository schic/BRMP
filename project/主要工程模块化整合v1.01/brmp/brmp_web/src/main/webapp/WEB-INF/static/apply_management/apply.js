/***********************************************************************
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

/**
 * 初始化用户类型
 */
var userType = '';


var modelDatasMap = new Map();

/**
 * 选择的详情查看
 */
var selectApply;


function setInit(sendUserType){
	this.userType = sendUserType;
}

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

/**
 * 获取模型名称
 */
function getModelName(value,row,index){
	var res ='';
	var modelData = modelDatasMap[value];
	res = modelData.modelName;
	return res;
}

/**
 * 重置查找
 */
function reSet4querySelect(){
	$('input#beginCreatDate').combo('setText','');
	$('input#toCreatDate').combo('setText','');
	//$('select#status4select').combobox('select', '');
	$('select#auditStatus4select').combobox('select', 1);//默认待审核
}
/**
 * 查找
 */
function querySelect(){
	var beginDate = $('input#beginCreatDate').combo('getText');
	var endDate = $('input#toCreatDate').combo('getText');
	
	var auditStatus = $('select#auditStatus4select').combo('getValue');
	$('table#apply').datagrid({
		url:'apply_management/apply/ajax/querySelect',
		method:'post',
		queryParams: {beginDate:beginDate,endDate:endDate,auditStatus:auditStatus},
	});
	
}

/**
 * 查看详情并审核
 * 审核申请
 */
function setAudit(){
	$('div#apply_data').dialog('center');
	$('div#apply_data').dialog('open');
	
	selectApply = $('table#apply').datagrid('getSelected');
	if(selectApply){
		if (userType=='admin') {//是管理员
    		if (this.selectApply.auditStatus != 1) {//不是待审核
    			$('a#auditPass').hide();
    			$('a#auditReject').hide();
    		} else {
    			$('a#auditPass').show();
    			$('a#auditReject').show();
    		}
    	}
		
		$('#table_apply_data_east').datagrid({
			url:'apply_management/apply/ajax/getApplyCol',
			method:'post',
			queryParams: {
				applyId: selectApply.applyId,
			},
		});
		
		$('input#apply_name').val(selectApply.applyName);
		$('input#apply_org_name').val(selectApply.applyOrgName);
		$('input#apply_user').val(selectApply.applyUser);
		$('textarea#apply_direction').val(selectApply.applyDirection);
	}
	
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
 * 审核申请资源通过
 */
function auditPass(){
	$('a#auditPass').hide();
	$('a#auditReject').hide();
	
	$.ajax({
		type : "post",
		url : "apply_management/apply/ajax/auditPass",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : true,//异步或同步加载
		data : {applyId:selectApply.applyId},
		success : function(msg) {
			$.messager.alert('审核通过',msg);
			$('#apply').datagrid('reload',{});//申请数据更新
		},
		error:function(err){
			$.messager.alert('审批提交错误请联系管理员', err);
			$('a#auditPass').show();
			$('a#auditReject').show();
        }
		
	});
	
}

/**
 * 审核申请资源拒绝
 */
function auditReject(){
	$('a#auditPass').hide();
	$('a#auditReject').hide();
	
	$.ajax({
		type : "post",
		url : "apply_management/apply/ajax/auditReject",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		async : true,//异步或同步加载
		data : {applyId:selectApply.applyId},
		success : function(msg) {
			$.messager.alert('审核拒绝',msg);
			$('#apply').datagrid('reload',{});//申请数据更新
		},
		error:function(err){
			$.messager.alert('审批提交错误请联系管理员', err);
			$('a#auditPass').show();
			$('a#auditReject').show();
        }
	});
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
	$('input#apply_org_name').val('');
	$('input#apply_user').val('');
	$('textarea#apply_direction').val('');
	
	$('#table_apply_data_east').datagrid('selectAll');
	var rows = $('#table_apply_data_east').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		$('#table_apply_data_east').datagrid('deleteRow',0);
	}
}

