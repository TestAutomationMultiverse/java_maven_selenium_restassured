package Tests.api;

import framework.petstore.data.StoreData;
import io.restassured.http.ContentType;
import org.testng.annotations.*;


import java.io.InputStream;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class StoreTest {
    @BeforeClass
    public void setup() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";
    }

    @Test(priority = 1)
    public void testPlaceAnOrderForAPet() {
        StoreData storeData = new StoreData();
        InputStream postStoreOrderJsonSchema = getClass().getClassLoader().getResourceAsStream("postStoreOrderSchema.json");

        Map<String, Object> requestData = storeData.placeAnOrderForAPetData();

        given()
            .body(requestData)
            .contentType(ContentType.JSON)
        .when()
            .post("/store/order")
        .then()
            .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(postStoreOrderJsonSchema))
                .body("id", equalTo(requestData.get("id")))
                .body("petId", equalTo(requestData.get("petId")))
                .body("quantity", equalTo(requestData.get("quantity")))
                .body("status", equalTo(requestData.get("status")))
                .body("complete", equalTo(requestData.get("complete")));
    }
}
