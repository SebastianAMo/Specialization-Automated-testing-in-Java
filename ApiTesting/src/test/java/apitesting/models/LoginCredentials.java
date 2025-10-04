package apitesting.models;

public class LoginCredentials {
    public String email;
    public String password;
    public boolean isValid;

    public LoginCredentials(String email, String password, boolean isValid) {
        this.email = email;
        this.password = password;
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", isValid=" + isValid +
                '}';
    }
}

