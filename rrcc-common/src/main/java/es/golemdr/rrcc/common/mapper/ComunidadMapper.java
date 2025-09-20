package es.golemdr.rrcc.common.mapper;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.entity.Comunidad;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComunidadMapper {
    
	ComunidadData toData(Comunidad entity);
    Comunidad toEntity(ComunidadData data);
}
