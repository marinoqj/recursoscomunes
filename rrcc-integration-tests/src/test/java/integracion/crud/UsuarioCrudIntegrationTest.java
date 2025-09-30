package integracion.crud;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import common.Constantes;
import integracion.BaseIntegrationTest;


public class UsuarioCrudIntegrationTest extends BaseIntegrationTest{

	
    @Test
    void shouldPerformCrudOperations() {

    	String urlUsuarios = baseUrl + "/usuarios";
    	
        // Create
        String idUsuario = given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body("{\"identificador\": \"22/4G\",\"comunidad\": {\"idComunidad\": 2}}")
        .when()
            .post(urlUsuarios)
        .then()
            .statusCode(HttpStatus.CREATED.value()) // 201
            .extract().path("idUsuario").toString();

        // Read
        given()
        .when()
            .get(urlUsuarios + "/" + idUsuario)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body("identificador", equalTo("22/4G"));

        // Update
        given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body("{\"idUsuario\":\"" + idUsuario +  "\", \"identificador\":\"22/5G\"}")
        .when()
            .put(urlUsuarios)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body("identificador", equalTo("22/5G"));

        // Delete
        given()
        .when()
            .delete(urlUsuarios + "/" + idUsuario)
        .then()
            .statusCode(HttpStatus.OK.value()); // 204);
    }
	
}



