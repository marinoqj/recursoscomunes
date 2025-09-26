package es.golemdr.rrcc.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.golemdr.rrcc.common.entity.Reserva;

public interface ReservasRepository extends JpaRepository<Reserva, Integer> {

}
