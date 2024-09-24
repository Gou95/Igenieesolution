package com.example.igenieesolution.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.igenieesolution.Database.DataBaseHelper;
import com.example.igenieesolution.R;
import com.example.igenieesolution.Response.RefrigeratorResponse;
import com.example.igenieesolution.Response.SetRefrigeratorResponse;
import com.example.igenieesolution.Session.AppSession;
import com.example.igenieesolution.Session.Constants;
import com.example.igenieesolution.ViewModel.RefrigeratorSetTempViewModel;
import com.example.igenieesolution.ViewModel.RefrigeratorViewModel;
import com.example.igenieesolution.databinding.FragmentACBinding;
import com.example.igenieesolution.databinding.FragmentRefrigeratorBinding;

import java.util.List;


public class RefrigeratorFragment extends Fragment {


    private FragmentRefrigeratorBinding binding;
    private boolean isPowerOn = false;
    private RefrigeratorViewModel refrigeratorViewModel;
    private RefrigeratorSetTempViewModel refrigeratorSetTempViewModel;
    private DataBaseHelper db;
    private String deviceId;
    private String tmpFreezer = ""; // Initialize with example values
    private String tmpFridge = "";
    private String isExpressMode = "";
    private boolean isChecked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRefrigeratorBinding.inflate(inflater, container, false);

        refrigeratorViewModel = new ViewModelProvider(this).get(RefrigeratorViewModel.class);
        refrigeratorSetTempViewModel = new ViewModelProvider(this).get(RefrigeratorSetTempViewModel.class);

        refrigeratorViewModel.init(getContext());
        refrigeratorSetTempViewModel.init(getContext());

        db = new DataBaseHelper(getContext());
        deviceId = db.getDeviceID("DEVICE_REFRIGERATOR");

        if (deviceId != null && !deviceId.isEmpty()) {

            fetchRefrigeratorData();
        } else {
            showToast("Device ID not found");
        }

        setupOnClickListeners();
        binding.cardOnoff.setCardBackgroundColor(Color.RED);
        onAttachObservers();
        return binding.getRoot();
    }



    private void fetchRefrigeratorData() {
        showLoader(true);
        refrigeratorViewModel.getRefrigeratorModelData(deviceId);
//        refrigeratorSetTempViewModel.updateFridgeTemperature(deviceId,tmpFridge);
//        refrigeratorSetTempViewModel.updateFreezerTemperature(deviceId,tmpFreezer);
        refrigeratorSetTempViewModel.updateExpressMode(deviceId,isExpressMode);
        refrigeratorSetTempViewModel.updateExpressMode(deviceId,tmpFridge);
        refrigeratorSetTempViewModel.updateExpressMode(deviceId,tmpFreezer);

    }


    private void showLoader(boolean show) {
        binding.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void setupOnClickListeners() {
      //  binding.cardOnoff.setOnClickListener(view -> togglePowerState());
        binding.cardArrowUp.setOnClickListener(view -> {
            adjustFridgeTemp("tmpFridgeInc");
            handleFridgeTemp();
            animateButton(binding.cardArrowUp);
        });
        binding.cardArrowDown.setOnClickListener(view -> {
            adjustFridgeTemp("tmpFridgeDec");
            handleFridgeTemp();
            animateButton(binding.cardArrowDown);
        });
        binding.cardArrowUp1.setOnClickListener(view -> {
            adjustFreezerTemp("tmpFreezerInc");
            handleFreezerTemp();
            animateButton(binding.cardArrowUp1);
        });
        binding.cardArrowDown1.setOnClickListener(view -> {
            adjustFreezerTemp("tmpFreezerDec");
            handleFreezerTemp();
            animateButton(binding.cardArrowDown1);
        });
    }



    private void onAttachObservers() {
        refrigeratorViewModel.getLiveData().observe(getViewLifecycleOwner(),refrigeratorResponse -> {
            if (refrigeratorResponse != null && refrigeratorResponse.getResponse() !=null){
                handleRefrigeratorResponse(refrigeratorResponse.getResponse());
            }else {
                showToast("API call failed or no data");
            }
            showLoader(false);
        });
        refrigeratorSetTempViewModel.getLiveData().observe(getViewLifecycleOwner(), response -> {
            if (response != null && response.getResponse() != null) {
                handleSetTemperatureResponse(response.getResponse());
            } else {
                showToast("Failed to update temperature");
            }
            showLoader(false);
        });
    }


    private void handleRefrigeratorResponse(RefrigeratorResponse.Response responseData) {
        if (responseData != null && responseData.getTemperature() != null) {
            for (RefrigeratorResponse.Temperature temp : responseData.getTemperature()) {
                if (temp != null) {
                    if ("FRIDGE".equalsIgnoreCase(temp.getLocationName())) {
                        tmpFridge = String.valueOf(temp.getTargetTemperature());
                        binding.txtFridge.setText(String.format("%s°C", tmpFridge));
                        AppSession.getInstance(getContext()).putString(Constants.FRIDGETEMP, tmpFridge);
                    }
                    else if ("FREEZER".equalsIgnoreCase(temp.getLocationName())) {
                        tmpFreezer = String.valueOf(temp.getTargetTemperature());
                        binding.txtFreezer.setText(String.format("%s°C", tmpFreezer));
                        AppSession.getInstance(getContext()).putString(Constants.FREEZERTEMP, tmpFreezer);
                    }
                }
            }
        }
    }


    private void handleSetTemperatureResponse(SetRefrigeratorResponse.Response response) {
        if (response != null && response.getTemperature() != null) {
            for (SetRefrigeratorResponse.Temperature temp : response.getTemperature()) {
                if (temp != null) {
                    if ("FRIDGE".equalsIgnoreCase(temp.getLocationName())) {
                        tmpFridge = temp.getTargetTemperature();
                        binding.txtFridge.setText(String.format("%s°C", tmpFridge));
                    } else if ("FREEZER".equalsIgnoreCase(temp.getLocationName())) {
                        tmpFreezer = temp.getTargetTemperature();
                        binding.txtFreezer.setText(String.format("%s°C", tmpFreezer));
                    }
                }
            }

            // Ensure isExpressMode is properly set based on the response
            Log.d("RefrigeratorFragment", "Express Mode from API: " + response.getRefrigeration().getExpressMode());

            // Ensure isExpressMode is properly set based on the response
            String expressModeStr = String.valueOf(response.getRefrigeration().getExpressMode());
            boolean expressMode = "TRUE".equalsIgnoreCase(expressModeStr);

            // Debug log
            Log.d("RefrigeratorFragment", "Setting card color to: " + (expressMode ? "GREEN" : "RED"));

            binding.cardOnoff.setCardBackgroundColor(expressMode ? Color.GREEN : Color.RED);
            binding.imgDoor.setImageResource(expressMode ? R.drawable.open_ref : R.drawable.fridge);
            binding.txtMode.setText(expressMode ? "ON" : "OFF" );

//          for (SetRefrigeratorResponse.Doorstatus doorstatus : response.getDoorStatus()){
//              if (doorstatus != null){
//                  binding.txtDoorState.setText("Door State : "+doorstatus.getDoorState());
//                  binding.txtDoorMain.setText( doorstatus.getLocationName());
//              }
//          }

//          String autoFilter = response.getRefrigeration().getFreshAirFilter();
//          binding.txtAuto.setText(autoFilter);
//          String mode = response.getRefrigeration().getExpressModeName();
//          binding.txtMode.setText(mode);
        }
    }

private void handleFridgeTemp() {
    if (deviceId == null || deviceId.isEmpty()) {
        showToast("Device ID not found");
        return;
    }
    refrigeratorSetTempViewModel.updateExpressMode(deviceId, tmpFridge);
}

    private void handleFreezerTemp() {
        if (deviceId == null || deviceId.isEmpty()) {
            showToast("Device ID not found");
            return;
        }
        refrigeratorSetTempViewModel.updateExpressMode(deviceId, tmpFreezer);
    }

    private void adjustFridgeTemp(String command) {
        if (deviceId == null || deviceId.isEmpty()) return;

        tmpFridge = command;
      //  binding.txtFridge.setText(String.format( tmpFridge));
        showLoader(true);
        handleFridgeTemp();

    }

    private void adjustFreezerTemp(String command) {
        if (deviceId == null || deviceId.isEmpty()) return;

        tmpFreezer = command;
       // binding.txtFreezer.setText(String.format("%s°C", tmpFreezer));
        showLoader(true);
        handleFreezerTemp();

    }


    private void togglePowerState() {
        if (deviceId == null || deviceId.isEmpty()) {
            showToast("Device ID not found");
            return;
        }

        isPowerOn = !isPowerOn;
        isExpressMode = isPowerOn ? "FALSE" : "TRUE";

        // Debug log
        Log.d("RefrigeratorFragment", "Toggling power state. New express mode: " + isExpressMode);

        binding.cardOnoff.setCardBackgroundColor("TRUE".equalsIgnoreCase(isExpressMode) ? Color.GREEN : Color.RED);
        binding.imgDoor.setImageResource("TRUE".equalsIgnoreCase(isExpressMode) ? R.drawable.open_ref : R.drawable.door);

        refrigeratorSetTempViewModel.updateExpressMode(deviceId, isExpressMode);

        if (isPowerOn) {
            fetchRefrigeratorData();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void animateButton(View view) {
        if (!isChecked) {
            // Start animation for the clicked button
            animateScale(view);

            isChecked = true;

            // Reset the animation state after the duration of the animation
            view.postDelayed(() -> {
                isChecked = false;
            }, 1600); // 800ms for scale up + 800ms for scale down
        }
    }


    private void animateScale(View view) {
        // Scale up animation
        ObjectAnimator scaleXUp = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.5f);
        ObjectAnimator scaleYUp = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.5f);

        scaleXUp.setDuration(800);
        scaleYUp.setDuration(800);

        scaleXUp.start();
        scaleYUp.start();

        // Scale back to original size after 800ms
        scaleXUp.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator scaleXBack = ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f);
                ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f);

                scaleXBack.setDuration(800);
                scaleYBack.setDuration(800);

                scaleXBack.start();
                scaleYBack.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}