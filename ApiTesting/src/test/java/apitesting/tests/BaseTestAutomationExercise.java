package apitesting.tests;

import apitesting.core.ConfigLoader;
import apitesting.models.User;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;


public class BaseTestAutomationExercise {

    protected static final String BASE_URL = "https://automationexercise.com";

    @BeforeEach
    void setUp() {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        RestAssured.baseURI = configLoader.getProperty("base.url", BASE_URL);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    protected static User getUser() {
        ConfigLoader configLoader = ConfigLoader.getInstance();
        return new User(
                configLoader.getProperty("user.first.name"),
                configLoader.getProperty("user.last.name"),
                configLoader.getProperty("user.email"),
                configLoader.getProperty("user.password"),
                configLoader.getProperty("user.address"),
                configLoader.getProperty("user.country"),
                configLoader.getProperty("user.state"),
                configLoader.getProperty("user.city"),
                configLoader.getProperty("user.zipcode"),
                configLoader.getProperty("user.mobile.number")
        );
    }


}
