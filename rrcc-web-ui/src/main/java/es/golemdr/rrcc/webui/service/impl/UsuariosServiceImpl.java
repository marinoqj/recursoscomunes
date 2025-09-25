package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.UsuariosService;
import reactor.core.publisher.Mono;

@Service
public class UsuariosServiceImpl extends BaseService implements UsuariosService {

	private static final String USUARIOS_PATH = "/usuarios";
	
	@Override
	public UsuarioData insertarUsuario(UsuarioData usuario) {
		return webClient.post().uri(serverName + ":" + port + context + USUARIOS_PATH)
				.body(Mono.just(usuario), UsuarioData.class).retrieve().bodyToMono(UsuarioData.class).block();
	}

	@Override
	public UsuarioData recuperarUsuarioPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + USUARIOS_PATH + "/" + id)
				.retrieve()
				.bodyToMono(UsuarioData.class)
				.block();
	}

	@Override
	public UsuarioData actualizarUsuario(UsuarioData usuario) {
		return webClient.put().uri(serverName + ":" + port + context + USUARIOS_PATH)
				.body(Mono.just(usuario), UsuarioData.class).retrieve().bodyToMono(UsuarioData.class).block();
	}

	@Override
	public void borrarUsuario(String id) {
		webClient.delete()
		.uri(serverName + ":" + port + context + USUARIOS_PATH + "/" + id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}
	
	@Override
	public List<UsuarioData> recuperarUsuarios(String idComunidad) {
		return webClient.get()
				.uri(serverName + ":" + port + context + USUARIOS_PATH + "/comunidad/" + idComunidad)
				.retrieve()
				.bodyToFlux(UsuarioData.class)
				.collectList()
				.block();
	}
	
}
