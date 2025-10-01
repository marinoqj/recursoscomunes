package es.golemdr.rrcc.webui.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.webui.controller.constantes.ForwardConstants;
import es.golemdr.rrcc.webui.controller.constantes.UrlConstants;
import es.golemdr.rrcc.webui.domain.form.ReservaForm;
import es.golemdr.rrcc.webui.domain.form.UsuarioForm;
import es.golemdr.rrcc.webui.ext.mapper.ReservaMapperUI;
import es.golemdr.rrcc.webui.ext.mapper.UsuarioMapperUI;
import es.golemdr.rrcc.webui.service.ComunidadesService;
import es.golemdr.rrcc.webui.service.RecursosService;
import es.golemdr.rrcc.webui.service.ReservasService;
import es.golemdr.rrcc.webui.service.UsuariosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class ReservasController {
	
	private ReservasService reservasService;
	
	private UsuariosService usuariosService;
	
	private RecursosService recursosService;
	
	private ComunidadesService comunidadesService;
	

	public ReservasController(ReservasService reservasService, UsuariosService usuariosService,
			RecursosService recursosService, ComunidadesService comunidadesService) {
		super();
		this.reservasService = reservasService;
		this.usuariosService = usuariosService;
		this.recursosService = recursosService;
		this.comunidadesService= comunidadesService;
	}

	@GetMapping(value=UrlConstants.LISTADO_RESERVAS_USUARIO)
	public String listReservasUsuario(String idUsuario, Map<String, Object> map, HttpServletRequest request){
		
		// TODO - Recuperar dinámicamente
		idUsuario = "1";
		

		List<ReservaData> reservas = reservasService.recuperarReservasPorUsuario(idUsuario);
		map.put("reservas", reservas);
		
		UsuarioData usuario = usuariosService.recuperarUsuarioPorId(idUsuario);
		map.put("usuario", usuario);
		
		return ForwardConstants.FWD_LISTADO_RESERVAS_USUARIO;
	}

	@PostMapping(value=UrlConstants.LISTADO_RESERVAS_COMUNIDAD)
	public String listReservasComunidad(String idComunidad, Map<String, Object> map, HttpServletRequest request){
		
		List<ReservaData> reservas = reservasService.recuperarReservasPorComunidad(idComunidad);
		map.put("reservas", reservas);
		
		ComunidadData comunidad = comunidadesService.recuperarComunidadPorId(idComunidad);
		map.put("comunidad", comunidad);
		
		return ForwardConstants.FWD_LISTADO_RESERVAS_COMUNIDAD;
	}
	
	@GetMapping(value = UrlConstants.VER_NUEVA_RESERVA)
	public String verAlta(@PathVariable String idUsuario, Model model, HttpServletRequest request) {
		
		UsuarioData usuario = usuariosService.recuperarUsuarioPorId(idUsuario);

		List<RecursoData> recursos = recursosService.recuperarRecursosPorComunidad(usuario.getComunidad().getIdComunidad().toString());
				
		ReservaForm reservaForm = new ReservaForm();
		reservaForm.setIdUsuario(idUsuario);

		model.addAttribute("modo", "insertar");
		model.addAttribute("reservaForm", reservaForm);
		model.addAttribute("recursos", recursos);
		
		return ForwardConstants.FWD_RESERVA_FORM;

	}
	
	@PostMapping(value = UrlConstants.INSERTAR_RESERVA)
	public String insertar(@Valid ReservaForm formulario, BindingResult result,  Model model, HttpServletRequest request) {
		
		String destino = null;
		ReservaData reserva = new ReservaData();
		
		
		if(result.hasErrors()) {			
			
			model.addAttribute("modo", "insertar");			
			destino = ForwardConstants.FWD_RESERVA_FORM;
			
		}else {
			
			ReservaMapperUI.copiarPropiedades(formulario, reserva);
			reservasService.insertarReserva(reserva);

			destino = listReservasUsuario(formulario.getIdUsuario(), model.asMap(), request);
		}
				

		return destino;
	}

	
	@PostMapping(value = UrlConstants.ANULAR_RESERVA)
	public String borrar(String idReserva, String idUsuario, Model model, HttpServletRequest request) {
		
		reservasService.borrarReserva(idReserva);
				
		model.addAttribute("mensaje", "La reserva se anuló correctamente");
		
		return listReservasUsuario(idReserva, model.asMap(), request);  // Utilizo dos return para poder pasar el message
	}
	
}
