package com.example.igenieesolution.Repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.LightListener;
import com.example.igenieesolution.ClickListener.LockListener;
import com.example.igenieesolution.Response.LightResponse;
import com.example.igenieesolution.Response.LockResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LockRepository {
    public static LockRepository repository;

    private final MutableLiveData<LockResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static LockRepository getInstance() {
        if (repository == null) {
            repository = new LockRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public LockRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }


    public MutableLiveData<LockResponse> getLockLiveData(Context context, LockListener listener) {
        Call<LockResponse> call = apiInterface.getLockResponse();
        call.enqueue(new Callback<LockResponse>() {
            @Override
            public void onResponse(Call<LockResponse> call, Response<LockResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LockResponse> call, Throwable t) {
              //  listener.onError("something went to wrong" + t.getMessage());
            }


        });
        return mutableLiveData;
    }


}
