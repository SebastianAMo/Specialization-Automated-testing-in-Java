package apitesting.services;

import apitesting.models.apiresponse.ApiResponse;
import apitesting.models.apiresponse.GeneralResponse;
import apitesting.models.User;
import io.restassured.response.Response;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class UserApiService {
    
    private static final String CREATE_ACCOUNT_ENDPOINT = "createAccount";
    private static final String DELETE_ACCOUNT_ENDPOINT = "deleteAccount";

    public ApiResponse<GeneralResponse> createUser(User user) {
        
        String uniqueEmail = generateUniqueEmail(user.email);
        
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", user.firstName)
                .formParam("email", uniqueEmail)
                .formParam("password", user.password)
                .formParam("firstname", user.firstName)
                .formParam("lastname", user.lastName)
                .formParam("address1", user.address)
                .formParam("country", user.country)
                .formParam("state", user.state)
                .formParam("city", user.city)
                .formParam("zipcode", user.zipCode)
                .formParam("mobile_number", user.mobileNumber)
                .when()
                .post(CREATE_ACCOUNT_ENDPOINT);
        
        // Update the user object with the unique email for later use
        user.email = uniqueEmail;
        
        return new ApiResponse<>(response, GeneralResponse.class);
    }

    public ApiResponse<GeneralResponse> deleteUser(String password, String email) {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", password)
                .formParam("email", email)
                .when()
                .delete(DELETE_ACCOUNT_ENDPOINT);
        return new ApiResponse<>(response, GeneralResponse.class);
    }
    
    /**
     * Generates a unique email by adding a random number to avoid conflicts during parallel test execution.
     * For example: juan@gmail.com becomes juan123@gmail.com
     * 
     * @param originalEmail The original email address
     * @return A unique email with a random number inserted before the @ symbol
     */
    private String generateUniqueEmail(String originalEmail) {
        if (originalEmail == null || !originalEmail.contains("@")) {
            return originalEmail;
        }
        
        String[] parts = originalEmail.split("@");
        if (parts.length != 2) {
            return originalEmail;
        }
        
        String localPart = parts[0];
        String domain = parts[1];
        
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 10000);
        
        return localPart + randomNumber + "@" + domain;
    }
}
