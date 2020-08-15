import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class FirstTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //given - all input details
        //when - submit the Api - resource and http method
        //then - validate the response
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json").body(Payload.addPlace())
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

        //Update place
        String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //Get Place
        String newResponse = given().log().all().queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when()
                .get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath1 = ReUsableMethods.rawToJson(newResponse);
        String address = jsonPath1.getString("address");

        Assert.assertEquals(address, newAddress);
    }


}
