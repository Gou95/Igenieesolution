package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.ClickListener.SetClickListener;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.SetACResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RefrigeratorRepository {

    public static RefrigeratorRepository repository;

    private final MutableLiveData<RefrigeratorResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static RefrigeratorRepository getInstance() {
        if (repository == null) {
            repository = new RefrigeratorRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public RefrigeratorRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }

    // Method to fetch AC data
    public MutableLiveData<RefrigeratorResponse> getRefrigeratorLiveData(Context context, String id, RefrigeratorClickListener listener) {
        Call<RefrigeratorResponse> call = apiInterface.getRefrigeratorResponse(id);
        call.enqueue(new Callback<RefrigeratorResponse>() {
            @Override
            public void onResponse(Call<RefrigeratorResponse> call, Response<RefrigeratorResponse> responseResponse) {
                if (responseResponse.isSuccessful() && responseResponse.body() != null) {
                    Log.d("ACRepository", "onResponse: " + responseResponse.body());
                    listener.onSuccess(responseResponse.body());
                    // Set value to LiveData
                }
            }

            @Override
            public void onFailure(Call<RefrigeratorResponse> call, Throwable t) {
                Log.e("ACRepository", "Failure: " + t.getMessage());
             //   listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

//    public MutableLiveData<RefrigeratorResponse> getUpdateFridgeTemp(Context context, String id, int id1, RefrigeratorClickListener listener){
//        Call<RefrigeratorResponse> call = apiInterface.updateFridgeTemperature(id, id1);
//        call.enqueue(new Callback<RefrigeratorResponse>() {
//            @Override
//            public void onResponse(Call<RefrigeratorResponse> call, Response<RefrigeratorResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listener.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RefrigeratorResponse> call, Throwable t) {
//                listener.onError("Something went wrong: " + t.getMessage());
//            }
//        });
//        return mutableLiveData;
//    }
//
//    public MutableLiveData<RefrigeratorResponse> getUpdateFreezerTemp(Context context, String id, int id1, RefrigeratorClickListener listener){
//        Call<RefrigeratorResponse> call = apiInterface.updateFridgeTemperature(id, id1);
//        call.enqueue(new Callback<RefrigeratorResponse>() {
//            @Override
//            public void onResponse(Call<RefrigeratorResponse> call, Response<RefrigeratorResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listener.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RefrigeratorResponse> call, Throwable t) {
//                listener.onError("Something went wrong: " + t.getMessage());
//            }
//        });
//        return mutableLiveData;
//    }
}
