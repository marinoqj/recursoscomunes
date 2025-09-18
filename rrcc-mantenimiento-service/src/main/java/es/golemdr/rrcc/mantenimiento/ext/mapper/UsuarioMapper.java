package es.golemdr.rrcc.mantenimiento.ext.mapper;

import es.golemdr.rrcc.mantenimiento.controller.request.UsuarioRequest;
import es.golemdr.rrcc.mantenimiento.domain.Comunidad;
import es.golemdr.rrcc.mantenimiento.domain.Usuario;

public class UsuarioMapper {
	
	public static void copiarPropiedades(UsuarioRequest origen, Usuario destino) {
		
		destino.setIdUsuario(origen.idUsuario());
		destino.setIdentificador(origen.identificador());
		
		Comunidad comunidad = new Comunidad();
		comunidad.setIdComunidad(origen.idComunidad());
		
		destino.setComunidad(comunidad);
		
		
	}

}
