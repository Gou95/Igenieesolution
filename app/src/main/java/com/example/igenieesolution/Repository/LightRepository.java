package com.example.igenieesolution.Repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.GetLightListener;
import com.example.igenieesolution.ClickListener.LightListener;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.GetLightResponse;
import com.example.igenieesolution.Response.LightResponse;
import com.example.igenieesolution.retrofit.Api;
import com.example.igenieesolution.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LightRepository {

    public static LightRepository repository;

    private final MutableLiveData<LightResponse> mutableLiveData = new MutableLiveData<>();

    // Singleton pattern
    public static LightRepository getInstance() {
        if (repository == null) {
            repository = new LightRepository();
        }
        return repository;
    }

    private final Api apiInterface;

    // Constructor
    public LightRepository() {
        apiInterface = RetrofitService.userService(Api.class);
    }


    public MutableLiveData<LightResponse> getLightLiveData(Context context, String id, LightListener listener) {
       Call<LightResponse> call = apiInterface.getLightRes(id);
       call.enqueue(new Callback<LightResponse>() {
           @Override
           public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
               if (response.isSuccessful() && response.body() != null) {
                   listener.onSuccess(response.body());
               }
           }

           @Override
           public void onFailure(Call<LightResponse> call, Throwable t) {
             //  listener.onError("something went to wrong" + t.getMessage());
           }


       });
       return mutableLiveData;
}

//    public MutableLiveData<LightResponse> getLightLiveOneTwo(Context context, String id1,String id2, LightListener listener) {
//        Call<LightResponse> call = apiInterface.getLightOneTwo(id1, id2);
//        call.enqueue(new Callback<LightResponse>() {
//            @Override
//            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    listener.onSuccess(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LightResponse> call, Throwable t) {
//               // listener.onError("something went to wrong" + t.getMessage());
//            }
//
//
//        });
//        return mutableLiveData;
//    }

    public MutableLiveData<LightResponse> getLightLiveDimming(Context context, String id1,String id2,String id3,String id4, LightListener listener) {
        Call<LightResponse> call = apiInterface.getLightDimming(id1, id2,id3,id4);
        call.enqueue(new Callback<LightResponse>() {
            @Override
            public void onResponse(Call<LightResponse> call, Response<LightResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<LightResponse> call, Throwable t) {
              //  listener.onError("something went to wrong" + t.getMessage());
            }


        });
        return mutableLiveData;
    }


}
