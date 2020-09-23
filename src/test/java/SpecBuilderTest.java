import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class SpecBuilderTest {
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

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpecBuilder = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON).expectStatusCode(200)
                .expectResponseTime(lessThanOrEqualTo(10L), TimeUnit.SECONDS).build();

        RequestSpecification body = given().spec(requestSpecification)
                .body(addPlace);


        Response response = body.when()
                .post("maps/api/place/add/json")
                .then().spec(responseSpecBuilder)
                .extract().response();

        String s = response.asString();
        System.out.println(s);
    }
}
