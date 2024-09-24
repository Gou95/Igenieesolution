package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.RefrigeratorClickListener;
import com.example.igenieesolution.ClickListener.SetRefrigeratorListener;
import com.example.igenieesolution.Repository.ACSetDataRepository;
import com.example.igenieesolution.Repository.RefrigeratorRepository;
import com.example.igenieesolution.Repository.SetRefrigeratorRepository;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.SetRefrigeratorResponse;

public class RefrigeratorSetTempViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<String> isFailed = new MutableLiveData<>();
    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();
    private MutableLiveData<SetRefrigeratorResponse> responseMutableLiveData = new MutableLiveData<>();
    private SetRefrigeratorRepository repository;


    public LiveData<String> getIsFailed() {
        return isFailed;
    }

    public LiveData<Boolean> getIsConnecting() {
        return isConnecting;
    }

    public LiveData<SetRefrigeratorResponse> getLiveData() {
        if (responseMutableLiveData == null){
            responseMutableLiveData = new MutableLiveData<>();
        }
        return responseMutableLiveData;
    }

    public void init(Context context) {
        this.context = context;
        if (repository == null) {
            repository = SetRefrigeratorRepository.getInstance();
        }
    }


    private SetRefrigeratorListener listener = new SetRefrigeratorListener() {
        @Override
        public void onSuccess(SetRefrigeratorResponse setRefrigeratorResponse) {
            responseMutableLiveData.setValue(setRefrigeratorResponse);
            isConnecting.setValue(false);
        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            isConnecting.setValue(false);

        }
    };

//    public void updateFreezerTemperature(String id, String id1) {
//        isConnecting.setValue(true);
//        if (repository == null) {
//            repository = SetRefrigeratorRepository.getInstance();
//        }
//       repository.getSetFreezer(context,id,id1,listener);
//    }
//
//    public void updateFridgeTemperature(String id, String id1) {
//        isConnecting.setValue(true);
//        if (repository == null) {
//            repository = SetRefrigeratorRepository.getInstance();
//        }
//        repository.getSetFridge(context,id,id1,listener);
//
//    }


    public void updateExpressMode(String id, String id1) {
        isConnecting.setValue(true);
        repository = SetRefrigeratorRepository.getInstance();
        repository.getSetExpressMode(context, id, id1, listener);
    }
}
