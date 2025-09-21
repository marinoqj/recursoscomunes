package es.golemdr.rrcc.mantenimiento.ext.mapper;

import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Usuario;
import es.golemdr.rrcc.mantenimiento.controller.request.UsuarioRequest;


public class UsuarioMapperCustom {
	
	public static void copiarPropiedades(UsuarioRequest origen, Usuario destino) {
		
		destino.setIdUsuario(origen.idUsuario());
		destino.setIdentificador(origen.identificador());
		
		Comunidad comunidad = new Comunidad();
		comunidad.setIdComunidad(origen.idComunidad());
		
		destino.setComunidad(comunidad);
		
		
	}

}
