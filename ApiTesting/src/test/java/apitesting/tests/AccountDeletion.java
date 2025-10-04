package apitesting.tests;

import apitesting.models.apiresponse.GeneralResponse;
import apitesting.services.UserApiService;
import apitesting.services.LoginApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import apitesting.core.ConfigLoader;
import apitesting.models.User;
import apitesting.models.apiresponse.ApiResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountDeletion  extends BaseTestAutomationExercise {
    private static final Logger logger = LoggerFactory.getLogger(AccountDeletion.class);

    private final UserApiService userApiService = new UserApiService();
    private final LoginApiService loginApiService = new LoginApiService();

    @Test
    public void testAccountDeletion() {
        logger.info("Starting test: testAccountDeletion");

        User userData = getUser();

        // Create User
        ApiResponse<GeneralResponse> createResponse = userApiService.createUser(userData);

        logger.debug("User data: {}", userData);
        assertEquals(200, createResponse.response.getStatusCode(), "Expected status code 200 for user creation");

        assertEquals(201, createResponse.parsedBody.responseCode, "Expected responseCode 201 for successful user creation");
        assertEquals("User created!", createResponse.parsedBody.message, "Expected message 'User created!' for successful creation");

        // Delete User
        ApiResponse<GeneralResponse> deleteResponse = userApiService.deleteUser(userData.password, userData.email);
        assertEquals(200, deleteResponse.response.getStatusCode(), "Expected status code 200 for account deletion");

        logger.info("Delete Response Body: {}", deleteResponse.parsedBody);

        assertEquals(200, deleteResponse.parsedBody.responseCode, "Expected responseCode 200 for successful account deletion");
        assertEquals("Account deleted!", deleteResponse.parsedBody.message, "Expected message 'Account Deleted!' for successful deletion");

        // Verify Deletion by attempting to log in
        ApiResponse<GeneralResponse> loginResponse = loginApiService.login(userData.email, userData.password);
        assertEquals(404, loginResponse.parsedBody.responseCode, "Expected responseCode 404 after account deletion");
        assertEquals("User not found!", loginResponse.parsedBody.message, "Expected message 'User not found!' after account deletion");

        logger.info("Test testAccountDeletion completed successfully");
    }
}
