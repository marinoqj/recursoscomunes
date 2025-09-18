package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.webui.domain.Usuario;



public interface UsuariosService {

	List<Usuario> recuperarUsuarios();
	
	Usuario recuperarUsuarioPorId(String id);
	
	Usuario actualizarUsuario(Usuario usuario);
	
	Usuario insertarUsuario(Usuario usuario);

	void borrarUsuario(String id);
	
	List<Usuario> recuperarUsuarios(String idComunidad);
	

	
}
