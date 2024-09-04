package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.LightResponse;

public interface LightListener {

    void onSuccess(LightResponse lightResponse);

    void onError(String error);
}
