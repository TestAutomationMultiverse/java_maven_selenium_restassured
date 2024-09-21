package Tests.api;

import framework.petstore.data.PetData;
import io.restassured.http.ContentType;

import java.io.InputStream;
import java.util.Map;

import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class PetTest {
    @BeforeClass
    public void setup() {
        baseURI = "https://petstore.swagger.io";
        basePath = "/v2";
    }

    @Test(priority = 1)
    public void testFindNonExistentPetById() {
        PetData petData = new PetData();
        Map<String, Object> requestData = petData.findPetByIdData();

        String path = String.format("/pet/%d", requestData.get("petId"));

        when()
            .get(path)
        .then()
            .assertThat()
                .statusCode(404)
                .body("message", equalTo("Pet not found"));
    }

    @Test(priority = 1)
    public void testUpdateAnExistingPet() {
        PetData petData = new PetData();
        Map<String, Object> requestData = petData.updatePetData();

        InputStream postStoreOrderJsonSchema = getClass().getClassLoader().getResourceAsStream("putPetSchema.json");

        given()
            .body(requestData)
            .contentType(ContentType.JSON)
        .when()
            .put("/pet")
        .then()
            .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(postStoreOrderJsonSchema))
                .body("id", equalTo(requestData.get("id")))
                .body("name", equalTo(requestData.get("name")))
                .body("status", equalTo(requestData.get("status")));
    }

    @Test(priority = 1)
    public void testFindPetsByStatusPending() {
        InputStream getPetsJsonSchema = getClass().getClassLoader().getResourceAsStream("getPetsSchema.json");

        when()
            .get("/pet/findByStatus?status=pending")
        .then()
            .assertThat()
                .statusCode(200)
                .body(matchesJsonSchema(getPetsJsonSchema))
                .body("status", everyItem(equalTo("pending")));

    }
}
