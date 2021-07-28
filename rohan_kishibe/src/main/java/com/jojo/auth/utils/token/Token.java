package com.jojo.auth.utils.token;

import java.util.UUID;

public class Token {

    private String token;

    public Token() {
        this.token = UUID.randomUUID().toString().replace("_", "");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
