package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import reactor.core.publisher.Mono;

@Service
public class ComunidadesServiceImpl extends BaseService implements ComunidadesService {

	private static final String COMUNIDADES_PATH = "/comunidades";
	
	public List<ComunidadData> recuperarComunidades() {
		return webClient.get()
				.uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.retrieve()
				.bodyToFlux(ComunidadData.class)
				.collectList()
				.block();
	}

	@Override
	public ComunidadData insertarComunidad(ComunidadData comunidad) {
		return webClient.post().uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.body(Mono.just(comunidad), ComunidadData.class).retrieve().bodyToMono(ComunidadData.class).block();
	}

	@Override
	public ComunidadData recuperarComunidadPorId(String id) {
		return webClient.get()
				.uri(serverName + ":" + port + context + COMUNIDADES_PATH + "/" + id)
				.retrieve()
				.bodyToMono(ComunidadData.class)
				.block();
	}

	@Override
	public ComunidadData actualizarComunidad(ComunidadData comunidad) {
		return webClient.put().uri(serverName + ":" + port + context + COMUNIDADES_PATH)
				.body(Mono.just(comunidad), ComunidadData.class).retrieve().bodyToMono(ComunidadData.class).block();
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
