package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.SetACResponse;

public interface SetClickListener {
    void onSuccess(SetACResponse setACResponse);
    void onError(String error);
}
