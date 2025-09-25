package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.common.dto.RecursoData;



public interface RecursosService {

	List<RecursoData> recuperarRecursos(String idComunidad);
	
	RecursoData recuperarRecursoPorId(String id);
	
	RecursoData actualizarRecurso(RecursoData recurso);
	
	RecursoData insertarRecurso(RecursoData recurso);

	void borrarRecurso(String id);
	

	
}
