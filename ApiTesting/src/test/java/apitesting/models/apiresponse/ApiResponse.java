package apitesting.models.apiresponse;

import apitesting.utils.ResponseParser;
import io.restassured.response.Response;

public class ApiResponse<T> {
    public final Response response;
    public final T parsedBody;


    public ApiResponse(Response response, Class<T> responseType) {
        this.response = response;
        this.parsedBody = ResponseParser.parseResponse(response.getBody().asString(), responseType);
    }
    

    public static <T> ApiResponse<T> of(Response response, Class<T> responseType) {
        return new ApiResponse<>(response, responseType);
    }


    @Override
    public String toString() {
        return "ApiResponse{" +
                "statusCode=" + response.getStatusCode() +
                ", parsedBody=" + parsedBody +
                '}';
    }
}
