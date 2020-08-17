package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonpath {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(Payload.coursePrice());

        int anInt = jsonPath.getInt("courses.size()");

        System.out.println(anInt);
    }




}
