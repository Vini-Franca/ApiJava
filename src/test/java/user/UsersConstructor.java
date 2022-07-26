package user;

import io.cucumber.messages.internal.com.google.gson.Gson;
import org.json.JSONObject;

import java.util.Objects;

public class UsersConstructor {

    private String email;
    private String gender;
    private String name;
    private String status;

    public UsersConstructor(String email, String gender, String name, String status) {
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.status = status;
    }

    public JSONObject getJson() {
        return new JSONObject(new Gson().toJson(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersConstructor that = (UsersConstructor) o;
        return Objects.equals(email, that.email)
                && Objects.equals(gender, that.gender)
                && Objects.equals(name, that.name)
                && Objects.equals(status, that.status);
    }

    @Override
    public String toString() {
        return "UsersConstructor{" +
                "email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
