package es.golemdr.rrcc.mantenimiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Recurso;
import es.golemdr.rrcc.common.entity.Usuario;
import es.golemdr.rrcc.mantenimiento.repository.RecursosRepository;
import es.golemdr.rrcc.mantenimiento.service.RecursosService;


@Service
public class RecursosServiceImpl implements RecursosService {
	
	private RecursosRepository recursosRepository;
	
	
	public RecursosServiceImpl(RecursosRepository recursosRepository) {
		super();
		this.recursosRepository = recursosRepository;
	}


	@Override
	public Recurso insertarActualizar(Recurso recurso) {
		return recursosRepository.save(recurso);
	}


	@Override
	public Optional<Recurso> recuperarRecursoPorId(int idRecurso) {
		return recursosRepository.findById(idRecurso);
	}


	@Override
	public List<Recurso> recuperarRecursos() {
		return recursosRepository.findAll();
	}

	@Override
	public void borrarRecurso(int idRecurso) {
		recursosRepository.deleteById(idRecurso);
	}

	@Override
	public List<Recurso> recuperarRecursosPorComunidad(int idComunidad) {

		Comunidad comunidad = new Comunidad();
		comunidad.setIdComunidad(idComunidad);
		
		Recurso recurso = new Recurso();
		recurso.setComunidad(comunidad);
		
		Example<Recurso> example = Example.of(recurso);
		
		
		return recursosRepository.findAll(example);
		
	}

}
