package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;


import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.SetClickListener;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.SetACResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ACRepository {

    public static ACRepository repository;

    private final MutableLiveData<AcResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static ACRepository getInstance() {
        if (repository == null) {
            repository = new ACRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public ACRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }


    public MutableLiveData<AcResponse> getAcLiveData(Context context,String id, AcClickListener listener) {
        Call<AcResponse> call = apiInterface.getACResponse(id);
        call.enqueue(new Callback<AcResponse>() {
            @Override
            public void onResponse(Call<AcResponse> call, Response<AcResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    listener.onSuccess(response.body());
                    // Set value to LiveData
                }
            }

            @Override
            public void onFailure(Call<AcResponse> call, Throwable t) {

               // listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }




}
