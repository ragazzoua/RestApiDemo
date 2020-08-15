package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {

    public static JsonPath rawToJson(String responce){
        JsonPath jsonPath = new JsonPath(responce);
        return jsonPath;
    }
}
