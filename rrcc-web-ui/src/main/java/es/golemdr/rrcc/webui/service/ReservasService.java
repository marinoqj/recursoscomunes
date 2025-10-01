package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.common.dto.ReservaData;




public interface ReservasService {

	List<ReservaData> recuperarReservasPorUsuario(String idUsuario);
	
	List<ReservaData> recuperarReservasPorComunidad(String idComunidad);
	
	ReservaData insertarReserva(ReservaData reserva);
	
	void borrarReserva(String idReserva);
	
}
