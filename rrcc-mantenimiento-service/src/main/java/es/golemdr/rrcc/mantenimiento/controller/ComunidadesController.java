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
import es.golemdr.rrcc.mantenimiento.controller.request.ComunidadRequest;
import es.golemdr.rrcc.mantenimiento.domain.Comunidad;
import es.golemdr.rrcc.mantenimiento.ext.exceptions.ResourceNotFoundException;
import es.golemdr.rrcc.mantenimiento.service.ComunidadesService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping(UrlConstants.URL_COMUNIDADES)
@RestController
public class ComunidadesController {

	private static final Logger log = LoggerFactory.getLogger(ComunidadesController.class);

	private static final String ID_COMUNIDAD = "idComunidad";

	private ComunidadesService comunidadesService;

	public ComunidadesController(ComunidadesService comunidadesService) {
		super();
		this.comunidadesService = comunidadesService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comunidad createComunidad(@Valid @RequestBody ComunidadRequest comunidadRequest) {

		Comunidad comunidad = new Comunidad();

		BeanUtils.copyProperties(comunidadRequest, comunidad);

		return comunidadesService.insertarActualizar(comunidad);
	}

	@GetMapping(value = UrlConstants.ID_COMUNIDAD_PATH)
	public Optional<Comunidad> recuperarComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {
		return comunidadesService.recuperarComunidadPorId(idComunidad);
	}

	@GetMapping
	public List<Comunidad> recuperarComunidades() {

		List<Comunidad> result = comunidadesService.recuperarComunidades();

		return result;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Comunidad updateComunidad(@Valid @RequestBody ComunidadRequest comunidadRequest) {

		final Comunidad entity = comunidadesService.recuperarComunidadPorId(comunidadRequest.idComunidad()).orElseThrow(
				() -> new ResourceNotFoundException("Comunidad " + comunidadRequest.idComunidad() + " no encontrada"));

		BeanUtils.copyProperties(comunidadRequest, entity);

		return comunidadesService.insertarActualizar(entity);
	}

	@DeleteMapping(value = UrlConstants.ID_COMUNIDAD_PATH)
	public List<Comunidad> deleteComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {
		
		comunidadesService.borrarComunidad(idComunidad);
		
		return recuperarComunidades();
	}

}
