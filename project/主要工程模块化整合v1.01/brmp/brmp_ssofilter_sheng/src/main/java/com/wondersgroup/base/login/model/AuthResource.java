/**
 * 功能： 系统资源定义，用于获取用户的菜单及其他有权限的操作<br>
 * 万达信息<br>
 * 创建日期：Nov 15, 2012
 * @author： "王胤洪"
 * 
 */
package com.wondersgroup.base.login.model;

import java.io.Serializable;
import java.util.List;

/**
 * 系统资源定义，用于获取用户的菜单及其他有权限的操作
 * 
 * @author "王胤洪"
 * 
 */
public class AuthResource implements Serializable {
  /**
   * MENU表示菜单资源
   */
  public final static String RES_MENU = "MENU";
  
  /**
   * 资源ID
   */
  private String resId;
  /**
   * 资源代码，代码编写规则<BR>
   * 资源类型_模块代码_子模块代码_自定义
   */
  private String resCode;
  /**
   * 资源名称
   */
  private String resName;
  /**
   * 资源内容，如资源的URL,操作等
   */
  private String resContent;
  /**
   * 同父同级资源顺序
   */
  private Long resSort;
  /**
   * 资源类型
   */
  private String resType;
  /**
   * 父资源，为NULL表示根资源
   */
  private AuthResource parentRes;
  /**
   * 子资源，注意资源不能嵌套形成死循环
   */
  private List<AuthResource> childRes;
  /**
   * 资源对应的目标，如菜单打开的目标窗口等
   */
  private String resTarget;
  /**
   * 资源展示图片（小），如菜单的图标等
   */
  private String resImgSmall;
  /**
   * 资源展示图片（大），如菜单的图标等
   */
  private String resImgLarge;
  /**
   * 资源图片地址
   */
  private String resImgURL;
  /**
   * 是否五笔
   */
  private boolean isWB;

  /**
   * @return the resId
   */
  public String getResId() {
    return resId;
  }

  /**
   * @param resId
   *          the resId to set
   */
  public void setResId(String resId) {
    this.resId = resId;
  }

  /**
   * @return the resCode
   */
  public String getResCode() {
    return resCode;
  }

  /**
   * @param resCode
   *          the resCode to set
   */
  public void setResCode(String resCode) {
    this.resCode = resCode;
  }

  /**
   * @return the resName
   */
  public String getResName() {
    return resName;
  }

  /**
   * @param resName
   *          the resName to set
   */
  public void setResName(String resName) {
    this.resName = resName;
  }

  /**
   * @return the resContent
   */
  public String getResContent() {
    return resContent;
  }

  /**
   * @param resContent
   *          the resContent to set
   */
  public void setResContent(String resContent) {
    this.resContent = resContent;
  }

  /**
   * @return the resSort
   */
  public Long getResSort() {
    return resSort;
  }

  /**
   * @param resSort
   *          the resSort to set
   */
  public void setResSort(Long resSort) {
    this.resSort = resSort;
  }

  /**
   * @return the resType
   */
  public String getResType() {
    return resType;
  }

  /**
   * @param resType
   *          the resType to set
   */
  public void setResType(String resType) {
    this.resType = resType;
  }

  /**
   * @return the parentRes
   */
  public AuthResource getParentRes() {
    return parentRes;
  }

  /**
   * @param parentRes
   *          the parentRes to set
   */
  public void setParentRes(AuthResource parentRes) {
    this.parentRes = parentRes;
  }

  /**
   * @return the childRes
   */
  public List<AuthResource> getChildRes() {
    return childRes;
  }

  /**
   * @param childRes
   *          the childRes to set
   */
  public void setChildRes(List<AuthResource> childRes) {
    this.childRes = childRes;
  }

  /**
   * @return the resTarget
   */
  public String getResTarget() {
    return resTarget;
  }

  /**
   * @param resTarget
   *          the resTarget to set
   */
  public void setResTarget(String resTarget) {
    this.resTarget = resTarget;
  }

  /**
   * @return the resImgSmall
   */
  public String getResImgSmall() {
    return resImgSmall;
  }

  /**
   * @param resImgSmall
   *          the resImgSmall to set
   */
  public void setResImgSmall(String resImgSmall) {
    this.resImgSmall = resImgSmall;
  }

  /**
   * @return the resImgLarge
   */
  public String getResImgLarge() {
    return resImgLarge;
  }

  /**
   * @param resImgLarge
   *          the resImgLarge to set
   */
  public void setResImgLarge(String resImgLarge) {
    this.resImgLarge = resImgLarge;
  }

public String getResImgURL() {
	return resImgURL;
}

public void setResImgURL(String resImgURL) {
	this.resImgURL = resImgURL;
}

public boolean getIsWB() {
	return isWB;
}

public void setIsWB(boolean isWB) {
	this.isWB = isWB;
}

}
