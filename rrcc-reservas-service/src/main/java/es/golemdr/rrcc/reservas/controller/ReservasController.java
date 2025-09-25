package es.golemdr.rrcc.reservas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.entity.Reserva;
import es.golemdr.rrcc.common.mapper.ReservaMapper;
import es.golemdr.rrcc.reservas.controller.constants.UrlConstants;
import es.golemdr.rrcc.reservas.service.ReservasService;
import jakarta.validation.constraints.Min;


@RequestMapping(UrlConstants.URL_RESERVAS)
@RestController
public class ReservasController {
	
	private ReservasService reservasService;
	
	private ReservaMapper reservaMapper;
	
	public ReservasController(ReservasService reservasService, ReservaMapper reservaMapper) {
		super();
		this.reservasService = reservasService;
		this.reservaMapper = reservaMapper;
	}

	public static final String ID_USUARIO = "idUsuario";

	@GetMapping(value = UrlConstants.LISTADO_RESERVAS_USUARIO_PATH)
	public List<ReservaData> recuperarReservasPorUsuario(@PathVariable(ID_USUARIO) @Min(1) int idUsuario) {
		
		List<ReservaData> result = new ArrayList<ReservaData>();
		
		reservasService.recuperarReservasPorUsuario(idUsuario).stream().toList().forEach(r -> result.add(reservaMapper.toData(r)));

		return result;
	}
	
}
