package com.adrianiglesia.atqr.model.response;

public class LoginBody {

    private Long document;
    private String password;

    public LoginBody(Long document, String password) {
        this.document = document;
        this.password = password;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
