package com.wondersgroup.base.login.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.base.login.dao.MenuDao;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.service.MenuService;
import com.wondersgroup.base.login.util.BaseCache;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.core.hibernate.PageRequest;

/**
 * 
 * @描述    ： 获取菜单的serviceImpl
 *
 * @创建人  ：acer
 * @创建时间：2014-10-24 上午9:31:22
 * @修改人  ：acer
 * @修改时间：2014-10-24 上午9:31:22
 * @修改备注：
 *
 * @version 1.0
 */
@Service(value = "menuServiceImpl")
@Transactional(propagation = Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService {

	/**
	 * 缓存工具类
	 */
	private BaseCache cache = new BaseCache(AuthResource.class, 120);

	private String[] nodeType = { "1", "2", "3", "4" };//1代表平台、2代表系统、3代表菜单、4代表功能菜单

	@Autowired
	private MenuDao mDao;
	/**
	 * 
	 * @描述：通过工程名与用户具体信息获取菜单资源
	 * @see com.wondersgroup.base.login.service.MenuService#getResources(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public AuthResource getResources(String loginName, String organId, String resid, String string) {
		AuthResource ar = null;
		try {
			/**
			 * 查看内存中是否存在缓存
			 */
			ar = (AuthResource) cache.get("getResources_" + loginName + "_" + organId + "_" + resid);
			if (cache.get("getResources_" + loginName + "_" + organId + "_" + resid) != null) {
				return ar;
			}
		} catch (Exception e) {
			List<Map<String, String>> listResources = mDao.getResources(loginName, organId, resid);
			/**
			 * 遍历结果集，将结果集封装成AuthResource
			 * 采用递归算法
			 */
			ar = RecursionRes(listResources, ar, resid);
			cache.put("getResources_" + loginName + "_" + organId + "_" + resid, ar);
		}
		return ar;
	}

	/**
	 * 
	 *
	 * @描述：递归获取资源菜单数据
	 *
	 * @return
	 * AuthResource
	 * @创建人  ：acer
	 * @创建时间：2014-10-24下午1:32:17
	 * @修改人  ：acer
	 * @修改时间：2014-10-24下午1:32:17
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	private AuthResource RecursionRes(List<Map<String, String>> listResources, AuthResource ar, String resid) {

		if (listResources != null && listResources.size() > 0) {
			List<AuthResource> childListRes = new ArrayList<AuthResource>();
			new ArrayList<AuthResource>();
			for (int i = 0; i < listResources.size(); i++) {
				Map<String, String> map = listResources.get(i);
				//结果集中的父级资源ID与ar中的资源ID相等；形成了上下级关系
				/**
				 * 第一步 先封装顶级节点
				 */
				if (ar == null && resid.equals(map.get("resid"))) {
					ar = new AuthResource();
					ar.setResId(map.get("resid"));
					ar.setResCode(map.get("keyword"));
					ar.setResName(map.get("resname"));
					ar.setResContent(map.get("url"));
					ar.setResType(map.get("type"));
					ar.setResImgSmall(map.get("icon_small"));
					ar.setResImgLarge(map.get("icon_large"));
					ar.setResSort(Long.valueOf(i));
					//listResources.remove(i);
					/**
					 * 递归获取子集
					 */
					return RecursionRes(listResources, ar, resid);
				} else if (ar != null && map.get("presid").equals(ar.getResId())) {
					//List<AuthResource> childListRes = new ArrayList<AuthResource>();
					/**
					 * 第二步 再封装相应的子集节点
					 */
					AuthResource childResource = new AuthResource();
					childResource.setResId(map.get("resid"));
					childResource.setResCode(map.get("keyword"));
					childResource.setResName(map.get("resname"));
					childResource.setResContent(map.get("url"));
					childResource.setResType(map.get("type"));
					childResource.setResImgSmall(map.get("icon_small"));
					childResource.setResImgLarge(map.get("icon_large"));
					childResource.setResSort(Long.valueOf(i));
					/**
					 * 递归获取子集
					 */
					childResource = RecursionRes(listResources, childResource, resid);
					childListRes.add(childResource);

				}
			}
			//ar.setChildRes(childListRes);
			if (ar != null) {
				ar.setChildRes((childListRes != null && childListRes.size() > 0) ? childListRes : null);
			}
		}
		return ar;
	}

	/**
	 * 
	 *
	 * @描述：将请求的URL的功能菜单获取
	 *
	 * @param request
	 * @return
	 * Map<String,String>
	 * @创建人  ：kh
	 * @创建时间：2014-11-3下午3:22:08
	 * @修改人  ：kh
	 * @修改时间：2014-11-3下午3:22:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public String getFunctionMenus(HttpServletRequest request, String url) {
		//String url = request.getServletPath();
		/**
		 * 将获得url与缓存中的进行对比，获取该功能下的功能菜单
		 */
		AuthInfo authInfo = SessionUtil.getCurrAuthInfo(request);
		String projectNameOrID = SessionUtil.getProjectNameOrID(request);
		//step1 获取缓存资源
		AuthResource ar;
		try {
			ar = (AuthResource) cache.get("getResources_" + authInfo.getLoginName() + "_"
					+ authInfo.getOrganId() + "_" + projectNameOrID);
		} catch (Exception e) {
			ar = getResources(authInfo.getLoginName(), authInfo.getOrganId(), projectNameOrID, "");
		}
		//遍历资源获取资源的功能菜单
		List<AuthResource> result = getFunctMenu(ar, url);
		return JSONArray.fromObject(result).toString();

	}

	/**
	 * 
	 *
	 * @描述：通过资源与URl的递归遍历获取功能菜单
	 *
	 * @param ar
	 * @param url
	 * @return
	 * List<AuthResource>
	 * @创建人  ：kh
	 * @创建时间：2014-11-3下午4:12:08
	 * @修改人  ：kh
	 * @修改时间：2014-11-3下午4:12:08
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	private List<AuthResource> getFunctMenu(AuthResource ar, String url) {
		List<AuthResource> result = new ArrayList<AuthResource>();
		if (ar != null && url.equals(ar.getResContent()) && ar.getChildRes() != null && ar.getChildRes().size() > 0) {
			List<AuthResource> list = ar.getChildRes();
			for (AuthResource resource : list) {
				if (resource.getResType() != null && resource.getResType().equals(nodeType[3])) {
					result.add(resource);
				}
			}
		} else if (ar != null && ar.getChildRes() != null && ar.getChildRes().size() > 0) {
			List<AuthResource> list = ar.getChildRes();
			for (AuthResource resource : list) {
				return getFunctMenu(resource, url);
			}
		}
		return result;
	}

	/**
	 * 
	 * @描述：排除掉节点类型为4的节点
	 * @see com.wondersgroup.base.login.service.MenuService#removeSomeResources(com.wondersgroup.base.login.model.AuthResource)
	 */
	@Override
	public AuthResource removeSomeResources(AuthResource resources) {
		if (resources == null) {
			return resources;
		}
		if (nodeType[3].equals(resources.getResType())) {
			return new AuthResource();
		} else {
			if (resources.getChildRes() != null && resources.getChildRes().size() > 0) {
				List<AuthResource> list = resources.getChildRes();
				List<AuthResource> result = new ArrayList<AuthResource>();
				for (AuthResource ar : list) {
					//if (!nodeType[3].equals(ar.getResType())) {
					//ar = removeSomeResources(ar);
						result.add(ar);
					//}
				}
				resources.setChildRes(result);
			}
		}
		return resources;
	}

	public static void mian(String[] args) {

	}
	
	// 获取系统发布版本
	public String getVersionByResid(String resid){
		return mDao.getVersionByResid(resid);
	}
	
	//分页查询系统历史版本信息
	public Map<String,Object> queryLogList(String resid,PageRequest pageRequest){
		return mDao.queryLogList(resid, pageRequest);
	}
}
