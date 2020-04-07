package com.wondersgroup.empi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wondersgroup.empi.dao.intf.EMPIClientJobDaoIntf;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.service.intf.EMPIClientJobIntf;
import com.wondersgroup.empi.util.cipher.IDUtil;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.daoutil.ParamMapUtil;
import com.wondersgroup.empi.util.sqlutil.EMPIJobSqlUtil;

@Service("EMPIClientJob")
public class EMPIClientJobImpl implements EMPIClientJobIntf {
	
	@Autowired
	BaseResource baseResource;
	
	@Autowired 
	EMPIClientJobDaoIntf empiClientJobDaoIntf;
	
	@Override
	public String useEMPIJob1() {
		return empiClientJobDaoIntf.updateBzSql(EMPIJobSqlUtil.init_bz,"init");
	}
	
	
	@Override
	public String useEMPIJob2(EMPIObj empiObj) {
		//empiObj为需要计算的记录
		empiObj.setGxrq(new Date());//更新日期为本日期
		
		
		int MaxDf = 0;//最高得分
		int zqddf = 51;//默认人员入库主索引得分51分
		boolean flagXM = true;//姓名分
		boolean flagSJHM = true;//手机号码分
		boolean flagJTDZ = true;//家庭地址分
		String sql;//主索引最终表，根据内容判断语句 
		
		
		String sql4InsertCacheEMPIObj = EMPIJobSqlUtil.insert_cache_EMPIObj;//更新主索引关联总表,当没有重复记录默认使用insert。
		//索引关联总表是否存在本条记录相同数据。
		EMPIObj empiObjCache = empiClientJobDaoIntf.getEMPIObj(EMPIJobSqlUtil.select_cache_EMPIObj, empiObj);
		if (null != empiObjCache.getRecordid() ) {
			sql4InsertCacheEMPIObj = EMPIJobSqlUtil.update_cache_EMPIObj;//更新主索引缓存表sql，有重复的记录使用update操作数据库
		}
		
		List<EMPIObj> empiObjByCols=null;
		//查找关键字段相同的记录
		if (!"-".equals(empiObj.getSfzh()) ) {
			try {
				empiObjByCols = empiClientJobDaoIntf.select(EMPIJobSqlUtil.select_cache_EMPIObj_bySFZH, EMPIObj.class, ParamMapUtil.getParamMap(empiObj));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}  else {
			
			try {
				empiObjByCols = empiClientJobDaoIntf.select(EMPIJobSqlUtil.select_cache_EMPIObj_byXM, EMPIObj.class, ParamMapUtil.getParamMap(empiObj));
			} catch (Exception e) {
				e.printStackTrace();
			}
			zqddf = 25;
			flagXM = false;
		}
		
		if (null == empiObjByCols || empiObjByCols.size() == 0) {
			empiObj.setObjId(IDUtil.getUUID());
			sql = EMPIJobSqlUtil.insert_EMPIObj;//用插入语句插入索引终表
		} else {
			EMPIObj empiObjFinal = empiClientJobDaoIntf.getEMPIObj(EMPIJobSqlUtil.select_EMPIObj_bySFZH, empiObj);
			empiObj.setObjId(empiObjFinal.getObjId());//创建主索引为终表相同人员索引
			
			for (int j=0;j<empiObjByCols.size();j++) {
				EMPIObj empiObjsByCol = empiObjByCols.get(j);
				if (empiObj.getOriginSystemId().equals(empiObjsByCol.getOriginSystemId()) && empiObj.getOriginId().equals(empiObjsByCol.getOriginId()) ) {
					continue;//如果是相同记录,则旧数据不列入主索引比对
				}
				
				if (empiObjsByCol.getZqddf()>MaxDf){
					MaxDf = empiObjsByCol.getZqddf();//判断是否最大得分数的数据
				}
				
				if (flagXM && empiObjsByCol.getXb().equals(empiObj.getXm()) && !"-".equals(empiObj.getXm()) ){
					zqddf += 25;//姓名相同+25分
					flagXM = false;
				}
				if (flagSJHM && empiObjsByCol.getSjhm().equals(empiObj.getSjhm()) && !"-".equals(empiObj.getSjhm()) ) {
					zqddf += 13;//手机号码相同+13分
					flagSJHM = false;
				}
				if (flagJTDZ && empiObjsByCol.getJtdz().equals(empiObj.getJtdz()) && !"-".equals(empiObj.getJtdz() ) ) {
					zqddf += 11;//家庭住址相同+11分
					flagJTDZ = false;
				}
				
			}
			sql = EMPIJobSqlUtil.update_EMPIObj;//用更新语句更新索引终表
		}
		
		empiObj.setZqddf(zqddf); //有相同索引记录的比较后最终得分
		if (zqddf > MaxDf) {//如果得分超过原来最高得分
			
			try {//更新主索引最终表 索引为本条记录
				empiClientJobDaoIntf.updateSql(sql, ParamMapUtil.getParamMap(empiObj) );
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		try {//更新主索引缓存表
			empiClientJobDaoIntf.updateSql(sql4InsertCacheEMPIObj, ParamMapUtil.getParamMap(empiObj));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		empiClientJobDaoIntf.updateBzSql(EMPIJobSqlUtil.completes_bz,"completes");
		
		return "Job2完成_completes";
	
	}


	@Override
	public String useEMPIJob3() {
		return empiClientJobDaoIntf.deleteEMPIObjByBz();
	}
	
	

}
