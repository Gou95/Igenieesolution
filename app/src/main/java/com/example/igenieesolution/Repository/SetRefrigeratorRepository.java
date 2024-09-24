package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.ClickListener.SetRefrigeratorListener;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.SetRefrigeratorResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetRefrigeratorRepository {

    public static SetRefrigeratorRepository repository;

    private final MutableLiveData<SetRefrigeratorResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static SetRefrigeratorRepository getInstance() {
        if (repository == null) {
            repository = new SetRefrigeratorRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public SetRefrigeratorRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }

    // Method to fetch AC data
//    public MutableLiveData<SetRefrigeratorResponse> getSetFridge(Context context, String id,String id1, SetRefrigeratorListener listener) {
//        Call<SetRefrigeratorResponse> call = apiInterface.updateFridgeTemperature(id,id1);
//        call.enqueue(new Callback<SetRefrigeratorResponse>() {
//            @Override
//            public void onResponse(Call<SetRefrigeratorResponse> call, Response<SetRefrigeratorResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("ACRepository", "onResponse: " + response.body());
//                    listener.onSuccess(response.body());
//
//                }else {
//                    Toast.makeText(context, "update fridge temperature not increase", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SetRefrigeratorResponse> call, Throwable t) {
//                listener.onError("Something went wrong: " + t.getMessage());
//            }
//        });
//
//        return mutableLiveData;
//    }
//
//    public MutableLiveData<SetRefrigeratorResponse> getSetFreezer(Context context, String id,String id1, SetRefrigeratorListener listener) {
//        Call<SetRefrigeratorResponse> call = apiInterface.updateFreezerTemperature(id,id1);
//        call.enqueue(new Callback<SetRefrigeratorResponse>() {
//            @Override
//            public void onResponse(Call<SetRefrigeratorResponse> call, Response<SetRefrigeratorResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listener.onSuccess(response.body());
//
//                }else {
//                    Toast.makeText(context, "update freezer temperature not increase", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<SetRefrigeratorResponse> call, Throwable t) {
//            //    listener.onError("Something went wrong: " + t.getMessage());
//            }
//        });
//
//        return mutableLiveData;
//    }



    public MutableLiveData<SetRefrigeratorResponse> getSetExpressMode(Context context, String id,String id1, SetRefrigeratorListener listener) {
        Call<SetRefrigeratorResponse> call = apiInterface.getExpressMode(id,id1);
        call.enqueue(new Callback<SetRefrigeratorResponse>() {
            @Override
            public void onResponse(Call<SetRefrigeratorResponse> call, Response<SetRefrigeratorResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());

                }
            }

            @Override
            public void onFailure(Call<SetRefrigeratorResponse> call, Throwable t) {
                listener.onError("Something went wrong: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}
