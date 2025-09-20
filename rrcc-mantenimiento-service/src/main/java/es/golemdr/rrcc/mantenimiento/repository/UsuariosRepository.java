package es.golemdr.rrcc.mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.golemdr.rrcc.common.entity.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> { }


