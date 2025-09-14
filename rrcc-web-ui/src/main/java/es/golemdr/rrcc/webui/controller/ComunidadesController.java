package es.golemdr.rrcc.webui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.Comunidad;
import es.golemdr.rrcc.webui.domain.form.ComunidadForm;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ComunidadesController {
	
	private ComunidadesService comunidadesService;
	

	public ComunidadesController(ComunidadesService comunidadesService) {
		super();
		this.comunidadesService = comunidadesService;
	}

	@GetMapping(value=UrlConstants.LISTADO_COMUNIDADES)
	public String list(Map<String, Object> map, HttpServletRequest request){

		List<Comunidad> comunidades = comunidadesService.recuperarComunidades();
		map.put("comunidades", comunidades);

		return ForwardConstants.FWD_LISTADO_COMUNIDADES;
	}
	
	@GetMapping(value = UrlConstants.VER_ALTA_COMUNIDAD)
	public String verAlta(Model model, HttpServletRequest request) {

		ComunidadForm comunidadForm = new ComunidadForm();

		model.addAttribute("modo", "insertar");
		model.addAttribute("comunidadForm", comunidadForm);
		
		return ForwardConstants.FWD_COMUNIDAD_FORM;

	}
	
	@PostMapping(value = UrlConstants.INSERTAR_COMUNIDAD)
	public String insertar(@Valid ComunidadForm formulario, BindingResult result,  Model model, HttpServletRequest request) {
		
		String destino = null;
		Comunidad entity = new Comunidad();
		
		
		if(result.hasErrors()) {			
			
			model.addAttribute("modo", "insertar");			
			destino = ForwardConstants.FWD_COMUNIDAD_FORM;
			
		}else {
			
			BeanUtils.copyProperties(formulario, entity);	
			comunidadesService.insertarComunidad(entity);

			destino = ForwardConstants.RED_LISTADO_COMUNIDADES;
		}
				

		return destino;
	}
	
	@PostMapping(value = UrlConstants.EDITAR_COMUNIDAD)
	public String editar(String idComunidad, Model model, HttpServletRequest request) {

		ComunidadForm comunidadForm = new ComunidadForm();
		
		Comunidad comunidad = comunidadesService.recuperarComunidadPorId(idComunidad);

		BeanUtils.copyProperties(comunidad, comunidadForm);
		
		comunidadForm.setIdComunidad(comunidad.getIdComunidad().toString());
		
		model.addAttribute("modo", "actualizar");
		model.addAttribute("comunidadForm", comunidadForm);
		
		return ForwardConstants.FWD_COMUNIDAD_FORM;

	}
	
	
	@PostMapping(value = UrlConstants.ACTUALIZAR_COMUNIDAD)
	public String actualizar(@Valid ComunidadForm formulario, BindingResult result,  Map<String, Object> map, HttpServletRequest request) {
		
		
		if(result.hasErrors()) {
			
			map.put("modo", "actualizar");
			
			return ForwardConstants.FWD_COMUNIDAD_FORM;
			
		}else {
				
			Comunidad comunidad  = new Comunidad();
			
			BeanUtils.copyProperties(formulario, comunidad);
			comunidad.setIdComunidad(Integer.valueOf(formulario.getIdComunidad()));
				
			comunidadesService.actualizarComunidad(comunidad);
			
			map.put("mensaje", "La comunidad se actualizó correctamente");
		
			return list(map, request);  // Utilizo dos return para poder pasar el message
		}
	}


	@PostMapping(value = UrlConstants.BORRAR_COMUNIDAD)
	public String borrar(String idComunidad, Model model, HttpServletRequest request) {
		
		comunidadesService.borrarComunidad(idComunidad);
				
		model.addAttribute("mensaje", "La comunidad se borró correctamente");
		
		return list(model.asMap(), request);
	}
}
