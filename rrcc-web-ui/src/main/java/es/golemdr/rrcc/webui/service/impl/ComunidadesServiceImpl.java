package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.webui.domain.Comunidad;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import reactor.core.publisher.Mono;

@Service
public class ComunidadesServiceImpl extends BaseService implements ComunidadesService {

	private static final String COMUNIDADES_PATH = "/comunidades";
	
	public List<Comunidad> recuperarComunidades() {
		return webClient.get()
				.uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.retrieve()
				.bodyToFlux(Comunidad.class)
				.collectList()
				.block();
	}

	@Override
	public Comunidad insertarComunidad(Comunidad comunidad) {
		return webClient.post().uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.body(Mono.just(comunidad), Comunidad.class).retrieve().bodyToMono(Comunidad.class).block();
	}

	@Override
	public Comunidad recuperarComunidadPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + COMUNIDADES_PATH + "/" + id)
				.retrieve()
				.bodyToMono(Comunidad.class)
				.block();
	}

	@Override
	public Comunidad actualizarComunidad(Comunidad comunidad) {
		return webClient.put().uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.body(Mono.just(comunidad), Comunidad.class).retrieve().bodyToMono(Comunidad.class).block();
	}

	@Override
	public void borrarComunidad(String id) {
		webClient.delete()
		.uri(serverName + ":" + port + context + COMUNIDADES_PATH + "/" + id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}
	
}
