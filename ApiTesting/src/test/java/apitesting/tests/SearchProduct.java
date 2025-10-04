package apitesting.tests;

import apitesting.models.apiresponse.ApiResponse;
import apitesting.models.apiresponse.ProductResponse;
import apitesting.services.ProductApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchProduct extends BaseTestAutomationExercise {
    private static final Logger logger = LoggerFactory.getLogger(SearchProduct.class);

    private final ProductApiService productApiService = new ProductApiService();

    @Test
    public void testSearchProduct() {
        logger.info("Starting test: testSearchProduct");


        ApiResponse<ProductResponse> response = productApiService.searchProduct(" ");
        assertEquals(200, response.response.getStatusCode(), "Expected status code 200");

        assertEquals(404, response.parsedBody.responseCode, "Expected responseCode 404 for unsuccessful product search");
        assertEquals("Product not found!", response.parsedBody.message, "Expected message 'Product not found!'");

        logger.info("Test testSearchProduct completed successfully");
    }
}
