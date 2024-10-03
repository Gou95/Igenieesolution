package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.AirPurifierResponse;

public interface AirPurifyListener {

    void onSuccess(AirPurifierResponse response);
    void onError(String error);
}
