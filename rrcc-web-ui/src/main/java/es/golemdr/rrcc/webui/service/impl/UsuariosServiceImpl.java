package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.webui.domain.Usuario;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.UsuariosService;
import reactor.core.publisher.Mono;

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

	@Override
	public Usuario insertarUsuario(Usuario usuario) {
		return webClient.post().uri(serverName + ":" + port + context + "/usuarios")
				.body(Mono.just(usuario), Usuario.class).retrieve().bodyToMono(Usuario.class).block();
	}

	@Override
	public Usuario recuperarUsuarioPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + "/usuarios/" + id)
				.retrieve()
				.bodyToMono(Usuario.class)
				.block();
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		return webClient.put().uri(serverName + ":" + port + context + "/usuarios")
				.body(Mono.just(usuario), Usuario.class).retrieve().bodyToMono(Usuario.class).block();
	}

	@Override
	public void borrarUsuario(String id) {
		webClient.delete()
		.uri(serverName + ":" + port + context + "/usuarios/" + id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}
	
}
