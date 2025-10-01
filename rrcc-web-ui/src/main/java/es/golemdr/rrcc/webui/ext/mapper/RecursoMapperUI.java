package es.golemdr.rrcc.webui.ext.mapper;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.dto.TipoRecursoData;
import es.golemdr.rrcc.webui.domain.form.RecursoForm;

public class RecursoMapperUI {
	
	public static void copiarPropiedades(RecursoForm origen, RecursoData destino) {
		
		destino.setIdRecurso(!origen.getIdRecurso().isEmpty() ? Integer.valueOf(origen.getIdRecurso()) : null);
		
		TipoRecursoData tipoRecurso = new TipoRecursoData();
		tipoRecurso.setIdTipoRecurso(Integer.valueOf(origen.getTipoRecurso()));
		
		destino.setTipoRecurso(tipoRecurso);
		
		ComunidadData comunidad = new ComunidadData();
		comunidad.setIdComunidad(Integer.valueOf(origen.getIdComunidad()));
		destino.setComunidad(comunidad);
		
		
	}
	
	public static void copiarPropiedades(RecursoData origen, RecursoForm destino) {
		
		destino.setIdRecurso(origen.getIdRecurso().toString());
		destino.setTipoRecurso(origen.getTipoRecurso().getIdTipoRecurso().toString());		
		destino.setIdComunidad(origen.getComunidad().getIdComunidad().toString());
		
		
	}

}
