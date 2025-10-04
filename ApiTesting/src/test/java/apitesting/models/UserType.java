package apitesting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserType {
    @JsonProperty("usertype")
    public String usertype;

    public UserType() {}

    public UserType(String usertype) {
        this.usertype = usertype;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "usertype='" + usertype + '\'' +
                '}';
    }
}

