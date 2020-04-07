package com.wondersgroup.empi.webservice.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@WebService
public interface EMPIObjIntf {
	
	/*
	@GET
	@Path("/getEMPIObj/{msg}")
	public String getEMPIObj (@PathParam("msg") String msg);
	
	@POST
	@Path("/putEMPIObj")
	@Produces({"application/json"})
    @Consumes({"application/json","application/x-www-form-urlencoded"})
	public String putEMPIObj (String msg);
	*/
	
	@POST
	@Path("/reqEMPIObj")
	@Produces({"application/json"})
    @Consumes({"application/json","application/x-www-form-urlencoded"})
	public String reqEMPIObj (String msg);
	
	
}
