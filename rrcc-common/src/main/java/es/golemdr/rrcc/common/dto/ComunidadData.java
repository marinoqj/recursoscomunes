package es.golemdr.rrcc.common.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idComunidad", scope = ComunidadData.class)
@Getter @Setter
public class ComunidadData {

    private Integer idComunidad;
    private String nombre;

}
