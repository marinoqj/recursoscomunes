package es.golemdr.rrcc.mantenimiento.ext.mapper;

import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Usuario;


public class UsuarioMapperCustom {
	
	public static void copiarPropiedades(UsuarioData origen, Usuario destino, String tipo) {
		
		destino.setIdUsuario(origen.getIdUsuario());
		destino.setIdentificador(origen.getIdentificador());
		
		if(tipo.equals("INSERT")) {

			Comunidad comunidad = new Comunidad();
			comunidad.setIdComunidad(origen.getComunidad().getIdComunidad());
			
			destino.setComunidad(comunidad);
		}
		
	}

}
