package org.automationexercise.tests;

import org.automationexercise.model.User;
import org.automationexercise.pages.AccountDeletePage;
import org.automationexercise.pages.HomePage;
import org.automationexercise.pages.LoginPage;
import org.automationexercise.pages.SignupPage;
import org.automationexercise.utils.ConfigLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class DeleteUserTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserTest.class);


    @Test(groups = {"smoke", "regression"})
    public void testDeleteUser() {
        logger.info("Starting test: testDeleteUser");

        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");
        LoginPage loginPage = homePage.navigationBar.clickSignupLogin();

        softAssert.assertTrue(loginPage.isLoginPageVisible(), "Login page is not visible");

        // Using business model and properties
        ConfigLoader configLoader = ConfigLoader.getInstance();
        User testUser = new User(
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


        SignupPage signupPage = loginPage.enterSignupName(testUser.firstName + " " + testUser.firstName)
                .enterSignupEmail(testUser.email)
                .clickSignupButton();

        softAssert.assertTrue(signupPage.isSignupPageVisible(), "Signup page is not visible");

        AccountDeletePage accountDeletePage = signupPage.enterFirstName(testUser.firstName)
                .enterLastName(testUser.lastName)
                .enterPassword(testUser.password)
                .enterAddress(testUser.address)
                .selectCountry(testUser.country)
                .enterState(testUser.state)
                .enterCity(testUser.city)
                .enterZipCode(testUser.zipCode)
                .enterMobileNumber(testUser.mobileNumber)
                .clickSignupButton().navigationBar.clickHome().navigationBar.clickDeleteAccount();

        softAssert.assertTrue(accountDeletePage.isDeleteMessageVisible(), "Account deletion confirmation is not visible");

        softAssert.assertAll();
        logger.info("Test 'testDeleteUser' completed successfully");
    }
}
