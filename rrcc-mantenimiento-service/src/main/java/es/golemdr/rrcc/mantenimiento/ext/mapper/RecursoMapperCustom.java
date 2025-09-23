package es.golemdr.rrcc.mantenimiento.ext.mapper;

import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Recurso;
import es.golemdr.rrcc.mantenimiento.controller.request.RecursoRequest;


public class RecursoMapperCustom {
	
	public static void copiarPropiedades(RecursoRequest origen, Recurso destino) {
		
		destino.setIdRecurso(origen.idRecurso());
		destino.setTipoRecurso(origen.tipoRecurso());
		
		Comunidad comunidad = new Comunidad();
		comunidad.setIdComunidad(origen.idComunidad());
		
		destino.setComunidad(comunidad);
		
		
	}

}
