package com.example.d2dstore.models;

import java.util.HashMap;
import java.util.Map;

public class LoginResponse {
    private int status;
    private String message;
    private Map<String, String> user;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getUser() {
        return user;
    }

    public void setUser(String name, String value) {
        this.user.put(name, value);
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
