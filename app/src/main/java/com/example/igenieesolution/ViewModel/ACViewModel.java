package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.Body.AcBody;
import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Response.AcResponse;

public class ACViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<AcResponse> responseMutableLiveData;


    private ACRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<AcResponse>getLiveData(){
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
        repository = ACRepository.getInstance();
    }
    AcClickListener listener = new AcClickListener() {
        @Override
        public void onSuccess(AcResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getAcModelData(String id){
        isConnecting.setValue(true);
        repository = ACRepository.getInstance();

        repository.getAcLiveData(context,id,listener);

    }
//    public void setPowerState(String id,String id1, String powerState) {
//        repository = ACRepository.getInstance();
//        repository.setPowerState(id, id1,powerState, listener);
//    }

}
