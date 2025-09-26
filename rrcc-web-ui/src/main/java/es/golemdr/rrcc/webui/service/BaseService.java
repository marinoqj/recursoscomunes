package es.golemdr.rrcc.webui.service;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class BaseService {

    @Value("${api.server.name}")
	protected String serverName;

    @Value("${api2.server.name}")
	protected String server2Name;
    
    @Value("${api.server.port}")
	protected String port;	

    @Value("${api2.server.port}")
	protected String port2;	

    
	@Value("${api.server.context}")
	protected String context;	
	

	protected final WebClient webClient;
		
    public BaseService (){
        
        webClient = WebClient.create();
        
    }

}
