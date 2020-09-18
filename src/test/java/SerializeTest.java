import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();
        Location location = new Location();
        List<String> types = new ArrayList<>();
        addPlace.setAccuracy(50);
        addPlace.setAddress("");
        addPlace.setLanguage("");
        addPlace.setPhone("");
        addPlace.setWebsite("");
        addPlace.setName("");
        location.setLat(8686.22);
        location.setLng(886.22);
        addPlace.setLocation(location);
        types.add("shop");
        types.add("shop1");
        addPlace.setTypes(types);

        Response response = given().queryParam("key", "qaclick123")
                .body(addPlace)
                .when()
                .post("maps/api/place/add/json")
                .then()
                .assertThat().statusCode(200).extract().response();

        String s = response.asString();
        System.out.println(s);
    }
}
