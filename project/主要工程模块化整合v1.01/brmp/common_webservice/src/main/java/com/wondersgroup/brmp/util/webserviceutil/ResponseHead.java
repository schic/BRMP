package com.wondersgroup.brmp.util.webserviceutil;

public enum ResponseHead {
	Success("成功",0),
	Error("错误",1),
	NoSupport("不支持的服务类型",2),
	Completenss("完成",9)
	;
	
	private ResponseHead (String name,int index) {
		this.name = name;  
        this.index = index; 
	}
	
	
	private String name;  
    private int index;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	} 
	
    
	
}
