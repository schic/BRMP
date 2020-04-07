package test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
import org.apache.cxf.jaxrs.client.WebClient;

public class test_cxf_client {
	
	public static void main(String[] args) {
		String baseAddress = "http://localhost:8980/EMPI_center/webservice/EMPIObj/putEMPIObj";
		WebClient webClient = WebClient.create(baseAddress);
		webClient.acceptEncoding("utf-8");
		webClient.encoding("utf-8");
		webClient.accept(MediaType.APPLICATION_JSON);
		webClient.type(MediaType.APPLICATION_JSON);
		Response resp = webClient.post("321o哦");
		
		//Response resp = webClient.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get();
		System.out.println(resp.getMetadata());
		//readEntity（“方法的返回值类型”）
		//System.out.println(resp3.readEntity(User.class));
		System.out.println(resp.readEntity(String.class));
		
	}

}
