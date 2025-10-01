package es.golemdr.rrcc.common.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTipoRecurso", scope = TipoRecursoData.class)
@Getter @Setter
public class TipoRecursoData {

    private Integer idTipoRecurso;
    private String descripcion;

}