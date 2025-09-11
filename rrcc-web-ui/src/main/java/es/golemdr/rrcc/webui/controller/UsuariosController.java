package es.golemdr.rrcc.webui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.Usuario;
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
	
}
