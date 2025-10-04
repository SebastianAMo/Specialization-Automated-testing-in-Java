package org.automationexercise.tests;

import org.automationexercise.model.LoginCredentials;
import org.automationexercise.pages.HomePage;
import org.automationexercise.pages.LoginPage;
import org.automationexercise.utils.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);


    @Test(groups = {"smoke", "regression"})
    public void testLoginWithNonExistentUser() {
        logger.info("Starting test: testLoginWithNonExistentUser");

        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();


        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        LoginPage loginPage = homePage.navigationBar.clickSignupLogin();

        softAssert.assertTrue(loginPage.isLoginPageVisible(), "Login page is not visible");


        // Using business model and properties
        ConfigLoader configLoader = ConfigLoader.getInstance();
        LoginCredentials invalidCredentials = new LoginCredentials(
            configLoader.getProperty("invalid.email"),
            configLoader.getProperty("invalid.password"),
            false
        );
        
        loginPage.enterLoginEmail(invalidCredentials.email)
                .enterLoginPassword(invalidCredentials.password)
                .clickLoginButton();

        softAssert.assertEquals(loginPage.getErrorMessage(), "Your email or password is incorrect!", "Error message is not as expected");

        softAssert.assertAll();
        logger.info("Completed test: testLoginWithNonExistentUser");

    }
}
