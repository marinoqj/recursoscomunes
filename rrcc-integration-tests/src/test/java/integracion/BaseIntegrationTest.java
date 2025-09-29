package integracion;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseIntegrationTest {

    protected static String baseUrl;

    @BeforeAll
    static void setup() {
        // Use env variable, system property, or fallback to localhost
        baseUrl = System.getProperty("baseUrl", "http://localhost:8091");

        RestAssured.baseURI = baseUrl;
    }
}
