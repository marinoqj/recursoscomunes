package es.golemdr.rrcc.mantenimiento.service;

import java.util.List;
import java.util.Optional;

import es.golemdr.rrcc.mantenimiento.domain.Recurso;

public interface RecursosService {
	
	Recurso insertarActualizar(Recurso recurso);
	
	Optional<Recurso> recuperarRecursoPorId(int recurso);
	
	List<Recurso> recuperarRecursos();

    void borrarRecurso(int idRecurso);
    
    
    
}
