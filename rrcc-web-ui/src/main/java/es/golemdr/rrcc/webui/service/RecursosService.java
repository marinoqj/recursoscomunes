package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.webui.domain.Recurso;



public interface RecursosService {

	List<Recurso> recuperarRecursos();
	
	Recurso recuperarRecursoPorId(String id);
	
	Recurso actualizarRecurso(Recurso recurso);
	
	Recurso insertarRecurso(Recurso recurso);

	void borrarRecurso(String id);
	

	
}
