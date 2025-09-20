package es.golemdr.rrcc.common.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "comunidades")
public class Comunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMUNIDAD")
    private Integer idComunidad;

    @Column(name = "NOMBRE")
    private String nombre;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comunidad")
    List<Usuario> usuarios;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comunidad")
    List<Recurso> recursos;
}
