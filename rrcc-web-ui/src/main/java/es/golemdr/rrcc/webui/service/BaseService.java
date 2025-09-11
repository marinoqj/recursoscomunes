package es.golemdr.rrcc.webui.service;



import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class BaseService {

    @Value("${api.server.name}")
	protected String serverName;
	
	@Getter
    @Value("${api.server.port}")
	protected String port;	
	
	@Value("${api.server.context}")
	protected String context;	
	

	protected final WebClient webClient;
	
    public BaseService (){
        
        webClient = WebClient.create();
    }

}
