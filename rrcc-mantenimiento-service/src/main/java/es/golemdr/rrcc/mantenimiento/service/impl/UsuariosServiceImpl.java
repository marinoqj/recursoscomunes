package es.golemdr.rrcc.mantenimiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.golemdr.rrcc.mantenimiento.domain.Usuario;
import es.golemdr.rrcc.mantenimiento.repository.UsuariosRepository;
import es.golemdr.rrcc.mantenimiento.service.UsuariosService;


@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	private UsuariosRepository usuarioesRepository;
	
	
	public UsuariosServiceImpl(UsuariosRepository usuarioesRepository) {
		super();
		this.usuarioesRepository = usuarioesRepository;
	}


	@Override
	public Usuario insertarActualizar(Usuario usuario) {
		return usuarioesRepository.save(usuario);
	}


	@Override
	public Optional<Usuario> recuperarUsuarioPorId(int idUsuario) {
		return usuarioesRepository.findById(idUsuario);
	}


	@Override
	public List<Usuario> recuperarUsuarios() {
		return usuarioesRepository.findAll();
	}

	@Override
	public void borrarUsuario(int idUsuario) {
		usuarioesRepository.deleteById(idUsuario);
	}


}
