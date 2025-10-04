package apitesting.services;
import apitesting.models.apiresponse.ApiResponse;
import apitesting.models.apiresponse.GeneralResponse;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class LoginApiService {
    private  static final String LOGIN_ENDPOINT = "verifyLogin";

    public ApiResponse<GeneralResponse> login(String email, String password) {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", email)
                .formParam("password", password)
                .when()
                .post(LOGIN_ENDPOINT);
        return new ApiResponse<>(response, GeneralResponse.class);
    }
}
