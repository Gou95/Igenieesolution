package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.SetRefrigeratorResponse;

public interface SetRefrigeratorListener {

    void onSuccess(SetRefrigeratorResponse setRefrigeratorResponse);
    void onError(String error);
}
