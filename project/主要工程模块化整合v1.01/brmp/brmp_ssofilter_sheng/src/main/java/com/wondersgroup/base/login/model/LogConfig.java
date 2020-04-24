package com.wondersgroup.base.login.model;

/**
 * 日志配置
 */
public class LogConfig {

	private String ssProject;
	private String projectName;
	private String operationName;// 操作名称
	private String actionClassName;// 需要进行日志记录的Action名称
	private String actionMethodName;// 需要进行日志记录的方法名称
	private String description;// 描述
	
	public LogConfig() {
		super();
	}

	public LogConfig(String ssProject, String projectName,
			String operationName, String actionClassName,
			String actionMethodName, String description) {
		super();
		this.ssProject = ssProject;
		this.projectName = projectName;
		this.operationName = operationName;
		this.actionClassName = actionClassName;
		this.actionMethodName = actionMethodName;
		this.description = description;
	}

	public String getSsProject() {
		return ssProject;
	}


	public void setSsProject(String ssProject) {
		this.ssProject = ssProject;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getActionClassName() {
		return actionClassName;
	}

	public void setActionClassName(String actionClassName) {
		this.actionClassName = actionClassName;
	}

	public String getActionMethodName() {
		return actionMethodName;
	}

	public void setActionMethodName(String actionMethodName) {
		this.actionMethodName = actionMethodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}