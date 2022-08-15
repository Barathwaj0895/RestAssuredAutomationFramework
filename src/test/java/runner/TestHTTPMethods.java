package runner;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class TestHTTPMethods {

    @Test
    public void testGetMethod() {
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getTime());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getStatusLine());
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testGetMethod1() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("data.id[0]", equalTo(7));
    }

    @Test
    public void testPostMethod() {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name", "Barathwaj");
        map.put("job", "Senior Software Development Engineer in Test");
        System.out.println(map);
    }

}
