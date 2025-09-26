package es.golemdr.rrcc.webui.ext.mapper;

import java.sql.Time;

import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.ext.Constantes;
import es.golemdr.rrcc.common.ext.utils.tools.DateUtils;
import es.golemdr.rrcc.webui.domain.form.ReservaForm;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;

public class ReservaMapperUI {
	
	public static void copiarPropiedades(ReservaForm origen, ReservaData destino) {

		destino.setIdReserva(origen.getIdReserva() != null ? Integer.valueOf(origen.getIdReserva()) : null);
		destino.setFecha(DateUtils.convertirString2DateSQL(origen.getFecha(), Constantes.FECHA_YYYY_MM_DD));
		destino.setHoraInicio(Time.valueOf(origen.getHoraInicio()));
		destino.setHoraFin(Time.valueOf(origen.getHoraFin()));
		
		UsuarioData usuario = new UsuarioData();
		usuario.setIdUsuario(Integer.valueOf(origen.getIdUsuario()));
		
		RecursoData recurso = new RecursoData();
		recurso.setIdRecurso(Integer.valueOf(origen.getIdRecurso()));
		
	}
	
	// TODO
	public static void copiarPropiedades(UsuarioData origen, UsuarioForm destino) {
		
		
	}

}
