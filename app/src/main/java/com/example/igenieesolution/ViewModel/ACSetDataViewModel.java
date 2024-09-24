package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.SetClickListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Repository.ACSetDataRepository;
import com.example.igenieesolution.Response.SetACResponse;

public class ACSetDataViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<SetACResponse> responseMutableLiveData;


    private ACSetDataRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<SetACResponse>getLiveData(){
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
        repository = ACSetDataRepository.getInstance();
    }
    SetClickListener listener = new SetClickListener() {
        @Override
        public void onSuccess(SetACResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getAcModelData(String id, String id1){
        isConnecting.setValue(true);
        repository = ACSetDataRepository.getInstance();
        repository.getSetAcLiveData(context,id,id1,listener);

    }




}
