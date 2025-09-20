package es.golemdr.rrcc.mantenimiento.service;

import java.util.List;
import java.util.Optional;

import es.golemdr.rrcc.common.entity.Comunidad;


public interface ComunidadesService {
	
	Comunidad insertarActualizar(Comunidad comunidad);
	
	Optional<Comunidad> recuperarComunidadPorId(int comunidad);
	
	List<Comunidad> recuperarComunidades();

    void borrarComunidad(int idComunidad);
    
    
    
}
