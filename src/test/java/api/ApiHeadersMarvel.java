package api;

import java.util.HashMap;
import java.util.Map;

public class ApiHeadersMarvel {

    Map<String, String> headers = new HashMap<>();

    public Map<String, String> marvelHeaders(String token) {
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", token);
        return headers;
    }
}
