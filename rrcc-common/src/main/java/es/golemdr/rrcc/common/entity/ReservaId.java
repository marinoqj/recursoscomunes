package es.golemdr.rrcc.common.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class ReservaId implements Serializable{

	private static final long serialVersionUID = 5440362766789251971L;
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	@Column(name = "ID_RECURSO")
	private Integer idRecurso;

}
