package es.golemdr.rrcc.webui.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario", scope = Usuario.class)
@Getter @Setter
public class Usuario {

    private Integer idUsuario;
    private String identificador;

}
