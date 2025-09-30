package es.golemdr.rrcc.mantenimiento.controller;

import java.util.ArrayList;
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
import es.golemdr.rrcc.common.dto.UsuarioData;
import es.golemdr.rrcc.common.entity.Recurso;
import es.golemdr.rrcc.common.mapper.RecursoMapper;
import es.golemdr.rrcc.mantenimiento.controller.constants.UrlConstants;
import es.golemdr.rrcc.mantenimiento.ext.exceptions.ResourceNotFoundException;
import es.golemdr.rrcc.mantenimiento.ext.mapper.RecursoMapperCustom;
import es.golemdr.rrcc.mantenimiento.service.RecursosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RequestMapping(UrlConstants.URL_RECURSOS)
@RestController
public class RecursosController {

	private static final Logger log = LoggerFactory.getLogger(RecursosController.class);

	public static final String ID_RECURSO = "idRecurso";
	public static final String ID_COMUNIDAD = "idComunidad";

	private RecursosService recursosService;
	
	private RecursoMapper recursoMapper;

	public RecursosController(RecursosService recursosService, RecursoMapper recursoMapper) {
		super();
		this.recursosService = recursosService;
		this.recursoMapper = recursoMapper;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RecursoData createRecurso(@Valid @RequestBody RecursoData recursoData) {

		Recurso recurso = new Recurso();

		RecursoMapperCustom.copiarPropiedades(recursoData, recurso, "INSERT");

		recurso = recursosService.insertarActualizar(recurso); 
		
		return recursoMapper.toData(recurso);
	}

	@GetMapping(value = UrlConstants.ID_RECURSO_PATH)
	public RecursoData recuperarRecurso(@PathVariable(ID_RECURSO) @Min(1) int idRecurso) {
		
		Recurso recurso = recursosService.recuperarRecursoPorId(idRecurso).get(); 
		
		return recursoMapper.toData(recurso);
	}

	@GetMapping
	public List<RecursoData> recuperarRecursos() {
		
		List<RecursoData> result = new ArrayList<RecursoData>();

		recursosService.recuperarRecursos().stream().toList().forEach(r -> result.add(recursoMapper.toData(r)));

		return result;
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public RecursoData updateRecurso(@Valid @RequestBody RecursoData recursoData) {

		Recurso entity = recursosService.recuperarRecursoPorId(recursoData.getIdRecurso()).orElseThrow(
				() -> new ResourceNotFoundException("Recurso " + recursoData.getIdRecurso() + " no encontrada"));

		RecursoMapperCustom.copiarPropiedades(recursoData, entity, "UPDATE");

		entity = recursosService.insertarActualizar(entity); 


		return recursoMapper.toData(entity);
	}
	

	@DeleteMapping(value = UrlConstants.ID_RECURSO_PATH)
	public List<RecursoData> deleteRecurso(@PathVariable(ID_RECURSO) @Min(1) int idRecurso) {
		
		recursosService.borrarRecurso(idRecurso);
		
		return recuperarRecursos();
	}
	
	@GetMapping(value = UrlConstants.LISTADO_RECURSOS_COMUNIDAD_PATH)
	public List<RecursoData> recuperarRecursosPorComunidad(@PathVariable(ID_COMUNIDAD) @Min(1) int idComunidad) {
		
		List<RecursoData> result = new ArrayList<RecursoData>();

		recursosService.recuperarRecursosPorComunidad(idComunidad).stream().toList().forEach(u -> result.add(recursoMapper.toData(u)));

		return result;
	}

}
