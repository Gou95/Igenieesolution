package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.AirPurifyListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Repository.AirPurifierRepository;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.AirPurifierResponse;

public class AirPurifierViewModel extends ViewModel {
    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<AirPurifierResponse> responseMutableLiveData;


    private AirPurifierRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<AirPurifierResponse>getLiveData(){
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
        repository = AirPurifierRepository.getInstance();
    }
    AirPurifyListener listener = new AirPurifyListener() {
        @Override
        public void onSuccess(AirPurifierResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getAirPurifierData(String id){
        isConnecting.setValue(true);
        repository = AirPurifierRepository.getInstance();
        repository.getAirPurifierLiveData(context,id,listener);

    }

    public void setAirPurifierData(String id,String id1){
        isConnecting.setValue(true);
        repository = AirPurifierRepository.getInstance();
        repository.setAirPurifier(context,id,id1,listener);

    }

}
