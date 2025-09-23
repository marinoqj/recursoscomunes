package es.golemdr.rrcc.webui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;
import es.golemdr.rrcc.webui.ext.mapper.UsuarioMapper;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import es.golemdr.rrcc.webui.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class UsuariosController {
	
	private UsuariosService usuariosService;
	
	private ComunidadesService comunidadesService;
	

	public UsuariosController(UsuariosService usuariosService, ComunidadesService comunidadesService) {
		super();
		this.usuariosService = usuariosService;
		this.comunidadesService= comunidadesService;
	}


	@PostMapping(value=UrlConstants.LISTADO_USUARIOS_COMUNIDAD)
	public String listUsuariosComunidad(String idComunidad, Map<String, Object> map, HttpServletRequest request){

		List<UsuarioData> usuarios = usuariosService.recuperarUsuarios(idComunidad);
		map.put("usuarios", usuarios);
		
		ComunidadData comunidad = comunidadesService.recuperarComunidadPorId(idComunidad);
		map.put("comunidad", comunidad);
		
		return ForwardConstants.FWD_LISTADO_USUARIOS;
	}
	
	@GetMapping(value=UrlConstants.LISTADO_USUARIOS_COMUNIDAD_GET)
	public String redListUsuariosComunidad(@PathVariable String idComunidad, Map<String, Object> map, HttpServletRequest request){

		return listUsuariosComunidad(idComunidad, map, request);
	}
	
	@GetMapping(value = UrlConstants.VER_ALTA_USUARIO)
	public String verAlta(@PathVariable String idComunidad, Model model, HttpServletRequest request) {

		UsuarioForm usuarioForm = new UsuarioForm();
		usuarioForm.setIdComunidad(idComunidad);

		model.addAttribute("modo", "insertar");
		model.addAttribute("usuarioForm", usuarioForm);
		
		return ForwardConstants.FWD_USUARIO_FORM;

	}
	
	@PostMapping(value = UrlConstants.INSERTAR_USUARIO)
	public String insertar(@Valid UsuarioForm formulario, BindingResult result,  Model model, HttpServletRequest request) {
		
		String destino = null;
		UsuarioData usuario = new UsuarioData();
		
		
		if(result.hasErrors()) {			
			
			model.addAttribute("modo", "insertar");			
			destino = ForwardConstants.FWD_USUARIO_FORM;
			
		}else {
			
			UsuarioMapper.copiarPropiedades(formulario, usuario);
			usuariosService.insertarUsuario(usuario);

			// No se puede utilizar un Forward y concatenarle la comunidad. Hay que construir aquí el destino
			//destino = "redirect:listadoUsuariosComunidad" + formulario.getIdComunidad();
			destino = listUsuariosComunidad(formulario.getIdComunidad(), model.asMap(), request);
		}
				

		return destino;
	}
	
	@PostMapping(value = UrlConstants.EDITAR_USUARIO)
	public String editar(String idUsuario, Model model, HttpServletRequest request) {

		UsuarioForm usuarioForm = new UsuarioForm();
		
		UsuarioData usuario = usuariosService.recuperarUsuarioPorId(idUsuario);

		UsuarioMapper.copiarPropiedades(usuario, usuarioForm);
		
		usuarioForm.setIdUsuario(usuario.getIdUsuario().toString());
		
		model.addAttribute("modo", "actualizar");
		model.addAttribute("usuarioForm", usuarioForm);
		
		return ForwardConstants.FWD_USUARIO_FORM;

	}
	
	
	@PostMapping(value = UrlConstants.ACTUALIZAR_USUARIO)
	public String actualizar(@Valid UsuarioForm formulario, BindingResult result,  Map<String, Object> map, HttpServletRequest request) {
		
		
		if(result.hasErrors()) {
			
			map.put("modo", "actualizar");
			
			return ForwardConstants.FWD_USUARIO_FORM;
			
		}else {
				
			UsuarioData usuario  = new UsuarioData();
			
			UsuarioMapper.copiarPropiedades(formulario, usuario);
				
			usuariosService.actualizarUsuario(usuario);
			
			map.put("mensaje", "El usuario se actualizó correctamente");
		
			return listUsuariosComunidad(formulario.getIdComunidad(), map, request);  // Utilizo dos return para poder pasar el message
		}
	}


	@PostMapping(value = UrlConstants.BORRAR_USUARIO)
	public String borrar(String idUsuario, String idComunidad, Model model, HttpServletRequest request) {
		
		usuariosService.borrarUsuario(idUsuario);
				
		model.addAttribute("mensaje", "El usuario se borró correctamente");
		
		return listUsuariosComunidad(idComunidad, model.asMap(), request);  // Utilizo dos return para poder pasar el message
	}
}
