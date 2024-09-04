package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.ClickListener.WashingCilckListener;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.WashingResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WashingRepository {

    public static WashingRepository repository;

    private final MutableLiveData<WashingResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static WashingRepository getInstance() {
        if (repository == null) {
            repository = new WashingRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public WashingRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }

    // Method to fetch AC data
    public MutableLiveData<WashingResponse> getWashingLiveData(Context context, String id, WashingCilckListener listener) {
        Call<WashingResponse> call = apiInterface.getWashingResponse(id);
        call.enqueue(new Callback<WashingResponse>() {
            @Override
            public void onResponse(Call<WashingResponse> call, Response<WashingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("WashingRepository", "onResponse: " + response.body());
                    listener.onSuccess(response.body());
                    // Set value to LiveData
                }
            }

            @Override
            public void onFailure(Call<WashingResponse> call, Throwable t) {
                Log.e("WashingRepository", "Failure: " + t.getMessage());
               // listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public MutableLiveData<WashingResponse>getSetWashingLiveData(Context context,String id,String id1,WashingCilckListener listener){
        Call<WashingResponse> call = apiInterface.getSetWashingResponse(id, id1);
        call.enqueue(new Callback<WashingResponse>() {
            @Override
            public void onResponse(Call<WashingResponse> call, Response<WashingResponse> response) {
                if (response.isSuccessful() && response.body() !=null){
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<WashingResponse> call, Throwable t) {
                listener.onError("something went to wrong" + t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
