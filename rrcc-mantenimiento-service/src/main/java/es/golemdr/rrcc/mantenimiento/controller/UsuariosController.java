package es.golemdr.rrcc.mantenimiento.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.golemdr.rrcc.mantenimiento.controller.constants.UrlConstants;
import es.golemdr.rrcc.mantenimiento.controller.request.UsuarioRequest;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.entity.Usuario;
import es.golemdr.rrcc.common.mapper.UsuarioMapper;
import es.golemdr.rrcc.mantenimiento.ext.exceptions.ResourceNotFoundException;
import es.golemdr.rrcc.mantenimiento.ext.mapper.UsuarioMapperCustom;
import es.golemdr.rrcc.mantenimiento.service.UsuariosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping(UrlConstants.URL_USUARIOS)
@RestController
public class UsuariosController {

	private static final Logger log = LoggerFactory.getLogger(UsuariosController.class);

	public static final String ID_USUARIO = "idUsuario";
	public static final String ID_COMUNIDAD = "idComunidad";

	private UsuariosService usuariosService;
	
	private UsuarioMapper usuarioMapper;

	public UsuariosController(UsuariosService usuariosService, UsuarioMapper usuarioMapper) {
		super();
		this.usuariosService = usuariosService;
		this.usuarioMapper = usuarioMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioData createUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {

		Usuario usuario = new Usuario();

		UsuarioMapperCustom.copiarPropiedades(usuarioRequest, usuario);

		usuario = usuariosService.insertarActualizar(usuario);
		
		return usuarioMapper.toData(usuario);
	}

	@GetMapping(value = UrlConstants.ID_USUARIO_PATH)
	public UsuarioData recuperarUsuario(@PathVariable(ID_USUARIO) @Min(1) int idUsuario) {
		return usuariosService.recuperarUsuarioPorId(idUsuario);
	}

	@GetMapping
	public List<UsuarioData> recuperarUsuarios() {

		List<Usuario> result = usuariosService.recuperarUsuarios();

		return result;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public UsuarioData updateUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {

		final Usuario entity = usuariosService.recuperarUsuarioPorId(usuarioRequest.idUsuario()).orElseThrow(
				() -> new ResourceNotFoundException("Usuario " + usuarioRequest.idUsuario() + " no encontrado"));

		//BeanUtils.copyProperties(usuarioRequest, entity);
		UsuarioMapper.copiarPropiedades(usuarioRequest, entity);

		return usuariosService.insertarActualizar(entity);
	}

	@DeleteMapping(value = UrlConstants.ID_USUARIO_PATH)
	public List<UsuarioData> deleteUsuario(@PathVariable(ID_USUARIO) @Min(1) int idUsuario) {
		
		usuariosService.borrarUsuario(idUsuario);
		
		return recuperarUsuarios();
	}
	
	@GetMapping(value = UrlConstants.LISTADO_USUARIOS_COMUNIDAD_PATH)
	public List<UsuarioData> recuperarUsuariosPorComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {

		List<Usuario> result = usuariosService.recuperarUsuariosPorComunidad(idComunidad);

		return result;
	}

}
