package integracion.crud;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import common.Constantes;
import integracion.BaseIntegrationTest;


public class RecursoCrudIntegrationTest extends BaseIntegrationTest{

	
    @Test
    void shouldPerformCrudOperations() {

    	String urlRecursos = baseUrl + "/recursos";
    	
        // Create
        String idRecurso = given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body("{\"tipoRecurso\": \"02\",\"comunidad\": {\"idComunidad\": 2}}")
        .when()
            .post(urlRecursos)
        .then()
            .statusCode(HttpStatus.CREATED.value()) // 201
            .extract().path("idRecurso").toString();

        // Read
        given()
        .when()
            .get(urlRecursos + "/" + idRecurso)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body("tipoRecurso", equalTo("02"));

        // Update
        given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body("{\"idRecurso\":\"" + idRecurso +  "\", \"tipoRecurso\":\"01\"}")
        .when()
            .put(urlRecursos)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body("tipoRecurso", equalTo("01"));

        // Delete
        given()
        .when()
            .delete(urlRecursos + "/" + idRecurso)
        .then()
            .statusCode(HttpStatus.OK.value()); // 204);
    }
	
}



