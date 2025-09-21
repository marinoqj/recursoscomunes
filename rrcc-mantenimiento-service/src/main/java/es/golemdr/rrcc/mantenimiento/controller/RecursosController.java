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

import es.golemdr.rrcc.common.dto.RecursoData;
import es.golemdr.rrcc.common.entity.Recurso;
import es.golemdr.rrcc.common.mapper.RecursoMapper;
import es.golemdr.rrcc.mantenimiento.controller.constants.UrlConstants;
import es.golemdr.rrcc.mantenimiento.controller.request.RecursoRequest;
import es.golemdr.rrcc.mantenimiento.ext.exceptions.ResourceNotFoundException;
import es.golemdr.rrcc.mantenimiento.service.RecursosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping(UrlConstants.URL_RECURSOS)
@RestController
public class RecursosController {

	private static final Logger log = LoggerFactory.getLogger(RecursosController.class);

	public static final String ID_RECURSO = "idRecurso";

	private RecursosService recursosService;
	
	private RecursoMapper recursoMapper;

	public RecursosController(RecursosService recursosService, RecursoMapper recursoMapper) {
		super();
		this.recursosService = recursosService;
		this.recursoMapper = recursoMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RecursoData createRecurso(@Valid @RequestBody RecursoRequest recursoRequest) {

		Recurso recurso = new Recurso();

		BeanUtils.copyProperties(recursoRequest, recurso);

		return recursosService.insertarActualizar(recurso);
	}

	@GetMapping(value = UrlConstants.ID_RECURSO_PATH)
	public RecursoData recuperarRecurso(@PathVariable(ID_RECURSO) @Min(1) int idRecurso) {
		return recursosService.recuperarRecursoPorId(idRecurso);
	}

	@GetMapping
	public List<RecursoData> recuperarRecursos() {

		List<Recurso> result = recursosService.recuperarRecursos();

		return result;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public RecursoData updateRecurso(@Valid @RequestBody RecursoRequest recursoRequest) {

		final Recurso entity = recursosService.recuperarRecursoPorId(recursoRequest.idRecurso()).orElseThrow(
				() -> new ResourceNotFoundException("Recurso " + recursoRequest.idRecurso() + " no encontrada"));

		BeanUtils.copyProperties(recursoRequest, entity);

		return recursosService.insertarActualizar(entity);
	}
	

	@DeleteMapping(value = UrlConstants.ID_RECURSO_PATH)
	public List<RecursoData> deleteRecurso(@PathVariable(ID_RECURSO) @Min(1) int idRecurso) {
		
		recursosService.borrarRecurso(idRecurso);
		
		return recuperarRecursos();
	}

}
