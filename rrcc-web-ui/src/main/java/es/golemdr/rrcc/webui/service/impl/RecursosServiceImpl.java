package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.webui.domain.Recurso;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.RecursosService;
import reactor.core.publisher.Mono;

@Service
public class RecursosServiceImpl extends BaseService implements RecursosService {

	private static final String RECURSOS_PATH = "/recursos";
	
	public List<Recurso> recuperarRecursos() {
		return webClient.get()
				.uri(serverName + ":" + port + context + RECURSOS_PATH)
				.retrieve()
				.bodyToFlux(Recurso.class)
				.collectList()
				.block();
	}

	@Override
	public Recurso insertarRecurso(Recurso recurso) {
		return webClient.post().uri(serverName + ":" + port + context + RECURSOS_PATH)
				.body(Mono.just(recurso), Recurso.class).retrieve().bodyToMono(Recurso.class).block();
	}

	@Override
	public Recurso recuperarRecursoPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + RECURSOS_PATH + "/" + id)
				.retrieve()
				.bodyToMono(Recurso.class)
				.block();
	}

	@Override
	public Recurso actualizarRecurso(Recurso recurso) {
		return webClient.put().uri(serverName + ":" + port + context + RECURSOS_PATH)
				.body(Mono.just(recurso), Recurso.class).retrieve().bodyToMono(Recurso.class).block();
	}

	@Override
	public void borrarRecurso(String id) {
		webClient.delete()
		.uri(serverName + ":" + port + context + RECURSOS_PATH + "/" + id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}
	
}
