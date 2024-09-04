package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.ClickListener.WashingCilckListener;
import com.example.igenieesolution.Repository.RefrigeratorRepository;
import com.example.igenieesolution.Repository.WashingRepository;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.WashingResponse;

public class WashingViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<WashingResponse> responseMutableLiveData;


    private WashingRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<WashingResponse>getLiveData(){
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
        repository = WashingRepository.getInstance();
    }
    WashingCilckListener listener = new WashingCilckListener() {
        @Override
        public void onSuccess(WashingResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };

    public void getWashingData(String id){
        isConnecting.setValue(true);
        repository = WashingRepository.getInstance();
        repository.getWashingLiveData(context,id,listener);

    }

    public void getSetWashingData(String id,String id1){
        isConnecting.setValue(true);
        repository = WashingRepository.getInstance();
        repository.getSetWashingLiveData(context,id,id1,listener);
    }
}
