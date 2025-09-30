package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.common.dto.ReservaData;




public interface ReservasService {

	List<ReservaData> recuperarReservasPorUsuario(String idUsuario);
	
	ReservaData insertarReserva(ReservaData reserva);
	
	void borrarReserva(String idReserva);
	
}
