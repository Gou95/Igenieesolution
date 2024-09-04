package com.example.igenieesolution.ClickListener;

import com.example.igenieesolution.Response.RefrigeratorResponse;

public interface RefrigeratorClickListener {

    void onSuccess(RefrigeratorResponse refrigeratorResponse);
    void onError(String error);

}
