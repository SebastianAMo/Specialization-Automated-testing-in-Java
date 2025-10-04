package apitesting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    @JsonProperty("usertype")
    public UserType usertype;
    
    @JsonProperty("category")
    public String category;

    public Category() {}

    public Category(UserType usertype, String category) {
        this.usertype = usertype;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Category{" +
                "usertype=" + usertype +
                ", category='" + category + '\'' +
                '}';
    }
}

