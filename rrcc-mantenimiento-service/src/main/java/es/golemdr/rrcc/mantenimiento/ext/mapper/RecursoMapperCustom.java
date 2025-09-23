package es.golemdr.rrcc.mantenimiento.ext.mapper;

import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Recurso;


public class RecursoMapperCustom {
	
	public static void copiarPropiedades(RecursoData origen, Recurso destino, String tipo) {
		
		destino.setIdRecurso(origen.getIdRecurso());
		destino.setTipoRecurso(origen.getTipoRecurso());
		
		if(tipo.equals("INSERT")) {

			Comunidad comunidad = new Comunidad();
			comunidad.setIdComunidad(origen.getComunidad().getIdComunidad());
			
			destino.setComunidad(comunidad);
		}
		
		
	}

}
