package es.golemdr.rrcc.common.mapper;

import org.mapstruct.Mapper;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.entity.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

	ReservaData toData(Reserva reserva);
    Reserva toEntity(ReservaData reservaData);

}

