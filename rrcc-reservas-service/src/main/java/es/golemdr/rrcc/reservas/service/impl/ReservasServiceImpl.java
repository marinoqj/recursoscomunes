package es.golemdr.rrcc.reservas.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.entity.Reserva;
import es.golemdr.rrcc.common.entity.Usuario;
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

		Usuario usuario = new Usuario();
		usuario.setIdUsuario(idUsuario);
		
		
		Reserva reserva = new Reserva();
		reserva.setUsuario(usuario);
		
		Example<Reserva> example = Example.of(reserva);		
		
		return reservasRepository.findAll(example);
		
		
	}

	@Override
	public Reserva insertarActualizar(Reserva reserva) {

		return reservasRepository.save(reserva);
	}

}
