package es.golemdr.rrcc.mantenimiento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan(basePackages = "es.golemdr.rrcc.common.entity")
@ComponentScan(basePackages = "es.golemdr.rrcc")
public class RecursosComunesMantenimientoServiceApplication {
	
	private static final Logger log = LoggerFactory.getLogger(RecursosComunesMantenimientoServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecursosComunesMantenimientoServiceApplication.class, args);
		
		log.info("##################################### Mantenimiento Service UP #####################################");
	}
}
