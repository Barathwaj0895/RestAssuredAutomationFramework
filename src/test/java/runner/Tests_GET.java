package runner;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Tests_GET {

    @Test
    public void test_Get_StatusCode_Validation() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(2001);
    }

    @Test
    public void test_Get_Body_Data_Validation_01() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.id[0]",equalTo(7))
                .body("data.id[1]",equalTo(8))
                .body("data.id[2]",equalTo(9))
                .body("data.id[3]",equalTo(10))
                .body("data.id[4]",equalTo(11))
                .body("data.id[5]",equalTo(12))
                .log().all();
    }

    @Test
    public void test_Get_Body_Data_Validation_02() {
        given()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("data.first_name", hasItems("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"))
                .body("data.last_name", hasItems("Lawson", "Ferguson", "Funke", "Fields", "Edwards", "Howell"))
                .body("data.email", hasItems("michael.lawson@reqres.in", "lindsay.ferguson@reqres.in", "tobias.funke@reqres.in", "byron.fields@reqres.in", "george.edwards@reqres.in", "rachel.howell@reqres.in"))
                .body("support.url", hasToString("https://reqres.in/#support-heading"))
                .body("support.text", hasToString("To keep ReqRes free, contributions towards server costs are appreciated!"))
                .log().all();
    }

    @Test
    public void test_Get_Whole_Header_Body_Validation() {
        given()
                .header("Content-Type", "application/json")
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data.id[1]", equalTo(8))
                .body("data.first_name", hasItems("Michael", "Lindsay"));
    }
}
