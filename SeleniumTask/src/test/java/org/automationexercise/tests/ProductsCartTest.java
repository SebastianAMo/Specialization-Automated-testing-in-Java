package org.automationexercise.tests;

import org.automationexercise.pages.CartPage;
import org.automationexercise.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsCartTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ProductsCartTest.class);


    @Test(groups = {"regression"})
    public void testRemoveProductsFromCart() {
        logger.info("Starting test: testRemoveProductsFromCart");
        HomePage homePage = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(homePage.isHomePageVisible(), "Home page is not visible");

        CartPage cartPage = homePage.addRandomProductToCart().addRandomProductToCart().navigationBar.clickCart();

        softAssert.assertTrue(cartPage.isCartPageVisible(), "Cart page is not visible");

        cartPage.clickEmptyCartButton();

        softAssert.assertAll();
        logger.info("Completed test: testRemoveProductsFromCart");
    }
}
