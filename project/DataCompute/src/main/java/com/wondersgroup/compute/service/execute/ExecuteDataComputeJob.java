package com.wondersgroup.compute.service.execute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.compute.dao.intf.CommonDaoIntf;
import com.wondersgroup.compute.service.intf.DataComputeMainIntf;
import com.wondersgroup.empi.po.empipo.ModelData;

@Service
public class ExecuteDataComputeJob {
	
	@Autowired DataComputeMainIntf dataComputeMainIntf;
	
	@Autowired CommonDaoIntf commonDaoIntf;
	
	private static boolean Job = false;//作业job是否占用
	private static byte[] lockJob = new byte[0];
	
	public void executeJob(){
		synchronized (lockJob) {//开启job执行开关，相同运行job撤销
			if (Job){
				System.out.println("Job方法正在执行，退出本次执行，is running，exit");
				return;
			}
			Job=true;
		}
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("status", 1);
			paramMap.put("auditStatus", 9);
			List<ModelData> modelDatas = commonDaoIntf.selectObjListByParam(ModelData.class,"brmp_conf_origin_system_modelbase", paramMap);
			for(int i=0;i<modelDatas.size();i++){
				
				dataComputeMainIntf.updateZYBZ1(modelDatas.get(i));//将临时表作业标志 修改为 1 进行处理
				
				dataComputeMainIntf.updateMainData(modelDatas.get(i));//删除在主题库的重复数据(与临时库比较得出) ↓ 临时表去重插入主题库 ↓ 将插入成功的作业标志修改为 2
				
				dataComputeMainIntf.delZYBZ2(modelDatas.get(i));//删除临时表中，已经成功插入主题库的数据，通过作业标志为 2
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			synchronized (lockJob) {//关闭job执行开关，可运行此job
				Job=false;
			}
		}
		
	}

}
