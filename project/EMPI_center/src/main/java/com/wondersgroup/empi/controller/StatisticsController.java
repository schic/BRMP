package com.wondersgroup.empi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	
	@RequestMapping("/statistics")
	public String statistics(){
		return "/statistics/statistics";
	}

}
