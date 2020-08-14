import files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class FirstTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //given - all input details
        //when - submit the Api - resource and http method
        //then - validate the response
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
                .when()
                .post("maps/api/place/add/json")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response); //for parsing Json

        String placeId = jsonPath.getString("place_id");
    }


}
