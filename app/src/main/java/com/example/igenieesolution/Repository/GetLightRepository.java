package com.example.igenieesolution.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.GetLightListener;
import com.example.igenieesolution.ClickListener.LightListener;
import com.example.igenieesolution.Response.GetLightResponse;
import com.example.igenieesolution.Response.LightResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetLightRepository {
    public static GetLightRepository repository;

    private final MutableLiveData<GetLightResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static GetLightRepository getInstance() {
        if (repository == null) {
            repository = new GetLightRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public GetLightRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }

    public MutableLiveData<GetLightResponse> getLightLive(Context context, GetLightListener listener) {
        Call<GetLightResponse> call = apiInterface.getLiveResponse();
        call.enqueue(new Callback<GetLightResponse>() {
            @Override
            public void onResponse(Call<GetLightResponse> call, Response<GetLightResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetLightResponse> call, Throwable t) {
              //  listener.onError("something went to wrong" + t.getMessage());
            }


        });
        return mutableLiveData;
    }
}
