package es.golemdr.rrcc.webui.domain.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaForm {

	private String idReserva;
    private String idUsuario;
    private String idRecurso;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    

}
