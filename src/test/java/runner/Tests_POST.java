package runner;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Tests_POST {

    @Test
    public void testPostMethod() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("First Name", "Barathwaj");
        map.put("Last Name", "Ravisankar");
        map.put("\"Company\"","\"Boeing\"");
        map.put("\"Designation\"", "\"Senior Software Development Engineer in Test\"");
        System.out.println(map);
    }

    @Test
    public void testPostMethodWithJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("First Name", "Barathwaj");
        jsonObject.put("Last Name", "Ravisankar");
        jsonObject.put("Company","Boeing");
        jsonObject.put("Designation", "Senior Software Development Engineer in Test");
        jsonObject.put("Hostname", "KATS");
        System.out.println(jsonObject);
        System.out.println(jsonObject.toJSONString());
        given()
                .header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(jsonObject.toJSONString())
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

}
