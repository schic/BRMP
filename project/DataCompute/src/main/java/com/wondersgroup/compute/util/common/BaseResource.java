package com.wondersgroup.compute.util.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseResource {
	
	@Value("${ComputeJobDate}")
	private String ComputeJobDate;

	public String getComputeJobDate() {
		return ComputeJobDate;
	}

	public void setComputeJobDate(String computeJobDate) {
		ComputeJobDate = computeJobDate;
	}

	
	
}
