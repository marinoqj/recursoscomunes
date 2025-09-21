package es.golemdr.rrcc.webui.service;

import java.util.List;

import es.golemdr.rrcc.common.dto.UsuarioData;




public interface UsuariosService {

	List<UsuarioData> recuperarUsuarios();
	
	UsuarioData recuperarUsuarioPorId(String id);
	
	UsuarioData actualizarUsuario(UsuarioData usuario);
	
	UsuarioData insertarUsuario(UsuarioData usuario);

	void borrarUsuario(String id);
	
	List<UsuarioData> recuperarUsuarios(String idComunidad);
	

	
}
