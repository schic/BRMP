package com.wondersgroup.empi.util.common;

import com.alibaba.fastjson.JSONObject;

public class HttpResult {
	
	  private Integer code;
	  private String desc;
	  private JSONObject data;
	  
	  public HttpResult() {}
	  
	  public static HttpResult buildHttpResult(String desc)
	  {
	    return new HttpResult(Integer.valueOf(404), desc);
	  }
	  
	  public HttpResult(Integer code, String desc, JSONObject data)
	  {
	    this.code = code;
	    this.desc = desc;
	    this.data = data;
	  }
	  
	  public HttpResult(Integer code, String desc)
	  {
	    this.code = code;
	    this.desc = desc;
	  }
	  
	  public HttpResult(Integer code, JSONObject data)
	  {
	    this.code = code;
	    this.data = data;
	  }
	  
	  public Integer getCode()
	  {
	    return this.code;
	  }
	  
	  public void setCode(Integer code)
	  {
	    this.code = code;
	  }
	  
	  public JSONObject getData()
	  {
	    return this.data;
	  }
	  
	  public void setData(JSONObject data)
	  {
	    this.data = data;
	  }
	  
	  public String getDesc()
	  {
	    return this.desc;
	  }
	  
	  public void setDesc(String desc)
	  {
	    this.desc = desc;
	  }

}
