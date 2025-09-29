package es.golemdr.rrcc.common.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.entity.Reserva;
import es.golemdr.rrcc.common.entity.Usuario;
import es.golemdr.rrcc.common.mapper.RecursoMapper;
import es.golemdr.rrcc.common.mapper.ReservaCustomMapper;

@Component
public class ReservaCustomMapperImpl implements ReservaCustomMapper {

    @Autowired
    private RecursoMapper recursoMapper;

    @Override
    public ReservaData toData(Reserva reserva) {
        if ( reserva == null ) {
            return null;
        }

        ReservaData reservaData = new ReservaData();

        reservaData.setFecha( reserva.getFecha() );
        reservaData.setHoraFin( reserva.getHoraFin() );
        reservaData.setHoraInicio( reserva.getHoraInicio() );
        reservaData.setIdReserva( reserva.getIdReserva() );
        reservaData.setIdUsuario( reserva.getIdUsuario() );
        reservaData.setRecurso( recursoMapper.toData( reserva.getRecurso() ) );

        return reservaData;
    }

    @Override
    public Reserva toEntity(ReservaData reservaData) {
        if ( reservaData == null ) {
            return null;
        }

        Reserva reserva = new Reserva();

        reserva.setFecha( reservaData.getFecha() );
        reserva.setHoraFin( reservaData.getHoraFin() );
        reserva.setHoraInicio( reservaData.getHoraInicio() );
        reserva.setIdReserva( reservaData.getIdReserva() );
        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(reservaData.getIdUsuario());
        reserva.setUsuario(usuario);
        
        reserva.setRecurso( recursoMapper.toEntity( reservaData.getRecurso() ) );

        return reserva;
    }
}
