package es.golemdr.rrcc.mantenimiento.controller;

import java.util.ArrayList;
import java.util.List;

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

import es.golemdr.rrcc.common.dto.ComunidadData;
import es.golemdr.rrcc.common.entity.Comunidad;
import es.golemdr.rrcc.common.mapper.ComunidadMapper;
import es.golemdr.rrcc.mantenimiento.controller.constants.UrlConstants;
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
	
	private ComunidadMapper comunidadMapper;
	

	public ComunidadesController(ComunidadesService comunidadesService, ComunidadMapper comunidadMapper) {
		super();
		this.comunidadesService = comunidadesService;
		this.comunidadMapper = comunidadMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComunidadData createComunidad(@Valid @RequestBody ComunidadData comunidadData) {

		Comunidad comunidad = new Comunidad();

		BeanUtils.copyProperties(comunidadData, comunidad);
		
		comunidad = comunidadesService.insertarActualizar(comunidad);
		
		return comunidadMapper.toData(comunidad);
	}

	@GetMapping(value = UrlConstants.ID_COMUNIDAD_PATH)
	public ComunidadData recuperarComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {
		
		Comunidad comunidad = comunidadesService.recuperarComunidadPorId(idComunidad).get(); // TODO - Habría que tratar el optional de alguna forma
		
		return comunidadMapper.toData(comunidad);
	}

	@GetMapping
	public List<ComunidadData> recuperarComunidades() {

		List<ComunidadData> result = new ArrayList<ComunidadData>();
		
		comunidadesService.recuperarComunidades().stream().toList().forEach(c -> result.add(comunidadMapper.toData(c)));

		return result;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public ComunidadData updateComunidad(@Valid @RequestBody ComunidadData comunidadData) {

		Comunidad entity = comunidadesService.recuperarComunidadPorId(comunidadData.getIdComunidad()).orElseThrow(
				() -> new ResourceNotFoundException("Comunidad " + comunidadData.getIdComunidad() + " no encontrada"));

		BeanUtils.copyProperties(comunidadData, entity);

		entity = comunidadesService.insertarActualizar(entity); 
		
		return comunidadMapper.toData(entity);
	}

	@DeleteMapping(value = UrlConstants.ID_COMUNIDAD_PATH)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public List<ComunidadData> deleteComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {
		
		comunidadesService.borrarComunidad(idComunidad);
		
		return recuperarComunidades();
	}

}
