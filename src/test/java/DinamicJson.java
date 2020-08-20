import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DinamicJson {


    @Test(dataProvider = "BooksData")
    public void addBook(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.addBook("1", "222"))
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        System.out.println(js.get("ID").toString());
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){

        return new Object[][]{{"2323","535345"},{"2342345","22ggs"},{"cgsfsd","rewrwer"}};
    }

}
