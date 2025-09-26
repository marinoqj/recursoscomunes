package es.golemdr.rrcc.common.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.golemdr.rrcc.common.enums.TiposRecursos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recurso")
    List<Reserva> reservas;
    
    @Transient
    private String descripcionTipoRecurso;
    
    public String getDescripcionTipoRecurso() {
    	return TiposRecursos.recuperarPorTipo(tipoRecurso).getDescripcion();
    }
}
