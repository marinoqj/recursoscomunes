package es.golemdr.rrcc.webui.ext.mapper;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;

public class UsuarioMapper {
	
	public static void copiarPropiedades(UsuarioForm origen, UsuarioData destino) {
		
		destino.setIdUsuario(!origen.getIdUsuario().isEmpty() ? Integer.valueOf(origen.getIdUsuario()) : null);
		destino.setIdentificador(origen.getIdentificador());
		
		ComunidadData comunidad = new ComunidadData();
		comunidad.setIdComunidad(Integer.valueOf(origen.getIdComunidad()));
		destino.setComunidad(comunidad);;
		
		
	}
	
	public static void copiarPropiedades(UsuarioData origen, UsuarioForm destino) {
		
		destino.setIdUsuario(origen.getIdUsuario().toString());
		destino.setIdentificador(origen.getIdentificador());		
		destino.setIdComunidad(origen.getComunidad().getIdComunidad().toString());
		
		
	}

}
