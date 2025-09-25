package es.golemdr.rrcc.common.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "reservas")
public class Reserva {
	
	@EmbeddedId
	private ReservaId idReserva;
	
	@Column(name = "FECHA")
	private Date fecha;
	@Column(name = "HORA_INICIO")
	private Time horaInicio;	
	@Column(name = "HORA_FIN")
	private Time horaFin;

}
