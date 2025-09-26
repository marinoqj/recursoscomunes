package es.golemdr.rrcc.common.dto;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva", scope = ReservaData.class)
@Getter @Setter
public class ReservaData {
	
	private Integer idReserva;
	private Date fecha;
	private Time horaInicio;	
	private Time horaFin;
	// No mapeo el usuario completo para evitar la duplicidad de la comunidad con la del recurso
	private Integer idUsuario;
	private RecursoData recurso;
	

}
