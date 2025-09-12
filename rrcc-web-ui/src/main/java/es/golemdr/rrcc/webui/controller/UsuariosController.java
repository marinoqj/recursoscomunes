package es.golemdr.rrcc.webui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.Usuario;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;
import es.golemdr.rrcc.webui.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class UsuariosController {
	
	private UsuariosService usuariosService;
	

	public UsuariosController(UsuariosService usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	@GetMapping(value=UrlConstants.LISTADO_USUARIOS)
	public String list(Map<String, Object> map, HttpServletRequest request){

		List<Usuario> usuarios = usuariosService.recuperarUsuarios();
		map.put("usuarios", usuarios);

		return ForwardConstants.FWD_LISTADO_USUARIOS;
	}
	
	@GetMapping(value = UrlConstants.VER_ALTA_USUARIO)
	public String verAlta(Model model, HttpServletRequest request) {

		UsuarioForm usuarioForm = new UsuarioForm();

		model.addAttribute("modo", "insertar");
		model.addAttribute("usuarioForm", usuarioForm);
		
		return ForwardConstants.FWD_USUARIO_FORM;

	}
	
	@PostMapping(value = UrlConstants.INSERTAR_USUARIO)
	public String insertar(@Valid UsuarioForm formulario, BindingResult result,  Model model, HttpServletRequest request) {
		
		String destino = null;
		Usuario entity = new Usuario();
		
		
		if(result.hasErrors()) {			
			
			model.addAttribute("modo", "insertar");			
			destino = ForwardConstants.FWD_USUARIO_FORM;
			
		}else {
			
			BeanUtils.copyProperties(formulario, entity);	
			usuariosService.insertarUsuario(entity);

			destino = ForwardConstants.RED_LISTADO_USUARIOS;
		}
				

		return destino;
	}
	
	@PostMapping(value = UrlConstants.EDITAR_USUARIO)
	public String editar(String idUsuario, Model model, HttpServletRequest request) {

		UsuarioForm usuarioForm = new UsuarioForm();
		
		Usuario usuario = usuariosService.recuperarUsuarioPorId(idUsuario);

		BeanUtils.copyProperties(usuario, usuarioForm);
		
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
				
			Usuario usuario  = new Usuario();
			
			BeanUtils.copyProperties(formulario, usuario);
			usuario.setIdUsuario(Integer.valueOf(formulario.getIdUsuario()));
				
			usuariosService.actualizarUsuario(usuario);
			
			map.put("mensaje", "El usuario se actualizó correctamente");
		
			return list(map, request);  // Utilizo dos return para poder pasar el message
		}
	}


	@PostMapping(value = UrlConstants.BORRAR_USUARIO)
	public String borrar(String idUsuario, Model model, HttpServletRequest request) {
		
		usuariosService.borrarUsuario(idUsuario);
				
		model.addAttribute("mensaje", "El usuario se borró correctamente");
		
		return list(model.asMap(), request);
	}
}
