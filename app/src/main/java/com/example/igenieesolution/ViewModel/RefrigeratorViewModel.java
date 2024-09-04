package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Repository.RefrigeratorRepository;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.RefrigeratorResponse;

public class RefrigeratorViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<RefrigeratorResponse> responseMutableLiveData;


    private RefrigeratorRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<RefrigeratorResponse>getLiveData(){
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
        repository = RefrigeratorRepository.getInstance();
    }
    RefrigeratorClickListener listener = new RefrigeratorClickListener() {
        @Override
        public void onSuccess(RefrigeratorResponse refrigeratorResponse) {
            responseMutableLiveData.setValue(refrigeratorResponse);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getRefrigeratorModelData(String id){
        isConnecting.setValue(true);
        repository = RefrigeratorRepository.getInstance();
        repository.getRefrigeratorLiveData(context,id,listener);

    }

}
