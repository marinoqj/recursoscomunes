package es.golemdr.rrcc.common.mapper;

import org.mapstruct.Mapper;

import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.entity.Recurso;

@Mapper(componentModel = "spring", uses = { ComunidadMapper.class })
public interface RecursoMapper {
	
    RecursoData toData(Recurso entity);
    Recurso toEntity(RecursoData data);
}

