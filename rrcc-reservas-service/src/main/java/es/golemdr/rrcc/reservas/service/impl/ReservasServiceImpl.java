package es.golemdr.rrcc.reservas.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.entity.Reserva;
import es.golemdr.rrcc.common.entity.ReservaId;
import es.golemdr.rrcc.reservas.repository.ReservasRepository;
import es.golemdr.rrcc.reservas.service.ReservasService;

@Service
public class ReservasServiceImpl implements ReservasService {
	
	private ReservasRepository reservasRepository;
	
	public ReservasServiceImpl(ReservasRepository reservasRepository) {
		super();
		this.reservasRepository = reservasRepository;
	}

	@Override
	public List<Reserva> recuperarReservasPorUsuario(Integer idUsuario) {

		ReservaId id = new ReservaId();
		id.setIdUsuario(idUsuario);
		
		Reserva reserva = new Reserva();
		reserva.setIdReserva(id);
		
		Example<Reserva> example = Example.of(reserva);
		
		
		return reservasRepository.findAll(example);
		
		
	}

}
