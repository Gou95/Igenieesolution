package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.WashingResponse;

public interface WashingCilckListener {
    void onSuccess(WashingResponse response);
    void onError(String error);
}
