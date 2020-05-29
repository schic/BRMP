/***********************************************************************
 * datatype页面js 值域字典管理页面js
 * 
 ***********************************************************************/
/**
 * 初始化全局变量选择的字典
 */
var selectDic;

/**
 * 可修改的datagrid,继承表格数据展示
 */
$.extend($.fn.datagrid.methods, {
    enableCellEditingDic: function(jq){
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


/**
 * 查找字典
 */
function dicSelect(){
    var beginDate = $('input#beginDicDate').combo('getText');
    var endDate = $('input#toDicDate').combo('getText');
    var status = $('select#status4selectDic').combo('getValue');
    var auditStatus = $('select#auditStatus4selectDic').combo('getValue');
	$('table#dic').datagrid({
		url:'dic_admin/datatype/ajax/dicQuery',
		method:'post',
		queryParams: {beginDate:beginDate,endDate:endDate,status:status,auditStatus:auditStatus},
	});
}
/**
 * 重置查找字典
 */
function reSet4dicSelect(){
	$('input#beginDicDate').combo('setText','');
	$('input#toDicDate').combo('setText','');
	$('select#status4selectDic').combobox('select', '');
	$('select#auditStatus4selectDic').combobox('select', '');
}

//修改选择的模型
function updateDic(){
	$('a#updateDicItem').show();
	$('a#insertDicItem').hide();
	
    var row = $('#dic').datagrid('getSelected');
    selectDic = row;
    if (row){
    	$('#dic_col').dialog('center');
    	$('#dic_col').dialog('open');
    	$('#dic_col_table').datagrid({
    		rownumbers:false,
    		singleSelect:true,
    		toolbar:'#toolbar_dic_col',
    		
    		url:'dic_admin/datatype/ajax/1',
    	    method:'post',
    	    queryParams: {
    			dicId: row.dicId
    		},
    		
    		//排序
    		sortName:'displayOrder',    
            sortOrder:'asc',
            remoteSort:false,
            
            
    	});
    	
    	$('input#originSystemIdDic').textbox('setValue',row.originSystemId);
    	$('input#dicId').textbox('setValue',row.dicId);
    	$('input#dicCode').textbox('setValue',row.dicCode);
    	$('input#dicName').textbox('setValue',row.dicName);
    	
    } else {
    	$.messager.alert('提示', '请选择一个模型');
    }
}


//新建立值域字典
function newDic(){
	
	$('#dic').datagrid('clearSelections');//取消选择
	
	$('a#updateDicItem').hide();
	$('a#insertDicItem').show();
	$('#dic_col').dialog('center');
	$('#dic_col').dialog('open');
	
	$('#dic_col_table').datagrid({
		rownumbers:false,
		singleSelect:true,
		toolbar:'#toolbar_dic_col',
		
//		url:'static/common/json/newModel.json',
//	    method:'get',
//	    
//	    queryParams: {
//	    	originSystemId: initOriginSystemId,
//		},
		
		//排序
		sortName:'displayOrder',    
        sortOrder:'asc',
        remoteSort:false,
        
        
	});
	
	$('input#originSystemIdDic').textbox('setValue',initOriginSystemId);//系统id,jsp页面初始化传入
	$('input#dicId').textbox('setValue','系统稍后生成..');
	$('input#dicCode').textbox('setValue','newDic');
	$('input#dicName').textbox('setValue','新字典');
	
	
}

//停用模型按钮
function disabledDic(){
	$.messager.confirm('请确认','停用模型后,将导致已经获取模型数据接口的用户,接口失效;   并且在将来再次启用时,模型将交由系统接入方重新设计',function(r){
		if(r){
			$.messager.alert('停用模型','按钮接口未完成');
			
		}
		
	});
	
}

//启用模型按钮
function enabledDic(){
	$.messager.alert('启用模型','按钮接口未完成');
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
function cancelDicCol(){
	$('#dic_col').dialog('close');
}

//弹窗关闭了清除窗口文本输入内容
function afterCloseDicCol(){
	//console.log('弹窗关闭了');
	$('input#originSystemIdDic').textbox('setValue','');
	$('input#dicId').textbox('setValue','');
	$('input#dicCode').textbox('setValue','');
	$('input#dicName').textbox('setValue','');
	$('#dic_col_table').datagrid('load',{});
}

/**
 * 新增字典项
 */
function insertDicCol(){
	$('#dic_col_table').datagrid('insertRow',{row:{displayOrder:null,code:'newCode',name:'newName'}});
}

/**
 * 删除字典项
 */
function deleteDicCol(){
	var colRow = $('#dic_col_table').datagrid('getSelected');
	if (colRow) {
		var colIndex = $('#dic_col_table').datagrid('getRowIndex',colRow);
		$('#dic_col_table').datagrid('deleteRow', colIndex);
	} else {
		$.messager.alert('提示', '请选择一行进行删除');
	}
}

/**
 * 保存值域字典数据
 */
function updateDicCol(){
	var dicName = $('input#dicName').textbox('getValue');
	var dicCode = $('input#dicCode').textbox('getValue');
	
	var opts = $('#dic_col_table').datagrid('options');//获取模型表数据设置
	$('#dic_col_table').datagrid('endEdit',opts.editIndex);//结束当前编辑
	$('#dic_col_table').datagrid('clearSelections');//取消选择
	var data = $('#dic_col_table').datagrid('getRows');
	$.ajax({
		type : "post",
		url : "dic_admin/datatype/ajax/updateDic",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		data : {json:JSON.stringify(data),originSystemId:selectDic.originSystemId,dicId:selectDic.dicId,dicCode:dicCode,dicName:dicName},
		success : function(msg) {
			$.messager.alert('保存修改信息', msg);
			//$('#dic_col_table').datagrid('reload',{modelId:selectModel.modelId});
			$('#dic').datagrid('reload',{});
		},
		error:function(err){
			$.messager.alert('保存出现错误', err);
			//$('#dic_col_table').datagrid('reload',{modelId:selectModel.modelId});
        }
	});
}

/**
 * 新增值域字典
 */
function insertDicItem(){
	$('a#insertDic').hide();//隐藏防止重复
	//$('a#insertModelData').show();//恢复显示
	
	var originSystemId = $('input#originSystemIdDic').textbox('getValue');
	var dicName = $('input#dicName').textbox('getValue');
	var dicCode = $('input#dicCode').textbox('getValue');
	
	var jsonDic = '{"originSystemId":"'+ originSystemId +'", "dicCode":"'+ dicCode + '", "dicName":"' + dicName + '" }';
	
	var opts = $('#dic_col_table').datagrid('options');//获取模型表数据设置
	$('#dic_col_table').datagrid('endEdit',opts.editIndex);//结束当前编辑
	$('#dic_col_table').datagrid('clearSelections');//取消选择
	var colData = $('#dic_col_table').datagrid('getRows');
	$.ajax({
		type : "post",
		url : "dic_admin/datatype/ajax/newDic",
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType : "json",
		async : true,
		//modelName:modelName,modelDescription:modelDescription
		data : {json:JSON.stringify(colData), jsonDic: jsonDic},
		success : function(msg) {
			$.messager.alert('信息', msg);
			$('#dic').datagrid('reload',{});
			if(msg == '新增完成'){
				$('#dic_col').dialog('close');
				$('a#insertDic').show();//恢复显示
			}
		},
		error:function(err){
			$.messager.alert('新增出现错误', err);
			$('a#insertDic').show();//恢复显示
        }
	});
	
}



