package com.wondersgroup.empi.service.intf;

import java.util.List;

import com.wondersgroup.empi.po.empipo.ApplyAttribute;
import com.wondersgroup.empi.po.empipo.ApplyManagement;
import com.wondersgroup.empi.po.empipo.ModelDataAttribute;

/**
 * 关于 申请管理 的业务处理
 *
 */
public interface ApplyIntf {
	
	/**
	 * 保存资源申请请求
	 * @param applyManagement 某项申请的信息
	 * @param applyAttributes 用户申请字段属性配置list
	 */
	String saveApply(ApplyManagement applyManagement, List<ApplyAttribute> applyAttributes);

	/**
	 * 返回  某项申请的信息
	 * @param beginDate 查询条件开始日期
	 * @param endDate   查询条件结束日期
	 * @param auditStatus 查询条件审核状态
	 * @return
	 */
	List<ApplyManagement> selectApply(String beginDate, String endDate, String auditStatus);
	
	/**
	 * 返回  指定用户的申请的信息
	 * @param beginDate 查询条件开始日期
	 * @param endDate 查询条件结束日期
	 * @param auditStatus 查询条件审核状态  //审核状态   1:待审核 2:审核拒绝 9:审核通过
	 * @param uname 用户名用于筛选本用户申请的资源
	 * @return
	 */
	List<ApplyManagement> selectApplyUser(String beginDate, String endDate, String auditStatus, String uname);

	/**
	 * 通过申请id获取申请属性字段。
	 * @param applyId
	 * @return
	 */
	List<ModelDataAttribute> queryApplyById4ModelDataAttribute(String applyId);

	/**
	 * 通过申请id获取申请的模型list，里面是各个申请模型的申请属性list
	 * @param applyId
	 * @return
	 */
	List<List<ModelDataAttribute>> queryApplyById4ApplyDataAttribute(String applyId);
	
	/**
	 * 审核申请资源是否通过
	 * @param applyId 申请资源的id
	 * @param i 设置审核状态  //审核状态   1:待审核 2:审核拒绝 9:审核通过
	 * @return
	 */
	String setAudit(String applyId, int i);


	
}
