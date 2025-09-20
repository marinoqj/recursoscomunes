package es.golemdr.rrcc.common.mapper;

import org.mapstruct.Mapper;

import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.entity.Usuario;

@Mapper(componentModel = "spring", uses = { ComunidadMapper.class })

public interface UsuarioMapper {
	
    UsuarioData toData(Usuario entity);
    Usuario toEntity(UsuarioData data);
}

