package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.webui.domain.Usuario;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.UsuariosService;

@Service
public class UsuariosServiceImpl extends BaseService implements UsuariosService {

	
	public List<Usuario> recuperarUsuarios() {
		return webClient.get()
				.uri(serverName + ":" + port + context + "/usuarios")
				.retrieve()
				.bodyToFlux(Usuario.class)
				.collectList()
				.block();
	}
	
}
