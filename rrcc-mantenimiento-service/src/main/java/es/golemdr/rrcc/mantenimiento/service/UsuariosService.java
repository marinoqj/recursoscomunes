package es.golemdr.rrcc.mantenimiento.service;

import java.util.List;
import java.util.Optional;

import es.golemdr.rrcc.common.entity.Usuario;


public interface UsuariosService {
	
	Usuario insertarActualizar(Usuario usuario);
	
	Optional<Usuario> recuperarUsuarioPorId(int idUsuario);
	
	List<Usuario> recuperarUsuarios();

    void borrarUsuario(int idComunidad);
    
    List<Usuario> recuperarUsuariosPorComunidad(int idComunidad);
    
    
    
}
