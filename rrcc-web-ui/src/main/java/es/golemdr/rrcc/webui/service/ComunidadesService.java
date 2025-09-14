package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.webui.domain.Comunidad;



public interface ComunidadesService {

	List<Comunidad> recuperarComunidades();
	
	Comunidad recuperarComunidadPorId(String id);
	
	Comunidad actualizarComunidad(Comunidad comunidad);
	
	Comunidad insertarComunidad(Comunidad comunidad);

	void borrarComunidad(String id);
	

	
}
