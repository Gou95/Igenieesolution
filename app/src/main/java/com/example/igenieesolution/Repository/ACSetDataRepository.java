package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

public class ACSetDataRepository {

    public static ACSetDataRepository repository;

    private final MutableLiveData<SetACResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static ACSetDataRepository getInstance() {
        if (repository == null) {
            repository = new ACSetDataRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public ACSetDataRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }

    // Method to fetch AC data
    public MutableLiveData<SetACResponse> getSetAcLiveData(Context context, String id,String id1, SetClickListener listener) {
       Call<SetACResponse> call = apiInterface.getSetResponse(id, id1);
       call.enqueue(new Callback<SetACResponse>() {
           @Override
           public void onResponse(Call<SetACResponse> call, Response<SetACResponse> response) {
               if (response.isSuccessful() && response.body() != null) {
                   listener.onSuccess(response.body());
               }
           }

           @Override
           public void onFailure(Call<SetACResponse> call, Throwable t) {

             //  listener.onError("Something went wrong: " + t.getMessage());
           }
       });
        return mutableLiveData;
    }


    public MutableLiveData<SetACResponse> getUpdateTemperature(Context context,String id,String id1,SetClickListener listener){
        Call<SetACResponse> call = apiInterface.getUpdateTempResponse(id, id1);
        call.enqueue(new Callback<SetACResponse>() {
            @Override
            public void onResponse(Call<SetACResponse> call, Response<SetACResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<SetACResponse> call, Throwable t) {
              //  listener.onError("Something went wrong: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }



}
