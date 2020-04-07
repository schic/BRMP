package com.wondersgroup.empi.webservice.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@WebService
public interface EMPIComputeJobIntf {
	
	@POST
	@Path("/reqEMPIClinet")
	@Produces({"application/json"})
    @Consumes({"application/json","application/x-www-form-urlencoded"})
	public String reqEMPIClinet (String msg);
	
}
