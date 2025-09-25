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
import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.form.RecursoForm;
import es.golemdr.rrcc.webui.ext.mapper.RecursoMapperUI;
import es.golemdr.rrcc.webui.ext.mapper.UsuarioMapperUI;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import es.golemdr.rrcc.webui.service.RecursosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class RecursosController {
	
	private RecursosService recursosService;
	
	private ComunidadesService comunidadesService;
	

	public RecursosController(RecursosService recursosService, ComunidadesService comunidadesService) {
		super();
		this.recursosService = recursosService;
		this.comunidadesService = comunidadesService;
	}

	@PostMapping(value=UrlConstants.LISTADO_RECURSOS_COMUNIDAD)
	public String listRecursosComunidad(String idComunidad, Map<String, Object> map, HttpServletRequest request){

		List<RecursoData> recursos = recursosService.recuperarRecursos(idComunidad);
		map.put("recursos", recursos);
		
		ComunidadData comunidad = comunidadesService.recuperarComunidadPorId(idComunidad);
		map.put("comunidad", comunidad);
		
		return ForwardConstants.FWD_LISTADO_RECURSOS;
	}
	
	@GetMapping(value=UrlConstants.LISTADO_RECURSOS_COMUNIDAD_GET)
	public String redListUsuariosComunidad(@PathVariable String idComunidad, Map<String, Object> map, HttpServletRequest request){

		return listRecursosComunidad(idComunidad, map, request);
	}
	
	@GetMapping(value = UrlConstants.VER_ALTA_RECURSO)
	public String verAlta(@PathVariable String idComunidad, Model model, HttpServletRequest request) {

		RecursoForm recursoForm = new RecursoForm();
		recursoForm.setIdComunidad(idComunidad);

		model.addAttribute("modo", "insertar");
		model.addAttribute("recursoForm", recursoForm);
		
		return ForwardConstants.FWD_RECURSO_FORM;

	}
	
	@PostMapping(value = UrlConstants.INSERTAR_RECURSO)
	public String insertar(@Valid RecursoForm formulario, BindingResult result,  Model model, HttpServletRequest request) {
		
		String destino = null;
		RecursoData entity = new RecursoData();
		
		
		if(result.hasErrors()) {			
			
			model.addAttribute("modo", "insertar");			
			destino = ForwardConstants.FWD_RECURSO_FORM;
			
		}else {
			
			RecursoMapperUI.copiarPropiedades(formulario, entity);
			recursosService.insertarRecurso(entity);

			destino = listRecursosComunidad(formulario.getIdComunidad(), model.asMap(), request);
		}
				

		return destino;
	}
	
	@PostMapping(value = UrlConstants.EDITAR_RECURSO)
	public String editar(String idRecurso, Model model, HttpServletRequest request) {

		RecursoForm recursoForm = new RecursoForm();
		
		RecursoData recurso = recursosService.recuperarRecursoPorId(idRecurso);

		RecursoMapperUI.copiarPropiedades(recurso, recursoForm);
		
		recursoForm.setIdRecurso(recurso.getIdRecurso().toString());
		
		model.addAttribute("modo", "actualizar");
		model.addAttribute("recursoForm", recursoForm);
		
		return ForwardConstants.FWD_RECURSO_FORM;

	}
		
	@PostMapping(value = UrlConstants.ACTUALIZAR_RECURSO)
	public String actualizar(@Valid RecursoForm formulario, BindingResult result,  Map<String, Object> map, HttpServletRequest request) {
		
		
		if(result.hasErrors()) {
			
			map.put("modo", "actualizar");
			
			return ForwardConstants.FWD_RECURSO_FORM;
			
		}else {
				
			RecursoData recurso  = new RecursoData();
			
			RecursoMapperUI.copiarPropiedades(formulario, recurso);
				
			recursosService.actualizarRecurso(recurso);
			
			map.put("mensaje", "El recurso se actualizó correctamente");
		
			return listRecursosComunidad(formulario.getIdComunidad(), map, request);  // Utilizo dos return para poder pasar el message
		}
	}

	@PostMapping(value = UrlConstants.BORRAR_RECURSO)
	public String borrar(String idRecurso, String idComunidad, Model model, HttpServletRequest request) {
		
		recursosService.borrarRecurso(idRecurso);
				
		model.addAttribute("mensaje", "El recurso se borró correctamente");
		
		return listRecursosComunidad(idComunidad, model.asMap(), request);  // Utilizo dos return para poder pasar el message
	}
}
