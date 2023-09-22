package APITests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUser extends BaseSetup {
    @Test
    public void creatingUser() {
        JSONObject request = new JSONObject();
        request.put("name", "morpheus");
        request.put("job", "leader");

        given()
                .header("Content-Type", "application/json").log().all()
                .body(request.toJSONString())
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().all();
    }
}
