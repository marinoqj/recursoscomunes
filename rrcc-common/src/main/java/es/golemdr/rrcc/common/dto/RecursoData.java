package es.golemdr.rrcc.common.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRecurso", scope = RecursoData.class)
@Getter @Setter
public class RecursoData {

    private Integer idRecurso;
    private String tipoRecurso;
    private String descripcionTipoRecurso;
    private ComunidadData comunidad;

}