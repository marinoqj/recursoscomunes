package es.golemdr.rrcc.common.enums;

import lombok.Getter;

@Getter
public enum TiposRecursos {
	
	PISTA_PADEL("01", "Pista de padel"),
	PISTA_TENIS("02", "Pista de tenis");

	private String tipo;
	private String descripcion;
	
	TiposRecursos(String tipo, String descripcion) {		
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	
	public static TiposRecursos recuperarPorTipo(String tipo) {
		
		TiposRecursos constante =  null;
		
		for(TiposRecursos tipoRecurso : TiposRecursos.values()) {
			
			if(tipoRecurso.tipo.equals(tipo)) {
				constante = tipoRecurso;
				break;
			}
		}
		
		return constante;
	}
}
