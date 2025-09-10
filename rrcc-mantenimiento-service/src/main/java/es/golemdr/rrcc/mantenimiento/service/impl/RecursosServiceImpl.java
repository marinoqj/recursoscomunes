package es.golemdr.rrcc.mantenimiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.mantenimiento.domain.Recurso;
import es.golemdr.rrcc.mantenimiento.repository.RecursosRepository;
import es.golemdr.rrcc.mantenimiento.service.RecursosService;


@Service
public class RecursosServiceImpl implements RecursosService {
	
	private RecursosRepository recursoesRepository;
	
	
	public RecursosServiceImpl(RecursosRepository recursoesRepository) {
		super();
		this.recursoesRepository = recursoesRepository;
	}


	@Override
	public Recurso insertarActualizar(Recurso recurso) {
		return recursoesRepository.save(recurso);
	}


	@Override
	public Optional<Recurso> recuperarRecursoPorId(int idRecurso) {
		return recursoesRepository.findById(idRecurso);
	}


	@Override
	public List<Recurso> recuperarRecursos() {
		return recursoesRepository.findAll();
	}

	@Override
	public void borrarRecurso(int idRecurso) {
		recursoesRepository.deleteById(idRecurso);
	}


}
