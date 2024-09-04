package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.AcResponse;

public interface AcClickListener {
    void onSuccess(AcResponse response);
    void onError(String error);
}
