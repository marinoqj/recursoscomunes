package es.golemdr.rrcc.common.entity;

import java.sql.Date;
import java.sql.Time;

import es.golemdr.rrcc.common.enums.TiposRecursos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "reservas")
public class Reserva {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESERVA")
	private Integer idReserva;	
	@Column(name = "FECHA")
	private Date fecha;
	@Column(name = "HORA_INICIO")
	private Time horaInicio;	
	@Column(name = "HORA_FIN")
	private Time horaFin;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_RECURSO")
	private Recurso recurso;
	
	@Transient
	private Integer idUsuario;
	
	@Transient
	private String identificador;
	
    public Integer getIdUsuario() {
    	return usuario.getIdUsuario();
    }
    
    public String getIdentificador() {
    	return usuario.getIdentificador();
    }
}
