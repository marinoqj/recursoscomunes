package es.golemdr.rrcc.webui.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComunidad", scope = Comunidad.class)
@Getter @Setter
public class Comunidad {

    private Integer idComunidad;
    private String nombre;

}
