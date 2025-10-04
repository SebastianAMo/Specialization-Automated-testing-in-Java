package apitesting.services;

import apitesting.models.apiresponse.ApiResponse;
import apitesting.models.apiresponse.ProductResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductApiService {
    private final static String SEARCH_PRODUCT_ENDPOINT = "searchProduct";

    public ApiResponse<ProductResponse> searchProduct(String productName) {
        Response response = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product", productName)
                .when()
                .post(SEARCH_PRODUCT_ENDPOINT);
        return ApiResponse.of(response, ProductResponse.class);
    }
}
