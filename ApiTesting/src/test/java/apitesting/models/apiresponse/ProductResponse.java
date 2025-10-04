package apitesting.models.apiresponse;

import apitesting.models.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {
    @JsonProperty("responseCode")
    public Integer responseCode = 0;
    
    @JsonProperty("products")
    public List<Product> products = new ArrayList<>();
    
    @JsonProperty("message")
    public String message = "";

    public ProductResponse(){
    }

    public ProductResponse(Integer responseCode, List<Product> products, String message){
        this.responseCode = responseCode != null ? responseCode : 0;
        this.products = products != null ? products : new ArrayList<>();
        this.message = message != null ? message : "";
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "responseCode=" + responseCode +
                ", products=" + (products != null ? products.size() + " products" : "null") +
                ", message='" + message + '\'' +
                '}';
    }
}
