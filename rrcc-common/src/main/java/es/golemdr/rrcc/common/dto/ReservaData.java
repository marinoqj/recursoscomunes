package es.golemdr.rrcc.common.dto;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import es.golemdr.rrcc.common.entity.ReservaId;
import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idReserva", scope = ReservaData.class)
@Getter @Setter
public class ReservaData {
	
	private ReservaId idReserva;
	private Date fecha;
	private Time horaInicio;	
	private Time horaFin;

}
