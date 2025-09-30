package integracion.crud;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import common.Constantes;
import integracion.BaseIntegrationTest;
import integracion.crud.utils.ComunidadExample;
import integracion.crud.utils.CrudTestable;


public class CommonCrudIntegrationTest extends BaseIntegrationTest{

	
	CrudTestable testCase = new ComunidadExample();
	
    @Test
    void shouldPerformCrudOperations() {

    	String url = baseUrl + testCase.getUrl();
    	
        // Create
        String id = given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body(testCase.getBody())
        .when()
            .post(url)
        .then()
            .statusCode(HttpStatus.CREATED.value()) // 201
            .extract().path(testCase.getId()).toString();

        // Read
        given()
        .when()
            .get(url + "/" + id)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body(testCase.getProperty(), equalTo(testCase.getPropertyValueOriginal()));

        // Update
        given()
            .contentType(Constantes.CONTENT_TYPE_JSON)
            .body(testCase.getBodyWithId(id))
        .when()
            .put(url)
        .then()
            .statusCode(HttpStatus.OK.value()) // 200
            .body(testCase.getProperty(), equalTo(testCase.getPropertyValueModified()));

        // Delete
        given()
        .when()
            .delete(url + "/" + id)
        .then()
            .statusCode(HttpStatus.OK.value()); // 200);
    }
	
}



