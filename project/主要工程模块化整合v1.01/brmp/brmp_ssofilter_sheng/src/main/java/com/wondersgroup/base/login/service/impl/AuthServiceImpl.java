package com.wondersgroup.base.login.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wondersgroup.base.login.dao.AuthDao;
import com.wondersgroup.base.login.filter.LoginFilter;
import com.wondersgroup.base.login.model.AuthInfo;
import com.wondersgroup.base.login.model.AuthResource;
import com.wondersgroup.base.login.model.ResultConstant;
import com.wondersgroup.base.login.model.ResultVO;
import com.wondersgroup.base.login.model.TbAuthResource;
import com.wondersgroup.base.login.model.TbAuthUser;
import com.wondersgroup.base.login.service.AuthService;
import com.wondersgroup.base.login.util.BaseCache;
import com.wondersgroup.base.login.util.ConfigManager;
import com.wondersgroup.base.login.util.FileType;
import com.wondersgroup.base.login.util.MD5util;
import com.wondersgroup.base.login.util.PathFormat;
import com.wondersgroup.base.login.util.SessionUtil;
import com.wondersgroup.core.ftp.service.FtpService;
import com.wondersgroup.core.ftp.util.ReadIniInfo;
import com.wondersgroup.core.utils.Identities;

/**
 * 
 * @描述    ： 获取登录人想=相关信息service
 *
 * @创建人  ：kh
 * @创建时间：2014-10-24 下午3:30:56
 * @修改人  ：kh
 * @修改时间：2014-10-24 下午3:30:56
 * @修改备注：
 *
 * @version 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthServiceImpl implements AuthService {

	protected Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 缓存工具类
	 */
	@SuppressWarnings("unused")
	private BaseCache cache = new BaseCache(AuthResource.class, 120);

	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private LoginFilter loginFilter;
	
	@Autowired
	private FtpService ftpService;

	/**
	 * 
	 * @描述：通过登录名、机构ID获取登录人信息
	 * @see com.wondersgroup.base.login.service.AuthService#getAuthUserInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public List<AuthInfo> getAuthUserInfo(String loginName, String organId) {
		List<AuthInfo> authInfoList = authDao.getAuthUserInfo(loginName, organId);
		if (authInfoList != null && authInfoList.size() > 0) {
			for (AuthInfo authInfo : authInfoList) {
				authInfo.setOrganLevel(transformOrganLevel(authInfo.getManageAreaCode()));
			}
		}
		return authInfoList;
	}

	/**
	 * 
	 * @描述：修改密码
	 * @see com.wondersgroup.base.login.service.AuthService#modifyUserPassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ResultVO modifyPassword(String orgId, String loginName, String oldPwd, String newPassword) {
		/**
		 * step1 将密码进行加密后保存进数据库
		 * step2 保存成功时，修改缓存中的密码，同时返回true；保存失败，返回false;返回失败的原因
		 */
		ResultVO resultVO = null;
		newPassword = MD5util.getInstance().calcMD5(newPassword);
		if (newPassword.equals(oldPwd)) {
			resultVO = new ResultVO(ResultConstant.RESULT_FAIL, ResultConstant.MESSAGE_FAIL, "新修改密码与旧密码一致，请重新输入！");
		} else {
			try{
				this.authDao.modifyUserPassword(orgId, loginName, newPassword);
				resultVO = new ResultVO(ResultConstant.RESULT_SUCCESS, ResultConstant.MESSAGE_SUCCESS, "");
			}catch (Exception e) {
				resultVO = new ResultVO(ResultConstant.RESULT_FAIL, ResultConstant.MESSAGE_FAIL, e.getMessage());
			}
		}
		return resultVO;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TbAuthUser checkLogin(String name, String pass) {
		return authDao.checkLogin(name, pass);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TbAuthResource getFatherMenuItem(String resid) {
		return authDao.getFatherMenuItem(resid);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TbAuthResource> getChildrenResource(String keyword) {
		return authDao.getChildrenResource(keyword);
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public String changePassword(String loginName, String oldPass,
			String newPass) {
		//判断该用户名或密码是否正确
		TbAuthUser user = authDao.checkLogin(loginName, MD5util.getInstance().calcMD5(oldPass));
		if (user != null){
			//修改密码
			boolean con = authDao.changePassword(loginName, MD5util.getInstance().calcMD5(newPass));
			if (con){
				return "changePassSuccess";
			} else {
				return "changePassFail";
			}
		} else {
			return "oldPassError";
		}
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TbAuthUser getUserByLoginName(String loginName) {
		return authDao.getUserByLoginName(loginName);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TbAuthResource getResourceByTreelayer(String treelayer) {
		return authDao.getResourceByTreelayer(treelayer);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<TbAuthResource> getAllResource() {
		return authDao.getAllResource();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<AuthInfo> getUserByLoginNameByOrgid(String loginName, String orgid) {
		return authDao.getUserByLoginNameByOrgid(loginName, orgid);
	}

	@Override
	public AuthInfo checkLogin(String loginName, String passWord, String orgId) {
		return authDao.checkLogin(loginName, passWord, orgId);
	}

	@Override
	public Map<String, List<AuthResource>> getResources(HttpServletRequest request, String loginName, String orgId) {

		/**
		 * 判断访问地址是内网还是外网
		 */
		@SuppressWarnings("static-access")
		boolean isInnerUrl = loginFilter.getRealCasPath(request);
		log.info("serverName:" + request.getHeader("host") + ",int?:" + isInnerUrl);
		String serverName = request.getHeader("host");
		if (serverName.indexOf(":") > -1) {
			serverName = serverName.split(":")[0];
		}
		Map<String, List<AuthResource>> ars = authDao.getResources(loginName, orgId, isInnerUrl, serverName);
		/*for (AuthResource ar : ars) {
			//对于外网，只有端口不同的，直接替换IP地址为访问地址
			if (!isInnerUrl) {
				String serverName = request.getHeader("host");
				if (serverName.indexOf(":") > -1) {
					serverName = serverName.split(":")[0];
				}
				//地址
				String newUrl = "";
				String targetUrl = ar.getResContent();
				if (StringUtils.isNotBlank(targetUrl) && targetUrl.indexOf("://") > 0) {
					String[] splitTemp = targetUrl.split("//");
					//更换为访问的地址+配置的端口及其他信息
					targetUrl = targetUrl.substring(splitTemp[0].length() + 2);
					newUrl = splitTemp[0] + "//" + serverName;
					if (splitTemp[1].indexOf("/") > 0) {
						splitTemp = targetUrl.split("/");
						//splitTemp[0] 为配置的地址或地址:端口
						if (splitTemp[0].indexOf(':') > 0) {
							newUrl = newUrl + ":" + splitTemp[0].substring(splitTemp[0].indexOf(':') + 1)
									+ targetUrl.substring(splitTemp[0].length());
						} else {
							newUrl = newUrl + targetUrl.substring(splitTemp[0].length());
						}
					}

				} else {
					newUrl = targetUrl;
				}
				ar.setResContent(newUrl);

			}
			if ("WEBSERVICE".equalsIgnoreCase(ar.getResCode())) {
				ars.remove(ar);
				break;
			}
		}*/
		return ars;
	}

	/**
	 * 
	 *
	 * @描述：将登陆用户进行本系统化
	 *
	 * @param authUserList
	 * @param resourceList
	 * void
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	@Override
	public void setSystemize(HttpServletRequest request, AuthInfo currentAuthInfo, List<AuthResource> resourceList) {
		SessionUtil.put(request, "user", setUser(currentAuthInfo));
		SessionUtil.put(request, "loginUser", setUser(currentAuthInfo));
		SessionUtil.put(request, "map", setResource(resourceList));

	}

	private Object setResource(List<AuthResource> resourceList) {

		return null;
	}

	private TbAuthUser setUser(AuthInfo currentAuthInfo) {
		TbAuthUser user = new TbAuthUser();
		user.setUserid(currentAuthInfo.getUserId());
		user.setUsername(currentAuthInfo.getLoginName());
		user.setLoginname(currentAuthInfo.getLoginName());
		user.setPsw(currentAuthInfo.getPwd());
		return user;
	}

	@Override
	public AuthInfo getUserByLoginName(String loginName, String orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifyUserPassword(String loginName, String orgId, String oldpwd, String newPassWord) {
		return this.authDao.modifyUserPassword(loginName, orgId, oldpwd, newPassWord);
	}

	@Override
	public void addApp(String userid, String orgid, String resId) {
		this.authDao.addApp(userid, orgid, resId);
	}

	@Override
	public void delApp(String userid, String orgid, String resId) {
		this.authDao.delApp(userid, orgid, resId);
	}

	@Override
	public List<AuthResource> queryApp(HttpServletRequest request, String userId, String organId) {
		@SuppressWarnings("static-access")
		boolean isInnerUrl = loginFilter.getRealCasPath(request);
		String serverName = request.getHeader("host");
		if (serverName.indexOf(":") > -1) {
			serverName = serverName.split(":")[0];
		}
		List<AuthResource> ars= this.authDao.queryApp(userId, organId, isInnerUrl, serverName);
		return ars;
	}

	@Override
	public Map<String, Object> queryPersonDetail(String empId) {
		return authDao.queryPersonDetail(empId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean modifyUserInfo(Map<String, String> info,String empid){
		try{
			String  personid = info.get("personid");
			Map<String, Object> person = authDao.queryPersonDetailById(personid);
			String fileid ="";
			//删除照片
			if (StringUtils.isNotBlank(person.get("PHOTO_ID")==null?"":person.get("PHOTO_ID").toString()) 
					&& StringUtils.isBlank(info.get("photo_id"))) {
				this.del("PERSONPHOTO", person.get("PHOTO_ID").toString());
			}
			//照片信息
			JSONObject photoInfo = JSONObject.fromObject(info.get("photoInfo"));
			if (!photoInfo.isEmpty()) {
				String file_name = photoInfo.getString("name");
				String image_data = photoInfo.getString("data");
				fileid = this.saveBase64("PERSONPHOTO", file_name, image_data);
			}
			
			@SuppressWarnings("rawtypes")
			Iterator it = info.keySet().iterator();  
			while(it.hasNext()) {  
				String key = (String) it.next(); 
				if(key.equals("personid")||key.equals("photoInfo")){
					it.remove();  
					info.remove(key);
				}
			} 
			
			if(StringUtils.isNotBlank(fileid)){
				info.put("photo_id",fileid);
			}
			info.put("create_by", empid);
			info.put("modify_by", empid);
			authDao.updatePerson(personid, info);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String saveBase64(String key, String fileName, String content) throws Exception {
		ConfigManager configManager = ConfigManager.getInstance();
		Map<String, Object> conf = configManager.getConfig(StringUtils.isBlank(key) ? "DEFAULT" : key);
		byte[] data = Base64.decodeBase64(content.getBytes());
		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (data.length > maxSize) {
			throw new Exception("文件大小超出限制");
		}

		String savePath = (String) conf.get("savePath");
		String originFileName = fileName;
		String suffix = FileType.getSuffixByFilename(originFileName);

		originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
		savePath = savePath + suffix;
		if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
			throw new Exception("文件类型错误");
		}
		savePath = PathFormat.parse(savePath, originFileName);

		String physicalPath = (String) conf.get("rootPath") + savePath;
		
		try{
			// 登陆FTP
			ReadIniInfo iniInfo = new ReadIniInfo();
			FTPClient ftpClient = new FTPClient();
			ftpService.login(ftpClient, iniInfo);
			ftpService.uploadFile(ftpClient, physicalPath.substring(0, physicalPath.lastIndexOf("/")), physicalPath.substring(physicalPath.lastIndexOf("/")+1, physicalPath.length()), new ByteArrayInputStream(data), iniInfo);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("FTP上传错误");
		}
		
		String id = Identities.uuid();
		List<String> list = new ArrayList<String>();
		list.add(id);
		list.add(fileName);
		list.add(suffix);
		list.add(savePath);
		list.add(Long.valueOf(data.length).toString());
		list.add("");
		list.add("");
		authDao.saveFile(list);
		return id;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void del(String key, String fileid) {
		try {
			Map<String, Object> file = authDao.queryFileById(fileid);
			authDao.deleteFile(fileid);
			ConfigManager configManager = ConfigManager.getInstance();
			Map<String, Object> conf = configManager.getConfig(key);

			ReadIniInfo iniInfo = new ReadIniInfo();
			FTPClient ftpClient = new FTPClient();
			ftpService.login(ftpClient, iniInfo);
			ftpService.delFile(ftpClient, conf.get("rootPath").toString() + file.get("PATH").toString());
			ftpService.logout(ftpClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}

	/**
	 * 
	 *
	 * @描述：通过行政区划转化成机构的级别
	 * 1表示省级】2表示市级】3表示区县级】4表示乡镇级】5表示业务级
	 *
	 * @param yyxzqh
	 * @return
	 * String
	 * @创建人  ：KouHao
	 * @创建时间：2014-4-28下午05:03:51
	 * @修改人  ：KouHao
	 * @修改时间：2014-4-28下午05:03:51
	 * @修改备注：
	 * @version 1.0
	 *
	 */
	private String transformOrganLevel(String yyxzqh) {
		if (StringUtils.isBlank(yyxzqh) && yyxzqh.length() < 6) {
			return "";
		}
		if (yyxzqh.length() >= 6 && yyxzqh.length() <= 6 && yyxzqh.substring(2).equals("0000")) {
			return "0";//省级
		} else if (yyxzqh.length() >= 6 && yyxzqh.length() <= 6 && yyxzqh.substring(4).equals("00")) {
			return "3";//市级
		} else if (yyxzqh.length() >= 6 && yyxzqh.length() < 9) {
			return "2";//乡镇级
		} else if (yyxzqh.length() >= 6 && yyxzqh.length() < 12) {
			return "1";
		} else {
			return "4";
		}
	}

	@Override
	public String getUserType(String userId) {
		return authDao.getUserType(userId);
	}
}
