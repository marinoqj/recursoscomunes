package es.golemdr.rrcc.common.mapper;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.entity.Reserva;


public interface ReservaCustomMapper {

	ReservaData toData(Reserva reserva);
    Reserva toEntity(ReservaData reservaData);

}

