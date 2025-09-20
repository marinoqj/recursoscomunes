package es.golemdr.rrcc.mantenimiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.mantenimiento.repository.ComunidadesRepository;
import es.golemdr.rrcc.mantenimiento.service.ComunidadesService;


@Service
public class ComunidadesServiceImpl implements ComunidadesService {
	
	private ComunidadesRepository comunidadesRepository;
	
	
	public ComunidadesServiceImpl(ComunidadesRepository comunidadesRepository) {
		super();
		this.comunidadesRepository = comunidadesRepository;
	}


	@Override
	public Comunidad insertarActualizar(Comunidad comunidad) {
		return comunidadesRepository.save(comunidad);
	}


	@Override
	public Optional<Comunidad> recuperarComunidadPorId(int idComunidad) {
		return comunidadesRepository.findById(idComunidad);
	}


	@Override
	public List<Comunidad> recuperarComunidades() {
		return comunidadesRepository.findAll();
	}

	@Override
	public void borrarComunidad(int idComunidad) {
		comunidadesRepository.deleteById(idComunidad);
	}


}
