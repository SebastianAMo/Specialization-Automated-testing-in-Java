package apitesting.models.apiresponse;

public class GeneralResponse {

    public Integer responseCode;
    public String message;

    public GeneralResponse() {
    }

    public GeneralResponse(Integer responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                '}';
    }
}
