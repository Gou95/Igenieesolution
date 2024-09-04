package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.LockListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Repository.LockRepository;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.LockResponse;

public class LockViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<LockResponse> responseMutableLiveData;


    private LockRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<LockResponse>getLiveData(){
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
        repository = LockRepository.getInstance();
    }
    LockListener listener = new LockListener() {
        @Override
        public void onSuccess(LockResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getLockRes(){
        isConnecting.setValue(true);
        repository = LockRepository.getInstance();

        repository.getLockLiveData(context,listener);

    }
}
