import files.Payload;
import files.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DinamicJson {


    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.addBook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        System.out.println(js.get("ID").toString());
    }

    @Test(dataProvider = "BooksData", dependsOnMethods = "addBook")
    public void deleteBook(String isbn, String aisle){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.getDeleteBody(isbn, aisle))
                .when()
                .delete("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{{"bdf","2102"},{"bsf","1142"},{"bjk","2154"}};
    }

}
