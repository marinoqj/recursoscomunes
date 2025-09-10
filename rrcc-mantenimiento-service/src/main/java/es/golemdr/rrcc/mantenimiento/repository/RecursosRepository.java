package es.golemdr.rrcc.mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.golemdr.rrcc.mantenimiento.domain.Recurso;


public interface RecursosRepository extends JpaRepository<Recurso, Integer> { }


