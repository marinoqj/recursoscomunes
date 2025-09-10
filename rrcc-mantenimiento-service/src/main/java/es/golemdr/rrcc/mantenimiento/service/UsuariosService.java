package es.golemdr.rrcc.mantenimiento.service;

import java.util.List;
import java.util.Optional;

import es.golemdr.rrcc.mantenimiento.domain.Usuario;

public interface UsuariosService {
	
	Usuario insertarActualizar(Usuario usuario);
	
	Optional<Usuario> recuperarUsuarioPorId(int usuario);
	
	List<Usuario> recuperarUsuarios();

    void borrarUsuario(int idComunidad);
    
    
    
}
