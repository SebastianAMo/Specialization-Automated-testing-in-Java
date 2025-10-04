package apitesting.tests;

import apitesting.models.apiresponse.ApiResponse;
import apitesting.models.apiresponse.GeneralResponse;
import apitesting.services.UserApiService;
import apitesting.services.LoginApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import apitesting.models.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUser extends BaseTestAutomationExercise {
    private static final Logger logger = LoggerFactory.getLogger(CreateUser.class);
    
    private final UserApiService userApiService = new UserApiService();
    private final LoginApiService loginApiService = new LoginApiService();

    @Test
    public void testCreateUser() {
        logger.info("Starting test: testCreateUser");

        User userData = getUser();

        ApiResponse<GeneralResponse> response = userApiService.createUser(userData);
        logger.debug("User data: {}", userData);
        assertEquals(200, response.response.getStatusCode(), "Expected status code 200");


        logger.info("Response Body: {}", response.parsedBody);

        assertEquals(201, response.parsedBody.responseCode, "Expected responseCode 201 for successful user creation");
        assertEquals("User created!", response.parsedBody.message, "Expected message 'User created!' for successful creation");


        ApiResponse<GeneralResponse> loginResponse = loginApiService.login(userData.email, userData.password);

        assertEquals(200, loginResponse.response.getStatusCode(), "Expected status code 200 for login");
        assertEquals("User exists!", loginResponse.parsedBody.message, "Expected message 'User exists!' for login");


        logger.info("Test testCreateUser completed successfully");

        ApiResponse<GeneralResponse> deleteResponse = userApiService.deleteUser(userData.password, userData.email);

        logger.info("Cleanup - Delete User Response: {}", deleteResponse.parsedBody);
    }
}
