package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.GetLightResponse;

public interface GetLightListener {
    void onSuccess(GetLightResponse getLightResponse);
    void onError(String error);
}
