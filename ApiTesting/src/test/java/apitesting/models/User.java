package apitesting.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("name")
    public String firstName;

    @JsonProperty("lastname")
    public String lastName;

    public String email;
    public String password;
    @JsonProperty("address1")
    public String address;
    public String country;
    public String state;
    public String city;

    @JsonProperty("zipcode")
    public String zipCode;

    @JsonProperty("mobile_number")
    public String mobileNumber;

    public User(String firstName, String lastName, String email, String password,
                String address, String country, String state, String city,
                String zipCode, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
