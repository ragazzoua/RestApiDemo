import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        Response response = given().queryParam("key", "qaclick123")
                .body("")
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200).extract().response();

        String s = response.asString();
        System.out.println(s);
    }
}
