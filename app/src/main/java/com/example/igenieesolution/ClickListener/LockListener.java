package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.LockResponse;

public interface LockListener {
    void onSuccess(LockResponse response);
    void onError (String error);
}
