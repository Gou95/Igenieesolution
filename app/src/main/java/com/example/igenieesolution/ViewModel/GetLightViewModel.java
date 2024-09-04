package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.GetLightListener;
import com.example.igenieesolution.ClickListener.LightListener;
import com.example.igenieesolution.Repository.GetLightRepository;
import com.example.igenieesolution.Repository.LightRepository;
import com.example.igenieesolution.Response.GetLightResponse;
import com.example.igenieesolution.Response.LightResponse;

public class GetLightViewModel extends ViewModel {
    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<GetLightResponse> responseMutableLiveData;


    private GetLightRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<GetLightResponse>getLiveData(){
        if (responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }

        return responseMutableLiveData;
    }
    public void init(Context context){
        this.context = context;
        if (responseMutableLiveData == null){
            return;
        }
        repository = GetLightRepository.getInstance();
    }
    GetLightListener listener = new GetLightListener() {
        @Override
        public void onSuccess(GetLightResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getLightResponse(){
        isConnecting.setValue(true);
        repository = GetLightRepository.getInstance();
        repository.getLightLive(context,listener);

    }




}
