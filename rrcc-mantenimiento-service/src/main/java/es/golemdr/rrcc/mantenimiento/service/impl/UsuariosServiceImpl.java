package es.golemdr.rrcc.mantenimiento.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.entity.Usuario;
import es.golemdr.rrcc.mantenimiento.repository.UsuariosRepository;
import es.golemdr.rrcc.mantenimiento.service.UsuariosService;


@Service
public class UsuariosServiceImpl implements UsuariosService {
	
	private UsuariosRepository usuariosRepository;
	
	
	public UsuariosServiceImpl(UsuariosRepository usuarioesRepository) {
		super();
		this.usuariosRepository = usuarioesRepository;
	}


	@Override
	public Usuario insertarActualizar(Usuario usuario) {
		return usuariosRepository.save(usuario);
	}


	@Override
	public Optional<Usuario> recuperarUsuarioPorId(int idUsuario) {
		return usuariosRepository.findById(idUsuario);
	}


	@Override
	public List<Usuario> recuperarUsuarios() {
		return usuariosRepository.findAll();
	}

	@Override
	public void borrarUsuario(int idUsuario) {
		usuariosRepository.deleteById(idUsuario);
	}


	@Override
	public List<Usuario> recuperarUsuariosPorComunidad(int idComunidad) {

		Comunidad comunidad = new Comunidad();
		comunidad.setIdComunidad(idComunidad);
		
		Usuario usuario = new Usuario();
		usuario.setComunidad(comunidad);
		
		Example<Usuario> example = Example.of(usuario);
		
		
		return usuariosRepository.findAll(example);
		
	}


}
