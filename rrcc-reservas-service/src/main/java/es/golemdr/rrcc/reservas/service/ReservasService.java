package es.golemdr.rrcc.reservas.service;

import java.util.List;

import es.golemdr.rrcc.common.entity.Reserva;

public interface ReservasService {
	
	List<Reserva> recuperarReservasPorUsuario(Integer idUsuario);
	
	List<Reserva> recuperarReservasPorComunidad(Integer idComunidad);
	
	Reserva insertarActualizar(Reserva reserva);
	
	Reserva recuperarReservaPorId(Integer idReserva);
	
	void borrarReserva(Integer idReserva);

}
