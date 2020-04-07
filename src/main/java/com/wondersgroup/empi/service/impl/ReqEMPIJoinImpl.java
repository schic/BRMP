package com.wondersgroup.empi.service.impl;

import java.text.ParseException;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import com.wondersgroup.empi.dao.intf.DaoJoinIntf;
import com.wondersgroup.empi.po.Dept;
import com.wondersgroup.empi.po.Org;
import com.wondersgroup.empi.po.Person;
import com.wondersgroup.empi.po.empipo.EMPIObj;
import com.wondersgroup.empi.po.webservicepo.RequestPo;
import com.wondersgroup.empi.service.intf.ReqEMPICenterIntf;
import com.wondersgroup.empi.service.intf.ReqEMPIJoinIntf;
import com.wondersgroup.empi.util.common.BaseJunit;
import com.wondersgroup.empi.util.common.BaseResource;
import com.wondersgroup.empi.util.common.CommonUtil;
import com.wondersgroup.empi.util.common.RestCXFClient;

@Service
public class ReqEMPIJoinImpl extends BaseJunit implements ReqEMPIJoinIntf {
	
	@Autowired
	private BaseResource baseResource;
	
	@Autowired
	private DaoJoinIntf daoJoinIntf;
	
	@Value("${EMPICenterProvincialPlatformPersionAdress}")
	private String reqCenterProvincialPlatformPersionAdress;
	
	@Value("${pageSize}")
	private long size;	
	
//	@Test	
//	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	@Override
	public void reqEMPPersion() {
		
		RequestPo reqPo = new RequestPo();
		reqPo.setUsername("proPlatform");
		reqPo.setPassword("123456");
//		reqPo.setParamType("AddEMPI");
		reqPo.setParamType("model");
		reqPo.setModelType("医护人员信息");
		
		try {
						
			long count = daoJoinIntf.selectJoinPersonCount();
			
			if(count>0)
			{
				long totalPage;
		        totalPage = (count / size);
		        if (totalPage * size < count) {
		            totalPage++;
		        }
		        
		        System.out.println("totalPage:"+totalPage);
		        
		        System.out.println("size:"+size);
		        
				for(int i=0;i<totalPage;i++) {
					
					List<Person> listP =  daoJoinIntf.selectJoinPerson(i*size+1,(i+1)*size);
					
					if(listP.size() >0 ){
						
						System.out.println(listP.get(0).getId());
						
						reqPo.setParams(CommonUtil.toJSONString(listP));
						
						String json = CommonUtil.toJSONString(reqPo);				
						
						RestCXFClient.reqEMPICenterPerson(reqCenterProvincialPlatformPersionAdress, json);
						
						
					}
					
				}	
				
			}
			
//			System.out.println("listP.size():"+listP.size());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
//	@Test	
//	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	@Override
	public void reqEMPOrg() {
		
		System.out.println("reqEMPOrg");
		
		RequestPo reqPo = new RequestPo();
		reqPo.setUsername("proPlatform");
		reqPo.setPassword("123456");
//		reqPo.setParamType("AddEMPI");
		reqPo.setParamType("model");
		reqPo.setModelType("机构信息");
		
		try {
						
			long count = daoJoinIntf.selectJoinOrgCount();
			
			System.out.println(count);
			
			if(count>0)
			{
				long totalPage;
		        totalPage = (count / size);
		        if (totalPage * size < count) {
		            totalPage++;
		        }
		        
		        System.out.println("totalPage:"+totalPage);
		        
		        System.out.println("size:"+size);
		        
				for(int i=0;i<totalPage;i++) {
					
					List<Org> listOrg =  daoJoinIntf.selectJoinOrg(i*size+1,(i+1)*size);
					
					if(listOrg.size() >0 ){
						
						System.out.println(listOrg.get(0).getId());
						
						reqPo.setParams(CommonUtil.toJSONString(listOrg));
						
						String json = CommonUtil.toJSONString(reqPo);				
						
						RestCXFClient.reqEMPICenterPerson(reqCenterProvincialPlatformPersionAdress, json);
						
						
					}
					
				}	
				
			}
			
//			System.out.println("listP.size():"+listP.size());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}
	
	@Test	
	@Rollback(true)  //标明使用完此方法后事务不回滚,true时为回滚  
	@Override
	public void reqEMPDept() {
		
		System.out.println("reqEMPDept");
		
		RequestPo reqPo = new RequestPo();
		reqPo.setUsername("proPlatform");
		reqPo.setPassword("123456");
//		reqPo.setParamType("AddEMPI");
		reqPo.setParamType("model");
		reqPo.setModelType("科室信息");
		
		try {
						
			long count = daoJoinIntf.selectJoinDeptCount();
			
			System.out.println(count);
			
			if(count>0)
			{
				long totalPage;
		        totalPage = (count / size);
		        if (totalPage * size < count) {
		            totalPage++;
		        }
		        
		        System.out.println("totalPage:"+totalPage);
		        
		        System.out.println("size:"+size);
		        
				for(int i=0;i<totalPage;i++) {
					
					List<Dept> listDept =  daoJoinIntf.selectJoinDept(i*size+1,(i+1)*size);
					
					if(listDept.size() >0 ){
						
						System.out.println(listDept.get(0).getId());
						
						reqPo.setParams(CommonUtil.toJSONString(listDept));
						
						String json = CommonUtil.toJSONString(reqPo);				
						
						RestCXFClient.reqEMPICenterPerson(reqCenterProvincialPlatformPersionAdress, json);
						
						
					}
					
				}	
				
			}
			
//			System.out.println("listP.size():"+listP.size());
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
