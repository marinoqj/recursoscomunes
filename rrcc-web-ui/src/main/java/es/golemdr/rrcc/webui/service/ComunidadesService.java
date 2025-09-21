package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.common.dto.ComunidadData;




public interface ComunidadesService {

	List<ComunidadData> recuperarComunidades();
	
	ComunidadData recuperarComunidadPorId(String id);
	
	ComunidadData actualizarComunidad(ComunidadData comunidad);
	
	ComunidadData insertarComunidad(ComunidadData comunidad);

	void borrarComunidad(String id);
	

	
}
