package integracion.crud;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import integracion.BaseIntegrationTest;


public class ComunidadCrudIntegrationTest extends BaseIntegrationTest{

	
    @Test
    void shouldPerformCrudOperations() {

    	String urlComunidades = baseUrl + "/comunidades";
    	
        // Create
        String idComunidad = given()
            .contentType("application/json")
            .body("{\"nombre\":\"Puerto de Lumbreras 24\"}")
        .when()
            .post(urlComunidades)
        .then()
            .statusCode(201)
            .extract().path("idComunidad").toString();

        // Read
        given()
        .when()
            .get(urlComunidades + "/" + idComunidad)
        .then()
            .statusCode(200)
            .body("nombre", equalTo("Puerto de Lumbreras 24"));

        // Update
        given()
            .contentType("application/json")
            .body("{\"idComunidad\":\"" + idComunidad +  "\", \"nombre\":\"Puerto de Lumbreras 28\"}")
        .when()
            .put(urlComunidades)
        .then()
            .statusCode(200)
            .body("nombre", equalTo("Puerto de Lumbreras 28"));

        // Delete
        given()
        .when()
            .delete(urlComunidades + "/" + idComunidad)
        .then()
            .statusCode(204);
    }
	
}



