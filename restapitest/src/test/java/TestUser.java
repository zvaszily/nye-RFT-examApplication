
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasKey;

public class TestUser {

    private final String BASE_URL = "http://localhost:8080";
    private final String NEW_USER_EMAIL = "new@user";

    public JSONObject newUser() {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", "newUser");
                jsonObject.put("fullName", "newUser Test");
                jsonObject.put("emailAddress", "new@user");
                jsonObject.put("password", "pass");
                return jsonObject;
    }


    @Test
    public void WhenUsersListIsRequestedExpect_StatusOk() {
        Response response = request("get", BASE_URL + "/user/users");

        response.then()
                .statusCode(200);
        System.out.println(response.getBody().asString());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
    }

    @Test
    public void WhenUsersListIsRequestedExpectProperFields() {
        Response response = request("get", BASE_URL + "/user/users");

        response.then()
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("userName"))
                .body("[0]", hasKey("fullName"))
                .body("[0]", hasKey("emailAddress"))
                .body("[0]", hasKey("password"));
    }

    @Test
    public void WhenCreatingNewUserRequestedExpect_StatusOk() {
        RequestSpecification request = given().log().ifValidationFails();
        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(newUser()));
        Response response = request.request("post", BASE_URL + "/user/add");

        response.then()
                .statusCode(201);
    }

    @Test
    public void WhenCreatingNewUserWithAlreadyExistingEmailErrorResponse() {
        RequestSpecification request = given().log().ifValidationFails();
        request.header("Content-Type", "application/json");
        request.body(JSONObject.toJSONString(newUser()));
        Response response = request.request("post", BASE_URL + "/user/add");

        response.then()
                .statusCode(400);
    }

    @Test
    public void WhenDeletingUserRequestedExpect_StatusOk() {
        Response response = request("get", BASE_URL + "/user/email/" + NEW_USER_EMAIL);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String newUserID = jsonPathEvaluator.get("id").toString();
        response = request("delete", BASE_URL + "/user/delete/" + newUserID);

        response.then()
                        .statusCode(200);
        System.out.println(newUserID);
    }

}
