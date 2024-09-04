package com.example.igenieesolution.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LockResponse {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("account_type")
    @Expose
    private String accountType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
