package pojo;

import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PojoTest {

    @Test
    public void pojoTest(){
        GetCourse getCourse = given().expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        System.out.println(getCourse.getLinkedin());
    }
}
