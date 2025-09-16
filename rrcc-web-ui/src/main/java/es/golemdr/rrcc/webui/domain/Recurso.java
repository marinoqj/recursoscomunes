package es.golemdr.rrcc.webui.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRecurso", scope = Recurso.class)
@Getter @Setter
public class Recurso {

    private Integer idRecurso;
    private String tipoRecurso;

}
