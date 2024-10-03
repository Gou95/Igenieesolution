package com.example.igenieesolution.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.AirPurifyListener;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.AirPurifierResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirPurifierRepository {

    public static AirPurifierRepository repository;

    private final MutableLiveData<AirPurifierResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static AirPurifierRepository getInstance() {
        if (repository == null) {
            repository = new AirPurifierRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public AirPurifierRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }


    public MutableLiveData<AirPurifierResponse> getAirPurifierLiveData(Context context,String id,  AirPurifyListener listener) {
        Call<AirPurifierResponse> call = apiInterface.getAirPurifierRes(id);
        call.enqueue(new Callback<AirPurifierResponse>() {
            @Override
            public void onResponse(Call<AirPurifierResponse> call, Response<AirPurifierResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());

                }
            }

            @Override
            public void onFailure(Call<AirPurifierResponse> call, Throwable t) {

                // listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<AirPurifierResponse> setAirPurifier(Context context,String id,String id1,  AirPurifyListener listener) {
        Call<AirPurifierResponse> call = apiInterface.setAirPurifierRes(id,id1);
        call.enqueue(new Callback<AirPurifierResponse>() {
            @Override
            public void onResponse(Call<AirPurifierResponse> call, Response<AirPurifierResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());

                }
            }

            @Override
            public void onFailure(Call<AirPurifierResponse> call, Throwable t) {

               // listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

}
