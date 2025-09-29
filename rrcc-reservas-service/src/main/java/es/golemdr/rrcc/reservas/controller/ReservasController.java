package es.golemdr.rrcc.reservas.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.golemdr.rrcc.common.dto.ReservaData;
import es.golemdr.rrcc.common.entity.Reserva;
import es.golemdr.rrcc.common.mapper.ReservaCustomMapper;
import es.golemdr.rrcc.common.mapper.ReservaMapper;
import es.golemdr.rrcc.reservas.controller.constants.UrlConstants;
import es.golemdr.rrcc.reservas.service.ReservasService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;


@RequestMapping(UrlConstants.URL_RESERVAS)
@RestController
public class ReservasController {
	
	private ReservasService reservasService;
	
	private ReservaCustomMapper reservaCustomMapper;
	
	public ReservasController(ReservasService reservasService, ReservaCustomMapper reservaCustomMapper) {
		super();
		this.reservasService = reservasService;
		this.reservaCustomMapper = reservaCustomMapper;
	}

	public static final String ID_USUARIO = "idUsuario";

	@GetMapping(value = UrlConstants.LISTADO_RESERVAS_USUARIO_PATH)
	public List<ReservaData> recuperarReservasPorUsuario(@PathVariable(ID_USUARIO) @Min(1) int idUsuario) {
		
		List<ReservaData> result = new ArrayList<ReservaData>();
		
		reservasService.recuperarReservasPorUsuario(idUsuario).stream().toList().forEach(r -> result.add(reservaCustomMapper.toData(r)));

		return result;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReservaData createReserva(@Valid @RequestBody ReservaData reservaData) {

		Reserva reserva = reservaCustomMapper.toEntity(reservaData);

		reserva = reservasService.insertarActualizar(reserva);
		
		return reservaCustomMapper.toData(reserva);
	}
	
}
