package es.golemdr.rrcc.common.entity;

import es.golemdr.rrcc.common.enums.TiposRecursos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RECURSO")
    private Integer idRecurso;

    @Column(name = "TIPO_RECURSO")
    private String tipoRecurso;
    
    @ManyToOne
	@JoinColumn(name = "ID_COMUNIDAD")
    private Comunidad comunidad;
    
    @Transient
    private String descripcionTipoRecurso;
    
    public String getDescripcionTipoRecurso() {
    	return TiposRecursos.recuperarPorTipo(tipoRecurso).getDescripcion();
    }
}
