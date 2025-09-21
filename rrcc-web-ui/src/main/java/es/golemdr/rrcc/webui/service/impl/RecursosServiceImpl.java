package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.RecursosService;
import reactor.core.publisher.Mono;

@Service
public class RecursosServiceImpl extends BaseService implements RecursosService {

	private static final String RECURSOS_PATH = "/recursos";
	
	public List<RecursoData> recuperarRecursos() {
		return webClient.get()
				.uri(serverName + ":" + port + context + RECURSOS_PATH)
				.retrieve()
				.bodyToFlux(RecursoData.class)
				.collectList()
				.block();
	}

	@Override
	public RecursoData insertarRecurso(RecursoData recurso) {
		return webClient.post().uri(serverName + ":" + port + context + RECURSOS_PATH)
				.body(Mono.just(recurso), RecursoData.class).retrieve().bodyToMono(RecursoData.class).block();
	}

	@Override
	public RecursoData recuperarRecursoPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + RECURSOS_PATH + "/" + id)
				.retrieve()
				.bodyToMono(RecursoData.class)
				.block();
	}

	@Override
	public RecursoData actualizarRecurso(RecursoData recurso) {
		return webClient.put().uri(serverName + ":" + port + context + RECURSOS_PATH)
				.body(Mono.just(recurso), RecursoData.class).retrieve().bodyToMono(RecursoData.class).block();
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
