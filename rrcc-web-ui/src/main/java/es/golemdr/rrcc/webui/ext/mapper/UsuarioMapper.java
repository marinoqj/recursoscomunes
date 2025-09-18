package es.golemdr.rrcc.webui.ext.mapper;

import es.golemdr.rrcc.webui.domain.Usuario;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;

public class UsuarioMapper {
	
	public static void copiarPropiedades(UsuarioForm origen, Usuario destino) {
		
		destino.setIdUsuario(!origen.getIdUsuario().isEmpty() ? Integer.valueOf(origen.getIdUsuario()) : null);
		destino.setIdentificador(origen.getIdentificador());
		destino.setIdComunidad(Integer.valueOf(origen.getIdComunidad()));
		
		
	}
	
	public static void copiarPropiedades(Usuario origen, UsuarioForm destino) {
		
		destino.setIdUsuario(origen.getIdUsuario().toString());
		destino.setIdentificador(origen.getIdentificador());
		destino.setIdComunidad(origen.getIdComunidad().toString());
		
		
	}

}
