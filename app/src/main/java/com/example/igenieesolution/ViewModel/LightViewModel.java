package com.example.igenieesolution.ViewModel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.igenieesolution.ClickListener.AcClickListener;
import com.example.igenieesolution.ClickListener.LightListener;
import com.example.igenieesolution.Repository.ACRepository;
import com.example.igenieesolution.Repository.LightRepository;
import com.example.igenieesolution.Response.AcResponse;
import com.example.igenieesolution.Response.LightResponse;

public class LightViewModel extends ViewModel {

    private Context context;

    private MutableLiveData<String> isFailed = new MutableLiveData<>();

    private MutableLiveData<Boolean> isConnecting = new MutableLiveData<>();

    private MutableLiveData<LightResponse> responseMutableLiveData;


    private LightRepository repository;

    public LiveData<String> getIsFailed(){
        return isFailed;
    }

    public LiveData<Boolean>getIsConnecting(){
        return isConnecting;


    }

    public LiveData<LightResponse>getLiveData(){
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
        repository = LightRepository.getInstance();
    }
    LightListener listener = new LightListener() {
        @Override
        public void onSuccess(LightResponse response) {
            responseMutableLiveData.setValue(response);

        }

        @Override
        public void onError(String error) {
            isFailed.setValue(error);
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
        }

    };


    public void getLightModelData(String id){
        isConnecting.setValue(true);
        repository = LightRepository.getInstance();
        repository.getLightLiveData(context,id,listener);

    }

//    public void getLightDataOneTwo(String id1,String id2){
//        isConnecting.setValue(true);
//        repository = LightRepository.getInstance();
//        repository.getLightLiveOneTwo(context,id1,id2,listener);
//    }

    public void getLightDataDimming(String id1,String id2,String id3,String id4){
        isConnecting.setValue(true);
        repository = LightRepository.getInstance();
        repository.getLightLiveDimming(context,id1,id2,id3,id4,listener);
    }


}
