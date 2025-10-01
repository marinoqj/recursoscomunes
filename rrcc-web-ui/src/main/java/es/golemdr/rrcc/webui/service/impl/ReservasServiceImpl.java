package es.golemdr.rrcc.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.service.BaseService;
import es.golemdr.rrcc.webui.service.ReservasService;
import reactor.core.publisher.Mono;

@Service
public class ReservasServiceImpl extends BaseService implements ReservasService {

	private static final String RESERVAS_PATH = "/reservas";
	
	
	@Override
	public List<ReservaData> recuperarReservasPorUsuario(String idUsuario) {
		return webClient.get()
				.uri(server2Name + ":" + port2 + context + RESERVAS_PATH + "/usuario/" + idUsuario)
				.retrieve()
				.bodyToFlux(ReservaData.class)
				.collectList()
				.block();
	}
	
	@Override
	public List<ReservaData> recuperarReservasPorComunidad(String idComunidad) {
		return webClient.get()
				.uri(server2Name + ":" + port2 + context + RESERVAS_PATH + "/comunidad/" + idComunidad)
				.retrieve()
				.bodyToFlux(ReservaData.class)
				.collectList()
				.block();
	}
	
	
	@Override
	public ReservaData insertarReserva(ReservaData reserva) {
		return webClient.post().uri(server2Name + ":" + port2 + context + RESERVAS_PATH)
				.body(Mono.just(reserva), ReservaData.class).retrieve().bodyToMono(ReservaData.class).block();
	}


	@Override
	public void borrarReserva(String id) {
		webClient.delete()
		.uri(server2Name + ":" + port2 + context + RESERVAS_PATH + "/" + id)
		.retrieve()
		.toBodilessEntity()
		.block();
		
	}
}
